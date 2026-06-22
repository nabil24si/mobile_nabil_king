package com.example.nabil_king.Home.Layanan.Sengketa

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.nabil_king.data.AppDatabase
import com.example.nabil_king.data.entity.SengketaEntity
import com.example.nabil_king.databinding.ActivityAddSengketaBinding
import com.example.nabil_king.util.NotificationHelper
import com.example.nabil_king.util.ScannerActivity
import kotlinx.coroutines.launch

class AddSengketaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddSengketaBinding
    private lateinit var db: AppDatabase
    private lateinit var notificationHelper: NotificationHelper

    private val scanLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val scanData = result.data?.getStringExtra("SCAN_RESULT")
            binding.etKodePersil.setText(scanData)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddSengketaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.getInstance(this)
        notificationHelper = NotificationHelper(this)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener { finish() }

        binding.btnScan.setOnClickListener {
            val intent = Intent(this, ScannerActivity::class.java)
            scanLauncher.launch(intent)
        }

        binding.btnSave.setOnClickListener {
            saveData()
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
                
                notificationHelper.sendSengketaNotification(
                    "Sengketa Baru Ditambahkan",
                    "Data sengketa untuk persil $kode telah berhasil disimpan."
                )
                
                Toast.makeText(this@AddSengketaActivity, "Data berhasil disimpan!", Toast.LENGTH_SHORT).show()
                finish()
            }
        } else {
            Toast.makeText(this, "Mohon lengkapi data yang berbintang!", Toast.LENGTH_SHORT).show()
        }
    }
}