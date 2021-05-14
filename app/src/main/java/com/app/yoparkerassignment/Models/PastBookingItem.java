package com.app.yoparkerassignment.Models;

import com.google.gson.annotations.SerializedName;

public class PastBookingItem {

	@SerializedName("end_date")
	private String endDate;

	@SerializedName("parking")
	private Parking parking;

	@SerializedName("user_email")
	private Object userEmail;

	@SerializedName("coupon_code")
	private Object couponCode;

	@SerializedName("discount_amount")
	private String discountAmount;

	@SerializedName("user_name")
	private Object userName;

	@SerializedName("canceled_at")
	private Object canceledAt;

	@SerializedName("otp_expired_at")
	private Object otpExpiredAt;

	@SerializedName("duration_type")
	private String durationType;

	@SerializedName("space_type")
	private String spaceType;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("deleted_at")
	private Object deletedAt;

	@SerializedName("vehicle")
	private Vehicle vehicle;

	@SerializedName("is_mail_sent")
	private int isMailSent;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("user_id")
	private String userId;

	@SerializedName("total_amount")
	private String totalAmount;

	@SerializedName("parking_id")
	private String parkingId;

	@SerializedName("user_phone")
	private Object userPhone;

	@SerializedName("id")
	private String id;

	@SerializedName("is_msg_sent")
	private int isMsgSent;

	@SerializedName("vehicle_id")
	private String vehicleId;

	@SerializedName("start_date")
	private String startDate;

	@SerializedName("status")
	private String status;

	public void setEndDate(String endDate){
		this.endDate = endDate;
	}

	public String getEndDate(){
		return endDate;
	}

	public void setParking(Parking parking){
		this.parking = parking;
	}

	public Parking getParking(){
		return parking;
	}

	public void setUserEmail(Object userEmail){
		this.userEmail = userEmail;
	}

	public Object getUserEmail(){
		return userEmail;
	}

	public void setCouponCode(Object couponCode){
		this.couponCode = couponCode;
	}

	public Object getCouponCode(){
		return couponCode;
	}

	public void setDiscountAmount(String discountAmount){
		this.discountAmount = discountAmount;
	}

	public String getDiscountAmount(){
		return discountAmount;
	}

	public void setUserName(Object userName){
		this.userName = userName;
	}

	public Object getUserName(){
		return userName;
	}

	public void setCanceledAt(Object canceledAt){
		this.canceledAt = canceledAt;
	}

	public Object getCanceledAt(){
		return canceledAt;
	}

	public void setOtpExpiredAt(Object otpExpiredAt){
		this.otpExpiredAt = otpExpiredAt;
	}

	public Object getOtpExpiredAt(){
		return otpExpiredAt;
	}

	public void setDurationType(String durationType){
		this.durationType = durationType;
	}

	public String getDurationType(){
		return durationType;
	}

	public void setSpaceType(String spaceType){
		this.spaceType = spaceType;
	}

	public String getSpaceType(){
		return spaceType;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setDeletedAt(Object deletedAt){
		this.deletedAt = deletedAt;
	}

	public Object getDeletedAt(){
		return deletedAt;
	}

	public void setVehicle(Vehicle vehicle){
		this.vehicle = vehicle;
	}

	public Vehicle getVehicle(){
		return vehicle;
	}

	public void setIsMailSent(int isMailSent){
		this.isMailSent = isMailSent;
	}

	public int getIsMailSent(){
		return isMailSent;
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

	public void setTotalAmount(String totalAmount){
		this.totalAmount = totalAmount;
	}

	public String getTotalAmount(){
		return totalAmount;
	}

	public void setParkingId(String parkingId){
		this.parkingId = parkingId;
	}

	public String getParkingId(){
		return parkingId;
	}

	public void setUserPhone(Object userPhone){
		this.userPhone = userPhone;
	}

	public Object getUserPhone(){
		return userPhone;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setIsMsgSent(int isMsgSent){
		this.isMsgSent = isMsgSent;
	}

	public int getIsMsgSent(){
		return isMsgSent;
	}

	public void setVehicleId(String vehicleId){
		this.vehicleId = vehicleId;
	}

	public String getVehicleId(){
		return vehicleId;
	}

	public void setStartDate(String startDate){
		this.startDate = startDate;
	}

	public String getStartDate(){
		return startDate;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}
}