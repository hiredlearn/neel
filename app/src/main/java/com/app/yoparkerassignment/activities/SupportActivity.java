package com.app.yoparkerassignment.activities;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.app.yoparkerassignment.Commonuses.CommonAlertDialog;
import com.app.yoparkerassignment.Commonuses.PreferenceProvider;
import com.app.yoparkerassignment.R;
import com.google.android.material.textfield.TextInputEditText;

public class SupportActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private PreferenceProvider provider;
    private TextView cancelTv;
    private TextView managebookingsTv;
    private TextView bookingserviceTv;
    private TextView payingforservicesTv;
    private TextView accountsettingsTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);
        provider = new PreferenceProvider(this);
        toolbar = findViewById(R.id.toolbar);

        cancelTv = findViewById(R.id.cancelTv);
        managebookingsTv = findViewById(R.id.managebookingsTv);
        bookingserviceTv = findViewById(R.id.bookingserviceTv);
        payingforservicesTv = findViewById(R.id.payingforservicesTv);
        accountsettingsTv = findViewById(R.id.accountsettingsTv);

        setSupportActionBar(toolbar);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.red)));
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int flags = getWindow().getDecorView().getSystemUiVisibility(); // get current flag
            flags = flags ^ View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            getWindow().getDecorView().setSystemUiVisibility(flags);
            getWindow().setStatusBarColor(getResources().getColor(R.color.red));
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Support");
        toolbar.setNavigationIcon(R.drawable.back);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancelTv:
            case R.id.managebookingsTv:
            case R.id.bookingserviceTv:
            case R.id.payingforservicesTv:
            case R.id.accountsettingsTv:
                Toast.makeText(SupportActivity.this, "Webview page", Toast.LENGTH_LONG).show();
                break;

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