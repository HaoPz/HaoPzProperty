package com.mydemo.Base.BaseKnowLedgeList.Activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.mydemo.Base.Base2Activity;
import com.mydemo.R;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Activity 横竖屏切换问题
 * Created by HaoPz on 2018/4/2.
 */

public class BaseKnowLedgeActivityScreenChange extends Base2Activity {
    private static final String TAG = "Activity 生命周期";

    private String currentScreenOrientation = "竖屏";
    private TextView screenOrientation;

    @Override
    public void setDate() {

    }

    @Override
    public void setView(View contentView) {
        screenOrientation = (TextView) findViewById(R.id.screenOrientation);
        screenOrientation.setText(currentScreenOrientation);

        TextView startModeToolBar = (TextView) findViewById(R.id.toolBar);
        startModeToolBar.setText("Activity 生命周期");
    }

//    @Override
//    public Object onRetainCustomNonConfigurationInstance() {
//        HashMap<Integer, String> hashMap = new HashMap<>();
//        hashMap.put(1, "1");
//        Log.i(TAG, "切换横竖屏前,要保存的数据:");
//        return hashMap;
//    }

    @Override
    protected View getContentView() {
        return LayoutInflater.from(this).inflate(R.layout.layout_base_knowledge_activity_scrren_change, null);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");

//        getLastNonConfigurationInstance();
//        getLastCustomNonConfigurationInstance();// 内部调用的 getLastNonConfigurationInstance
//        HashMap<Integer, String> lastCustomNonConfigurationInstance = (HashMap<Integer, String>) getLastCustomNonConfigurationInstance();
//        if (lastCustomNonConfigurationInstance != null && lastCustomNonConfigurationInstance.entrySet() != null && lastCustomNonConfigurationInstance.size() > 0) {
//
//            Set<Map.Entry<Integer, String>> entries = lastCustomNonConfigurationInstance.entrySet();
//            for (Map.Entry<Integer, String> entry : entries) {
//                Integer key = entry.getKey();
//                String value = entry.getValue();
//                Log.i(TAG, "切换横竖屏后,数据信息:key " + key + "   value:" + value);
//            }
//
//        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.i(TAG, getResources().getConfiguration().orientation + "  2: 橫屏");
        // newConfig.orientation  1:竖屏 2:横屏
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) { // 横屏
            currentScreenOrientation = "横屏";
        } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) { // 竖屏
            currentScreenOrientation = "竖屏";
        }

        if (screenOrientation == null) {
            Log.i(TAG, "throw RunTimeExcepiton : screenOrientation is null");
            screenOrientation = (TextView) findViewById(R.id.screenOrientation);
            screenOrientation.setText(currentScreenOrientation);
        } else {
            screenOrientation.setText(currentScreenOrientation);
        }

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
}
