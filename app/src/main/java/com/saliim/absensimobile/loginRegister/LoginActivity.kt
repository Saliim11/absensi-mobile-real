package com.saliim.absensimobile.loginRegister

import android.content.Context
import androidx.appcompat.app.AppCompatActivity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.google.gson.Gson

import com.saliim.absensimobile.admin.AdminActivity
import com.saliim.absensimobile.MainActivity
import com.saliim.absensimobile.R
import com.saliim.absensimobile.api.API
import com.saliim.absensimobile.model.loginUser.LoginUser
import com.saliim.absensimobile.subAdmin.SubAdminActivity

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etLoginUsername = findViewById<EditText>(R.id.et_login_username)
        val etLoginPassword = findViewById<EditText>(R.id.et_login_password)
        val loginSubmit = findViewById<Button>(R.id.btn_login_submit)
        val pindahReg = findViewById<TextView>(R.id.pindah_register)

        loginSubmit.setOnClickListener { v ->
            val inLogUn = etLoginUsername.text.toString()
            val inLogPw = etLoginPassword.text.toString()
            API.loginUsers(inLogUn, inLogPw).enqueue(object : Callback<LoginUser> {
                override fun onResponse(call: Call<LoginUser>, response: Response<LoginUser>) {
                    Log.d("login", response.body()!!.toString())

                    val user = response.body()

                    getSharedPreferences(MY_LOGIN_PREF, Context.MODE_PRIVATE).edit().putString(MY_LOGIN_PREF_KEY, Gson().toJson(user)).apply()

                    if (user!!.result == "1") {

                        val level = response.body()!!.user.vslevel

                        val savedUser = Gson().fromJson<LoginUser>(this@LoginActivity.getSharedPreferences(MY_LOGIN_PREF, Context.MODE_PRIVATE)
                                .getString(MY_LOGIN_PREF_KEY, ""), LoginUser::class.java)

                        id = savedUser.user.id
                        name = savedUser.user.nama

                        when (level) {
                            "normal user" -> {
                                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                                finish()
                            }
                            "admin" -> {
                                startActivity(Intent(this@LoginActivity, AdminActivity::class.java))
                                finish()
                            }
                            "subAdmin" -> {
                                startActivity(Intent(this@LoginActivity, SubAdminActivity::class.java))
                                finish()
                            }
                        }

                    } else {
                        Toast.makeText(this@LoginActivity, "periksa kembali name dan passwordmu", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<LoginUser>, t: Throwable) {
                    Toast.makeText(this@LoginActivity, "gagal", Toast.LENGTH_SHORT).show()
                }
            })
        }

        pindahReg.setOnClickListener { startActivity(Intent(this@LoginActivity, RegisterActivity::class.java)) }

    }

    companion object {
        lateinit var name: String
        lateinit var id: String

        const val MY_LOGIN_PREF = "myLoginPref"
        const val MY_LOGIN_PREF_KEY = "loginPrefKey"
    }
}
