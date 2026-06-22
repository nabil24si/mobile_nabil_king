package com.example.nabil_king.Home.Layanan.Sengketa

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.nabil_king.R
import com.example.nabil_king.data.entity.SengketaEntity
import com.example.nabil_king.databinding.ItemSengketaBinding
import java.text.SimpleDateFormat
import java.util.*

class SengketaAdapter(
    private var sengketaList: List<SengketaEntity>,
    private val onItemClick: (SengketaEntity) -> Unit
) : RecyclerView.Adapter<SengketaAdapter.SengketaViewHolder>() {

    inner class SengketaViewHolder(val binding: ItemSengketaBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SengketaViewHolder {
        val binding = ItemSengketaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SengketaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SengketaViewHolder, position: Int) {
        val item = sengketaList[position]
        holder.binding.apply {
            tvTitle.text = item.title
            tvStatus.text = item.status
            tvParties.text = "Pihak: ${item.pihak1} vs ${item.pihak2}"
            tvParcelCode.text = "Kode Persil: ${item.kodePersil}"
            
            val sdf = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
            tvDate.text = sdf.format(Date(item.createdAt))

            // Change status color
            if (item.status == "Selesai") {
                tvStatus.backgroundTintList = ContextCompat.getColorStateList(holder.itemView.context, R.color.status_dispute_resolved)
            } else {
                tvStatus.backgroundTintList = ContextCompat.getColorStateList(holder.itemView.context, R.color.status_dispute_pending)
            }

            root.setOnClickListener { onItemClick(item) }
        }
    }

    override fun getItemCount(): Int = sengketaList.size

    fun updateData(newList: List<SengketaEntity>) {
        sengketaList = newList
        notifyDataSetChanged()
    }
}