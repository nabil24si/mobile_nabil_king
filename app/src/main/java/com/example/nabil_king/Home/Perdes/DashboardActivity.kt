package com.example.nabil_king.Home.Perdes

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.nabil_king.LoginActivity
import com.example.nabil_king.MainActivity
import com.example.nabil_king.databinding.ActivityDashboardBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import androidx.core.content.edit

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding
    private val TAG = "DashboardLifecycle"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: Dashboard Dibuat")

        binding = ActivityDashboardBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.mainDashboard) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)

        // --- Navigasi Ke Halaman Lain ---
        binding.btnRuang.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        binding.btnBina.setOnClickListener {
            startActivity(Intent(this, PageThreeActivity::class.java))
        }

        binding.btnInfo.setOnClickListener {
            startActivity(Intent(this, PageoneActivity::class.java))
        }

        binding.btnDeveloper.setOnClickListener {
            startActivity(Intent(this, PagetwoActivity::class.java))
        }

        // --- Logika Logout dengan AlertDialog & Snackbar ---
        binding.btnLogout.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("Konfirmasi")
                .setMessage("Apakah Anda yakin ingin melanjutkan?")
                .setPositiveButton("Ya") { dialog, _ ->
                    sharedPref.edit {
                        clear()
                    }

                    dialog.dismiss()
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                }
                .setNegativeButton("Batal") { dialog, _ ->
                    dialog.dismiss()
                    Log.e("Info Dialog","Anda memilih Tidak!")
                }
                .show()

        }

        // --- Ambil Data Dari Intent ---
        val namaDariLogin = intent.getStringExtra("username")
        binding.tvWelcome.text = if (namaDariLogin != null) "Welcome, $namaDariLogin" else "Welcome To Dashboard"
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: Dashboard Mulai Terlihat")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: Dashboard Dihancurkan")
    }
}