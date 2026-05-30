package com.example.nabil_king.Home.Layanan

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.nabil_king.Home.Layanan.Persil.DataPersilFragment
import com.example.nabil_king.Home.Layanan.Warga.DataWargaFragment

class TabsAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    // Jumlah total tab yang ada
    override fun getItemCount(): Int = 2

    // Menentukan Fragment mana yang akan ditampilkan berdasarkan posisi tab
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> DataWargaFragment()
            1 -> DataPersilFragment()
            else -> throw IllegalStateException("Posisi tidak valid")
        }
    }
}