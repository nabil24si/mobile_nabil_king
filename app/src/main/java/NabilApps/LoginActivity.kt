package NabilApps

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.nabil_king.databinding.ActivityLoginBinding

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

        binding.btnLogin.setOnClickListener {
            val username = binding.username.text.toString()
            val password = binding.password.text.toString()

            if (username.isNotEmpty() && password.isNotEmpty()) {
                Toast.makeText(this, "$username, Login Anda Berhasil!", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, DashboardActivity::class.java)
                intent.putExtra("username", username)
                startActivity(intent)
                finish()

                // Opsional: panggil finish() jika tidak ingin user kembali ke halaman login setelah masuk dashboard
                // finish()
            } else {
                Toast.makeText(this, "Isi semua data ya!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // --- Implementasi Lifecycle Activity ---

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: Activity Mulai Terlihat")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: Activity Siap Berinteraksi (Fokus)")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: Activity Dijeda (Kehilangan Fokus)")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: Activity Berhenti (Tidak Terlihat)")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart: Activity Dijalankan Kembali")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: Activity Dihancurkan")
    }
}