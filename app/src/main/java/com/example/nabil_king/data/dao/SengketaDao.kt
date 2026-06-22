package com.example.nabil_king.data.dao

import androidx.room.*
import com.example.nabil_king.data.entity.SengketaEntity

@Dao
interface SengketaDao {
    @Query("SELECT * FROM sengketa ORDER BY createdAt DESC")
    suspend fun getAll(): List<SengketaEntity>

    @Insert
    suspend fun insert(sengketa: SengketaEntity)

    @Update
    suspend fun update(sengketa: SengketaEntity)

    @Delete
    suspend fun delete(sengketa: SengketaEntity)
}