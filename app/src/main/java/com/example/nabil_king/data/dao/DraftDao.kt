package com.example.nabil_king.data.dao

import androidx.room.*
import com.example.nabil_king.data.entity.DraftEntity

@Dao
interface DraftDao {
    @Query("SELECT * FROM drafts ORDER BY createdAt DESC")
    suspend fun getAll(): List<DraftEntity>

    @Insert
    suspend fun insert(draft: DraftEntity)

    @Delete
    suspend fun delete(draft: DraftEntity)
}