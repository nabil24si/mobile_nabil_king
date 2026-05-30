package com.example.nabil_king.Home.tutorial

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.nabil_king.LoginActivity
import com.example.nabil_king.databinding.ActivityTutorialBinding

class TutorialActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTutorialBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Mengaktifkan tampilan full screen / edge-to-edge

        binding = ActivityTutorialBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mengatur padding otomatis agar tidak tertutup Status Bar / Notch
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 1. Siapkan list fragment yang akan ditampilkan
        val fragmentsList = listOf(Tutorial1Fragment(), Tutorial2Fragment(), Tutorial3Fragment())
        // 2. Inisialisasi adapter dengan membawa list fragment
        val adapter = TutorialFragmentAdapter(this, fragmentsList)
        // 3. Pasangkan adapter ke ViewPager2 terlebih dahulu
        binding.tutorialViewPager.adapter = adapter
        // 4. Hubungkan Dot Indicator setelah adapter terpasang (Cukup panggil 1 kali)
        binding.dotIndicator.attachTo(binding.tutorialViewPager)

    }
}