package com.haopz.androidpaomadeng;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private final String TAG =getClass().getSimpleName();
    private String paomadengInfo = "序列化和反序";

    private MarqueeTextView paomadeng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        paomadeng = (MarqueeTextView)findViewById(R.id.paomadeng);
        paomadeng.setText(paomadengInfo);

        Log.i("xdpi : "+TAG, String.valueOf(getResources().getDisplayMetrics().xdpi));
        Log.i("ydpi : "+TAG, String.valueOf(getResources().getDisplayMetrics().ydpi));

    }
}
