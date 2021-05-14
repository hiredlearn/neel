package com.app.yoparkerassignment;

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
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.yoparkerassignment.Commonuses.CommonAlertDialog;
import com.app.yoparkerassignment.Commonuses.PreferenceProvider;
import com.app.yoparkerassignment.LocationService.Utils;
import com.app.yoparkerassignment.Login.LoginTypeSelectionActivity;
import com.app.yoparkerassignment.Models.BrandsItem;
import com.app.yoparkerassignment.Models.CategoriesItem;
import com.app.yoparkerassignment.Models.ModelsItem;
import com.app.yoparkerassignment.Models.MyBookingResponse;
import com.app.yoparkerassignment.Models.TypesItem;
import com.app.yoparkerassignment.Models.VehicleGeneral;
import com.app.yoparkerassignment.adapter.ColorRecyclerAdapter;
import com.app.yoparkerassignment.adapter.ImageRecyclerAdapter;
import com.app.yoparkerassignment.interfces.OnItemClick;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.gson.Gson;
import com.nguyenhoanglam.imagepicker.model.Image;
import com.nguyenhoanglam.imagepicker.ui.imagepicker.ImagePicker;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class AddVehicleActivity extends AppCompatActivity implements View.OnClickListener, OnItemClick {

    private RelativeLayout CarLayout, BikeLayout, OtherLayout;
    private ChipGroup categoryGroup, brandGroup, modelGroup;
    private Toolbar toolbar;
    private PreferenceProvider provider;
    private boolean isInternetConnection;
    private AlertDialog alertDialog;
    private JSONObject userObject = null;
    private List<TypesItem> typesItemList;
    private List<CategoriesItem> categoriesItemList;
    private List<BrandsItem> brandsItemList;
    private List<ModelsItem> modelsItemList;
    private ArrayList<Image> images;
    private TextView AddBTN;

    private ArrayList<Chip> Categorychips, Brandchips, Modelschips;
    private String CurrentTypeName = "car";
    private String CurrentTypeID;
    private String CurrentCategoryID;
    private String CurrentBrandID;
    private String CurrentModelID;
    private VehicleGeneral mainvehicleGeneral;
    private int CategoryCheckId;
    private RecyclerView ImageRecycler, color_recycler;
    private ImageRecyclerAdapter adapter;
    private EditText v_number;
    private String validator = "[A-Z]{2}[0-9]{2}[A-Z0-9]{1,2}[A-Z][0-9]{4}";
    private String SelectedColor = "";
    int CompressCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vehicle);

        Categorychips = new ArrayList<>();
        Brandchips = new ArrayList<>();
        Modelschips = new ArrayList<>();
        CarLayout = findViewById(R.id.CarLayout);
        BikeLayout = findViewById(R.id.BikeLayout);
        OtherLayout = findViewById(R.id.OtherLayout);
        categoryGroup = findViewById(R.id.categoryGroup);
        brandGroup = findViewById(R.id.brandGroup);
        modelGroup = findViewById(R.id.modelGroup);
        ImageRecycler = findViewById(R.id.ImageRecycler);
        color_recycler = findViewById(R.id.color_recycler);
        AddBTN = findViewById(R.id.AddBTN);
        v_number = findViewById(R.id.v_number);
        categoriesItemList = new ArrayList<>();
        brandsItemList = new ArrayList<>();
        modelsItemList = new ArrayList<>();
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.red)));
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle(R.string.add_vehicle);
        toolbar.setNavigationIcon(R.drawable.back);
        clearLightStatusBar(this);

        provider = new PreferenceProvider(this);
        String userObjStr = provider.GetLoginResponse();
        try {
            userObject = new JSONObject(userObjStr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        alertDialog = CommonAlertDialog.CreateDialog(this, getString(R.string.please_wait));


        CarLayout.setOnClickListener(this);
        BikeLayout.setOnClickListener(this);
        OtherLayout.setOnClickListener(this);


        getGeneralVehicleData();


        ImageRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapter = new ImageRecyclerAdapter(this, new ArrayList<Image>(), this);
        ImageRecycler.setAdapter(adapter);

        AddBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (v_number.getText().toString().matches(validator) && v_number.getText().toString().trim().length() > 0 && images != null && images.size() >= 4 && !SelectedColor.equals("")
                        && !CurrentCategoryID.equals("") && !CurrentBrandID.equals("") && !CurrentModelID.equals("")) {
                    CompressCount = 0;
                    ArrayList<File> files = new ArrayList<>();
                    ArrayList<File> CompressImage = new ArrayList<>();
                    ContextWrapper cw = new ContextWrapper(getApplicationContext());
                    File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);

                    for (int i = 0; i < images.size(); i++) {
                        File file = new File(images.get(i).getPath());
                        files.add(file);
                    }

                    //
                } else {
                    if (CurrentCategoryID.equals("")) {
                        Toast.makeText(AddVehicleActivity.this, R.string.select_category, Toast.LENGTH_SHORT).show();
                    } else if (CurrentBrandID.equals("")) {
                        Toast.makeText(AddVehicleActivity.this, R.string.select_brand, Toast.LENGTH_SHORT).show();
                    } else if (CurrentModelID.equals("")) {
                        Toast.makeText(AddVehicleActivity.this, R.string.select_model, Toast.LENGTH_SHORT).show();
                    } else if (!v_number.getText().toString().matches(validator) || v_number.getText().toString().trim().length() <= 0) {
                        Toast.makeText(AddVehicleActivity.this, R.string.valid_number, Toast.LENGTH_LONG).show();
                    } else if (images == null || images.size() < 4) {
                        Toast.makeText(AddVehicleActivity.this, R.string.add_photo_4, Toast.LENGTH_LONG).show();
                    } else if (SelectedColor.equals("")) {
                        Toast.makeText(AddVehicleActivity.this, R.string.select_color, Toast.LENGTH_LONG).show();
                    }
                }

            }
        });
    }

    private void getGeneralVehicleData() {
        Gson gson = new Gson();
        String jsonFileString = Utils.loadJSONFromAsset(AddVehicleActivity.this, "vehiclegeneral.json");
        VehicleGeneral vehicleGeneral = gson.fromJson(jsonFileString, VehicleGeneral.class);

        if (vehicleGeneral != null && vehicleGeneral.getStatusCode() == getResources().getInteger(R.integer.Response_Success)) {
            mainvehicleGeneral = vehicleGeneral;
            color_recycler.setLayoutManager(new LinearLayoutManager(AddVehicleActivity.this, LinearLayoutManager.HORIZONTAL, false));
            color_recycler.setAdapter(new ColorRecyclerAdapter(AddVehicleActivity.this, mainvehicleGeneral.getData().getColors(), new ColorRecyclerAdapter.ColorSelected() {
                @Override
                public void ColorString(String colorCode) {
                    SelectedColor = colorCode;
                }
            }));
            typesItemList = vehicleGeneral.getData().getTypes();

            GetCurrentID();
        } else {
            if (vehicleGeneral.getStatusCode() == 401) {
                provider.DeleteData();
/*  val intent : Intent = Intent(this@PaymentActivity,SignupSuccessActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    finish()*/
                Intent intent = new Intent(AddVehicleActivity.this, LoginTypeSelectionActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
            Toast.makeText(AddVehicleActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
        }
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
    public void onClick(View v) {

    }

    @Override
    public void OnItemPositon(int position, ArrayList<Image> images) {
        ImagePicker.with(this)
                .setFolderMode(true)
                .setFolderTitle("Album")
                .setDirectoryName("Image Picker")
                .setMultipleMode(true)
                .setShowNumberIndicator(true)
                .setMaxSize(5)
                .setLimitMessage("You can select up to 5 images")
                .setSelectedImages(images)
                .setRequestCode(100)
                .start();
    }

    @Override
    public void OnItemDelete(int position) {
        images.remove(position);
    }

    public void GetCurrentID() {
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < typesItemList.size(); i++) {
            arrayList.add(typesItemList.get(i).getSlug());
            if (typesItemList.get(i).getSlug().equals(CurrentTypeName)) {

                CurrentTypeID = typesItemList.get(i).getId();
            }
        }
        if (arrayList.contains("car")) {
            CarLayout.setVisibility(View.VISIBLE);
        } else {
            CarLayout.setVisibility(View.GONE);
        }
        if (arrayList.contains("bike")) {
            BikeLayout.setVisibility(View.VISIBLE);
        } else {
            BikeLayout.setVisibility(View.GONE);
        }
        if (arrayList.contains("other")) {
            OtherLayout.setVisibility(View.VISIBLE);
        } else {
            OtherLayout.setVisibility(View.GONE);
        }
        GetCurrentCategory();
    }

    public void GetCurrentCategory() {
        categoryGroup.removeAllViews();

        categoriesItemList.clear();
        Categorychips.clear();
        for (int i = 0; i < mainvehicleGeneral.getData().getCategories().size(); i++) {
            CategoriesItem item = mainvehicleGeneral.getData().getCategories().get(i);
            if (CurrentTypeID.equals(item.getTypeId())) {
                categoriesItemList.add(item);

            }
        }
        CurrentCategoryID = "";
        for (int i = 0; i < categoriesItemList.size(); i++) {
            Chip chip = (Chip) LayoutInflater.from(this).inflate(R.layout.chip_item, null, false);
            //Chip chip=new Chip(categoryGroup.getContext());
            chip.setText(categoriesItemList.get(i).getName());
            int finalI = i;

            chip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CurrentCategoryID = categoriesItemList.get(finalI).getId();
                    Refresh(finalI);
                    GetCurrentBrands();
                }
            });
            Categorychips.add(chip);

            categoryGroup.addView(chip);
        }
        if (Categorychips.size() != 0) {
            Categorychips.get(0).performClick();
        }

    }

    public void GetCurrentBrands() {
        brandGroup.removeAllViews();
        brandsItemList.clear();
        Brandchips.clear();
        for (int i = 0; i < mainvehicleGeneral.getData().getBrands().size(); i++) {
            BrandsItem item = mainvehicleGeneral.getData().getBrands().get(i);
            if (CurrentTypeID.equals(item.getTypeId())) {
                brandsItemList.add(item);

            }
        }
        CurrentBrandID = "";
        for (int i = 0; i < brandsItemList.size(); i++) {
            Chip chip = (Chip) LayoutInflater.from(this).inflate(R.layout.chip_item, null, false);
            //Chip chip=new Chip(categoryGroup.getContext());
            chip.setText(brandsItemList.get(i).getName());
            int finalI = i;
            chip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    RefreshBrand(finalI);
                    CurrentBrandID = brandsItemList.get(finalI).getId();
                    GetCurrentModels();
                }
            });
            Brandchips.add(chip);

            brandGroup.addView(chip);
        }
        if (Brandchips.size() != 0) {
            Brandchips.get(0).performClick();
        }
    }

    public void RefreshBrand(int positon) {
        for (int i = 0; i < brandsItemList.size(); i++) {
            if (positon == i) {
                Brandchips.get(i).setChecked(true);
            } else {
                Brandchips.get(i).setChecked(false);
            }

        }
    }

    public void GetCurrentModels() {
        modelGroup.removeAllViews();
        modelsItemList.clear();
        Modelschips.clear();
        for (int i = 0; i < mainvehicleGeneral.getData().getModels().size(); i++) {
            ModelsItem item = mainvehicleGeneral.getData().getModels().get(i);
            if (CurrentBrandID.equals(item.getBrandId())) {
                modelsItemList.add(item);

            }
        }
        CurrentModelID = "";
        for (int i = 0; i < modelsItemList.size(); i++) {
            Chip chip = (Chip) LayoutInflater.from(this).inflate(R.layout.chip_item, null, false);
            //Chip chip=new Chip(categoryGroup.getContext());
            chip.setText(modelsItemList.get(i).getName());
            int finalI = i;
            chip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    RefreshModel(finalI);
                    CurrentModelID = modelsItemList.get(finalI).getId();

                }
            });
            Modelschips.add(chip);

            modelGroup.addView(chip);
        }
        if (Modelschips.size() != 0) {
            Modelschips.get(0).performClick();
        }
    }

    public void RefreshModel(int positon) {
        for (int i = 0; i < modelsItemList.size(); i++) {
            if (positon == i) {
                Modelschips.get(i).setChecked(true);
            } else {
                Modelschips.get(i).setChecked(false);
            }

        }
    }

    public void Refresh(int positon) {
        for (int i = 0; i < categoriesItemList.size(); i++) {
            if (positon == i) {
                Categorychips.get(i).setChecked(true);
            } else {
                Categorychips.get(i).setChecked(false);
            }

        }
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