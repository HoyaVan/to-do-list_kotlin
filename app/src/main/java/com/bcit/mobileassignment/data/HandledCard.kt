package com.bcit.mobileassignment.data

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase

@Entity(tableName = "handled_card_table")
data class HandledCard(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val text: String,
    val isLocked: Boolean
)

@Dao
interface HandledCardDao {
    @Insert
    suspend fun insert(card: HandledCard)

    @Query("SELECT * FROM handled_card_table")
    suspend fun getAll(): List<HandledCard>

    @Query("DELETE FROM handled_card_table")
    suspend fun clearAll()
}

@Database(entities = [HandledCard::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun handledCardDao(): HandledCardDao
}

object CardDatabase {
    private var INSTANCE: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase {
        if (INSTANCE == null) {
            synchronized(AppDatabase::class) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "card_database"
                ).allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
            }
        }
        return INSTANCE!!
    }
}

