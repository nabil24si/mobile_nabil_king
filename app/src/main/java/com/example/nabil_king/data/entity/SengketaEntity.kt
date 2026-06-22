package com.example.nabil_king.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sengketa")
data class SengketaEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String,
    val pihak1: String,
    val pihak2: String,
    val kodePersil: String,
    val status: String, // e.g., "Dalam Proses", "Selesai"
    val createdAt: Long
)