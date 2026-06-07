package com.example.nabil_king.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "drafts")
data class DraftEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nik: String,
    val namaPemilik: String,
    val luasTanah: String,
    val createdAt: Long
)