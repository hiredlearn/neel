package com.app.yoparkerassignment.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.view.View;
import android.widget.LinearLayout;

import com.app.yoparkerassignment.Commonuses.CommonAlertDialog;
import com.app.yoparkerassignment.Commonuses.PreferenceProvider;
import com.app.yoparkerassignment.LocationService.LocationAddress;
import com.app.yoparkerassignment.LocationService.LocationUpdatesService;
import com.app.yoparkerassignment.Models.LocationModel;
import com.app.yoparkerassignment.R;
import com.app.yoparkerassignment.home.HomeActivity;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

import static com.app.yoparkerassignment.Commonuses.PreferenceProvider.MobileNumber;
import static com.app.yoparkerassignment.Commonuses.PreferenceProvider.UserEmail;
import static com.app.yoparkerassignment.Commonuses.PreferenceProvider.UserId;
import static com.app.yoparkerassignment.Commonuses.PreferenceProvider.UserName;

@RuntimePermissions
public class SelectLocationActivity extends AppCompatActivity implements View.OnClickListener, SharedPreferences.OnSharedPreferenceChangeListener {

    private LinearLayout Current_Location_Btn, OtherLocationBTN;
    public static int AUTOCOMPLETE_REQUEST_CODE = 99;
    private static final String TAG = SelectLocationActivity.class.getSimpleName();
    private static final int REQUEST_PERMISSIONS_REQUEST_CODE = 34;
    public static int CURRENT_LOCATION = 0;
    public static int SEARCH_LOCATION = 1;
    private MyReceiver myReceiver;
    // A reference to the service used to get location updates.
    private LocationUpdatesService mService = null;

    // Tracks the bound state of the service.
    private boolean mBound = false;
    private AlertDialog alertDialog;
    private PreferenceProvider preferenceProvider;
    private int LocationType;  // Current location or Search location
    private LocationModel locationModel;

    private final ServiceConnection mServiceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            LocationUpdatesService.LocalBinder binder = (LocationUpdatesService.LocalBinder) service;
            mService = binder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mService = null;
            mBound = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_location);
        locationModel = new LocationModel();
        preferenceProvider = new PreferenceProvider(this);
        myReceiver = new MyReceiver();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        Current_Location_Btn = findViewById(R.id.Current_Location_Btn);
        OtherLocationBTN = findViewById(R.id.OtherLocationBTN);
        Current_Location_Btn.setOnClickListener(this);
        OtherLocationBTN.setOnClickListener(this);
        String apiKey = getString(R.string.API_KEY);
        if (!Places.isInitialized()) {
            Places.initialize(getApplicationContext(), apiKey);
        }
        PlacesClient placesClient = Places.createClient(this);
        alertDialog = CommonAlertDialog.CreateDialog(SelectLocationActivity.this, "Please wait...");

    }

    public void onSearchCalled() {
        // Set the fields to specify which types of place data to return.
        List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS, Place.Field.LAT_LNG);
        // Start the autocomplete intent.
        Intent intent = new Autocomplete.IntentBuilder(
                AutocompleteActivityMode.FULLSCREEN, fields).setCountry("IN") //India
                .build(this);
        startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.Current_Location_Btn:
                //  startActivity(new Intent(SelectLocationActivity.this, LoginActivity.class));
                /*LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
                boolean gps_enabled = false;
                try {
                    gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
                } catch(Exception ex) {
                    ex.printStackTrace();
                }*/
                if (isLocationEnabled(this)) {
                    LocationType = CURRENT_LOCATION;
                    locationModel.setLocationType(LocationType);
                    SelectLocationActivityPermissionsDispatcher.locationWithPermissionCheck(this);
                } else {
                    new AlertDialog.Builder(SelectLocationActivity.this)
                            .setMessage("Location service not enable, Please press 'Setting' for enable Location service")
                            .setPositiveButton("Setting", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                                    startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                                }
                            })
                            .setNegativeButton("Cancel", null)
                            .show();
                }

                break;
            case R.id.OtherLocationBTN:
                // startActivity(new Intent(SelectLocationActivity.this, LoginActivity.class));
                LocationType = SEARCH_LOCATION;
                locationModel.setLocationType(LocationType);
                onSearchCalled();
                break;
        }
    }


    private class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Location location = intent.getParcelableExtra(LocationUpdatesService.EXTRA_LOCATION);
            if (location != null) {

                mService.removeLocationUpdates();
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                if (LocationType == CURRENT_LOCATION) {
                    locationModel.setCur_latitude(latitude);
                    locationModel.setCur_longitude(longitude);
                    locationModel.setLatitude(latitude);
                    locationModel.setLongitude(longitude);
                } else {
                    locationModel.setLatitude(latitude);
                    locationModel.setLongitude(longitude);
                }

                LocationAddress locationAddress = new LocationAddress();
                locationAddress.getAddressFromLocation(latitude, longitude,
                        getApplicationContext(), new GeocoderHandler());

                /*Toast.makeText(SelectLocationActivity.this, Utils.getLocationText(location),
                        Toast.LENGTH_SHORT).show();*/
            }
        }
    }

    public static Boolean isLocationEnabled(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
// This is new method provided in API 28
            LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            return lm.isLocationEnabled();
        } else {
// This is Deprecated in API 28
            int mode = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.LOCATION_MODE,
                    Settings.Secure.LOCATION_MODE_OFF);
            return (mode != Settings.Secure.LOCATION_MODE_OFF);

        }
    }

    private class GeocoderHandler extends Handler {
        @Override
        public void handleMessage(Message message) {

            String locationAddress, city = "", state = "", postal_code = "", country = "", address = "";
            switch (message.what) {
                case 1:
                    Bundle bundle = message.getData();

                    city = bundle.getString("city");
                    state = bundle.getString("state");
                    postal_code = bundle.getString("postal_code");
                    country = bundle.getString("country");
                    address = bundle.getString("address");
                    break;
                default:
                    locationAddress = null;
            }
            locationModel.setCity(city);
            locationModel.setAddress(address);
            locationModel.setCountry(country);
            locationModel.setState(state);
            locationModel.setPostal_code(postal_code);
            preferenceProvider.SaveLocationDetail(locationModel);
            if (alertDialog != null) {
                if (alertDialog.isShowing()) {
                    alertDialog.dismiss();
                }
            }
            startActivity(new Intent(SelectLocationActivity.this, HomeActivity.class));

            try {
                JSONObject userObject = new JSONObject();
                userObject.put(UserId, "1234");
                userObject.put(UserName, "Neel Mevada");
                userObject.put(MobileNumber, "+91-8320079300");
                userObject.put(UserEmail, "neelmevada93@gmail.com");
                preferenceProvider.SaveLoginResponse(userObject.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
            finish();
            // tvAddress.setText(locationAddress);
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {

    }

    @Override
    protected void onStart() {
        super.onStart();
        PreferenceManager.getDefaultSharedPreferences(this)
                .registerOnSharedPreferenceChangeListener(this);
        // Bind to the service. If the service is in foreground mode, this signals to the service
        // that since this activity is in the foreground, the service can exit foreground mode.
        bindService(new Intent(this, LocationUpdatesService.class), mServiceConnection,
                Context.BIND_AUTO_CREATE);
    }
    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(myReceiver,
                new IntentFilter(LocationUpdatesService.ACTION_BROADCAST));
    }
    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(myReceiver);
        super.onPause();
    }
    @Override
    protected void onStop() {
        if (mBound) {
            // Unbind from the service. This signals to the service that this activity is no longer
            // in the foreground, and the service can respond by promoting itself to a foreground
            // service.
            unbindService(mServiceConnection);
            mBound = false;
        }
        PreferenceManager.getDefaultSharedPreferences(this)
                .unregisterOnSharedPreferenceChangeListener(this);
        super.onStop();
    }


    @NeedsPermission({Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION})
    void location() {
        alertDialog.show();
        mService.requestLocationUpdates();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        SelectLocationActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @OnShowRationale({Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION})
    void location(final PermissionRequest request) {
    }




}