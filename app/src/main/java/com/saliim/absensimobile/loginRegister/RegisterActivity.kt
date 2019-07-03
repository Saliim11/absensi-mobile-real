package com.saliim.absensimobile.loginRegister

import androidx.appcompat.app.AppCompatActivity

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.widget.Toolbar

import com.saliim.absensimobile.R
import com.saliim.absensimobile.api.API
import com.saliim.absensimobile.model.registerUser.RegisterUser
import com.saliim.absensimobile.model.users.DataSubAdmin
import kotlinx.android.synthetic.main.activity_register.*

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {

    private var toolbar: Toolbar? = null

    var sub_admin : ArrayList<DataSubAdmin?>? = null

    var id_admin : String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        toolbar = findViewById(R.id.toolbars)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = "Register User"

        getSubAdmin {}

        spn_sub_admin.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val id_sub_admin = sub_admin?.get(position)?.id
                id_admin = id_sub_admin
                Log.d("id_Admin", "$id_sub_admin")

                val prefs = this@RegisterActivity.getSharedPreferences("X", android.content.Context.MODE_PRIVATE)
                val editor = prefs.edit()
                editor.putString("id_admin", id_sub_admin)
                editor.commit()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }

        btn_reg_submit.setOnClickListener { v ->
            val inputFN = et_reg_nama.text.toString()
            val inputUN = et_reg_username.text.toString()
            val inputPW = et_reg_password.text.toString()
            val inputSA = spn_sub_admin.selectedItem

            if (inputFN.isEmpty()){
                et_reg_nama.error = "tidak boleh kosong"
            }else if (inputUN.isEmpty()){
                et_reg_username.error = "tidak boleh kosong"
            }else if (inputPW.isEmpty()){
                et_reg_password.error = "tidak boleh kosong"
            }else if (inputSA == null){
                Toast.makeText(this@RegisterActivity, "Pilih Admin terlebih dahulu", Toast.LENGTH_SHORT).show()
            }else{
                registerNorUser()
            }
        }
    }

    fun registerNorUser(){
        val progressDialog = ProgressDialog(this@RegisterActivity)
        progressDialog.setMessage("Membuat User Baru......")
        progressDialog.show()

        val inNama = et_reg_nama.text.toString()
        val inRegUn = et_reg_username.text.toString()
        val inRegPw = et_reg_password.text.toString()
//        val idAdmin = spn_sub_admin.selectedItem.toString()

        API.registerUsers(inNama, inRegUn, inRegPw, id_admin).enqueue(object : Callback<RegisterUser> {
            override fun onResponse(call: Call<RegisterUser>, response: Response<RegisterUser>) {
                if (response.code() == 200) {
                    Log.i("registerUser", response.body()!!.toString())
                    Toast.makeText(this@RegisterActivity, "berhasil", Toast.LENGTH_LONG).show()
                    progressDialog.dismiss()
                    finish()
                }
            }

            override fun onFailure(call: Call<RegisterUser>, t: Throwable) {
                Toast.makeText(this@RegisterActivity, "gagal", Toast.LENGTH_LONG).show()
                progressDialog.dismiss()
            }
        })
    }

    fun getSubAdmin(onFinish : () -> Unit){
        API.dataSubAdmin().enqueue(object : Callback<ArrayList<DataSubAdmin>>{
            override fun onResponse(call: Call<ArrayList<DataSubAdmin>>?, response: Response<ArrayList<DataSubAdmin>>?) {
                if (response!!.code() == 200){
                    sub_admin = ArrayList()
                    sub_admin?.add(0, null)
                    response.body()?.forEach { sub_admin?.add(it) }
                    val adapter = CustomAdapter<DataSubAdmin?>(this@RegisterActivity,
                            R.layout.spinner_custom, R.layout.spinner_dropdown_item, sub_admin?.toTypedArray()!!)

                    Log.d("subAdmin", sub_admin.toString())

                    spn_sub_admin.adapter = adapter
                    onFinish()
                }else{
                    Toast.makeText(this@RegisterActivity, "Error1", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<ArrayList<DataSubAdmin>>?, t: Throwable?) {
                Toast.makeText(this@RegisterActivity, "Error2", Toast.LENGTH_LONG).show()
            }

        })
    }

    class CustomAdapter<T>(context: RegisterActivity, val viewResourceId: Int, val dropDownReourceId: Int, val list: Array<T>) : ArrayAdapter<T>(context, viewResourceId, dropDownReourceId, list) {

        internal var layoutInflater: LayoutInflater = context.layoutInflater

        override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
            return getCustomView(position, convertView, parent, dropDownReourceId)
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            return getCustomView(position, convertView, parent, viewResourceId)
        }


        fun getCustomView(position: Int, convertView: View?, parent: ViewGroup?, resourceId: Int): View {

            var view = convertView

            if (view == null) {
                view = layoutInflater.inflate(resourceId, parent, false)
            }

            val textView = view as? TextView
            if (list[position] != null) {
                textView?.text = list[position].toString()
            } else {
                textView?.text = "Pilih Admin"
            }

            return view!!
        }

        override fun isEnabled(position: Int): Boolean {
            return position != 0
        }

    }

}
