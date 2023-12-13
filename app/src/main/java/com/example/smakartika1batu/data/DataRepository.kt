package com.example.smakartika1batu.data

import android.app.Application
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class DataRepository(application: Application) {
    private val dataDao: DataDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = DataDatabase.getDatabase(application)
        dataDao = db.dataDao()
    }

    fun insert(data: Data) {
        executorService.execute { dataDao.insertData(data) }
    }
}