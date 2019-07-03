package com.saliim.absensimobile.model.absensi

import com.google.gson.annotations.SerializedName

class DataAbsenPerIdAdmin {

    @SerializedName("status_absen")
    var statusAbsen: String? = null

    @SerializedName("nama")
    var nama: String? = ""

    @SerializedName("jam_masuk")
    var jamMasuk: String? = null

    @SerializedName("jam_pulang")
    var jamPulang: String? = null

    @SerializedName("lokasi")
    var lokasi: String? = null

    @SerializedName("id_absen")
    var idAbsen: String? = null

    @SerializedName("id_user")
    var idUser: String? = null

    @SerializedName("gambar")
    var gambar: String? = null

    override fun toString(): String {
        return this.nama!!
    }
}