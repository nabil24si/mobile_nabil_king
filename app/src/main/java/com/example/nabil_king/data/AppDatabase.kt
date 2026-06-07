package com.example.nabil_king.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.nabil_king.data.dao.DraftDao
import com.example.nabil_king.data.dao.NoteDao
import com.example.nabil_king.data.entity.DraftEntity
import com.example.nabil_king.data.entity.NoteEntity

@Database(
    entities = [NoteEntity::class, DraftEntity::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao
    abstract fun draftDao(): DraftDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                .fallbackToDestructiveMigration() // Penting karena versi naik ke 2
                .build().also { INSTANCE = it }
            }
        }
    }
}