package com.app.yoparkerassignment.Models;

import com.google.gson.annotations.SerializedName;

public class TypesItem {

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private String id;

	@SerializedName("slug")
	private String slug;

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

	public void setSlug(String slug){
		this.slug = slug;
	}

	public String getSlug(){
		return slug;
	}
}