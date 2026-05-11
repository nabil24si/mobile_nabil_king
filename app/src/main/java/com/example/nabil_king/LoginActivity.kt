package com.example.nabil_king

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.nabil_king.databinding.ActivityLoginBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val TAG = "LoginLifecycle"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: Activity Dibuat")

        binding = ActivityLoginBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // SharedPref untuk menyimpan sesi login
        val sharedPrefLogin = getSharedPreferences("user_pref", Context.MODE_PRIVATE)
        // SharedPref untuk mengambil data hasil Register
        val sharedPrefReg = getSharedPreferences("UserReg", Context.MODE_PRIVATE)

        // Tombol menuju halaman Register
        binding.tvRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        // Logika saat tombol Login ditekan
        binding.btnLogin.setOnClickListener {
            val inputUsername = binding.username.text.toString()
            val inputPassword = binding.password.text.toString()

            // Mengambil data dari SharedPreferences "UserReg"
            val registeredUser = sharedPrefReg.getString("username", "")
            val registeredPass = sharedPrefReg.getString("password", "")

            // Kondisi 1: username == password (Tidak boleh kosong)
            val kondisiSatu = (inputUsername == inputPassword) && inputUsername.isNotEmpty()

            // Kondisi 2: username dan password sesuai dengan SP Register
            val kondisiDua = (inputUsername == registeredUser) && (inputPassword == registeredPass) && inputUsername.isNotEmpty()

            // Jika memenuhi SALAH SATU kondisi di atas
            if (kondisiSatu || kondisiDua) {
                // Simpan sesi login
                sharedPrefLogin.edit {
                    putBoolean("isLogin", true)
                    putString("username", inputUsername)
                }

                Toast.makeText(this, "Login Berhasil!", Toast.LENGTH_SHORT).show()

                // Arahkan ke halaman Home (BaseActivity)
                val intent = Intent(this, BaseActivity::class.java)
                intent.putExtra("username", inputUsername)
                startActivity(intent)
                finish() // Tutup halaman login agar tidak bisa di-back

            } else {
                // Jika gagal, tampilkan MaterialAlertDialog
                MaterialAlertDialogBuilder(this)
                    .setTitle("Login Gagal")
                    .setMessage("Username atau password salah, atau belum terdaftar. Silakan coba lagi.")
                    .setPositiveButton("Oke") { dialog, _ ->
                        dialog.dismiss() // Menutup dialog
                    }
                    .show()
            }
        }
    }

    // --- Implementasi Lifecycle Activity ---
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: Activity Mulai Terlihat")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: Activity Dihancurkan")
    }
}