package com.app.yoparkerassignment.interfces;

import com.nguyenhoanglam.imagepicker.model.Image;

import java.util.ArrayList;

public interface OnItemClick {
    public void OnItemPositon(int position, ArrayList<Image> images);
    void OnItemDelete(int position);
}
