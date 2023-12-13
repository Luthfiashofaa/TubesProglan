package com.example.smakartika1batu.ui.datapribadi

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.smakartika1batu.data.Data
import com.example.smakartika1batu.data.DataRepository

class DataPribadiViewModel(application: Application): ViewModel() {
    private val dataRepository: DataRepository = DataRepository(application)

    fun insert(data: Data){
        dataRepository.insert(data)
    }
}