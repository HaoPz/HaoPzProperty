package com.mydemo.Base.BaseKnowLedgeList.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.mydemo.Base.Base2Activity;
import com.mydemo.R;

/**
 * Created by HaoPz on 2018/4/2.
 */

public class BaseKnowLedgeActivityIsWhat extends Base2Activity {

    @Override
    protected View getContentView() {
        return LayoutInflater.from(BaseKnowLedgeActivityIsWhat.this).inflate(
                R.layout.layout_base_knowledge_activity_is_what, null);
    }

    @Override
    public void setDate() {

    }

    @Override
    public void setView(View contentView) {
        TextView toolBar = (TextView) findViewById(R.id.toolBar);
        toolBar.setText("Activity 简介");
    }
}
