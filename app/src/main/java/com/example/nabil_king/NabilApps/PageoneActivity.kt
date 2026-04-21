package com.example.nabil_king.NabilApps

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.nabil_king.R
import com.example.nabil_king.databinding.ActivityPageoneBinding

class PageoneActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPageoneBinding
    private val TAG = "PageoneLifecycle"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: Halaman Informasi Dibuat")

        binding = ActivityPageoneBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. SETUP TOOLBAR
        // Pastikan ID di XML adalah android:id="@+id/toolbarInfo"
        setSupportActionBar(binding.toolbarInfo)

        // 2. AKTIFKAN TOMBOL BACK DI TOOLBAR
        supportActionBar?.apply {
            title = "Informasi Detail"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        // Tombol Kembali di bawah (Button XML)
        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_latihan, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            // Perbaikan Logika Share (Karena tidak ada WebView, kita share teks manual)
            R.id.action_share -> {
                val shareIntent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, "Cek aplikasi KindStep: Solusi transisi dunia sekolah anak!")
                }
                startActivity(Intent.createChooser(shareIntent, "Bagikan via"))
                true
            }
            // Perbaikan: Karena tidak ada WebView, fitur refresh bisa diganti Toast atau dihapus
            R.id.sub_refresh -> {
                Toast.makeText(this, "Halaman diperbarui", Toast.LENGTH_SHORT).show()
                true
            }
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // Perbaikan onBackPressed: Hapus logika WebView karena di XML tidak ada WebView
    @Suppress("DEPRECATION")
    override fun onBackPressed() {
        super.onBackPressed()
    }

    override fun onStart() { super.onStart(); Log.d(TAG, "onStart") }
    override fun onResume() { super.onResume(); Log.d(TAG, "onResume") }
    override fun onPause() { super.onPause(); Log.d(TAG, "onPause") }
    override fun onStop() { super.onStop(); Log.d(TAG, "onStop") }
    override fun onDestroy() { super.onDestroy(); Log.d(TAG, "onDestroy") }
}