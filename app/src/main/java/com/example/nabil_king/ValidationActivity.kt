package com.example.nabil_king

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.nabil_king.Home.HomeFragment
import com.example.nabil_king.databinding.ActivityValidationBinding

class ValidationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityValidationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityValidationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPref = getSharedPreferences("UserReg", Context.MODE_PRIVATE)

        // Ambil data dari SharedPreference
        val nama = sharedPref.getString("nama", "") ?: ""
        val email = sharedPref.getString("email", "") ?: ""
        val tglLahir = sharedPref.getString("tgl_lahir", "") ?: ""
        val gender = sharedPref.getString("gender", "") ?: ""
        val user = sharedPref.getString("username", "") ?: ""
        val pass = sharedPref.getString("password", "") ?: ""
        val confirmPass = sharedPref.getString("confirm_password", "") ?: ""

        // Tampilkan ke layar melalui binding
        binding.tvDataDisplay.text = """
            Nama: $nama
            Email: $email
            Tgl Lahir: $tglLahir
            Gender: $gender
            Username: $user
        """.trimIndent()

        binding.btnSubmit.setOnClickListener {
            // Logika Validasi
            if (nama.isEmpty() || email.isEmpty() || tglLahir.isEmpty() ||
                gender.isEmpty() || user.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Semua inputan tidak boleh kosong!", Toast.LENGTH_SHORT).show()
            } else if (pass != confirmPass) {
                Toast.makeText(this, "Password dan Konfirmasi Password harus sama!", Toast.LENGTH_SHORT).show()
            } else {
                showSuccessDialog()
            }
        }

        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    private fun showSuccessDialog() {
        AlertDialog.Builder(this)
            .setTitle("Informasi")
            .setMessage("Registrasi Berhasil")
            .setPositiveButton("OK") { _, _ ->
                val intent = Intent(this, BaseActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
            .setCancelable(false)
            .show()
    }
}