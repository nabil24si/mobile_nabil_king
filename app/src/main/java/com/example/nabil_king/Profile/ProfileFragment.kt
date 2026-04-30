package com.example.nabil_king.Profile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.nabil_king.R
import com.example.nabil_king.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val TAG = "ProfileFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        // Memberitahu Fragment bahwa ia memiliki menu sendiri di Toolbar
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated: Memuat Profil Pengembang")
    }

    // --- LOGIKA MENU DI FRAGMENT ---

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_latihan, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_share -> {
                val shareIntent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    val profilInfo = "Kenalan yuk sama Pengembang PERDES! Aplikasi ini dibuat untuk transparansi aset desa."
                    putExtra(Intent.EXTRA_TEXT, profilInfo)
                }
                startActivity(Intent.createChooser(shareIntent, "Bagikan Profil"))
                true
            }
            R.id.sub_refresh -> {
                Toast.makeText(requireContext(), "Profil telah diperbarui", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // --- Lifecycle Monitoring ---
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Mencegah memory leak
    }
}