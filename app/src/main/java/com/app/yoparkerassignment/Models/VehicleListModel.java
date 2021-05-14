package com.app.yoparkerassignment.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VehicleListModel {

	@SerializedName("status_code")
	private int statusCode;

	@SerializedName("data")
	private List<VehicleDataItem> data;

	public void setStatusCode(int statusCode){
		this.statusCode = statusCode;
	}

	public int getStatusCode(){
		return statusCode;
	}

	public void setData(List<VehicleDataItem> data){
		this.data = data;
	}

	public List<VehicleDataItem> getData(){
		return data;
	}
}