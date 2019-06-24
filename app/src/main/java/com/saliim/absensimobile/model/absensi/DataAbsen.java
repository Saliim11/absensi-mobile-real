package com.saliim.absensimobile.model.absensi;

import com.google.gson.annotations.SerializedName;

public class DataAbsen{

	@SerializedName("status_absen")
	private String statusAbsen;

	@SerializedName("nama")
	private String nama;

	@SerializedName("lokasi")
	private String lokasi;

	@SerializedName("created")
	private String created;

	@SerializedName("id")
	private String id;

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

	public void setLokasi(String lokasi){
		this.lokasi = lokasi;
	}

	public String getLokasi(){
		return lokasi;
	}

	public void setCreated(String created){
		this.created = created;
	}

	public String getCreated(){
		return created;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	@Override
 	public String toString(){
		return 
			"DataAbsen{" + 
			"status_absen = '" + statusAbsen + '\'' + 
			",nama = '" + nama + '\'' + 
			",lokasi = '" + lokasi + '\'' + 
			",created = '" + created + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}