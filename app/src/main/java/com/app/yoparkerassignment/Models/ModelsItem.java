package com.app.yoparkerassignment.Models;

import com.google.gson.annotations.SerializedName;

public class ModelsItem {

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private String id;

	@SerializedName("brand_id")
	private String brandId;

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setBrandId(String brandId){
		this.brandId = brandId;
	}

	public String getBrandId(){
		return brandId;
	}
}