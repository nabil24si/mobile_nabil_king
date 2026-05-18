package com.example.nabil_king.Home.Layanan.Warga

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.bumptech.glide.Glide
import com.example.nabil_king.R
import com.example.nabil_king.databinding.ItemWargaBinding

class WargaAdapter(context: Context, private val listWarga: List<WargaModel>)
    : ArrayAdapter<WargaModel>(context, 0, listWarga) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Gunakan ViewBinding dengan cara yang paling simpel
        val binding = if (convertView == null) {
            ItemWargaBinding.inflate(LayoutInflater.from(context), parent, false)
        } else {
            ItemWargaBinding.bind(convertView)
        }

        val data = listWarga[position]

        binding.tvNamaWarga.text = data.nama
        binding.tvStatusWarga.text = data.status

        // Load Gambar
        Glide.with(context)
            .load(data.fotoUrl)
            .placeholder(R.drawable.pro)
            .error(R.drawable.pro)
            .circleCrop()
            .into(binding.ivAvatar)

        return binding.root
    }
}