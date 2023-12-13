package com.example.smakartika1batu.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("datas")
data class Data(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    val id: Int = 0,

    @ColumnInfo("nama")
    val nama: String,

    @ColumnInfo("domisili")
    val domisili: String,

    @ColumnInfo("tanggalLahir")
    val tanggalLahir: String,

    @ColumnInfo("agama")
    val agama: String,

    @ColumnInfo("alamat")
    val alamat: String,

    @ColumnInfo("noHP")
    val noHP: String,

    @ColumnInfo("nisn")
    val nisn: String,
)


