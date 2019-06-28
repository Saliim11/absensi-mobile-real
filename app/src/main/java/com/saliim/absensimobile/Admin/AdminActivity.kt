package com.saliim.absensimobile.Admin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import android.view.MenuItem
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.saliim.absensimobile.R
import com.saliim.absensimobile.adapter.AbsensiAdapter
import com.saliim.absensimobile.api.API
import com.saliim.absensimobile.loginRegister.LoginActivity
import com.saliim.absensimobile.model.absensi.DataAbsen
import kotlinx.android.synthetic.main.content_admin.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.jvm.java

class AdminActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var toolbar: Toolbar? = null

    var dataAbsensis: ArrayList<DataAbsen>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = "Admin Activity"

        getDataAbsen()

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.admin, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.add_location -> {
                startActivity(Intent(this@AdminActivity, TambahLokasiActivity::class.java))
            }
            R.id.logout -> {
                val builder = AlertDialog.Builder(this@AdminActivity)
                builder.setMessage("Yakin ingin logout?")
                builder.setPositiveButton("Iya"){dialog, which ->

                    startActivity(Intent(this@AdminActivity, LoginActivity::class.java))
                    finish()
                }
                builder.setNegativeButton("Tidak"){dialog, which ->
                    dialog.dismiss()
                }
                val dialog: AlertDialog = builder.create()
                dialog.show()
            }
            R.id.add_admin -> {
                startActivity(Intent(this@AdminActivity, RegisterAdminActivity::class.java))
            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun getDataAbsen() {
        API.dataAbsensi().enqueue(object : Callback<ArrayList<DataAbsen>> {
            override fun onResponse(call: Call<ArrayList<DataAbsen>>, response: Response<ArrayList<DataAbsen>>) {
                if (response.code() == 200){
                    dataAbsensis = response.body()
                    Log.i("dataAbsen", "" + dataAbsensis)

                    if (dataAbsensis == null){
                        Toast.makeText(this@AdminActivity, "Data Kosong", Toast.LENGTH_SHORT).show()
                    } else {
                        recycler_absensi?.setHasFixedSize(true)
                        recycler_absensi?.layoutManager = LinearLayoutManager(this@AdminActivity)
                        recycler_absensi?.adapter = AbsensiAdapter(dataAbsensis)
                    }
                } else {
                    Toast.makeText(this@AdminActivity, "gagal mendapatkan data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ArrayList<DataAbsen>>, t: Throwable) {
                Toast.makeText(this@AdminActivity, "gagal", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
