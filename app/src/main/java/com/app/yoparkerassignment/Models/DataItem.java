package com.app.yoparkerassignment.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataItem {

	@SerializedName("sale_id")
	private Object saleId;

	@SerializedName("country")
	private String country;

	@SerializedName("parking_type_id")
	private String parkingTypeId;

	@SerializedName("bike_daily_applicable")
	private int bikeDailyApplicable;

	@SerializedName("bike_monthly_applicable")
	private int bikeMonthlyApplicable;

	@SerializedName("zip_code")
	private String zipCode;

	@SerializedName("car_hourly_applicable")
	private int carHourlyApplicable;

	@SerializedName("bike_price_month")
	private int bikePriceMonth;

	@SerializedName("id")
	private String id;

	@SerializedName("state")
	private String state;

	@SerializedName("car_daily_applicable")
	private int carDailyApplicable;

	@SerializedName("car_monthly_applicable")
	private int carMonthlyApplicable;

	@SerializedName("car_price_day")
	private int carPriceDay;

	@SerializedName("longitude")
	private String longitude;

	@SerializedName("images")
	private List<ImagesItem> images;

	public List<RecentReview> getRecent_reviews() {
		return recent_reviews;
	}

	public void setRecent_reviews(List<RecentReview> recent_reviews) {
		this.recent_reviews = recent_reviews;
	}

	@SerializedName("recent_reviews")
	private List<RecentReview> recent_reviews;

	@SerializedName("unique_id")
	private Object uniqueId;

	@SerializedName("opening_time")
	private String openingTime;

	@SerializedName("car_price_month")
	private int carPriceMonth;

	@SerializedName("deleted_at")
	private Object deletedAt;

	@SerializedName("car_space")
	private int carSpace;

	@SerializedName("bike_price_week")
	private int bikePriceWeek;

	@SerializedName("parking_category")
	private ParkingCategory parkingCategory;

	@SerializedName("user_id")
	private String userId;

	@SerializedName("name")
	private String name;

	@SerializedName("bike_weekly_applicable")
	private int bikeWeeklyApplicable;

	@SerializedName("available_bike_space")
	private Object availableBikeSpace;

	@SerializedName("car_min_hours")
	private int carMinHours;

	@SerializedName("status")
	private String status;

	@SerializedName("bike_hourly_applicable")
	private int bikeHourlyApplicable;

	@SerializedName("city")
	private String city;

	@SerializedName("available_car_space")
	private Object availableCarSpace;

	@SerializedName("latitude")
	private String latitude;

	@SerializedName("description")
	private String description;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("bike_price_hour")
	private int bikePriceHour;

	@SerializedName("car_price_hour")
	private int carPriceHour;

	@SerializedName("parking_type")
	private ParkingType parkingType;

	@SerializedName("bike_min_hours")
	private int bikeMinHours;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("closing_time")
	private String closingTime;

	@SerializedName("car_price_week")
	private int carPriceWeek;

	@SerializedName("address")
	private String address;

	@SerializedName("car_weekly_applicable")
	private int carWeeklyApplicable;

	@SerializedName("verified")
	private int verified;

	@SerializedName("parking_service_type")
	private Object parkingServiceType;

	@SerializedName("bike_space")
	private int bikeSpace;

	@SerializedName("parking_category_id")
	private String parkingCategoryId;

	@SerializedName("sales_user")
	private Object salesUser;

	@SerializedName("bike_price_day")
	private int bikePriceDay;

	@SerializedName("user")
	private User user;

	public String getDistance() {
		return Distance;
	}

	public void setDistance(String distance) {
		Distance = distance;
	}

	private String Distance;

	public void setSaleId(Object saleId){
		this.saleId = saleId;
	}

	public Object getSaleId(){
		return saleId;
	}

	public void setCountry(String country){
		this.country = country;
	}

	public String getCountry(){
		return country;
	}

	public void setParkingTypeId(String parkingTypeId){
		this.parkingTypeId = parkingTypeId;
	}

	public String getParkingTypeId(){
		return parkingTypeId;
	}

	public void setBikeDailyApplicable(int bikeDailyApplicable){
		this.bikeDailyApplicable = bikeDailyApplicable;
	}

	public int getBikeDailyApplicable(){
		return bikeDailyApplicable;
	}

	public void setBikeMonthlyApplicable(int bikeMonthlyApplicable){
		this.bikeMonthlyApplicable = bikeMonthlyApplicable;
	}

	public int getBikeMonthlyApplicable(){
		return bikeMonthlyApplicable;
	}

	public void setZipCode(String zipCode){
		this.zipCode = zipCode;
	}

	public String getZipCode(){
		return zipCode;
	}

	public void setCarHourlyApplicable(int carHourlyApplicable){
		this.carHourlyApplicable = carHourlyApplicable;
	}

	public int getCarHourlyApplicable(){
		return carHourlyApplicable;
	}

	public void setBikePriceMonth(int bikePriceMonth){
		this.bikePriceMonth = bikePriceMonth;
	}

	public int getBikePriceMonth(){
		return bikePriceMonth;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setState(String state){
		this.state = state;
	}

	public String getState(){
		return state;
	}

	public void setCarDailyApplicable(int carDailyApplicable){
		this.carDailyApplicable = carDailyApplicable;
	}

	public int getCarDailyApplicable(){
		return carDailyApplicable;
	}

	public void setCarMonthlyApplicable(int carMonthlyApplicable){
		this.carMonthlyApplicable = carMonthlyApplicable;
	}

	public int getCarMonthlyApplicable(){
		return carMonthlyApplicable;
	}

	public void setCarPriceDay(int carPriceDay){
		this.carPriceDay = carPriceDay;
	}

	public int getCarPriceDay(){
		return carPriceDay;
	}

	public void setLongitude(String longitude){
		this.longitude = longitude;
	}

	public String getLongitude(){
		return longitude;
	}

	public void setImages(List<ImagesItem> images){
		this.images = images;
	}

	public List<ImagesItem> getImages(){
		return images;
	}

	public void setUniqueId(Object uniqueId){
		this.uniqueId = uniqueId;
	}

	public Object getUniqueId(){
		return uniqueId;
	}

	public void setOpeningTime(String openingTime){
		this.openingTime = openingTime;
	}

	public String getOpeningTime(){
		return openingTime;
	}

	public void setCarPriceMonth(int carPriceMonth){
		this.carPriceMonth = carPriceMonth;
	}

	public int getCarPriceMonth(){
		return carPriceMonth;
	}

	public void setDeletedAt(Object deletedAt){
		this.deletedAt = deletedAt;
	}

	public Object getDeletedAt(){
		return deletedAt;
	}

	public void setCarSpace(int carSpace){
		this.carSpace = carSpace;
	}

	public int getCarSpace(){
		return carSpace;
	}

	public void setBikePriceWeek(int bikePriceWeek){
		this.bikePriceWeek = bikePriceWeek;
	}

	public int getBikePriceWeek(){
		return bikePriceWeek;
	}

	public void setParkingCategory(ParkingCategory parkingCategory){
		this.parkingCategory = parkingCategory;
	}

	public ParkingCategory getParkingCategory(){
		return parkingCategory;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return userId;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setBikeWeeklyApplicable(int bikeWeeklyApplicable){
		this.bikeWeeklyApplicable = bikeWeeklyApplicable;
	}

	public int getBikeWeeklyApplicable(){
		return bikeWeeklyApplicable;
	}

	public void setAvailableBikeSpace(Object availableBikeSpace){
		this.availableBikeSpace = availableBikeSpace;
	}

	public Object getAvailableBikeSpace(){
		return availableBikeSpace;
	}

	public void setCarMinHours(int carMinHours){
		this.carMinHours = carMinHours;
	}

	public int getCarMinHours(){
		return carMinHours;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	public void setBikeHourlyApplicable(int bikeHourlyApplicable){
		this.bikeHourlyApplicable = bikeHourlyApplicable;
	}

	public int getBikeHourlyApplicable(){
		return bikeHourlyApplicable;
	}

	public void setCity(String city){
		this.city = city;
	}

	public String getCity(){
		return city;
	}

	public void setAvailableCarSpace(Object availableCarSpace){
		this.availableCarSpace = availableCarSpace;
	}

	public Object getAvailableCarSpace(){
		return availableCarSpace;
	}

	public void setLatitude(String latitude){
		this.latitude = latitude;
	}

	public String getLatitude(){
		return latitude;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setBikePriceHour(int bikePriceHour){
		this.bikePriceHour = bikePriceHour;
	}

	public int getBikePriceHour(){
		return bikePriceHour;
	}

	public void setCarPriceHour(int carPriceHour){
		this.carPriceHour = carPriceHour;
	}

	public int getCarPriceHour(){
		return carPriceHour;
	}

	public void setParkingType(ParkingType parkingType){
		this.parkingType = parkingType;
	}

	public ParkingType getParkingType(){
		return parkingType;
	}

	public void setBikeMinHours(int bikeMinHours){
		this.bikeMinHours = bikeMinHours;
	}

	public int getBikeMinHours(){
		return bikeMinHours;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setClosingTime(String closingTime){
		this.closingTime = closingTime;
	}

	public String getClosingTime(){
		return closingTime;
	}

	public void setCarPriceWeek(int carPriceWeek){
		this.carPriceWeek = carPriceWeek;
	}

	public int getCarPriceWeek(){
		return carPriceWeek;
	}

	public void setAddress(String address){
		this.address = address;
	}

	public String getAddress(){
		return address;
	}

	public void setCarWeeklyApplicable(int carWeeklyApplicable){
		this.carWeeklyApplicable = carWeeklyApplicable;
	}

	public int getCarWeeklyApplicable(){
		return carWeeklyApplicable;
	}

	public void setVerified(int verified){
		this.verified = verified;
	}

	public int getVerified(){
		return verified;
	}

	public void setParkingServiceType(Object parkingServiceType){
		this.parkingServiceType = parkingServiceType;
	}

	public Object getParkingServiceType(){
		return parkingServiceType;
	}

	public void setBikeSpace(int bikeSpace){
		this.bikeSpace = bikeSpace;
	}

	public int getBikeSpace(){
		return bikeSpace;
	}

	public void setParkingCategoryId(String parkingCategoryId){
		this.parkingCategoryId = parkingCategoryId;
	}

	public String getParkingCategoryId(){
		return parkingCategoryId;
	}

	public void setSalesUser(Object salesUser){
		this.salesUser = salesUser;
	}

	public Object getSalesUser(){
		return salesUser;
	}

	public void setBikePriceDay(int bikePriceDay){
		this.bikePriceDay = bikePriceDay;
	}

	public int getBikePriceDay(){
		return bikePriceDay;
	}

	public void setUser(User user){
		this.user = user;
	}

	public User getUser(){
		return user;
	}





	public DataItem() {
	}




}