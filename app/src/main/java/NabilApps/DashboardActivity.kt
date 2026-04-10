package NabilApps

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.nabil_king.MainActivity
import com.example.nabil_king.databinding.ActivityDashboardBinding
import com.google.android.material.snackbar.Snackbar

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

        // --- Navigasi Ke Halaman Lain ---
        binding.btnRuang.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        binding.btnInfo.setOnClickListener {
            startActivity(Intent(this, PageoneActivity::class.java))
        }

        binding.btnDeveloper.setOnClickListener {
            startActivity(Intent(this, PagetwoActivity::class.java))
        }

        // --- Logika Logout dengan AlertDialog & Snackbar ---
        binding.btnLogout.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Konfirmasi Logout")
            builder.setMessage("Apakah Anda yakin ingin keluar dari aplikasi?")

            // Jika klik YA
            builder.setPositiveButton("Iya") { _, _ ->
                val intent = Intent(this, LoginActivity::class.java)
                // Clear stack agar user tidak bisa back lagi ke dashboard setelah logout
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)

            }

            // Jika klik TIDAK
            builder.setNegativeButton("Tidak") { dialog, _ ->
                dialog.dismiss()
                // Menampilkan Snackbar
                Snackbar.make(binding.mainDashboard, "Logout dibatalkan", Snackbar.LENGTH_SHORT).show()
            }

            val alertDialog = builder.create()
            alertDialog.show()
        }

        // --- Ambil Data Dari Intent ---
        val namaDariLogin = intent.getStringExtra("username")
        binding.tvWelcome.text = if (namaDariLogin != null) "Welcome, $namaDariLogin" else "Welcome, Guest"
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