package com.mydemo.Base.BaseKnowLedgeList.VersionDiff;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.mydemo.Base.Base2Activity;
import com.mydemo.R;

/**
 * 运行时权限
 * Created by HaoPz on 2018/5/8.
 */

public class AndroidVersionDiffPermission extends Base2Activity {

    private TextView toolBar;

    @Override
    public void setDate() {

    }

    @Override
    public void setView(View contentView) {
        toolBar = (TextView) contentView.findViewById(R.id.toolBar);
        toolBar.setText("Android Verion 6.0 运行时权限");
    }

    @Override
    protected View getContentView() {
        return LayoutInflater.from(this).inflate(R.layout.layout_base_knowledge_version_diff_permission,null);
    }
}
