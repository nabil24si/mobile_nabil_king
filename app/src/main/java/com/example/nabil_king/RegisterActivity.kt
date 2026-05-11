package com.example.nabil_king

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import com.example.nabil_king.databinding.ActivityRegisterBinding
import java.util.*

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // DatePicker Logic
        binding.etTanggalLahir.setOnClickListener {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            val dpd = DatePickerDialog(this, { _, selectedYear, monthOfYear, dayOfMonth ->
                binding.etTanggalLahir.setText("$dayOfMonth/${monthOfYear + 1}/$selectedYear")
            }, year, month, day)
            dpd.show()
        }

        binding.btnNext.setOnClickListener {
            saveToSP()
            val intent = Intent(this, ValidationActivity::class.java)
            startActivity(intent)
        }
    }

    private fun saveToSP() {
        val sharedPref = getSharedPreferences("UserReg", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()

        val selectedGenderId = binding.rgGender.checkedRadioButtonId
        val gender = if (selectedGenderId != -1) {
            findViewById<RadioButton>(selectedGenderId).text.toString()
        } else ""

        editor.putString("nama", binding.etNama.text.toString())
        editor.putString("email", binding.etEmail.text.toString())
        editor.putString("tgl_lahir", binding.etTanggalLahir.text.toString())
        editor.putString("gender", gender)
        editor.putString("username", binding.etUsername.text.toString())
        editor.putString("password", binding.etPassword.text.toString())
        editor.putString("confirm_password", binding.etConfirmPassword.text.toString())
        editor.apply()
    }
}