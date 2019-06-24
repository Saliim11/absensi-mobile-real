package com.saliim.absensimobile.model.lokasi;

import com.google.gson.annotations.SerializedName;

public class InsertLokasi{

	@SerializedName("nama")
	private String nama;

	@SerializedName("latitude")
	private String latitude;

	@SerializedName("message")
	private String message;

	@SerializedName("radius")
	private String radius;

	@SerializedName("status")
	private String status;

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setLatitude(String latitude){
		this.latitude = latitude;
	}

	public String getLatitude(){
		return latitude;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setRadius(String radius){
		this.radius = radius;
	}

	public String getRadius(){
		return radius;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"InsertLokasi{" + 
			"nama = '" + nama + '\'' + 
			",latitude = '" + latitude + '\'' + 
			",message = '" + message + '\'' + 
			",radius = '" + radius + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}