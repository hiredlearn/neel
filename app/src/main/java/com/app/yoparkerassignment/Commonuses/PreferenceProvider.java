package com.app.yoparkerassignment.Commonuses;

import android.content.Context;

import com.app.yoparkerassignment.Models.LocationModel;

import org.json.JSONObject;

import io.paperdb.Paper;


public class PreferenceProvider {
    public PreferenceProvider(Context context) {
        Paper.init(context);
    }

    private String APP_DATA = "app_data";

    private String LOGIN_RESPONSE = "login_response";
    private String VERIFY_RESPONSE = "verify_login_response";
    private String LOCATION = "location_detail";
    private String PARKING_LOCATION = "parking_location_detail";
    private String ADD_PARKING = "add_parking";
    private String DEVICE_TOKEN = "device_token";

    public static String UserId = "UserId";
    public static String UserName = "UserName";
    public static String UserEmail = "UserEmail";
    public static String MobileNumber = "MobileNumber";

    public void SaveLoginResponse(String loginResponsestr) {
        Paper.book(APP_DATA).write(LOGIN_RESPONSE, loginResponsestr);
    }

    public String GetLoginResponse() {
        return Paper.book(APP_DATA).read(LOGIN_RESPONSE);
    }


    public void SaveLocationDetail(LocationModel locationModel) {
        Paper.book(APP_DATA).write(LOCATION, locationModel);
    }

    public LocationModel getLocationDetail() {
        return Paper.book(APP_DATA).read(LOCATION);
    }
 /*
    public void SaveParkingLocationDetail(AddParkingLocationModel locationModel){
        Paper.book(APP_DATA).write(PARKING_LOCATION,locationModel);
    }
    public AddParkingLocationModel getParkingLocationDetail(){
        return Paper.book(APP_DATA).read(PARKING_LOCATION);
    }

    public void SaveParkingDetail(AddParkingParam addParkingParam){
        Paper.book(APP_DATA).write(ADD_PARKING,addParkingParam);
    }
    public AddParkingParam getParkingDetail(){
        return Paper.book(APP_DATA).read(ADD_PARKING);
    }*/

    public void DeleteData() {
        Paper.book(APP_DATA).delete(LOGIN_RESPONSE);
    }

    public void SaveDeviceToken(String token) {
        Paper.book(APP_DATA).write(DEVICE_TOKEN, token);
    }

    public String GetDeviceToken() {
        return Paper.book(APP_DATA).read(DEVICE_TOKEN);
    }
}
