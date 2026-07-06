package com.example.nabil_king.Home

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.edit
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nabil_king.Home.Layanan.LayananActivity
import com.example.nabil_king.Home.Layanan.Persil.DataPersilFragment
import com.example.nabil_king.Home.Layanan.Warga.DataWargaFragment
import com.example.nabil_king.Home.Perdes.PageThreeActivity
import com.example.nabil_king.Home.Perdes.PagetwoActivity
import com.example.nabil_king.Home.photo.PhotoAdapter
import com.example.nabil_king.LoginActivity
import com.example.nabil_king.MainActivity
import com.example.nabil_king.Profile.ProfileFragment
import com.example.nabil_king.R
import com.example.nabil_king.data.api.PhotoApiClient
import com.example.nabil_king.databinding.FragmentHomeBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPref = requireContext().getSharedPreferences("user_pref", MODE_PRIVATE)

        binding.btnRuang.setOnClickListener {
            startActivity(Intent(requireContext(), MainActivity::class.java))
        }

        binding.btnBina.setOnClickListener {
            startActivity(Intent(requireContext(), PageThreeActivity::class.java))
        }

        binding.btnInfo.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ProfileFragment())
                .addToBackStack(null)
                .commit()
        }

        binding.btnDeveloper.setOnClickListener {
            startActivity(Intent(requireContext(), PagetwoActivity::class.java))
        }

        // Navigasi tombol layanan ke DataPersilFragment
        binding.btnLayanan.setOnClickListener {
            startActivity(Intent(requireContext(), LayananActivity::class.java))
        }

        binding.btnDataWarga.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, DataWargaFragment())
                .addToBackStack(null)
                .commit()
        }

        binding.btnLogout.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Konfirmasi")
                .setMessage("Apakah Anda yakin ingin keluar?")
                .setPositiveButton("Ya") { dialog, _ ->
                    sharedPref.edit { clear() }
                    dialog.dismiss()
                    startActivity(Intent(requireContext(),
                        LoginActivity::class.java))
                    requireActivity().finish()
                }
                .setNegativeButton("Batal") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }

        val intent = requireActivity().intent
        val namaDariLogin = intent.getStringExtra("username")
        binding.tvWelcome.text = if (!namaDariLogin.isNullOrEmpty()) "Welcome, $namaDariLogin" else "Welcome To Dashboard"

        loadPhoto()
    }

    private fun loadPhoto() {
        lifecycleScope.launch {
            try {
                val photos = PhotoApiClient.apiService.getPhotos()
                val adapter = PhotoAdapter(photos)
                binding.rvGallery.adapter = adapter
                /** List Tampil Horizontal */
                binding.rvGallery.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Gagal memuat gambar", Toast.LENGTH_SHORT).show()
            }
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}