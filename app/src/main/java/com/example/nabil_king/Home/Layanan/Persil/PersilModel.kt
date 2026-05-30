package com.example.nabil_king.Home.Layanan.Persil

data class PersilModel(
    val kodePersil: String,
    val pemilik: String,
    val luas: String,
    val penggunaan: String,
    val alamat: String,
    val rtRw: String,
    val imageDummy: Int,  // Resource ID foto profil pemilik
    val imagePersil: Int  // Resource ID foto fisik persil/lahan
)