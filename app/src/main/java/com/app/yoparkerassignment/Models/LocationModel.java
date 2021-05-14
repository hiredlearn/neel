package com.app.yoparkerassignment.Models;

public class LocationModel {
    double latitude;
    double longitude;
    String City;
    String Country;
    String Address;
    String State;
    String postal_code;
    double Cur_latitude;
    double Cur_longitude;

    public int getLocationType() {
        return LocationType;
    }

    public void setLocationType(int locationType) {
        LocationType = locationType;
    }

    int LocationType; // 0 = current, 1 = search

    public double getCur_latitude() {
        return Cur_latitude;
    }

    public void setCur_latitude(double cur_latitude) {
        Cur_latitude = cur_latitude;
    }

    public double getCur_longitude() {
        return Cur_longitude;
    }

    public void setCur_longitude(double cur_longitude) {
        Cur_longitude = cur_longitude;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }



    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }
}
