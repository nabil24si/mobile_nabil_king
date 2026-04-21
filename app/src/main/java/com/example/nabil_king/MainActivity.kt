package com.example.nabil_king

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.nabil_king.R
import com.example.nabil_king.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val TAG = "MainBangunLifecycle"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: Activity Bangun Datar/Ruang Dibuat")

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. SETUP TOOLBAR
        // Pastikan di activity_main.xml kamu sudah menambahkan Toolbar dengan ID toolbarMain
        setSupportActionBar(binding.toolbarMain)

        // 2. AKTIFKAN TOMBOL BACK DI TOOLBAR
        supportActionBar?.apply {
            title = "Hitung Bangun Ruang"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        // --- Logika Hitung Luas Persegi Panjang ---
        binding.btnHitungLuas.setOnClickListener {
            val panjang = binding.etPanjang.text.toString().toDoubleOrNull() ?: 0.0
            val lebar = binding.etLebar.text.toString().toDoubleOrNull() ?: 0.0
            val hasil = panjang * lebar
            binding.tvHasilLuas.text = "Hasil Luas: $hasil"
        }

        // --- Logika Hitung Volume Kubus ---
        binding.btnHitungVolume.setOnClickListener {
            val sisi = binding.etSisi.text.toString().toDoubleOrNull() ?: 0.0
            val hasil = sisi * sisi * sisi
            binding.tvHasilVolume.text = "Hasil Volume: $hasil"
        }

        // --- Tombol Kembali ke Dashboard (Button di bawah) ---
        binding.btnBackToDashboard.setOnClickListener {
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
                val shareIntent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    val pesan = "Saya baru saja menghitung bangun ruang di KindStep!"
                    putExtra(Intent.EXTRA_TEXT, pesan)
                }
                startActivity(Intent.createChooser(shareIntent, "Bagikan Hasil"))
                true
            }
            R.id.sub_refresh -> {
                // Reset semua inputan
                binding.etPanjang.text?.clear()
                binding.etLebar.text?.clear()
                binding.etSisi.text?.clear()
                binding.tvHasilLuas.text = "Hasil Luas: 0"
                binding.tvHasilVolume.text = "Hasil Volume: 0"
                Toast.makeText(this, "Inputan direset", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // Lifecycle tetap dipertahankan
    override fun onStart() { super.onStart(); Log.d(TAG, "onStart") }
    override fun onResume() { super.onResume(); Log.d(TAG, "onResume") }
    override fun onPause() { super.onPause(); Log.d(TAG, "onPause") }
    override fun onStop() { super.onStop(); Log.d(TAG, "onStop") }
    override fun onDestroy() { super.onDestroy(); Log.d(TAG, "onDestroy") }
}