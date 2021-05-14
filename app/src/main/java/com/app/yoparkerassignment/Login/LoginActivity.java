package com.app.yoparkerassignment.Login;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.app.yoparkerassignment.R;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    RelativeLayout LoginBTN;
    String pattern = "^\\s*(?:\\+?(\\d{1,3}))?[-. (]*(\\d{3})[-. )]*(\\d{3})[-. ]*(\\d{4})(?: *x(\\d+))?\\s*$";
    private EditText PhnNumEDT;
    Matcher m;
    private AlertDialog alertDialog;
    private boolean isInternetConnection = true;
    private static final int RC_SIGN_IN=101;
    private static final String EMAIL = "email";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        LoginBTN = findViewById(R.id.LoginBTN);
        PhnNumEDT = findViewById(R.id.PhnNumEDT);

        LoginBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isInternetConnection) {
                    Pattern r = Pattern.compile(pattern);
                    if (PhnNumEDT.getText().toString().length() != 0) {
                        m = r.matcher(PhnNumEDT.getText().toString().trim());
                        if (m.find()) {
                            startActivity(new Intent(LoginActivity.this, OTPActivity.class));
                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this, R.string.enter_valid_number, Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(LoginActivity.this, R.string.enter_mobile, Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(LoginActivity.this, R.string.no_internet, Toast.LENGTH_LONG).show();
                }
                //
            }
        });
    }
}