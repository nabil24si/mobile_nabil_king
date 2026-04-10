package NabilApps

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.nabil_king.databinding.ActivityPagetwoBinding

class PagetwoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPagetwoBinding
    private val TAG = "PagetwoLifecycle"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: Memuat Profil Pengembang")

        binding = ActivityPagetwoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Klik tombol kembali
        binding.btnBackFromDev.setOnClickListener {
            finish() // Menutup activity ini untuk kembali ke Dashboard
        }
    }

    // --- Lifecycle Monitoring ---

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: Profil Terlihat")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: Fokus pada Profil")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: Meninggalkan Halaman Profil")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: Profil Tidak Terlihat")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: Activity Profil Dihancurkan")
    }
}