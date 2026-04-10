package NabilApps

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.nabil_king.databinding.ActivityPageoneBinding

class PageoneActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPageoneBinding
    private val TAG = "PageoneLifecycle"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: Halaman Informasi Dibuat")

        // Inisialisasi View Binding
        binding = ActivityPageoneBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.mainPageone) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Tombol Kembali ke Dashboard (Menghancurkan activity saat ini)
        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    // Penerapan Lifecycle Activity lainnya
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: Halaman mulai terlihat")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: Halaman siap berinteraksi")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: Halaman dijeda")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: Halaman berhenti (tidak terlihat)")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: Halaman dihancurkan")
    }
}