package com.example.smakartika1batu.ui

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.smakartika1batu.data.DataRepository
import com.example.smakartika1batu.ui.datapribadi.DataPribadiViewModel

class DataPribadiModelFactory private constructor(private val mApplication: Application) : ViewModelProvider.NewInstanceFactory()  {
    companion object {
        @Volatile
        private var INSTANCE: DataPribadiModelFactory? = null
        @JvmStatic
        fun getInstance(application: Application): DataPribadiModelFactory {
            if (INSTANCE == null) {
                synchronized(DataPribadiModelFactory::class.java) {
                    INSTANCE = DataPribadiModelFactory(application)
                }
            }
            return INSTANCE as DataPribadiModelFactory
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DataPribadiViewModel::class.java)) {
            return DataPribadiViewModel(mApplication) as T
        } else if (modelClass.isAssignableFrom(DataPribadiViewModel::class.java)) {
            return DataPribadiViewModel(mApplication) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }

}