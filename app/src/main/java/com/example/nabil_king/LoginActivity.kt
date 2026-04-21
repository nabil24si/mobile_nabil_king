package com.example.nabil_king

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.nabil_king.NabilApps.DashboardActivity
import com.example.nabil_king.databinding.ActivityLoginBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    // Tag untuk mempermudah pencarian di Logcat
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
        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)

        //Kondisi jika isLogin bernilai true
//        val isLogin = sharedPref.getBoolean("isLogin", false)
//        if (isLogin) {
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//            finish()//kill auth activity
//        }
        binding.btnLogin.setOnClickListener {
            val username = binding.username.text.toString()
            val password = binding.password.text.toString()

            if (username == password && username.isNotEmpty() && password.isNotEmpty()) {
                sharedPref.edit {
                    putBoolean("isLogin", true)
                    putString("username", username)
                }
                Toast.makeText(this, " $username Login Anda Berhasil !", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, DashboardActivity::class.java)
                intent.putExtra("username", binding.username.text.toString()) // Kuncinya adalah "username"
                startActivity(intent)
            } else {
                MaterialAlertDialogBuilder(this)
                    .setTitle("Coba Lagi")
                    .setMessage("Silahkan Coba lagi")
                    .setPositiveButton("Ya") { dialog, _ ->
                        dialog.dismiss()
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