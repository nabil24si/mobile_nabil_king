package com.example.nabil_king.Home.Layanan.Persil

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nabil_king.databinding.ItemPersilBinding

class PersilAdapter(private val listPersil: List<PersilModel>) : RecyclerView.Adapter<PersilAdapter.PersilViewHolder>() {

    class PersilViewHolder(val binding: ItemPersilBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersilViewHolder {
        val binding = ItemPersilBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PersilViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PersilViewHolder, position: Int) {
        val item = listPersil[position]
        with(holder.binding) {
            tvKodePersil.text = item.kodePersil
            tvPemilik.text = item.pemilik
            tvLuas.text = item.luas
            tvPenggunaan.text = item.penggunaan
            tvAlamat.text = "Alamat: ${item.alamat}"
            tvRtRw.text = "RT/RW: ${item.rtRw}"
            ivImageDummy.setImageResource(item.imageDummy)
            ivImagePersil.setImageResource(item.imagePersil)
        }
    }

    override fun getItemCount(): Int = listPersil.size
}