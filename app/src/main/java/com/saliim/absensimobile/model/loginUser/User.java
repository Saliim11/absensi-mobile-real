package com.saliim.absensimobile.model.loginUser;

import com.google.gson.annotations.SerializedName;

public class User{

	@SerializedName("vsusername")
	private String vsusername;

	@SerializedName("nama")
	private String nama;

	@SerializedName("vspassword")
	private String vspassword;

	@SerializedName("vslevel")
	private String vslevel;

	@SerializedName("id")
	private String id;

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

	public void setVspassword(String vspassword){
		this.vspassword = vspassword;
	}

	public String getVspassword(){
		return vspassword;
	}

	public void setVslevel(String vslevel){
		this.vslevel = vslevel;
	}

	public String getVslevel(){
		return vslevel;
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
			"User{" + 
			"vsusername = '" + vsusername + '\'' + 
			",nama = '" + nama + '\'' + 
			",vspassword = '" + vspassword + '\'' + 
			",vslevel = '" + vslevel + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}