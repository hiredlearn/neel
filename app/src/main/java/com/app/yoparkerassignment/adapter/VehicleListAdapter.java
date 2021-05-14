package com.app.yoparkerassignment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.yoparkerassignment.Models.VehicleDataItem;
import com.app.yoparkerassignment.Models.VehicleImagesItem;
import com.app.yoparkerassignment.R;
import com.app.yoparkerassignment.interfces.OnRecyclerItemClick;
import com.bumptech.glide.Glide;
import com.github.siyamed.shapeimageview.RoundedImageView;

import java.util.List;

public class VehicleListAdapter extends RecyclerView.Adapter<VehicleListAdapter.ViewHolder> {
    List<VehicleDataItem> dataItems;
    Context context;
    OnRecyclerItemClick itemClick;

    public VehicleListAdapter(List<VehicleDataItem> dataItems, Context context, OnRecyclerItemClick itemClick) {
        this.dataItems = dataItems;
        this.context = context;
        this.itemClick = itemClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.vehicle_recycler_item, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        VehicleDataItem item = dataItems.get(position);
        holder.VehicleType.setText(item.getType().getName());
        holder.VehicleCategory.setText(item.getCategory().getName());
        holder.VehicleBrand.setText(item.getBrand().getName());
        holder.VehicleModel.setText(item.getModel().getName());
        holder.VehicleColor.setText(item.getColor());
        holder.VehicleNumber.setText(item.getNumber());
        if (item.getImages() != null && item.getImages().size() > 0) {
            VehicleImagesItem item1 = item.getImages().get(0);
            Glide.with(context)
                    .load(item1.getUrl())
                    .into(holder.vehicleImage);
        }
        holder.lay_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClick.OnItemClick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView VehicleType, VehicleCategory, VehicleColor, VehicleBrand, VehicleModel, VehicleNumber;
        RoundedImageView vehicleImage;
        LinearLayout lay_delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            VehicleType = itemView.findViewById(R.id.VehicleType);
            VehicleCategory = itemView.findViewById(R.id.VehicleCategory);
            VehicleColor = itemView.findViewById(R.id.VehicleColor);
            VehicleBrand = itemView.findViewById(R.id.VehicleBrand);
            VehicleModel = itemView.findViewById(R.id.VehicleModel);
            VehicleNumber = itemView.findViewById(R.id.VehicleNumber);
            vehicleImage = itemView.findViewById(R.id.vehicleImage);
            lay_delete = itemView.findViewById(R.id.lay_delete);
        }
    }
}
