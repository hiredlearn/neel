package com.app.yoparkerassignment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.app.yoparkerassignment.Models.DataItem;
import com.app.yoparkerassignment.R;
import com.app.yoparkerassignment.interfces.OnRecyclerItemClick;
import com.bumptech.glide.Glide;
import com.github.siyamed.shapeimageview.RoundedImageView;

import java.util.List;

public class HomeListRecyclerAdapter extends RecyclerView.Adapter<HomeListRecyclerAdapter.ViewHolder> {
    private Context mContext;
    private boolean is_map;
    private List<DataItem> data;
    private int width;
    private OnRecyclerItemClick itemClick;
    public HomeListRecyclerAdapter(Context context, boolean is_map, List<DataItem> data, int width, OnRecyclerItemClick itemClick)
    {
        mContext=context;
        this.is_map=is_map;
        this.data = data;
        this.width = width;
        this.itemClick = itemClick;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(is_map)
        {
            View v = LayoutInflater.from(mContext).inflate(R.layout.raw_home_recycler, parent, false);
            return new ViewHolder(v);
        }
        else {
            View v = LayoutInflater.from(mContext).inflate(R.layout.raw_home_recycler, parent, false);
            return new ViewHolder(v);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mContext.startActivity(new Intent(mContext, ParkingDetailActivity.class));
                itemClick.OnItemClick(position);
            }
        });
        DataItem item = data.get(position);
        holder.ParkingName.setText(item.getName());
        if(item.getParkingCategory() != null) {
            holder.ParkingType.setText(item.getParkingCategory().getName());
        }
        holder.ParkingTime.setText(item.getOpeningTime()+" To "+item.getClosingTime());
        holder.DistanceTXT.setText(item.getDistance());
        if(item.getImages()!= null && item.getImages().size()!=0)
        {
            Glide.with(mContext).load(item.getImages().get(0).getUrl()).into(holder.ParkingImage);
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView ParkingName,ParkingType,ParkingTime,DistanceTXT;
        RoundedImageView ParkingImage;
        CardView Cardview;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ParkingName = itemView.findViewById(R.id.ParkingName);
            ParkingType = itemView.findViewById(R.id.ParkingType);
            ParkingTime = itemView.findViewById(R.id.ParkingTime);
            ParkingImage = itemView.findViewById(R.id.ParkingImage);
            DistanceTXT = itemView.findViewById(R.id.DistanceTXT);
            Cardview = itemView.findViewById(R.id.Cardview);
            Cardview.getLayoutParams().width = width - mContext.getResources().getDimensionPixelSize(R.dimen._20sdp);
        }
    }


}
