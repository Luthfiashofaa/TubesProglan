package com.example.smakartika1batu

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.smakartika1batu.databinding.ActivityRegisterBinding

class Register : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)

        val view = binding.root
        setContentView(view)

        binding.buttonNext.setOnClickListener{
            val namaLengkap = binding.inputNama.text.toString()
            val Email = binding.inputEmail.text.toString()
            val Password = binding.inputPassword.text.toString()

            try{
                if (namaLengkap.isEmpty() || Email.isEmpty() || Password.isEmpty()){
                    throw IllegalArgumentException("Tidak boleh kosong")
                }
                else if (!Email.endsWith("@gmail.com")){
                    throw IllegalArgumentException("Bukan alamat email")
                }
                else{
                    val intent = Intent(this, Login::class.java)
                    startActivity(intent)
                }
            }catch (e: IllegalArgumentException){
                Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}