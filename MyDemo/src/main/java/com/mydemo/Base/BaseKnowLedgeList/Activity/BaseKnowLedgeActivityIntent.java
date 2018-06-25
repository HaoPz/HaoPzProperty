package com.mydemo.Base.BaseKnowLedgeList.Activity;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.mydemo.Base.Base2Activity;
import com.mydemo.R;

/**
 * Activity 跳转界面
 * Created by HaoPz on 2018/4/4.
 */

public class BaseKnowLedgeActivityIntent extends Base2Activity {
    @Override
    public void setDate() {

    }

    @Override
    public void setView(View contentView) {
        TextView startModeToolBar = (TextView) findViewById(R.id.toolBar);
        startModeToolBar.setText("Activity 跳转及数据传递");
    }

    @Override
    protected View getContentView() {
        return LayoutInflater.from(BaseKnowLedgeActivityIntent.this).inflate(R.layout.layout_base_knowledge_activity_intent, null);
    }
}
