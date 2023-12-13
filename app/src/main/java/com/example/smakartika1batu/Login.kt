package com.example.smakartika1batu

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.smakartika1batu.databinding.ActivityLoginBinding


class Login : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)

        val view = binding.root
        setContentView(view)

        binding.button.setOnClickListener{
            val username = binding.loginemail.text.toString()
            val password = binding.loginpassword.text.toString()
            try{
                if (username.isEmpty() || password.isEmpty()){
                    throw IllegalArgumentException("Tidak boleh kosong")
                }
                else{
                    if(username == "Admin123@gmail.com"){
                        val intent = Intent(this, AdminView::class.java)
                        startActivity(intent)
                    }
                    else {
                        val intent = Intent(this, Homepage::class.java)
                        startActivity(intent)
                    }
                }
            }catch (e: IllegalArgumentException){
                Toast.makeText(this, "Tidak boleh kosong",Toast.LENGTH_SHORT).show()
            }
            

        }

        binding.daftarAkun.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }


    }


}