package com.saliim.absensimobile.admin

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.saliim.absensimobile.R
import com.saliim.absensimobile.api.API
import com.saliim.absensimobile.model.lokasi.InsertLokasi
import kotlinx.android.synthetic.main.activity_tambah_lokasi.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TambahLokasiActivity : AppCompatActivity() {

    private var toolbar: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_lokasi)

        toolbar = findViewById<View>(R.id.toolbars) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar!!.title = "Tambah Lokasi Event"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        btn_tambah_lokasi.setOnClickListener {
            addLokasis()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun addLokasis() {
        val progressDialog = ProgressDialog(this@TambahLokasiActivity)
        progressDialog.setMessage("Membuat Lokasi Baru....")
        progressDialog.show()

        val lokasi = et_nama_lokasi.text.toString()
        val lat = et_latitude_lokasi.text.toString()
        val lon = et_longitude_lokasi.text.toString()
        val rad = et_radius_lokasi.text.toString()
        API.addLokasi(lokasi, lat, lon, rad).enqueue(object : Callback<InsertLokasi>{
            override fun onResponse(call: Call<InsertLokasi>, response: Response<InsertLokasi>) {
                if (response.code() == 200){
                    Log.i("tambahLokasi", ""+response.body())
                    Toast.makeText(this@TambahLokasiActivity, "Lokasi Berhasil di Tambahkan", Toast.LENGTH_LONG).show()
                    progressDialog.dismiss()
                    finish()
                }else{
                    Toast.makeText(this@TambahLokasiActivity, "gagal", Toast.LENGTH_LONG).show()
                    progressDialog.dismiss()
                }
            }

            override fun onFailure(call: Call<InsertLokasi>, t: Throwable) {
                Toast.makeText(this@TambahLokasiActivity, "gagal", Toast.LENGTH_LONG).show()
                progressDialog.dismiss()
            }
        })
    }
}
