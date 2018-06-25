package com.mydemo.Base.BaseKnowLedgeList.VersionDiff;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.mydemo.Base.Base2Activity;
import com.mydemo.R;

/**
 * Android版本间差异
 * Created by HaoPz on 2018/5/8.
 */

public class AndroidVersionDiffActivity extends Base2Activity {

    private TextView toolbar;
    private TextView meterialDesign;
    private TextView androidPermission;
    private TextView fielProvider;

    @Override
    public void setDate() {
        meterialDesign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 进入到 MD 界面
            }
        });

        androidPermission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 运行时权限
                startActivity(new Intent(AndroidVersionDiffActivity.this, AndroidVersionDiffPermission.class));
            }
        });

        fielProvider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // FileProvider
                startActivity(new Intent(AndroidVersionDiffActivity.this, AndroidVersionDiffFileProvider.class));
            }
        });
    }

    @Override
    public void setView(View contentView) {
        toolbar = (TextView) contentView.findViewById(R.id.toolBar);
        toolbar.setText("Android 版本间差异性");

        meterialDesign = (TextView) contentView.findViewById(R.id.MeterialDesign);
        androidPermission = (TextView) contentView.findViewById(R.id.androidPermission);
        fielProvider = (TextView) contentView.findViewById(R.id.fielProvider);
    }

    @Override
    protected View getContentView() {
        return LayoutInflater.from(this).inflate(R.layout.layout_base_knowledge_version_diff, null);
    }
}
