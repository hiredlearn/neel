package com.app.yoparkerassignment.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.yoparkerassignment.Models.UpcomingBookingItem;
import com.app.yoparkerassignment.R;
import com.app.yoparkerassignment.adapter.UpcomingRecyclerAdapter;

import java.util.List;

public class UpComingFragment extends Fragment {
    private RecyclerView recyclerView;
    private List<UpcomingBookingItem> upcomingBookingItems;
    private TextView NoDataTXT;

    public static UpComingFragment newInstance(List<UpcomingBookingItem> upcomingBookingItems) {
        UpComingFragment fragment = new UpComingFragment();
        fragment.upcomingBookingItems = upcomingBookingItems;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_upcoming, null, false);
        recyclerView = v.findViewById(R.id.recyclerView);
        NoDataTXT = v.findViewById(R.id.NoDataTXT);
        if (upcomingBookingItems.size() == 0) {
            NoDataTXT.setVisibility(View.VISIBLE);
        } else {
            NoDataTXT.setVisibility(View.GONE);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        recyclerView.setAdapter(new UpcomingRecyclerAdapter(requireActivity(), upcomingBookingItems));
        return v;
    }
}
