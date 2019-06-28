package com.saliim.absensimobile.model.absensi;

import com.google.gson.annotations.SerializedName;

public class DataAbsenPerUser{

	@SerializedName("status_absen")
	private String statusAbsen;

	@SerializedName("nama")
	private String nama;

	@SerializedName("jam_masuk")
	private String jamMasuk;

	@SerializedName("jam_pulang")
	private String jamPulang;

	@SerializedName("lokasi")
	private String lokasi;

	@SerializedName("id_user")
	private String idUser;

	@SerializedName("id_absen")
	private String idAbsen;

	@SerializedName("gambar")
	private String gambar;

	public void setStatusAbsen(String statusAbsen){
		this.statusAbsen = statusAbsen;
	}

	public String getStatusAbsen(){
		return statusAbsen;
	}

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setJamMasuk(String jamMasuk){
		this.jamMasuk = jamMasuk;
	}

	public String getJamMasuk(){
		return jamMasuk;
	}

	public void setJamPulang(String jamPulang){
		this.jamPulang = jamPulang;
	}

	public String getJamPulang(){
		return jamPulang;
	}

	public void setLokasi(String lokasi){
		this.lokasi = lokasi;
	}

	public String getLokasi(){
		return lokasi;
	}

	public void setIdUser(String idUser){
		this.idUser = idUser;
	}

	public String getIdUser(){
		return idUser;
	}

	public void setIdAbsen(String idAbsen){
		this.idAbsen = idAbsen;
	}

	public String getIdAbsen(){
		return idAbsen;
	}

	public void setGambar(String gambar){
		this.gambar = gambar;
	}

	public String getGambar(){
		return gambar;
	}

	@Override
 	public String toString(){
		return 
			"DataAbsenPerUser{" + 
			"status_absen = '" + statusAbsen + '\'' + 
			",nama = '" + nama + '\'' + 
			",jam_masuk = '" + jamMasuk + '\'' + 
			",jam_pulang = '" + jamPulang + '\'' + 
			",lokasi = '" + lokasi + '\'' + 
			",id_user = '" + idUser + '\'' + 
			",id_absen = '" + idAbsen + '\'' + 
			",gambar = '" + gambar + '\'' + 
			"}";
		}
}