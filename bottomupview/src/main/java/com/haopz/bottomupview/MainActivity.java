package com.haopz.bottomupview;

import android.Manifest;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView btmUpForPopupWindowm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView btmUpForDialog = findViewById(R.id.btmUpForDialog);
        btmUpForPopupWindowm = findViewById(R.id.btmUpForPopupWindow);

        btmUpForDialog.setOnClickListener(this);
        btmUpForPopupWindowm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btmUpForDialog:
                Dialog dialog = new Dialog(MainActivity.this, R.style.upBtmStyle);
                dialog.setCanceledOnTouchOutside(true);
                View btmUpForDialog = LayoutInflater.from(MainActivity.this).inflate(R.layout.item_up_btm_dialog, null, false);
                dialog.setContentView(btmUpForDialog);
                Window window = dialog.getWindow();
                window.setGravity(Gravity.CENTER);
                WindowManager.LayoutParams attributes = window.getAttributes();
                attributes.alpha = (float) 0.9;
                attributes.x = 0;
                attributes.y = 0;
                btmUpForDialog.measure(0,0);
                attributes.width = getResources().getDisplayMetrics().widthPixels;
                attributes.height = btmUpForDialog.getMeasuredHeight();
                Log.i("width-->" , String.valueOf(getResources().getDisplayMetrics().widthPixels));
                window.setAttributes(attributes);
                dialog.show();
                break;

            case R.id.btmUpForPopupWindow:
                View btmUpForDialog1 = LayoutInflater.from(MainActivity.this).inflate(R.layout.item_up_btm_dialog, null, false);
                PopupWindow popupWindow = new PopupWindow(btmUpForDialog1,ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                popupWindow.setAnimationStyle(R.style.DialogAnimation);
                popupWindow.setOutsideTouchable(true);
                popupWindow.setFocusable(true);
                final WindowManager.LayoutParams attributes1 = getWindow().getAttributes();
                attributes1.alpha = 0.7f;
                getWindow().setAttributes(attributes1);
                popupWindow.showAtLocation(getWindow().getDecorView(), Gravity.BOTTOM,0,0);
                popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        attributes1.alpha = 1.0f;
                        getWindow().setAttributes(attributes1);
                    }
                });
                break;
        }
    }
}
