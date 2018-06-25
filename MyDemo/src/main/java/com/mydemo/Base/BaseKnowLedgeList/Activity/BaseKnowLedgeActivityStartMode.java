package com.mydemo.Base.BaseKnowLedgeList.Activity;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.mydemo.Base.Base2Activity;
import com.mydemo.R;

/**
 * Activity 启动模式
 * Created by HaoPz on 2018/4/3.
 */

public class BaseKnowLedgeActivityStartMode extends Base2Activity {
    @Override
    public void setDate() {

    }

    @Override
    public void setView(View contentView) {
        TextView startModeToolBar = (TextView) contentView.findViewById(R.id.toolBar);
        startModeToolBar.setText("Activity 启动模式");
    }

    @Override
    protected View getContentView() {
        return LayoutInflater.from(this).inflate(R.layout.layout_base_knowledge_activity_start_mode, null);
    }
}
