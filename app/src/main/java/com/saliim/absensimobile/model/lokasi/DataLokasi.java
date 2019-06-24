package com.saliim.absensimobile.model.lokasi;

import com.google.gson.annotations.SerializedName;

public class DataLokasi{

	@SerializedName("lokasi")
	private String lokasi;

	@SerializedName("latitude")
	private String latitude;

	@SerializedName("id")
	private String id;

	@SerializedName("radius")
	private String radius;

	@SerializedName("longitude")
	private String longitude;

	public void setLokasi(String lokasi){
		this.lokasi = lokasi;
	}

	public String getLokasi(){
		return lokasi;
	}

	public void setLatitude(String latitude){
		this.latitude = latitude;
	}

	public String getLatitude(){
		return latitude;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setRadius(String radius){
		this.radius = radius;
	}

	public String getRadius(){
		return radius;
	}

	public void setLongitude(String longitude){
		this.longitude = longitude;
	}

	public String getLongitude(){
		return longitude;
	}

	@Override
 	public String toString(){
		return 
			"DataLokasi{" + 
			"lokasi = '" + lokasi + '\'' + 
			",latitude = '" + latitude + '\'' + 
			",id = '" + id + '\'' + 
			",radius = '" + radius + '\'' + 
			",longitude = '" + longitude + '\'' + 
			"}";
		}
}