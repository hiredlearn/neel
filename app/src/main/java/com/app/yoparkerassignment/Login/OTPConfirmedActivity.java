package com.app.yoparkerassignment.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.app.yoparkerassignment.Commonuses.PreferenceProvider;
import com.app.yoparkerassignment.R;

public class OTPConfirmedActivity extends AppCompatActivity implements View.OnClickListener  {
    private RelativeLayout OkBTN;
    private PreferenceProvider provider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_o_t_p_confirmed);
        provider = new PreferenceProvider(this);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        OkBTN = findViewById(R.id.OkBTN);
        OkBTN.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.OkBTN:
                Intent intent = new Intent(OTPConfirmedActivity.this, SelectLocationActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
               /* if(loginResponse.getData().getActive() == 1)
                {
                    startActivity(new Intent(OTPConfirmedActivity.this, HomeActivity.class));
                }
                else {
                    startActivity(new Intent(OTPConfirmedActivity.this, EditProfile.class));
                }*/
                break;
        }

    }

}