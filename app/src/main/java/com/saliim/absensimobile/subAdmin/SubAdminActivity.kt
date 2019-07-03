package com.saliim.absensimobile.subAdmin

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import com.saliim.absensimobile.R
import com.saliim.absensimobile.adapter.AbsensiPerAdminIdAdapter
import com.saliim.absensimobile.api.API
import com.saliim.absensimobile.loginRegister.LoginActivity
import com.saliim.absensimobile.model.absensi.DataAbsenPerIdAdmin
import com.saliim.absensimobile.model.users.DataUsersBySubAdmin
import kotlinx.android.synthetic.main.content_sub_admin.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SubAdminActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    var dataAbsensis: ArrayList<DataAbsenPerIdAdmin>? = null
    var dataNama: ArrayList<DataUsersBySubAdmin?>?= null

    var bulan: String? = ""
    var nama: String? = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub_admin)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = "SubAdmin "+LoginActivity.name

//        val adapter : ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(
//                this@SubAdminActivity, R.array.bulan_isi, android.R.layout.simple_spinner_item)
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spn_filter_bulan_sa.adapter = adapter

        val items = arrayOf("Bln", "Jan", "Feb", "Mar", "Apr", "Mei", "Jun", "Jul", "Ags", "Sep", "Okt", "Nov", "Des")
        val items_value = arrayOf<String>("","01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items)
        spn_filter_bulan_sa.adapter = adapter

        spn_filter_bulan_sa.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                    val isiBulan = items_value[position]
                    bulan = isiBulan
                    Log.d("isiBulan", isiBulan)

                    val prefs = this@SubAdminActivity.getSharedPreferences("X", android.content.Context.MODE_PRIVATE)
                    val editor = prefs.edit()
                    editor.putString("id_admin", bulan)
                    editor.commit()

            }
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        spn_filter_nama_sa.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                    val isiNama = dataNama?.get(position)?.id
                    nama = isiNama
//                Log.d("isiNama", isiNama)
                    if(isiNama.equals(null)){
                        nama = ""
                    }
                    val prefs = this@SubAdminActivity.getSharedPreferences("X", android.content.Context.MODE_PRIVATE)
                    val editor = prefs.edit()
                    editor.putString("id_admin", nama)
                    editor.commit()

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                nama = ""

            }
        }

        btn_filter_sa.setOnClickListener {
//            var spn_bulan = spn_filter_bulan_sa.selectedItem
//            var spn_nama = spn_filter_nama_sa.selectedItem

            getAbsen()

        }

        getNama {}
        getAbsen()

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout_sub_admin)
        val navView: NavigationView = findViewById(R.id.nav_view_subAdmin)
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
            R.id.logout_sub_admin -> {
                val builder = AlertDialog.Builder(this@SubAdminActivity)
                builder.setMessage("Yakin ingin logout?")
                builder.setPositiveButton("Iya"){dialog, which ->


                    startActivity(Intent(this@SubAdminActivity, LoginActivity::class.java))
                    finish()
                }
                builder.setNegativeButton("Tidak"){dialog, which ->
                    dialog.dismiss()
                }
                val dialog: AlertDialog = builder.create()
                dialog.show()
            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout_sub_admin)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun getAbsen() {
        val progressDialog = ProgressDialog(this@SubAdminActivity)
        progressDialog.setMessage("memuat data......")
        progressDialog.show()

        val id_admin = LoginActivity.id

        API.dataAbsenPerAdminId(id_admin, nama, bulan).enqueue(object : Callback<ArrayList<DataAbsenPerIdAdmin>>{
            override fun onResponse(call: Call<ArrayList<DataAbsenPerIdAdmin>>?, response: Response<ArrayList<DataAbsenPerIdAdmin>>?) {
                if (response!!.code() == 200){
                    dataAbsensis = response.body()
                    Log.d("data absen per admin id", dataAbsensis.toString())

                    if (dataAbsensis!!.isEmpty()){
                        Toast.makeText(this@SubAdminActivity, "Data Kosong", Toast.LENGTH_SHORT).show()
                        progressDialog.dismiss()
                    }else{
                        recycler_absensi_per_admin_id?.hasFixedSize()
                        recycler_absensi_per_admin_id?.layoutManager = LinearLayoutManager(this@SubAdminActivity)
                        recycler_absensi_per_admin_id?.adapter = AbsensiPerAdminIdAdapter(dataAbsensis)
                        progressDialog.dismiss()
                    }
                }else{
                    Toast.makeText(this@SubAdminActivity, "gagal mendapatkan data1", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ArrayList<DataAbsenPerIdAdmin>>?, t: Throwable?) {
                Toast.makeText(this@SubAdminActivity, "gagal mendapatkan data2", Toast.LENGTH_SHORT).show()
                progressDialog.dismiss()
            }
        })
    }

    fun getNama(onFinish : () -> Unit){
        val id_admin = LoginActivity.id

        API.dataUserPerAdminId(id_admin).enqueue(object : Callback<ArrayList<DataUsersBySubAdmin>>{
            override fun onResponse(call: Call<ArrayList<DataUsersBySubAdmin>>?, response: Response<ArrayList<DataUsersBySubAdmin>>?) {
                if (response!!.code() == 200){
                    dataNama = ArrayList()
                    dataNama?.add(0, null)
                    response.body()?.forEach { dataNama?.add(it) }
                    val adapter = CustomAdapter<DataUsersBySubAdmin?>(this@SubAdminActivity,
                            R.layout.spinner_custom, R.layout.spinner_dropdown_item, dataNama?.toTypedArray()!!)

                    Log.d("data nama", dataNama.toString())

                    spn_filter_nama_sa.adapter = adapter
                    onFinish
                }else{
                    Toast.makeText(this@SubAdminActivity, "Error1", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<ArrayList<DataUsersBySubAdmin>>?, t: Throwable?) {
                Toast.makeText(this@SubAdminActivity, "Error2", Toast.LENGTH_LONG).show()
            }
        })
    }

    class CustomAdapter<T>(context: SubAdminActivity, val viewResourceId: Int, val dropDownReourceId: Int, val list: Array<T>) : ArrayAdapter<T>(context, viewResourceId, dropDownReourceId, list) {

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
                textView?.text = "Semua"
            }

            return view!!
        }

    }


}
