package com.app.yoparkerassignment.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.app.yoparkerassignment.Commonuses.CommonAlertDialog;
import com.app.yoparkerassignment.Commonuses.PreferenceProvider;
import com.app.yoparkerassignment.R;
import com.app.yoparkerassignment.activities.EnterAmountActivity;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class WalletFragment extends Fragment implements View.OnClickListener {
    private TextView rs_100, rs_200, rs_500, rs_1000, other, CurrentBalance, addMoneyTv;
    private boolean isInternetConnection = true;
    private AlertDialog alertDialog;
    private PreferenceProvider provider;
    private int Amount;
    String Payment_id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_wallet, null, false);
        provider = new PreferenceProvider(getActivity());
        alertDialog = CommonAlertDialog.CreateDialog(getActivity(), getString(R.string.please_wait));

        other = v.findViewById(R.id.other);
        other.setOnClickListener(this);
        addMoneyTv = v.findViewById(R.id.addMoneyTv);
        addMoneyTv.setOnClickListener(this);
        rs_100 = v.findViewById(R.id.rs_100);
        rs_100.setOnClickListener(this);
        rs_200 = v.findViewById(R.id.rs_200);
        rs_200.setOnClickListener(this);
        rs_500 = v.findViewById(R.id.rs_500);
        rs_500.setOnClickListener(this);
        rs_1000 = v.findViewById(R.id.rs_1000);
        rs_1000.setOnClickListener(this);
        CurrentBalance = v.findViewById(R.id.CurrentBalance);
        rs_100.setText(String.format(getString(R.string.rs100), 100));
        rs_200.setText(String.format(getString(R.string.rs100), 200));
        rs_500.setText(String.format(getString(R.string.rs100), 500));
        rs_1000.setText(String.format(getString(R.string.rs100), 1000));

        int currentBalance = Integer.parseInt("122");
        CurrentBalance.setText(String.format(getString(R.string.rs100),currentBalance));

        return v;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rs_100:
                Amount = 100;
                GetLink(100);
                break;
            case R.id.rs_200:
                Amount = 200;
                GetLink(200);
                break;
            case R.id.rs_500:
                Amount = 500;
                GetLink(500);
                break;
            case R.id.rs_1000:
                Amount = 1000;
                GetLink(1000);
                break;
            case R.id.other:
            case R.id.addMoneyTv:
                // startPayment(String.valueOf(1));
                Intent i = new Intent(getActivity(), EnterAmountActivity.class);
                startActivityForResult(i, 125);
                break;
        }
    }

    public void GetLink(int amount) {
        Toast.makeText(getActivity(), "Payment page for Amount : " + Amount, Toast.LENGTH_LONG).show();
        /*Intent intent = new Intent(getActivity(), PaymentWebviewActivity.class);
        intent.putExtra("url", "");
        intent.putExtra("amount", Amount);
        startActivityForResult(intent, 99);*/
    }

}