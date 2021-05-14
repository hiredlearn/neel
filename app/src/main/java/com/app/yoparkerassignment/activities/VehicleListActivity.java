package com.app.yoparkerassignment.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.app.yoparkerassignment.AddVehicleActivity;
import com.app.yoparkerassignment.Commonuses.CommonAlertDialog;
import com.app.yoparkerassignment.Commonuses.PreferenceProvider;
import com.app.yoparkerassignment.LocationService.Utils;
import com.app.yoparkerassignment.Models.VehicleDataItem;
import com.app.yoparkerassignment.Models.VehicleGeneral;
import com.app.yoparkerassignment.Models.VehicleListModel;
import com.app.yoparkerassignment.R;
import com.app.yoparkerassignment.adapter.VehicleListAdapter;
import com.app.yoparkerassignment.interfces.OnRecyclerItemClick;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

public class VehicleListActivity extends AppCompatActivity implements OnRecyclerItemClick {

    private RecyclerView vehicleRecycler;
    private Toolbar toolbar;
    private PreferenceProvider provider;
    private boolean isInternetConnection;
    private AlertDialog alertDialog;
    private VehicleListAdapter adapter;
    private TextView AddBTN;
    private OnRecyclerItemClick itemClick;
    private List<VehicleDataItem> items;
    private int DeletePosition;
    private TextView NoDataTXT;
    private JSONObject userObject = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_list);
        provider = new PreferenceProvider(this);

        itemClick = this;
        vehicleRecycler = findViewById(R.id.vehicleRecycler);
        AddBTN = findViewById(R.id.AddBTN);
        NoDataTXT = findViewById(R.id.NoDataTXT);
        vehicleRecycler.setLayoutManager(new LinearLayoutManager(this));
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.red)));
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle(R.string.vehicle);
        toolbar.setNavigationIcon(R.drawable.back);
        String userObjStr = provider.GetLoginResponse();
        try {
            userObject = new JSONObject(userObjStr);
        } catch (Exception e) {
            e.printStackTrace();
        }


        clearLightStatusBar(this);


        alertDialog = CommonAlertDialog.CreateDialog(this, getString(R.string.please_wait));

        Gson gson = new Gson();
        String jsonFileString = Utils.loadJSONFromAsset(VehicleListActivity.this, "vehicleslist.json");
        VehicleListModel vehicleListModel = gson.fromJson(jsonFileString, VehicleListModel.class);
        if (vehicleListModel != null && vehicleListModel.getStatusCode() == getResources().getInteger(R.integer.Response_Success)) {
            items = vehicleListModel.getData();
            if (items.size() == 0) {
                NoDataTXT.setVisibility(View.VISIBLE);
            } else {
                NoDataTXT.setVisibility(View.GONE);
            }
            adapter = new VehicleListAdapter(items, VehicleListActivity.this, itemClick);
            vehicleRecycler.setAdapter(adapter);
        } else {
            Toast.makeText(VehicleListActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
        }

        AddBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(VehicleListActivity.this, AddVehicleActivity.class));
            }
        });


    }

    public static void clearLightStatusBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            int flags = activity.getWindow().getDecorView().getSystemUiVisibility();
            if (flags != 0) {
                flags = flags ^ View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
                activity.getWindow().getDecorView().setSystemUiVisibility(flags);
            }
            Window window = activity.getWindow();
            window.setStatusBarColor(ContextCompat
                    .getColor(activity, R.color.red));
        }
    }


    @Override
    public void OnItemClick(int position) {

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return true;
    }

}