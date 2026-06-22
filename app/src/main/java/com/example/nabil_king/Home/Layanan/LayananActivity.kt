package com.example.nabil_king.Home.Layanan

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.nabil_king.databinding.ActivityLayananBinding
import com.google.android.material.tabs.TabLayoutMediator

class LayananActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLayananBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) // PENTING: Wajib ada agar tidak crash

        // Inisialisasi View Binding
        binding = ActivityLayananBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        // Mengatur padding otomatis untuk notch / status bar / navigation bar HP
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Setup Toolbar (Biar ada tombol back ke Home)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // 1. Inisialisasi Adapter (Menggunakan kelas TabsAdapter)
        val tabsAdapter = TabsAdapter(this)

        // 2. Set adapter ke ViewPager2 melalui binding
        binding.viewPager.adapter = tabsAdapter

        // 3. Hubungkan TabLayout & ViewPager2 menggunakan TabLayoutMediator
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            // Mengatur judul tab berdasarkan posisinya
            when (position) {
                0 -> tab.text = "Warga"
                1 -> tab.text = "Persil"
                2 -> tab.text = "Sengketa"
                3 -> tab.text = "Peta"
            }
        }.attach()
    }

    // Aksi ketika tombol back di toolbar atas diklik
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}