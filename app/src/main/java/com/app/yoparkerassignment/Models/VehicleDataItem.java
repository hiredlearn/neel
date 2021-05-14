package com.app.yoparkerassignment.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VehicleDataItem {

	@SerializedName("images")
	private List<VehicleImagesItem> images;

	@SerializedName("color")
	private String color;

	@SerializedName("type_id")
	private String typeId;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("model_id")
	private String modelId;

	@SerializedName("type")
	private Type type;

	@SerializedName("deleted_at")
	private Object deletedAt;

	@SerializedName("colors")
	private Object colors;

	@SerializedName("brand_id")
	private String brandId;

	@SerializedName("number")
	private String number;

	@SerializedName("color_id")
	private Object colorId;

	@SerializedName("category_id")
	private String categoryId;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("user_id")
	private String userId;

	@SerializedName("model")
	private VehicleModel model;

	@SerializedName("id")
	private String id;

	@SerializedName("category")
	private Category category;

	@SerializedName("brand")
	private Brand brand;

	@SerializedName("status")
	private String status;

	public void setImages(List<VehicleImagesItem> images){
		this.images = images;
	}

	public List<VehicleImagesItem> getImages(){
		return images;
	}

	public void setColor(String color){
		this.color = color;
	}

	public String getColor(){
		return color;
	}

	public void setTypeId(String typeId){
		this.typeId = typeId;
	}

	public String getTypeId(){
		return typeId;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setModelId(String modelId){
		this.modelId = modelId;
	}

	public String getModelId(){
		return modelId;
	}

	public void setType(Type type){
		this.type = type;
	}

	public Type getType(){
		return type;
	}

	public void setDeletedAt(Object deletedAt){
		this.deletedAt = deletedAt;
	}

	public Object getDeletedAt(){
		return deletedAt;
	}

	public void setColors(Object colors){
		this.colors = colors;
	}

	public Object getColors(){
		return colors;
	}

	public void setBrandId(String brandId){
		this.brandId = brandId;
	}

	public String getBrandId(){
		return brandId;
	}

	public void setNumber(String number){
		this.number = number;
	}

	public String getNumber(){
		return number;
	}

	public void setColorId(Object colorId){
		this.colorId = colorId;
	}

	public Object getColorId(){
		return colorId;
	}

	public void setCategoryId(String categoryId){
		this.categoryId = categoryId;
	}

	public String getCategoryId(){
		return categoryId;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return userId;
	}

	public void setModel(VehicleModel model){
		this.model = model;
	}

	public VehicleModel getModel(){
		return model;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setCategory(Category category){
		this.category = category;
	}

	public Category getCategory(){
		return category;
	}

	public void setBrand(Brand brand){
		this.brand = brand;
	}

	public Brand getBrand(){
		return brand;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}
}