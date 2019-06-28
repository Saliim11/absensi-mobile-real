package com.saliim.absensimobile.model.users

import com.google.gson.annotations.SerializedName

class DataSubAdmin {

    @SerializedName("vsusername")
    var vsusername: String? = null

    @SerializedName("nama")
    var nama: String? = ""

    @SerializedName("id_admin")
    var idAdmin: String? = null

    @SerializedName("vspassword")
    var vspassword: String? = null

    @SerializedName("vslevel")
    var vslevel: String? = null

    @SerializedName("id")
    var id: String? = null

    override fun toString(): String {
        return this.nama!!
    }
}