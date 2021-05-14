package com.app.yoparkerassignment.Models;

import com.google.gson.annotations.SerializedName;

public class ImagesItem {

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("name")
	private Object name;

	@SerializedName("parking_id")
	private String parkingId;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private String id;

	@SerializedName("url")
	private String url;

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setName(Object name){
		this.name = name;
	}

	public Object getName(){
		return name;
	}

	public void setParkingId(String parkingId){
		this.parkingId = parkingId;
	}

	public String getParkingId(){
		return parkingId;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}
}