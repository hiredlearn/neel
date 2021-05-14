package com.app.yoparkerassignment.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.yoparkerassignment.Models.UpcomingBookingItem;
import com.app.yoparkerassignment.R;
import com.github.siyamed.shapeimageview.RoundedImageView;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class UpcomingRecyclerAdapter extends RecyclerView.Adapter<UpcomingRecyclerAdapter.ViewHolder> {
    private Context mContext;
    private List<UpcomingBookingItem> items;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//"2020-10-20 06:00:00"
    private SimpleDateFormat newDate = new SimpleDateFormat("dd MMM yyyy");

    public UpcomingRecyclerAdapter(Context context, List<UpcomingBookingItem> items) {
        mContext = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.raw_upcoming_recycler, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UpcomingBookingItem item = items.get(position);
        holder.rupeesTXT.setText(Html.fromHtml(String.format(mContext.getResources().getString(R.string._500_rs), item.getTotalAmount().replace(".00", ""))));
        holder.ParkingName.setText(item.getParking().getName());
        holder.ParkingType.setText(item.getParking().getAddress());
        try {
            holder.DateTXT.setText(newDate.format(dateFormat.parse(item.getStartDate())));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String start = item.getStartDate().split(" ")[1].split(":")[0] + ":" + item.getStartDate().split(" ")[1].split(":")[1];
        String end = item.getEndDate().split(" ")[1].split(":")[0] + ":" + item.getStartDate().split(" ")[1].split(":")[1];
        holder.Timing.setText(start + " To " + end);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView rupeesTXT, ParkingName, ParkingType, DateTXT, Timing;
        RoundedImageView ParkingImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rupeesTXT = itemView.findViewById(R.id.rupeesTXT);
            ParkingName = itemView.findViewById(R.id.ParkingName);
            ParkingType = itemView.findViewById(R.id.ParkingType);
            DateTXT = itemView.findViewById(R.id.DateTXT);
            Timing = itemView.findViewById(R.id.Timing);
            ParkingImage = itemView.findViewById(R.id.ParkingImage);

        }
    }
}
