package com.example.smakartika1batu

import java.util.Random

data class StudentModel(
    var id : Int = getAutoId(),
    var nama: String = "",
    var tempatLahir: String = "",
    var tanggalLahir: String = "",
    var agama: String = "",
    var alamatRumah: String = "",
    var noHP: String = "",
    var NISN: String = ""

) {
    companion object {
        fun getAutoId(): Int{
            val random = Random()
            return random.nextInt(100)
        }
    }

}

