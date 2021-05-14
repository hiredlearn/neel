package com.app.yoparkerassignment.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataVehicle {

	@SerializedName("models")
	private List<ModelsItem> models;

	@SerializedName("types")
	private List<TypesItem> types;

	@SerializedName("brands")
	private List<BrandsItem> brands;

	@SerializedName("categories")
	private List<CategoriesItem> categories;

	@SerializedName("colors")
	private List<ColorsItem> colors;

	public void setModels(List<ModelsItem> models){
		this.models = models;
	}

	public List<ModelsItem> getModels(){
		return models;
	}

	public void setTypes(List<TypesItem> types){
		this.types = types;
	}

	public List<TypesItem> getTypes(){
		return types;
	}

	public void setBrands(List<BrandsItem> brands){
		this.brands = brands;
	}

	public List<BrandsItem> getBrands(){
		return brands;
	}

	public void setCategories(List<CategoriesItem> categories){
		this.categories = categories;
	}

	public List<CategoriesItem> getCategories(){
		return categories;
	}

	public void setColors(List<ColorsItem> colors){
		this.colors = colors;
	}

	public List<ColorsItem> getColors(){
		return colors;
	}
}