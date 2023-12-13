package com.example.smakartika1batu.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.sqlite.db.SupportSQLiteQuery

@Dao
interface DataDao {
//    @Query("SELECT * FROM datas")
//    fun getData(query: SupportSQLiteQuery): LiveData<List<Data>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertData(data: Data)
}