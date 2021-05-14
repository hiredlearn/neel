package com.app.yoparkerassignment.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.app.yoparkerassignment.Commonuses.CommonAlertDialog;
import com.app.yoparkerassignment.Commonuses.PreferenceProvider;
import com.app.yoparkerassignment.Commonuses.TextClass;
import com.app.yoparkerassignment.LocationService.LocationAddress;
import com.app.yoparkerassignment.LocationService.LocationUpdatesService;
import com.app.yoparkerassignment.Login.LoginTypeSelectionActivity;
import com.app.yoparkerassignment.Models.LocationModel;
import com.app.yoparkerassignment.R;
import com.app.yoparkerassignment.activities.ReferEarnActivity;
import com.app.yoparkerassignment.fragment.HomeFragment;
import com.app.yoparkerassignment.fragment.MyBookingFragment;
import com.app.yoparkerassignment.fragment.ProfileFragment;
import com.app.yoparkerassignment.fragment.WalletFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONObject;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class HomeActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private TextView MapTXT, ListTXT;
    private TextView helloTXT;
    int currentPosition = 0;
    private BottomNavigationView navigation;
    private CardView switchBtn;
    TextView Title;
    public static int CurrentFlag;
    private PreferenceProvider provider;
    private LocationModel locationModel;
    private NavigationView navigation_view;
    public static HomeActivity homeActivity;
    // public HomeFragment homeFragment;
    private int RedirectPosition;

    private boolean mBound = false;
    // The BroadcastReceiver used to listen from broadcasts from the service.
    private MyReceiver myReceiver;

    // A reference to the service used to get location updates.
    private LocationUpdatesService mService = null;
    private AlertDialog alertDialog;
    private JSONObject userObject = null;

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


    private class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Location location = intent.getParcelableExtra(LocationUpdatesService.EXTRA_LOCATION);
            if (location != null) {


                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                locationModel.setCur_latitude(latitude);
                locationModel.setCur_longitude(longitude);
                LocationAddress locationAddress = new LocationAddress();
                locationAddress.getAddressFromLocation(latitude, longitude,
                        getApplicationContext(), new GeocoderHandler());

                /*Toast.makeText(SelectLocationActivity.this, Utils.getLocationText(location),
                        Toast.LENGTH_SHORT).show();*/
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        homeActivity = this;
        alertDialog = CommonAlertDialog.CreateDialog(homeActivity, "Please wait...");
        //   getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        setLightStatusBar(this);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.containsKey("position")) {
            RedirectPosition = bundle.getInt("position");
        }
        myReceiver = new MyReceiver();
        provider = new PreferenceProvider(this);

        String userObjStr = provider.GetLoginResponse();
        try {
            userObject = new JSONObject(userObjStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        locationModel = provider.getLocationDetail();
        toolbar = findViewById(R.id.toolbar);
        drawer = findViewById(R.id.drawer);
        navigation = findViewById(R.id.navigation);
        switchBtn = findViewById(R.id.switchBtn);
        Title = findViewById(R.id.Title);
        navigation_view = findViewById(R.id.navigation_view);
        setSupportActionBar(toolbar);
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.app_name, R.string.app_name);
        drawer.addDrawerListener(toggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        toolbar.setNavigationIcon(R.drawable.group_81);
        getSupportFragmentManager().beginTransaction().replace(R.id.HomeContainer, new HomeFragment()).commit();
        toolbar.setTitleTextColor(getResources().getColor(R.color.blue_color));
        getSupportActionBar().setTitle(locationModel.getCity());
        MapTXT = findViewById(R.id.MapTXT);
        ListTXT = findViewById(R.id.ListTXT);
        MapTXT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MapTXT.setBackgroundResource(R.drawable.gray_rounded);
                ListTXT.setBackgroundResource(R.drawable.white_rounded);
                if (currentPosition != 0) {
                    currentPosition = 0;
                    //getSupportFragmentManager().beginTransaction().replace(R.id.HomeContainer, homeFragment).commit();
                    //viewModel.is_Map.setValue(true);
                    //onBackPressed();

                    /*
                    Fragment fragment = manager.findFragmentById(R.id.HomeContainer);
                    fragment.myMethod();*/
                    try {
                        FragmentManager manager = getSupportFragmentManager();
                        HomeFragment fragment =
                                (HomeFragment) manager.findFragmentById(R.id.HomeContainer);
                        fragment.setListMap(true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        View headerView = navigation_view.getHeaderView(0);
        helloTXT = headerView.findViewById(R.id.helloTXT);

        helloTXT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                finish();
                startActivity(intent);

            }
        });
        ListTXT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListTXT.setBackgroundResource(R.drawable.gray_rounded);
                MapTXT.setBackgroundResource(R.drawable.white_rounded);
                if (currentPosition != 1) {
                    currentPosition = 1;
                  /*  FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.add(R.id.HomeContainer, new HomeFragmentList());
                    TextClass.fragmentStack.lastElement().onPause();
                    ft.hide(TextClass.fragmentStack.lastElement());
                    TextClass.fragmentStack.push(new HomeFragmentList());
                    ft.commit();*/
                    //getSupportFragmentManager().beginTransaction().replace(R.id.HomeContainer, new HomeFragmentList()).commit();
                    //viewModel.is_Map.setValue(false);
                    try {
                        FragmentManager manager = getSupportFragmentManager();
                        HomeFragment fragment =
                                (HomeFragment) manager.findFragmentById(R.id.HomeContainer);
                        fragment.setListMap(false);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.parking:
                        TextClass.fragmentStack.clear();
                        switchBtn.setVisibility(View.VISIBLE);
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        FragmentTransaction ft = fragmentManager.beginTransaction();
                        ft.replace(R.id.HomeContainer, new HomeFragment());

                        ft.commit();
                        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
                        }
                        setLightStatusBar(HomeActivity.this);
                        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                        getSupportActionBar().setHomeButtonEnabled(true);
                        toolbar.setNavigationIcon(R.drawable.group_81);

                        toolbar.setTitle(locationModel.getCity());
                        toolbar.setTitleTextColor(getResources().getColor(R.color.blue_color));
                        return true;
                    case R.id.booking:
                        TextClass.fragmentStack.clear();
                        switchBtn.setVisibility(View.INVISIBLE);
                        getSupportFragmentManager().beginTransaction().replace(R.id.HomeContainer, new MyBookingFragment()).commit();
                        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.red)));

                        getSupportActionBar().setTitle("");
                        clearLightStatusBar(HomeActivity.this);
                        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                        getSupportActionBar().setHomeButtonEnabled(true);
                        toolbar.setNavigationIcon(R.drawable.group_81_copy);
                        //Title.setTextColor(getResources().getColor(R.color.white));
                        Title.setText("");
                        toolbar.setTitle(R.string.my_booking);
                        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
                        //toolbar.setNavigationIcon(R.drawable.group_81_copy);
                        return true;
                    case R.id.wallet:
                        switchBtn.setVisibility(View.INVISIBLE);
                        getSupportFragmentManager().beginTransaction().replace(R.id.HomeContainer, new WalletFragment()).commit();
                        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.red)));

                        getSupportActionBar().setTitle("");

                        clearLightStatusBar(HomeActivity.this);
                        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                        getSupportActionBar().setHomeButtonEnabled(true);
                        toolbar.setNavigationIcon(R.drawable.group_81_copy);
                        //Title.setTextColor(getResources().getColor(R.color.white));
                        Title.setText("");
                        toolbar.setTitle(R.string.my_wallet);
                        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
                        return true;
                    case R.id.me:
                        switchBtn.setVisibility(View.INVISIBLE);
                        getSupportFragmentManager().beginTransaction().replace(R.id.HomeContainer, new ProfileFragment()).commit();
                        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.red)));

                        getSupportActionBar().setTitle("");

                        clearLightStatusBar(HomeActivity.this);
                        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                        getSupportActionBar().setHomeButtonEnabled(true);
                        toolbar.setNavigationIcon(R.drawable.group_81_copy);
                        //Title.setTextColor(getResources().getColor(R.color.white));
                        Title.setText("");
                        toolbar.setTitle(R.string.my_profile);
                        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
                        return true;
                }
                return true;
            }
        });

        navigation_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.rent_parking:
                        drawer.closeDrawer(GravityCompat.START);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                //startActivity(new Intent(HomeActivity.this, GetAllParkingActivity.class));
                            }
                        }, 500);

                        return true;
                    case R.id.refer_earn:
                        drawer.closeDrawer(GravityCompat.START);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                startActivity(new Intent(HomeActivity.this, ReferEarnActivity.class));
                            }
                        }, 500);
                        return true;
                    case R.id.find_vehicle:
                        drawer.closeDrawer(GravityCompat.START);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                //startActivity(new Intent(HomeActivity.this, FindMyVehicleActivity.class));
                            }
                        }, 500);
                        return true;
                    case R.id.logout:

                        provider.DeleteData();
                        Intent intent = new Intent(HomeActivity.this, LoginTypeSelectionActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                        return true;
                    case R.id.my_booking:
                        drawer.closeDrawer(GravityCompat.START);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                navigation.getMenu().findItem(R.id.booking).setChecked(true);
                                switchBtn.setVisibility(View.INVISIBLE);
                                getSupportFragmentManager().beginTransaction().replace(R.id.HomeContainer, new MyBookingFragment()).commit();
                                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.red)));

                                getSupportActionBar().setTitle("");
                                clearLightStatusBar(HomeActivity.this);
                                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                                getSupportActionBar().setHomeButtonEnabled(true);
                                toolbar.setNavigationIcon(R.drawable.group_81_copy);
                                //Title.setTextColor(getResources().getColor(R.color.white));
                                Title.setText("");
                                toolbar.setTitle(R.string.my_booking);
                                toolbar.setTitleTextColor(getResources().getColor(R.color.white));
                            }
                        }, 500);


                        return true;
                    case R.id.rateus:
                        drawer.closeDrawer(GravityCompat.START);
                        Intent intent1 = new Intent(Intent.ACTION_VIEW);
                        intent1.setData(Uri.parse(
                                "https://play.google.com/store/apps/details?id=com.yoparking.admin.parking"));
                        //  intent1.setPackage("com.yoparking.admin.parkinghawker");
                        startActivity(intent1);
                        return true;
                    case R.id.share:
                        // navigation.getMenu().findItem(R.id.booking).setChecked(true);
                        drawer.closeDrawer(GravityCompat.START);
                        ShareReferlaCode();
                        return true;
                    case R.id.feedback:
                        drawer.closeDrawer(GravityCompat.START);
                        // navigation.getMenu().findItem(R.id.booking).setChecked(true);
                       /* Intent intent2 = new Intent(HomeActivity.this, FeedbackActivity.class);
                        intent2.putExtra("Type", "Feedback");
                        startActivity(intent2);
                        return true;*/
                    case R.id.settings:
                        drawer.closeDrawer(GravityCompat.START);
                        Toast.makeText(HomeActivity.this, "Coming soon...", Toast.LENGTH_SHORT).show();
                        return true;
                }
                return false;
            }
        });

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
            provider.SaveLocationDetail(locationModel);
           /* if(alertDialog != null)
            {
                if(alertDialog.isShowing())
                {
                    alertDialog.dismiss();
                }
            }*/

            // tvAddress.setText(locationAddress);
        }
    }

    public void SetTitle(String title) {
        //   Log.e("City",title);
        if (title != null) {
            getSupportActionBar().setTitle(title);
        }
    }

    public void CheckPermission() {
        HomeActivityPermissionsDispatcher.NeedsPermissionWithPermissionCheck(HomeActivity.this);
    }

    @NeedsPermission({Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION})
    void NeedsPermission() {
        // alertDialog.show();
        mService.requestLocationUpdates();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        HomeActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @OnShowRationale({Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION})
    void OnShowRationale(final PermissionRequest request) {
    }

    public static void setLightStatusBar(Activity activity) {


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            int flags = activity.getWindow().getDecorView().getSystemUiVisibility();

            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            CurrentFlag = flags;
            Log.i("flag", "" + CurrentFlag);
            activity.getWindow().getDecorView().setSystemUiVisibility(flags);
            activity.getWindow().setStatusBarColor(Color.WHITE);

        }
    }


    public static void clearLightStatusBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (CurrentFlag != 0) {
                int flags = activity.getWindow().getDecorView().getSystemUiVisibility();

                flags = flags ^ View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
                CurrentFlag = flags;
                Log.i("flag", "" + CurrentFlag);
                activity.getWindow().getDecorView().setSystemUiVisibility(flags);
            }
            Window window = activity.getWindow();
            window.setStatusBarColor(ContextCompat
                    .getColor(activity, R.color.red));
        }
    }


    public void ShareReferlaCode() {
        /*Create an ACTION_SEND Intent*/
        Intent intent = new Intent(android.content.Intent.ACTION_SEND);
        /*This will be the actual content you wish you share.*/
        String shareBody = String.format(getString(R.string.refer_text), "NELM147Y");
        /*The type of the content is text, obviously.*/
        intent.setType("text/html");
        /*Applying information Subject and Body.*/

        intent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        /*Fire!*/
        startActivity(Intent.createChooser(intent, "Select.."));
    }


}