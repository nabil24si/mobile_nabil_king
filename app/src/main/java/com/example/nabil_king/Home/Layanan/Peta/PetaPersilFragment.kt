package com.example.nabil_king.Home.Layanan.Peta

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.nabil_king.databinding.FragmentPetaPersilBinding

class PetaPersilFragment : Fragment() {

    private var _binding: FragmentPetaPersilBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentPetaPersilBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // Placeholder logic: Since API Key is required for Maps, 
        // we show the placeholder by default unless key is set.
        binding.mapView.visibility = View.GONE
        binding.placeholderMap.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}