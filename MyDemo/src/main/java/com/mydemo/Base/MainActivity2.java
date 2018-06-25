package com.mydemo.Base;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.mydemo.Base.BaseKnowLedgeList.BaseKnowLedgeListActivity;
import com.mydemo.Base.UIActivity.UIBaseActivity;
import com.mydemo.R;

public class MainActivity2 extends Base2Activity {
    private TextView baseKnowledgeText;
    private TextView baseKnowledgeText1;
    private TextView toolBarTextView;

    @Override
    public void setDate() {
        baseKnowledgeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoActivity(BaseKnowLedgeListActivity.class);
            }
        });
        baseKnowledgeText1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoActivity(UIBaseActivity.class);
            }
        });
    }

    @Override
    public void setView(View contentView) {
        toolBarTextView = (TextView) contentView.findViewById(R.id.toolBar);
        toolBarTextView.setText("Android 知识体系");

        baseKnowledgeText = (TextView) findViewById(R.id.item_text);
        baseKnowledgeText.setText("基础知识");

        baseKnowledgeText1 = (TextView) findViewById(R.id.item_text1);
        baseKnowledgeText1.setText("UI效果");
    }

    @Override
    protected View getContentView() {
        return LayoutInflater.from(MainActivity2.this).inflate(R.layout.activity_main2, null);
    }

}
