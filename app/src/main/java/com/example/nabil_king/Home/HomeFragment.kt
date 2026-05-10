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
import com.example.nabil_king.Home.Perdes.PageThreeActivity
import com.example.nabil_king.Home.Perdes.PageoneActivity
import com.example.nabil_king.Home.Perdes.PagetwoActivity
import com.example.nabil_king.Warga.DataWargaFragment
import com.example.nabil_king.LoginActivity
import com.example.nabil_king.MainActivity
import com.example.nabil_king.Profile.ProfileFragment
import com.example.nabil_king.R
import com.example.nabil_king.databinding.FragmentHomeBinding
import com.google.android.material.chip.Chip
import com.google.android.material.dialog.MaterialAlertDialogBuilder

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

        // --- Navigasi Ke Halaman Lain (GridLayout Menu) ---
        binding.btnRuang.setOnClickListener {
            startActivity(Intent(requireContext(), MainActivity::class.java))
        }

        binding.btnBina.setOnClickListener {
            startActivity(Intent(requireContext(), PageThreeActivity::class.java))
        }


        binding.btnInfo.setOnClickListener {
            startActivity(Intent(requireContext(), ProfileFragment::class.java))
        }

        binding.btnDeveloper.setOnClickListener {
            startActivity(Intent(requireContext(), PagetwoActivity::class.java))
        }

        // --- Logika Logout ---
        binding.btnLogout.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Konfirmasi")
                .setMessage("Apakah Anda yakin ingin keluar?")
                .setPositiveButton("Ya") { dialog, _ ->
                    sharedPref.edit { clear() }
                    dialog.dismiss()
                    startActivity(Intent(requireContext(), LoginActivity::class.java))
                    requireActivity().finish()
                }
                .setNegativeButton("Batal") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }

        // --- FIXED: Ambil Data Dari Intent ---
        val intent = requireActivity().intent
        val namaDariLogin = intent.getStringExtra("username")
        binding.tvWelcome.text = if (!namaDariLogin.isNullOrEmpty()) "Welcome, $namaDariLogin" else "Welcome To Dashboard"
    }

    override fun onStart() {
        super.onStart()
        Log.d("HomeFragment", "onStart: Dashboard Mulai Terlihat")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}