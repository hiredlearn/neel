package com.app.yoparkerassignment.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.yoparkerassignment.R;

public class EnterAmountActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView BTN_1, BTN_2, BTN_3, BTN_4, BTN_5, BTN_6, BTN_7, BTN_8, BTN_9, BTN_0, ruppesTXT;
    private ImageView BTN_DEL, BTN_DONE;
    String Total = "";
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_amount);

        BTN_1 = findViewById(R.id.BTN_1);
        BTN_2 = findViewById(R.id.BTN_2);
        BTN_3 = findViewById(R.id.BTN_3);
        BTN_4 = findViewById(R.id.BTN_4);
        BTN_5 = findViewById(R.id.BTN_5);
        BTN_6 = findViewById(R.id.BTN_6);
        BTN_7 = findViewById(R.id.BTN_7);
        BTN_8 = findViewById(R.id.BTN_8);
        BTN_9 = findViewById(R.id.BTN_9);
        BTN_0 = findViewById(R.id.BTN_0);
        BTN_DEL = findViewById(R.id.BTN_DEL);
        BTN_DONE = findViewById(R.id.BTN_DONE);
        ruppesTXT = findViewById(R.id.ruppesTXT);
        BTN_1.setOnClickListener(this);
        BTN_2.setOnClickListener(this);
        BTN_3.setOnClickListener(this);
        BTN_4.setOnClickListener(this);
        BTN_5.setOnClickListener(this);
        BTN_6.setOnClickListener(this);
        BTN_7.setOnClickListener(this);
        BTN_8.setOnClickListener(this);
        BTN_9.setOnClickListener(this);
        BTN_0.setOnClickListener(this);
        BTN_DEL.setOnClickListener(this);
        BTN_DONE.setOnClickListener(this);
        ruppesTXT.setText(String.format(getString(R.string.rs100), 0));
        toolbar = findViewById(R.id.toolbar);
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
        getSupportActionBar().setTitle("Enter Amount");
        toolbar.setNavigationIcon(R.drawable.back);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.BTN_1:
                Total = Total + "1";
                CheckLimit();
                break;
            case R.id.BTN_2:
                Total = Total + "2";
                CheckLimit();
                break;
            case R.id.BTN_3:
                Total = Total + "3";
                CheckLimit();
                break;
            case R.id.BTN_4:
                Total = Total + "4";
                CheckLimit();
                break;
            case R.id.BTN_5:
                Total = Total + "5";
                CheckLimit();
                break;
            case R.id.BTN_6:
                Total = Total + "6";
                CheckLimit();
                break;
            case R.id.BTN_7:
                Total = Total + "7";
                CheckLimit();
                break;
            case R.id.BTN_8:
                Total = Total + "8";
                CheckLimit();
                break;
            case R.id.BTN_9:
                Total = Total + "9";
                CheckLimit();
                break;
            case R.id.BTN_0:
                Total = Total + "0";
                CheckLimit();
                break;
            case R.id.BTN_DEL:
                if (Total.length() > 0) {
                    Total = method(Total);
                    if (Total.length() != 0) {
                        ruppesTXT.setText(String.format(getString(R.string.rs100), Integer.parseInt(Total)));
                    } else {
                        ruppesTXT.setText(String.format(getString(R.string.rs100), 0));
                    }
                }
                break;
            case R.id.BTN_DONE:
                if (Total.length() > 0) {
                    Intent intent = new Intent();
                    intent.putExtra("rs", Integer.parseInt(Total));
                    setResult(Activity.RESULT_OK, intent);
                    finish();

                }
                break;
        }
    }

    public void CheckLimit() {
        if (Integer.parseInt(Total) > 10000) {
            Toast.makeText(EnterAmountActivity.this, "Maximum limit 10,000", Toast.LENGTH_SHORT).show();
            Total = method(Total);
        }
        ruppesTXT.setText(String.format(getString(R.string.rs100), Integer.parseInt(Total)));
    }

    public String method(String str) {
        if (str != null && str.length() > 0) {
            str = str.substring(0, str.length() - 1);
        }
        return str;
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