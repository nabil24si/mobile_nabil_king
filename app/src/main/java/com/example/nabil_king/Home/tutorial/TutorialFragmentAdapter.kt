package com.example.nabil_king.Home.tutorial

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class TutorialFragmentAdapter(
    fragmentActivity: FragmentActivity,
    private val fragments: List<Fragment>
) : FragmentStateAdapter(fragmentActivity) {

    // Mengambil jumlah data berdasarkan ukuran list fragment
    override fun getItemCount(): Int = fragments.size

    // Menampilkan fragment sesuai posisi tracking swipe
    override fun createFragment(position: Int): Fragment = fragments[position]
}