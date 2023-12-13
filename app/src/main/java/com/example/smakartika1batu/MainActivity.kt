package com.example.smakartika1batu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.smakartika1batu.ui.login.Login

class MainActivity : AppCompatActivity() {
    private val SPLASH_TIME_OUT:Long = 3000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        Handler().postDelayed({
            startActivity(Intent(this, Login::class.java))
            finish()
        },SPLASH_TIME_OUT)
    }
}