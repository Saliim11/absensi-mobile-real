package com.saliim.absensimobile.admin

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.saliim.absensimobile.R
import com.saliim.absensimobile.api.API
import com.saliim.absensimobile.model.registerUser.RegisterAdmin
import kotlinx.android.synthetic.main.activity_register_admin.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.jvm.java

class RegisterAdminActivity : AppCompatActivity() {

    private var toolbar: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_admin)

        toolbar = findViewById<View>(R.id.toolbars) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar!!.title = "Register SubAdmin"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        btn_reg_submit.setOnClickListener {
            val inputFN = et_reg_nama.text.toString()
            val inputUN = et_reg_username.text.toString()
            val inputPW = et_reg_password.text.toString()
            if (inputFN.isEmpty()){
                et_reg_nama.error = "tidak boleh kosong"
            }else if (inputUN.isEmpty()){
                et_reg_username.error = "tidak boleh kosong"
            }else if (inputPW.isEmpty()){
                et_reg_password.error = "tidak boleh kosong"
            }else{
                registerAdmin()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun registerAdmin() {
        val progressDialog = ProgressDialog(this@RegisterAdminActivity)
        progressDialog.setMessage("Membuat Lokasi Baru....")
        progressDialog.show()

        val inNama = et_reg_nama.text.toString()
        val inUn = et_reg_username.text.toString()
        val inPW = et_reg_password.text.toString()

        API.registerAdmins(inNama, inUn, inPW).enqueue(object : Callback<RegisterAdmin>{
            override fun onResponse(call: Call<RegisterAdmin>, response: Response<RegisterAdmin>) {
                if (response.code() == 200){
                    Log.i("register admin", ""+response.body())
                    Toast.makeText(this@RegisterAdminActivity, "Admin Berhasil di tambah kan", Toast.LENGTH_LONG).show()

                    startActivity(Intent(this@RegisterAdminActivity, AdminActivity::class.java))

                    progressDialog.dismiss()
                    finish()
                }else{
                    Toast.makeText(this@RegisterAdminActivity, "gagal", Toast.LENGTH_SHORT).show()
                    progressDialog.dismiss()
                }
            }

            override fun onFailure(call: Call<RegisterAdmin>, t: Throwable) {
                Toast.makeText(this@RegisterAdminActivity, "gagal1", Toast.LENGTH_SHORT).show()
                progressDialog.dismiss()
            }

        })
    }
}
