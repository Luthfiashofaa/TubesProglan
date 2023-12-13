package com.example.smakartika1batu

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    
    companion object{
        private const val DATABASE_VERSION = 3
        private const val DATABASE_NAME = "student.db"
        private const val ID = "id"
        private const val TBL_STUDENT = "tbl_student"
        private const val NAMA = "Nama"
        private const val TEMPATLAHIR = "TempatLahir"
        private const val TANGGALLAHIR = "TanggalLahir"
        private const val AGAMA = "Agama"
        private const val ALAMATRUMAH = "AlamatRumah"
        private const val NOHP = "NoHP"
        private const val NISN= "NISN"
    }


    override fun onCreate(db: SQLiteDatabase?) {
        val createTblStudent = (" CREATE TABLE " + TBL_STUDENT + " ( " + ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAMA+ " Nama, " + TEMPATLAHIR + " TempatLahir, " +TANGGALLAHIR+ " TanggalLahir, "
                + AGAMA + " Agama, " + ALAMATRUMAH + " AlamatRumah, " + NOHP + " NoHP, " +NISN+ " NISN " +" ) ")
        db?.execSQL(createTblStudent)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TBL_STUDENT")
        onCreate(db)
    }

    fun insertStudent(std: StudentModel): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(NAMA,std.nama)
        contentValues.put(TEMPATLAHIR,std.tempatLahir)
        contentValues.put(TANGGALLAHIR,std.tanggalLahir)
        contentValues.put(AGAMA,std.agama)
        contentValues.put(ALAMATRUMAH,std.alamatRumah)
        contentValues.put(NOHP,std.noHP)
        contentValues.put(NISN,std.NISN)

        val success = db.insert(TBL_STUDENT, null, contentValues)
        db.close()
        return success
    }

    @SuppressLint("Range")
    fun getAllStudent(): ArrayList<StudentModel>{
        val stdList : ArrayList<StudentModel> = ArrayList()
        val selectQuerry = "SELECT * FROM $TBL_STUDENT"
        val db= this.readableDatabase

        val cursor: Cursor?

        try{
            cursor = db.rawQuery(selectQuerry, null)
        }catch (e: Exception){
            e.printStackTrace()
            db.execSQL(selectQuerry)
            return ArrayList()
        }

        var id : Int
        var nama: String
        var tempatLahir: String
        var tanggalLahir: String
        var agama: String
        var alamatRumah: String
        var noHP: String
        var NISN: String

        if(cursor.moveToFirst()){
            do {
                id = cursor.getInt(cursor.getColumnIndex("id"))
                nama = cursor.getString(cursor.getColumnIndex("Nama"))
                tempatLahir = cursor.getString(cursor.getColumnIndex("TempatLahir"))
                tanggalLahir = cursor.getString(cursor.getColumnIndex("TanggalLahir"))
                agama = cursor.getString(cursor.getColumnIndex("Agama"))
                alamatRumah = cursor.getString(cursor.getColumnIndex("AlamatRumah"))
                noHP = cursor.getString(cursor.getColumnIndex("NoHP"))
                NISN = cursor.getString(cursor.getColumnIndex("NISN"))
                val std = StudentModel(
                    id,
                    nama,
                    tempatLahir,
                    tanggalLahir,
                    agama,
                    alamatRumah,
                    noHP,
                    NISN
                )
                stdList.add(std)
            }while (cursor.moveToNext())
        }
        return stdList
    }
}