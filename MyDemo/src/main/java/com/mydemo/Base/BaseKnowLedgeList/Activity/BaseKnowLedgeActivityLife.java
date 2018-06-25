package com.mydemo.Base.BaseKnowLedgeList.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.mydemo.Base.Base2Activity;
import com.mydemo.MainActivity;
import com.mydemo.R;

/**
 * Activity 生命周期
 * Created by HaoPz on 2018/4/2.
 */

public class BaseKnowLedgeActivityLife extends Base2Activity {
    private static final String TAG = "Activity 生命周期";

    @Override
    public void setDate() {

    }

    @Override
    public void setView(View contentView) {
        TextView startModeToolBar = (TextView) findViewById(R.id.toolBar);
        startModeToolBar.setText("Activity 生命周期");
    }

    @Override
    protected View getContentView() {
        return LayoutInflater.from(this).inflate(R.layout.layout_base_knowledge_activity_life, null);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }


    public void goToMainActiviyt(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }
}
