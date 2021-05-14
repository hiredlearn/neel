package com.app.yoparkerassignment.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;

import com.app.yoparkerassignment.Commonuses.PreferenceProvider;
import com.app.yoparkerassignment.R;

import org.json.JSONException;
import org.json.JSONObject;

import static com.app.yoparkerassignment.Commonuses.PreferenceProvider.UserId;

public class SplashScreenActivity extends AppCompatActivity {
    private Handler handler;
    private Runnable runnable;
    private PreferenceProvider preferenceProvider;
    private String userObjStr = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        preferenceProvider = new PreferenceProvider(this);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        handler = new Handler();
        handler.postDelayed(runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    userObjStr = preferenceProvider.GetLoginResponse();
                    if (!TextUtils.isEmpty(userObjStr)) {
                        JSONObject userObject = new JSONObject(userObjStr);
                        if (userObject.getString(UserId) == null) {
                            Intent intent = new Intent(SplashScreenActivity.this, LoginTypeSelectionActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Intent intent = new Intent(SplashScreenActivity.this, SelectLocationActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    } else {
                        Intent intent = new Intent(SplashScreenActivity.this, LoginTypeSelectionActivity.class);
                        startActivity(intent);
                        finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, 3000);

    }
}