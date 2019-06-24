package com.saliim.absensimobile.model.registerUser;

import com.google.gson.annotations.SerializedName;

public class RegisterUser{

	@SerializedName("vsusername")
	private String vsusername;

	@SerializedName("nama")
	private String nama;

	@SerializedName("vslevel")
	private String vslevel;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private String status;

	public void setVsusername(String vsusername){
		this.vsusername = vsusername;
	}

	public String getVsusername(){
		return vsusername;
	}

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setVslevel(String vslevel){
		this.vslevel = vslevel;
	}

	public String getVslevel(){
		return vslevel;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
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
			"RegisterUser{" + 
			"vsusername = '" + vsusername + '\'' + 
			",nama = '" + nama + '\'' + 
			",vslevel = '" + vslevel + '\'' + 
			",message = '" + message + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}