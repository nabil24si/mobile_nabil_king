package NabilApps

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.nabil_king.databinding.ActivityLoginBinding // Pastikan import binding ini ada

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.login) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnLogin.setOnClickListener {
            val username = binding.username.text.toString()
            val password = binding.password.text.toString()

            if (username.isNotEmpty() && password.isNotEmpty()) {
                Toast.makeText(this, " $username Login Anda Berhasil !", Toast.LENGTH_SHORT).show()
                // Di dalam LoginActivity, saat tombol login diklik:
                val intent = Intent(this, DashboardActivity::class.java)
                intent.putExtra("username", binding.username.text.toString()) // Kuncinya adalah "username"
                startActivity(intent)
            } else {5
                Toast.makeText(this, "Isi semua data ya!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}