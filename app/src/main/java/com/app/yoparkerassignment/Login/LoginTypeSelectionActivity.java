package com.app.yoparkerassignment.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.app.yoparkerassignment.R;


public class LoginTypeSelectionActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout Login_phn;
    private LinearLayout Login_Email;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_type_selection);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        Login_phn = findViewById(R.id.Login_phn);
        Login_phn.setOnClickListener(this);
        Login_Email = findViewById(R.id.Login_Email);
        Login_Email.setOnClickListener(this);
        Login_Email.setVisibility(View.GONE);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.Login_phn:
                startActivity(new Intent(LoginTypeSelectionActivity.this, LoginActivity.class));
                break;
            case R.id.Login_Email:
                //startActivity(new Intent(LoginTypeSelectionActivity.this, LoginWithEmailActivity.class));
                break;
        }

    }
}
