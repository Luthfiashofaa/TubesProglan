package com.example.smakartika1batu.ui.admin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.smakartika1batu.databinding.ActivityAdminViewBinding

class AdminView : AppCompatActivity() {
    private lateinit var binding: ActivityAdminViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminViewBinding.inflate(layoutInflater)

        val view = binding.root
        setContentView(view)
    }

}
