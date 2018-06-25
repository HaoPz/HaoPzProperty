package com.mydemo.Base.BaseKnowLedgeList.Activity;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.mydemo.Base.Base2Activity;
import com.mydemo.MainActivity;
import com.mydemo.R;

/**
 * 基础知识Activity
 * Created by HaoPz on 2018/4/2.
 */

public class BaseKnowLedgeActivity extends Base2Activity {

    private TextView toolBarTextView;
    private TextView activityIsWhat;
    private TextView activityLife;
    private TextView activityScreen;
    private TextView activityStartMode;
    private TextView activityIntent;

    /*@Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_base_knowledge_activity);
    }*/

    @Override
    protected View getContentView() {
        return LayoutInflater.from(BaseKnowLedgeActivity.this).inflate(R.layout.layout_base_knowledge_activity, null);
    }


    @Override
    public void setDate() {
        activityIsWhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoActivity(BaseKnowLedgeActivityIsWhat.class);
            }
        });

        activityLife.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoActivity(BaseKnowLedgeActivityLife.class);
            }
        });

        activityScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoActivity(BaseKnowLedgeActivityScreenChange.class);
            }
        });

        activityStartMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoActivity(BaseKnowLedgeActivityStartMode.class);
            }
        });

        activityIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoActivity(BaseKnowLedgeActivityIntent.class);
            }
        });
    }

    @Override
    public void setView(View contentView) {
        toolBarTextView = (TextView) contentView.findViewById(R.id.toolBar);
        toolBarTextView.setText(" Android 之 Activity");

        activityIsWhat = (TextView) findViewById(R.id.activityIsWhat);
        activityIsWhat.setText(" Activity 简介");

        activityLife = (TextView) findViewById(R.id.activityLife);
        activityLife.setText(" Activity 生命周期");

        activityScreen = (TextView) findViewById(R.id.activityScreen);
        activityScreen.setText(" Activity 横竖屏");

        activityStartMode = (TextView) findViewById(R.id.activityStartMode);
        activityStartMode.setText(" Activity 启动模式");

        activityIntent = (TextView) findViewById(R.id.activityIntent);
        activityIntent.setText(" Activity 跳转及数据传递");

    }

}
