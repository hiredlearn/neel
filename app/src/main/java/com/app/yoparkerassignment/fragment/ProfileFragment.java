package com.app.yoparkerassignment.fragment;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.yoparkerassignment.Commonuses.PreferenceProvider;
import com.app.yoparkerassignment.Login.LoginTypeSelectionActivity;
import com.app.yoparkerassignment.Models.LocationModel;
import com.app.yoparkerassignment.R;
import com.app.yoparkerassignment.activities.EditProfileActivity;
import com.app.yoparkerassignment.activities.SupportActivity;
import com.app.yoparkerassignment.activities.VehicleListActivity;
import com.github.siyamed.shapeimageview.CircularImageView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment implements View.OnClickListener {

    private PreferenceProvider provider;
    private TextView WelcomeText, PhoneNumberTXT;
    private TextView CityTXT, AddVehicle, Logout, Support;
    private ImageView EditIcon;
    private LocationModel locationModel;
    private CircularImageView ProfilePic;
    private ArrayList<Image> images = new ArrayList<>();
    private AlertDialog alertDialog;
    private boolean isInternetConnection = true;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile, null, false);
        provider = new PreferenceProvider(requireActivity());
        EditIcon = v.findViewById(R.id.EditIcon);
        WelcomeText=v.findViewById(R.id.WelcomeText);
        CityTXT=v.findViewById(R.id.CityTXT);
        EditIcon=v.findViewById(R.id.EditIcon);
        AddVehicle=v.findViewById(R.id.AddVehicle);
        Logout=v.findViewById(R.id.Logout);
        Support=v.findViewById(R.id.Support);
        ProfilePic=v.findViewById(R.id.ProfilePic);
        PhoneNumberTXT = v.findViewById(R.id.PhoneNumberTXT);

        EditIcon.setOnClickListener(this);
        AddVehicle.setOnClickListener(this);
        Support.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.EditIcon:
                startActivity(new Intent(getActivity(), EditProfileActivity.class));
                break;
            case R.id.AddVehicle:
                startActivity(new Intent(getActivity(), VehicleListActivity.class));
                break;
            case R.id.Logout:
                provider.DeleteData();
                Intent intent = new Intent(getActivity(), LoginTypeSelectionActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                getActivity().startActivity(intent);
                getActivity().finish();
                break;
            case R.id.Support:
                Intent intent1 = new Intent(getActivity(), SupportActivity.class);
                intent1.putExtra("Type", "Support");
                startActivity(intent1);
                break;
        }
    }
}