package com.app.yoparkerassignment.Models;

import com.google.gson.annotations.SerializedName;

public class VehicleGeneral {

	@SerializedName("status_code")
	private int statusCode;

	@SerializedName("data")
	private DataVehicle data;

	public void setStatusCode(int statusCode){
		this.statusCode = statusCode;
	}

	public int getStatusCode(){
		return statusCode;
	}

	public void setData(DataVehicle data){
		this.data = data;
	}

	public DataVehicle getData(){
		return data;
	}
}