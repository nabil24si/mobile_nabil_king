package com.example.nabil_king

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.nabil_king.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // 1. Deklarasi View Binding
    private lateinit var binding: ActivityMainBinding
    private val TAG = "MainBangunLifecycle"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: Activity Bangun Datar/Ruang Dibuat")

        // 2. Inisialisasi View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

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

        // --- Tombol Kembali ke Dashboard ---
        binding.btnBackToDashboard.setOnClickListener {
            finish() // Menutup activity ini dan kembali ke sebelumnya
        }
    }

    // --- Implementasi Lifecycle Activity ---
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: Activity Mulai Terlihat")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: Activity Siap Berinteraksi")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: Activity Dijeda")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: Activity Berhenti")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: Activity Dihancurkan")
    }
}