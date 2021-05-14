package com.app.yoparkerassignment.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.yoparkerassignment.Commonuses.PreferenceProvider;
import com.app.yoparkerassignment.R;
import com.mukesh.OnOtpCompletionListener;
import com.mukesh.OtpView;

public class OTPActivity extends AppCompatActivity implements View.OnClickListener {
    private OtpView otpView;
    private RelativeLayout ContinueBTN;
    private boolean isInternetConnection = true;
    private PreferenceProvider preferenceProvider;
    private String OTPString;
    private CountDownTimer countDownTimer;
    private TextView MinuteTXT, ResendTXT;
    private String From = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_o_t_p);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            if (bundle.containsKey("from")) {
                From = bundle.getString("from");

            }
        }
        preferenceProvider = new PreferenceProvider(this);
        otpView = findViewById(R.id.otp_view);
        ContinueBTN = findViewById(R.id.ContinueBTN);
        MinuteTXT = findViewById(R.id.MinuteTXT);
        ResendTXT = findViewById(R.id.ResendTXT);
        ResendTXT.setEnabled(false);
        ResendTXT.setOnClickListener(this);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        otpView.setOtpCompletionListener(new OnOtpCompletionListener() {
            @Override
            public void onOtpCompleted(String otp) {
                OTPString = otp;
            }
        });
        ContinueBTN.setOnClickListener(this);

        countDownTimer = new CountDownTimer(2 * 60 * 1000, 1000) {

            @Override
            public void onTick(long l) {
                //Log.e("Long", "" + l);
                long minutes = (l / 1000) / 60;
                long seconds = (l / 1000) % 60;
                MinuteTXT.setText(String.format("%02d:%02d", minutes, seconds));
            }

            @Override
            public void onFinish() {
                ResendTXT.setEnabled(true);
            }
        }.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        countDownTimer.cancel();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ContinueBTN:
                if (isInternetConnection) {
                    if (otpView.getText().length() == 4) {
                        startActivity(new Intent(OTPActivity.this, OTPConfirmedActivity.class));
                        finish();
                    }
                } else {
                    Toast.makeText(OTPActivity.this, R.string.no_internet, Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.ResendTXT:
                otpView.setText("");
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }


}