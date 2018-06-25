package com.mydemo.Base.UIActivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.mydemo.Base.UIActivity.widget.TanXingHead;
import com.mydemo.R;

/**
 * Created by HaoPz on 2018/6/25.
 *
 * 弹性 View，
 */

public class TanXingHeadActivity extends AppCompatActivity {

    private TanXingHead tanXingHead;
    private LinearLayout scrollChildren;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tan_xing_head);

        tanXingHead = (TanXingHead) findViewById(R.id.tanXingHead);

        scrollChildren = (LinearLayout) findViewById(R.id.scrollChildren);
    }
}
