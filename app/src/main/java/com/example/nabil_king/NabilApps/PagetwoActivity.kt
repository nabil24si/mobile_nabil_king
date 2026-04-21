package com.example.nabil_king.NabilApps

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.nabil_king.R
import com.example.nabil_king.databinding.ActivityPagetwoBinding

class PagetwoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPagetwoBinding
    private val TAG = "PagetwoLifecycle"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: Memuat Profil Pengembang")

        binding = ActivityPagetwoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. SETUP TOOLBAR
        setSupportActionBar(binding.toolbarDev)

        // 2. AKTIFKAN TOMBOL BACK DI TOOLBAR
        supportActionBar?.apply {
            title = "Profil Pengembang"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        // Klik tombol kembali di bagian bawah
        binding.btnBackFromDev.setOnClickListener {
            finish()
        }
    }

    // 3. MEMASANG MENU (Option Menu)
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_latihan, menu)
        return true
    }

    // 4. LOGIKA KLIK MENU & TOMBOL BACK TOOLBAR
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish() // Tombol Back di Toolbar
                true
            }
            R.id.action_share -> {
                // Improvisasi: Share Profil Pengembang
                val shareIntent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    val profilInfo = "Kenalan yuk sama Pengembang KindStep! Aplikasi ini dibuat dengan dedikasi untuk edukasi anak."
                    putExtra(Intent.EXTRA_TEXT, profilInfo)
                }
                startActivity(Intent.createChooser(shareIntent, "Bagikan Profil"))
                true
            }
            R.id.sub_refresh -> {
                Toast.makeText(this, "Profil telah diperbarui", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // --- Lifecycle Monitoring Tetap Dipertahankan ---
    override fun onStart() { super.onStart(); Log.d(TAG, "onStart") }
    override fun onResume() { super.onResume(); Log.d(TAG, "onResume") }
    override fun onPause() { super.onPause(); Log.d(TAG, "onPause") }
    override fun onStop() { super.onStop(); Log.d(TAG, "onStop") }
    override fun onDestroy() { super.onDestroy(); Log.d(TAG, "onDestroy") }
}