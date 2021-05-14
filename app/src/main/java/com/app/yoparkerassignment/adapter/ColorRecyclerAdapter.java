package com.app.yoparkerassignment.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;


import com.app.yoparkerassignment.Models.ColorsItem;
import com.app.yoparkerassignment.R;

import java.util.List;

public class ColorRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<ColorsItem> colorsItems;
    private static int TYPE_NORMAL = 0;
    private static int TYPE_OTHER = 1;
    private ColorSelected colorSelected;

    public ColorRecyclerAdapter(Context context, List<ColorsItem> colorsItems, ColorSelected colorSelected) {
        this.context = context;
        this.colorsItems = colorsItems;
        this.colorSelected = colorSelected;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_NORMAL) {
            View v = LayoutInflater.from(context).inflate(R.layout.color_item, null, false);
            return new ViewHolder(v);
        } else {
            View v = LayoutInflater.from(context).inflate(R.layout.other_item, null, false);
            return new OtherHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder1, int position) {
        ViewHolder holder;
        OtherHolder otherHolder;
        if (holder1 instanceof ViewHolder) {
            holder = (ViewHolder) holder1;
            ColorsItem item = colorsItems.get(position);
            Drawable background = holder.colorImage.getBackground();
            if (background instanceof ShapeDrawable) {
                // cast to 'ShapeDrawable'
                ShapeDrawable shapeDrawable = (ShapeDrawable) background;
                shapeDrawable.getPaint().setColor(Color.parseColor(item.getCode()));
                if (item.getCode().equalsIgnoreCase("#ffffff")) {
                    //holder.selectImage.setColorFilter(Color.parseColor("#000000"));
                    holder.selectImage.setColorFilter(ContextCompat.getColor(context, R.color.black), android.graphics.PorterDuff.Mode.SRC_IN);
                } else {
                    holder.selectImage.setColorFilter(ContextCompat.getColor(context, R.color.white), android.graphics.PorterDuff.Mode.SRC_IN);
                }
            } else if (background instanceof GradientDrawable) {
                // cast to 'GradientDrawable'
                GradientDrawable gradientDrawable = (GradientDrawable) background;
                gradientDrawable.setColor(Color.parseColor(item.getCode()));
                if (item.getCode().equalsIgnoreCase("#ffffff")) {
                    //holder.selectImage.setColorFilter(Color.parseColor("#000000"));
                    holder.selectImage.setColorFilter(ContextCompat.getColor(context, R.color.black), android.graphics.PorterDuff.Mode.SRC_IN);
                } else {
                    holder.selectImage.setColorFilter(ContextCompat.getColor(context, R.color.white), android.graphics.PorterDuff.Mode.SRC_IN);
                }
            } else if (background instanceof ColorDrawable) {
                // alpha value may need to be set again after this call
                ColorDrawable colorDrawable = (ColorDrawable) background;
                colorDrawable.setColor(Color.parseColor(item.getCode()));
                if (item.getCode().equalsIgnoreCase("#ffffff")) {
                    //holder.selectImage.setColorFilter(Color.parseColor("#000000"));
                    holder.selectImage.setColorFilter(ContextCompat.getColor(context, R.color.black), android.graphics.PorterDuff.Mode.SRC_IN);
                } else {
                    holder.selectImage.setColorFilter(ContextCompat.getColor(context, R.color.white), android.graphics.PorterDuff.Mode.SRC_IN);
                }
            }
            holder.colorImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    for (int i = 0; i < colorsItems.size(); i++) {
                        ColorsItem item1 = colorsItems.get(i);
                        if (i == position) {
                            item1.setSelected(true);
                            colorSelected.ColorString(item1.getCode());
                        } else {
                            item1.setSelected(false);
                        }
                    }
                    notifyDataSetChanged();
                }
            });
            if (item.isSelected()) {
                holder.selectImage.setVisibility(View.VISIBLE);
            } else {
                holder.selectImage.setVisibility(View.GONE);
            }
        } else if (holder1 instanceof OtherHolder) {
            otherHolder = (OtherHolder) holder1;

            otherHolder.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                }
            });
        }
        /*imageView.setColorFilter(ContextCompat.getColor(context, R.color.COLOR_YOUR_COLOR), android.graphics.PorterDuff.Mode.MULTIPLY);*/
    }

    @Override
    public int getItemViewType(int position) {
        if (position == colorsItems.size()) {
            return TYPE_OTHER;
        } else {
            return TYPE_NORMAL;
        }

    }

    @Override
    public int getItemCount() {
        return colorsItems.size() + 1;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView colorImage;
        ImageView selectImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            colorImage = itemView.findViewById(R.id.colorImage);
            selectImage = itemView.findViewById(R.id.selectImage);

        }
    }

    class OtherHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public OtherHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
        }

    }

    public interface ColorSelected {
        void ColorString(String colorCode);
    }
}
