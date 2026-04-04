package com.example.nabil_king // Sesuaikan dengan package name anda

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inisialisasi View Bangun Datar
        val etPanjang = findViewById<EditText>(R.id.etPanjang)
        val etLebar = findViewById<EditText>(R.id.etLebar)
        val btnHitungLuas = findViewById<Button>(R.id.btnHitungLuas)
        val tvHasilLuas = findViewById<TextView>(R.id.tvHasilLuas)

        // Inisialisasi View Bangun Ruang
        val etSisi = findViewById<EditText>(R.id.etSisi)
        val btnHitungVolume = findViewById<Button>(R.id.btnHitungVolume)
        val tvHasilVolume = findViewById<TextView>(R.id.tvHasilVolume)

        // Logika Hitung Luas Persegi Panjang (P x L)
        btnHitungLuas.setOnClickListener {
            val panjang = etPanjang.text.toString().toDoubleOrNull() ?: 0.0
            val lebar = etLebar.text.toString().toDoubleOrNull() ?: 0.0
            val hasil = panjang * lebar
            tvHasilLuas.text = "Hasil Luas: $hasil"
        }

        // Logika Hitung Volume Kubus (S x S x S)
        btnHitungVolume.setOnClickListener {
            val sisi = etSisi.text.toString().toDoubleOrNull() ?: 0.0
            val hasil = sisi * sisi * sisi
            tvHasilVolume.text = "Hasil Volume: $hasil"
        }
    }
}