package com.app.yoparkerassignment.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.yoparkerassignment.Commonuses.PreferenceProvider;
import com.app.yoparkerassignment.LocationService.Utils;
import com.app.yoparkerassignment.Models.GetAllParkingResponse;
import com.app.yoparkerassignment.Models.MyBookingResponse;
import com.app.yoparkerassignment.Models.PastBookingItem;
import com.app.yoparkerassignment.Models.UpcomingBookingItem;
import com.app.yoparkerassignment.R;
import com.app.yoparkerassignment.home.HomeActivity;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MyBookingFragment extends Fragment {
    private String[] titles = {"UPCOMING", "HISTORY"};
    private ViewPager pager;
    private TabLayout tabLayout;
    private List<UpcomingBookingItem> upcomingBookingItems;
    private List<PastBookingItem> pastBookingItems;
    private PreferenceProvider provider;
    private boolean isInternetConnection;
    private AlertDialog alertDialog;
    private JSONObject userObject = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Context ctx = new ContextThemeWrapper(getActivity(), R.style.AppThemeSecond);
        LayoutInflater inflater1 = inflater.cloneInContext(ctx);
        View v = inflater1.inflate(R.layout.fragment_my_booking, null, false);
        provider = new PreferenceProvider(getActivity());
        pager = v.findViewById(R.id.pager);
        tabLayout = v.findViewById(R.id.tabLayout);

        String userObjStr = provider.GetLoginResponse();
        try {
            userObject = new JSONObject(userObjStr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        getBookingsList();


        return v;

    }

    private void getBookingsList() {
        upcomingBookingItems = new ArrayList<>();
        pastBookingItems = new ArrayList<>();
        Gson gson = new Gson();
        String jsonFileString = Utils.loadJSONFromAsset(getActivity(), "parkingbooking.json");
        MyBookingResponse myBookingResponse = gson.fromJson(jsonFileString, MyBookingResponse.class);
        if (myBookingResponse.getStatusCode() == HomeActivity.homeActivity.getResources().getInteger(R.integer.Response_Success)) {
            if (pager != null) {
                List<UpcomingBookingItem> temp = myBookingResponse.getData().getUpcomingBooking();
                for (int i = 0; i < temp.size(); i++) {
                    if (temp.get(i).getParking() != null) {
                        upcomingBookingItems.add(temp.get(i));
                    }
                }
                //upcomingBookingItems = myBookingResponse.getData().getUpcomingBooking();
                List<PastBookingItem> temp1 = myBookingResponse.getData().getPastBooking();
                for (int i = 0; i < temp1.size(); i++) {
                    if (temp1.get(i).getParking() != null) {
                        pastBookingItems.add(temp1.get(i));
                    }
                }
                //pastBookingItems = myBookingResponse.getData().getPastBooking();
                pager.setAdapter(new ViewPagerAdapter(getChildFragmentManager()));
                tabLayout.setupWithViewPager(pager);
            }
        }
    }

    public class ViewPagerAdapter extends FragmentStatePagerAdapter {
        public ViewPagerAdapter(@NonNull FragmentManager fm) {
            super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return UpComingFragment.newInstance(upcomingBookingItems);
            } else {
                return PastFragment.newInstance(pastBookingItems);
            }
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }


}