package com.app.yoparkerassignment.Models;

import com.google.gson.annotations.SerializedName;

public class VehicleModel {

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("name")
	private String name;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private String id;

	@SerializedName("deleted_at")
	private Object deletedAt;

	@SerializedName("slug")
	private String slug;

	@SerializedName("brand_id")
	private String brandId;

	@SerializedName("status")
	private String status;

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
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

	public void setDeletedAt(Object deletedAt){
		this.deletedAt = deletedAt;
	}

	public Object getDeletedAt(){
		return deletedAt;
	}

	public void setSlug(String slug){
		this.slug = slug;
	}

	public String getSlug(){
		return slug;
	}

	public void setBrandId(String brandId){
		this.brandId = brandId;
	}

	public String getBrandId(){
		return brandId;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}
}