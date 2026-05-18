package com.example.nabil_king.Home.Layanan.Warga

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.nabil_king.databinding.FragmentDataWargaBinding

class DataWargaFragment : Fragment() {
    private var _binding: FragmentDataWargaBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentDataWargaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Siapkan Data
        val listWarga = ArrayList<WargaModel>()
        listWarga.add(WargaModel("Nabil King", "Admin Sistem", "https://avatar.iran.liara.run/public/1"))
        listWarga.add(WargaModel("Bapak Ahmad", "Warga Privat", "https://avatar.iran.liara.run/public/2"))
        listWarga.add(WargaModel("Ibu Siti", "Warga Privat", "https://avatar.iran.liara.run/public/3"))

        // 2. Pasang Adapter
        val adapter = WargaAdapter(requireContext(), listWarga)
        binding.lvWarga.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}