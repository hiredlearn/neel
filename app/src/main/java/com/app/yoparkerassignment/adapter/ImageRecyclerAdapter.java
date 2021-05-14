package com.app.yoparkerassignment.adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.yoparkerassignment.R;
import com.app.yoparkerassignment.interfces.OnItemClick;
import com.bumptech.glide.Glide;
import com.github.siyamed.shapeimageview.RoundedImageView;
import com.nguyenhoanglam.imagepicker.model.Image;


import java.util.ArrayList;

public class ImageRecyclerAdapter extends RecyclerView.Adapter<ImageRecyclerAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<Image> images;
    private OnItemClick itemClick;
    public ImageRecyclerAdapter(Context context, ArrayList<Image> images, OnItemClick itemClick)
    {
        mContext=context;
        this.images=images;
        this.itemClick=itemClick;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(mContext).inflate(R.layout.recyler_image_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.MainLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClick.OnItemPositon(position,images);
                }
            });
            if(position <= images.size()-1) {
                holder.DeleteBTN.setVisibility(View.VISIBLE);
                holder.imageView2.setVisibility(View.VISIBLE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {

                    Glide.with(mContext)
                            .load(images.get(position).getUri())
                            .into(holder.imageView2);
                } else {
                    Glide.with(mContext)
                            .load(images.get(position).getPath())
                            .into(holder.imageView2);
                }
            }
            else
            {
                holder.DeleteBTN.setVisibility(View.GONE);
                holder.imageView2.setVisibility(View.GONE);
            }
            holder.DeleteBTN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    images.remove(position);
                    itemClick.OnItemDelete(position);
                    notifyDataSetChanged();
                }
            });
    }

    @Override
    public int getItemCount() {
        if(images.size()>0 && images.size()==5) {
            return images.size();
        }
        else if(images.size()>0 && images.size()<5)
        {
            return images.size()+1;
        }
        else{
            return 1;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        RelativeLayout MainLayout;
        RoundedImageView imageView2;
        ImageView DeleteBTN;
        TextView Doc_name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            MainLayout=itemView.findViewById(R.id.MainLayout);
            imageView2=itemView.findViewById(R.id.imageView2);
            DeleteBTN = itemView.findViewById(R.id.DeleteBTN);
            Doc_name = itemView.findViewById(R.id.Doc_name);
            Doc_name.setVisibility(View.GONE);
        }
    }
public void UpDateData(ArrayList<Image> img){
     images.clear();
     images.addAll(img);
     notifyDataSetChanged();
}
}
