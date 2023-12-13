package com.example.smakartika1batu.ui.datapribadi

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.smakartika1batu.R
import com.example.smakartika1batu.SQLiteHelper
import com.example.smakartika1batu.StringHelper
import com.example.smakartika1batu.StudentModel
import com.example.smakartika1batu.data.Data
import com.example.smakartika1batu.databinding.ActivityDataPribadiBinding
import com.example.smakartika1batu.ui.DataPribadiModelFactory
import java.util.Calendar

class dataPribadi : AppCompatActivity() {
    private lateinit var binding: ActivityDataPribadiBinding
    private lateinit var sqliteHelper : SQLiteHelper
    private lateinit var dataPribadiViewModel: DataPribadiViewModel

    private var savedNama: String? = null
    private var savedTempatLahir: String? = null
    private var savedTanggalLahir: String? = null
    private var savedAgama: String? = null
    private var savedAlamatRumah: String? = null
    private var savedNoHP: String? = null
    private var savedNISN: String? = null
    private var isDataSaved = false



    override fun onCreate(savedInstanceState: Bundle?) {
        dataPribadiViewModel = obtainViewModel(this)
        super.onCreate(savedInstanceState)
        binding = ActivityDataPribadiBinding.inflate(layoutInflater)

        val dropDownList = arrayOf("option A", "option B", "Option C")

        val view = binding.root
        setContentView(view)

        //ambilData
        sqliteHelper = SQLiteHelper(this)

        binding.saveButtonid.setOnClickListener {
            addStudent()
            isDataSaved=true
            getStudent()
        }
        


        //setTanggal
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, dropDownList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)

        binding.statusfilter.adapter = adapter
        binding.statusfilter.setSelection(Adapter.NO_SELECTION, true)
        binding.statusfilter.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (binding.statusfilter.selectedItemPosition == 0) {
                    condition1()
                }
                if (binding.statusfilter.selectedItemPosition == 1) {
                    condition2()
                }
                if (binding.statusfilter.selectedItemPosition == 2) {
                    condition3()
                }
            }
        }

        binding.edtDatePicker.setOnClickListener {
            setDate()
        }

        binding.editButton.setOnClickListener {
            enableText()
        }

        if (savedInstanceState != null) {
            restoreInstanceState(savedInstanceState)
            restoreSavedData()
        }

    }

    private fun obtainViewModel(activity: AppCompatActivity): DataPribadiViewModel {
        val factory = DataPribadiModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(DataPribadiViewModel::class.java)
    }

    private fun getStudent(){
        val stdList = sqliteHelper.getAllStudent()
        Log.e("cek", "${stdList.size}")
    }

    private fun addStudent() {
        val nama = binding.namalengkap.text.toString()
        val tempatlahir = binding.tempatLahir.text.toString()
        val tanggallahir = binding.edtDatePicker.text.toString()
        val agama = binding.agama.text.toString()
        val alamatrumah = binding.alamatRumah.text.toString()
        val noHP = binding.noHP.text.toString()
        val NISN = binding.NISN.text.toString()

        if (nama.isEmpty() || tempatlahir.isEmpty() || tanggallahir.isEmpty() || agama.isEmpty() || alamatrumah.isEmpty() || noHP.isEmpty() || NISN.isEmpty()) {
            Toast.makeText(this, "Tidak boleh kosong", Toast.LENGTH_SHORT).show()
        } else {
            val std = StudentModel(
                nama = nama,
                tempatLahir = tempatlahir,
                tanggalLahir = tanggallahir,
                agama = agama,
                alamatRumah = alamatrumah,
                noHP = noHP,
                NISN = NISN
            )
            val status = sqliteHelper.insertStudent(std)

            dataPribadiViewModel.insert(Data(
                nama = nama,
                domisili = tempatlahir,
                tanggalLahir = tanggallahir,
                agama = agama,
                alamat = alamatrumah,
                noHP = noHP,
                nisn = NISN
            ))

            Toast.makeText(this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show()
            disableText()

        }
    }

    private fun disableText(){
        binding.namalengkap.isEnabled = false
        binding.tempatLahir.isEnabled = false
        binding.edtDatePicker.isEnabled = false
        binding.agama.isEnabled = false
        binding.alamatRumah.isEnabled = false
        binding.noHP.isEnabled = false
        binding.NISN.isEnabled = false
        binding.namalengkap.setTextColor(resources.getColor(R.color.disabledTextColor))
        binding.tempatLahir.setTextColor(resources.getColor(R.color.disabledTextColor))
        binding.edtDatePicker.setTextColor(resources.getColor(R.color.disabledTextColor))
        binding.agama.setTextColor(resources.getColor(R.color.disabledTextColor))
        binding.alamatRumah.setTextColor(resources.getColor(R.color.disabledTextColor))
        binding.noHP.setTextColor(resources.getColor(R.color.disabledTextColor))
        binding.NISN.setTextColor(resources.getColor(R.color.disabledTextColor))

    }

    private fun enableText(){
        binding.namalengkap.isEnabled = true
        binding.tempatLahir.isEnabled = true
        binding.edtDatePicker.isEnabled = true
        binding.agama.isEnabled = true
        binding.alamatRumah.isEnabled = true
        binding.noHP.isEnabled = true
        binding.NISN.isEnabled = true
        binding.namalengkap.setTextColor(resources.getColor(R.color.black))
        binding.tempatLahir.setTextColor(resources.getColor(R.color.black))
        binding.edtDatePicker.setTextColor(resources.getColor(R.color.black))
        binding.agama.setTextColor(resources.getColor(R.color.black))
        binding.alamatRumah.setTextColor(resources.getColor(R.color.black))
        binding.noHP.setTextColor(resources.getColor(R.color.black))
        binding.NISN.setTextColor(resources.getColor(R.color.black))

    }


    private fun condition1() {
        Toast.makeText(
            this,
            "selected Item: " + binding.statusfilter.selectedItem,
            Toast.LENGTH_LONG
        ).show()
    }

    private fun condition2() {
        Toast.makeText(
            this,
            "selected Item: " + binding.statusfilter.selectedItem,
            Toast.LENGTH_LONG
        ).show()
    }

    private fun condition3() {
        Toast.makeText(this, "Checked Item: ", Toast.LENGTH_LONG).show()
    }

    private fun setDate() {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)


        val dpd = DatePickerDialog(
            this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                val returnDate = "${monthOfYear + 1} $dayOfMonth $year"
                val date = StringHelper.parseDate(
                    "MM dd yyyy",
                    "MM/dd/yyyy",
                    returnDate
                )
                val dateString = date
                binding.edtDatePicker.setText(
                    StringHelper.parseDate(
                        "MM/dd/yyyy",
                        "MMM dd yyyy",
                        date
                    )
                )
                binding.edtDatePicker.error = null
                Toast.makeText(
                    this,
                    "pick date input format and display $dateString",
                    Toast.LENGTH_LONG
                ).show()
                //Thanks for watching this tutorial
            }, year - 30, month, day
        )
        dpd.show()


    }


    //Menyimpan isian

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("savedNama", savedNama)
        outState.putString("savedTempatLahir", savedTempatLahir)
        outState.putString("savedTanggalLahir", savedTanggalLahir)
        outState.putString("savedAgama", savedAgama)
        outState.putString("savedAlamatRumah", savedAlamatRumah)
        outState.putString("savedNoHP", savedNoHP)
        outState.putString("savedNISN", savedNISN)
        outState.putBoolean("isDataSaved", isDataSaved)
    }

    private fun restoreInstanceState(savedInstanceState: Bundle) {
        savedNama = savedInstanceState.getString("savedNama")
        savedTempatLahir = savedInstanceState.getString("savedTempatLahir")
        savedTanggalLahir = savedInstanceState.getString("savedTanggalLahir")
        savedAgama = savedInstanceState.getString("savedAgama")
        savedAlamatRumah = savedInstanceState.getString("savedAlamatRumah")
        savedNoHP = savedInstanceState.getString("savedNoHP")
        savedNISN = savedInstanceState.getString("savedNISN")
        isDataSaved = savedInstanceState.getBoolean("isDataSaved")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        restoreInstanceState(savedInstanceState)
    }

    override fun onPause() {
        super.onPause()
        if (!isDataSaved) {
            saveCurrentData()
        }
    }

    override fun onResume() {
        super.onResume()
        if (!isDataSaved) {
            restoreSavedData()
        }
    }

    private fun saveCurrentData() {
        savedNama = binding.namalengkap.text.toString()
        savedTempatLahir = binding.tempatLahir.text.toString()
        savedTanggalLahir = binding.edtDatePicker.text.toString()
        savedAgama = binding.agama.text.toString()
        savedAlamatRumah = binding.alamatRumah.text.toString()
        savedNoHP = binding.noHP.text.toString()
        savedNISN = binding.NISN.text.toString()
    }

    private fun restoreSavedData() {
        binding.namalengkap.setText(savedNama)
        binding.tempatLahir.setText(savedTempatLahir)
        binding.edtDatePicker.setText(savedTanggalLahir)
        binding.agama.setText(savedAgama)
        binding.alamatRumah.setText(savedAlamatRumah)
        binding.noHP.setText(savedNoHP)
        binding.NISN.setText(savedNISN)
    }


}