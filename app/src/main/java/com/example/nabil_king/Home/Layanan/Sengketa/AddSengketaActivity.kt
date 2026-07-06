package com.example.nabil_king.Home.Layanan.Sengketa

import android.Manifest
import android.app.Activity
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.example.nabil_king.data.AppDatabase
import com.example.nabil_king.data.entity.SengketaEntity
import com.example.nabil_king.databinding.ActivityAddSengketaBinding
import com.example.nabil_king.util.NotificationHelper
import com.example.nabil_king.util.ReminderReceiver
import com.example.nabil_king.util.ScannerActivity
import kotlinx.coroutines.launch

class AddSengketaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddSengketaBinding
    private lateinit var db: AppDatabase
    private lateinit var notificationHelper: NotificationHelper
    private var sengketaId: Int = 0
    private var isEditMode: Boolean = false
    private var currentSengketa: SengketaEntity? = null

    private val scanLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val scanData = result.data?.getStringExtra("SCAN_RESULT")
            binding.etKodePersil.setText(scanData)
        }
    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            // Permission granted
        } else {
            Toast.makeText(this, "Izin notifikasi ditolak. Anda tidak akan menerima pengingat.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddSengketaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkNotificationPermission()

        db = AppDatabase.getInstance(this)
        notificationHelper = NotificationHelper(this)

        sengketaId = intent.getIntExtra("SENGKETA_ID", 0)
        isEditMode = sengketaId != 0

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener { finish() }

        if (isEditMode) {
            supportActionBar?.title = "Edit Sengketa"
            binding.btnSave.text = "Update Data"
            loadExistingData()
        }

        binding.btnScan.setOnClickListener {
            val intent = Intent(this, ScannerActivity::class.java)
            scanLauncher.launch(intent)
        }

        binding.btnSave.setOnClickListener {
            saveData()
        }
    }

    private fun checkNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) !=
                PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }

    private fun scheduleReminder(title: String, kode: String) {
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, ReminderReceiver::class.java).apply {
            putExtra("TITLE", "Pengingat Tindak Lanjut Sengketa")
            putExtra("MESSAGE", "Sengketa '$title' pada persil $kode perlu segera diproses lebih lanjut.")
        }
        
        val pendingIntent = PendingIntent.getBroadcast(
            this,
            System.currentTimeMillis().toInt(),
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        // Set reminder 10 detik dari sekarang (untuk keperluan demo)
        val triggerTime = System.currentTimeMillis() + 10000 
        
        alarmManager.set(
            AlarmManager.RTC_WAKEUP,
            triggerTime,
            pendingIntent
        )
    }

    private fun loadExistingData() {
        lifecycleScope.launch {
            val sengketa = db.sengketaDao().getAll().find { it.id == sengketaId }
            if (sengketa != null) {
                currentSengketa = sengketa
                binding.apply {
                    etTitle.setText(sengketa.title)
                    etPihak1.setText(sengketa.pihak1)
                    etPihak2.setText(sengketa.pihak2)
                    etKodePersil.setText(sengketa.kodePersil)
                    etDescription.setText(sengketa.description)
                }
            }
        }
    }

    private fun saveData() {
        val title = binding.etTitle.text.toString()
        val pihak1 = binding.etPihak1.text.toString()
        val pihak2 = binding.etPihak2.text.toString()
        val kode = binding.etKodePersil.text.toString()
        val desc = binding.etDescription.text.toString()

        if (title.isNotBlank() && pihak1.isNotBlank() && pihak2.isNotBlank() && kode.isNotBlank()) {
            lifecycleScope.launch {
                if (isEditMode && currentSengketa != null) {
                    val updatedSengketa = currentSengketa!!.copy(
                        title = title,
                        pihak1 = pihak1,
                        pihak2 = pihak2,
                        kodePersil = kode,
                        description = desc
                    )
                    db.sengketaDao().update(updatedSengketa)
                    
                    notificationHelper.sendNotification(
                        "Data Diperbarui",
                        "Sengketa '$title' telah berhasil diperbarui."
                    )
                } else {
                    val sengketa = SengketaEntity(
                        title = title,
                        pihak1 = pihak1,
                        pihak2 = pihak2,
                        kodePersil = kode,
                        description = desc,
                        status = "Dalam Proses",
                        createdAt = System.currentTimeMillis()
                    )
                    db.sengketaDao().insert(sengketa)
                    
                    Log.d("AddSengketa", "Sending notification...")
                    notificationHelper.sendNotification(
                        "Sengketa Baru Ditambahkan",
                        "Data sengketa untuk persil $kode telah berhasil disimpan."
                    )
                    
                    scheduleReminder(title, kode)
                }
                finish()
            }
        } else {
            Toast.makeText(this, "Mohon lengkapi data yang berbintang!", Toast.LENGTH_SHORT).show()
        }
    }
}