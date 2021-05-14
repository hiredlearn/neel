package com.app.yoparkerassignment.Commonuses;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.widget.DatePicker;

import androidx.fragment.app.Fragment;

import com.app.yoparkerassignment.Models.DataItem;
import com.app.yoparkerassignment.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;
import java.util.List;
import java.util.Stack;

public class TextClass {
    public static DataItem item;
    public static List<DataItem> items;
    public static String VEHICLE_TYPE = "vehicle_type";
    public static String FREQUENCY = "frequency";
    public static String Distance;
    public static String Total_hour_day;
    public static Stack<Fragment> fragmentStack = new Stack<Fragment>();


    public static int is_approve = 0;
    public static void getDate(Context context, final TextInputEditText edtDate){
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(context, R.style.DialogTheme,new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                edtDate.setText(i2 + "-" + (i1 + 1) + "-" + i);

            }
        },mYear,mMonth,mDay);
        /*long now = System.currentTimeMillis() - 1000;
        datePickerDialog.getDatePicker().setMinDate(now);*/
        datePickerDialog.show();
        datePickerDialog.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#081951"));
        datePickerDialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#081951"));

    }

}
