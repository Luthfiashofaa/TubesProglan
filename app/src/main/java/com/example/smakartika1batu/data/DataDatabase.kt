package com.example.smakartika1batu.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Data::class], version = 1, exportSchema = false)
abstract class DataDatabase : RoomDatabase() {
    abstract fun dataDao(): DataDao

    companion object{
        @Volatile
        private var INSTANCE: DataDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): DataDatabase {
            if (INSTANCE == null) {
                synchronized(DataDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        DataDatabase::class.java, "datas").build()
                }
            }
            return INSTANCE as DataDatabase
        }
    }
}