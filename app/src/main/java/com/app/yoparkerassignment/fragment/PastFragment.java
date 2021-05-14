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


import com.app.yoparkerassignment.Models.PastBookingItem;
import com.app.yoparkerassignment.R;
import com.app.yoparkerassignment.adapter.PastRecyclerAdapter;

import java.util.List;

public class PastFragment extends Fragment {
    private RecyclerView recyclerView;
    private List<PastBookingItem> pastBookingItems;
    private TextView NoDataTXT;

    public static PastFragment newInstance(List<PastBookingItem> pastBookingItems) {
        PastFragment fragment = new PastFragment();
        fragment.pastBookingItems = pastBookingItems;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_upcoming, null, false);

        recyclerView = v.findViewById(R.id.recyclerView);
        NoDataTXT = v.findViewById(R.id.NoDataTXT);
        if (pastBookingItems.size() == 0) {
            NoDataTXT.setVisibility(View.VISIBLE);
        } else {
            NoDataTXT.setVisibility(View.GONE);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        recyclerView.setAdapter(new PastRecyclerAdapter(requireActivity(), pastBookingItems));
        return v;
    }
}
