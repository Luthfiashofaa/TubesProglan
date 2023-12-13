package com.example.smakartika1batu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.smakartika1batu.databinding.ActivityHomepageBinding

class Homepage : AppCompatActivity() {

    private lateinit var binding: ActivityHomepageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomepageBinding.inflate(layoutInflater)

        val view = binding.root
        setContentView(view)

        binding.menu.setOnClickListener {
            val intent = Intent(this, dataPribadi::class.java)
            startActivity(intent)
        }
    }
}