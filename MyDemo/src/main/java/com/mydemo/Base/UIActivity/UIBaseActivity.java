package com.mydemo.Base.UIActivity;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.mydemo.Base.Base2Activity;
import com.mydemo.R;

/**
 * Created by HaoPz on 2018/6/25.
 */

public class UIBaseActivity extends Base2Activity {

    private TextView item_tan_xing_head;
    private TextView toolBarTextView;

    @Override
    public void setDate() {
        item_tan_xing_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goTanXingHead = new Intent(UIBaseActivity.this, TanXingHeadActivity.class);
                startActivity(goTanXingHead);
            }
        });
    }

    @Override
    public void setView(View contentView) {
        toolBarTextView = (TextView) contentView.findViewById(R.id.toolBar);
        toolBarTextView.setText("Android UI 效果");

        item_tan_xing_head = (TextView) contentView.findViewById(R.id.item_tan_xing_head);
        item_tan_xing_head.setText("弹性View");
    }

    @Override
    protected View getContentView() {
        return LayoutInflater.from(UIBaseActivity.this).inflate(R.layout.activity_ui_base,null);
    }
}
