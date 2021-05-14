package com.app.yoparkerassignment.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.app.yoparkerassignment.Commonuses.PreferenceProvider;
import com.app.yoparkerassignment.R;

import org.json.JSONObject;

import static com.app.yoparkerassignment.Commonuses.PreferenceProvider.MobileNumber;
import static com.app.yoparkerassignment.Commonuses.PreferenceProvider.UserEmail;
import static com.app.yoparkerassignment.Commonuses.PreferenceProvider.UserId;
import static com.app.yoparkerassignment.Commonuses.PreferenceProvider.UserName;

public class EditProfileActivity extends AppCompatActivity {

    private JSONObject userObject = null;
    private PreferenceProvider provider;
    private Toolbar toolbar;

    private ImageView ProfilePic;
    private EditText FirstNameEDT;
    private EditText LastNameEDT;
    private EditText EmailAddressEDT;
    private EditText PhnNumEDT;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        provider = new PreferenceProvider(this);
        FirstNameEDT = findViewById(R.id.FirstNameEDT);
        LastNameEDT = findViewById(R.id.LastNameEDT);
        EmailAddressEDT = findViewById(R.id.EmailAddressEDT);
        PhnNumEDT = findViewById(R.id.PhnNumEDT);

        ProfilePic = findViewById(R.id.ProfilePic);

        String userObjStr = provider.GetLoginResponse();
        try {
            userObject = new JSONObject(userObjStr);
            userObject.getString(UserId);
            FirstNameEDT.setText(userObject.getString(UserName));
            LastNameEDT.setText(userObject.getString(UserName));
            EmailAddressEDT.setText(userObject.getString(UserEmail));
            PhnNumEDT.setText(userObject.getString(MobileNumber));

            toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.red)));
            toolbar.setNavigationIcon(R.drawable.back);
            toolbar.setTitleTextColor(getResources().getColor(R.color.white));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setTitle(R.string.edit_profile);

            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                int flags = getWindow().getDecorView().getSystemUiVisibility(); // get current flag
                flags = flags ^ View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
                getWindow().getDecorView().setSystemUiVisibility(flags);
                getWindow().setStatusBarColor(getResources().getColor(R.color.red));
            }

        } catch (Exception e) {
            e.printStackTrace();
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