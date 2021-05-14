package com.app.yoparkerassignment.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.yoparkerassignment.Commonuses.CommonAlertDialog;
import com.app.yoparkerassignment.Commonuses.PreferenceProvider;
import com.app.yoparkerassignment.R;
import com.google.android.material.textfield.TextInputEditText;

public class ReferEarnActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private PreferenceProvider provider;
    private boolean isInternetConnection;
    private AlertDialog alertDialog;
    private TextInputEditText Refer_codeEDT;
    private TextView MyReferCode;
    private LinearLayout SubmitBTN, InviteBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refer_earn);
        provider = new PreferenceProvider(this);
        InviteBTN = findViewById(R.id.InviteBTN);
        alertDialog = CommonAlertDialog.CreateDialog(this, getString(R.string.please_wait));
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
        getSupportActionBar().setTitle(R.string.refer_earn);
        toolbar.setNavigationIcon(R.drawable.back);

        InviteBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareReferlaCode();
            }
        });

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