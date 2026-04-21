package com.example.nabil_king.NabilApps

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.nabil_king.R
import com.example.nabil_king.databinding.ActivityPageThreeBinding

class PageThreeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPageThreeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPageThreeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Setup Toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Bina Desa"
            setDisplayHomeAsUpEnabled(true)
        }

        // 2. Setup WebView (Bahasan WebView)
        binding.webView.apply {
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
            loadUrl("https://pertanahan-admin.alwaysdata.net/")
        }
    }

    // 3. Memasang Menu Improvisasi (Action Share & Sub-menu)
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Kita pakai menu_latihan yang sudah dibuat tadi
        menuInflater.inflate(R.menu.menu_latihan, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            // Improvisasi: Tombol Share mengambil URL aktif dari WebView
            R.id.action_share -> {
                val shareIntent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, "Baca berita ini: ${binding.webView.url}")
                }
                startActivity(Intent.createChooser(shareIntent, "Bagikan via"))
                true
            }
            R.id.sub_refresh -> {
                binding.webView.reload()
                Toast.makeText(this, "Halaman dimuat ulang", Toast.LENGTH_SHORT).show()
                true
            }
            android.R.id.home -> { // Tombol back di toolbar
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // Logika tombol back fisik agar web tidak langsung tertutup
    @Suppress("DEPRECATION")
    override fun onBackPressed() {
        if (binding.webView.canGoBack()) {
            binding.webView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}