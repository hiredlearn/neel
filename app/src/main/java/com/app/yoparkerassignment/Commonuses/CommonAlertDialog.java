package com.app.yoparkerassignment.Commonuses;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.app.yoparkerassignment.R;


public class CommonAlertDialog {

    public static AlertDialog CreateDialog(Context context, String msg) {
        AlertDialog alertDialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        View v = LayoutInflater.from(context).inflate(R.layout.alert_dialog_view, null, false);
        TextView messageTXT = v.findViewById(R.id.messageTXT);
        messageTXT.setText(msg);
        builder.setView(v);
        builder.setCancelable(false);
        alertDialog = builder.create();
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return alertDialog;
    }
}
