package com.saliim.absensimobile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.saliim.absensimobile.admin.AdminActivity
import com.saliim.absensimobile.loginRegister.LoginActivity
import com.saliim.absensimobile.loginRegister.LoginActivity.Companion.MY_LOGIN_PREF
import com.saliim.absensimobile.loginRegister.LoginActivity.Companion.MY_LOGIN_PREF_KEY
import com.saliim.absensimobile.model.loginUser.LoginUser
import com.saliim.absensimobile.subAdmin.SubAdminActivity
import java.util.*

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
            val savedUser = Gson().fromJson(getSharedPreferences(MY_LOGIN_PREF, Context.MODE_PRIVATE).getString(MY_LOGIN_PREF_KEY, ""), LoginUser::class.java)

            if (savedUser != null && savedUser.status == true) {
                if (savedUser.user.vslevel.equals("normal user")){
                    startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
                    finish()
                }else if (savedUser.user.vslevel.equals("subAdmin")){
                    startActivity(Intent(this@SplashScreenActivity, SubAdminActivity::class.java))
                    finish()
                }else{
                    startActivity(Intent(this@SplashScreenActivity, AdminActivity::class.java))
                    finish()
                }
            }
            else {
                val i = Intent(this@SplashScreenActivity, LoginActivity::class.java)
                startActivity(i)
                finish()
            }
        }, TIME_OUT.toLong())
    }

    companion object {
        val TIME_OUT = 2000
    }
}