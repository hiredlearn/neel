package com.app.yoparkerassignment.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {

	@SerializedName("upcoming_booking")
	private List<UpcomingBookingItem> upcomingBooking;

	@SerializedName("past_booking")
	private List<PastBookingItem> pastBooking;

	public void setUpcomingBooking(List<UpcomingBookingItem> upcomingBooking){
		this.upcomingBooking = upcomingBooking;
	}

	public List<UpcomingBookingItem> getUpcomingBooking(){
		return upcomingBooking;
	}

	public void setPastBooking(List<PastBookingItem> pastBooking){
		this.pastBooking = pastBooking;
	}

	public List<PastBookingItem> getPastBooking(){
		return pastBooking;
	}
}