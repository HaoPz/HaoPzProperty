package com.mydemo.Base.BaseKnowLedgeList.VersionDiff;

import android.text.Layout;
import android.view.View;
import android.widget.TextView;

import com.mydemo.Base.Base2Activity;
import com.mydemo.R;

/**
 * Created by HaoPz on 2018/5/8.
 */

public class AndroidVersionDiffFileProvider extends Base2Activity {

    @Override
    public void setDate() {

    }

    @Override
    public void setView(View contentView) {
        TextView toolBar = (TextView) contentView.findViewById(R.id.toolBar);
        toolBar.setText("Android Verion 7.0 FileProvider");
    }

    @Override
    protected View getContentView() {
        return inflateView(R.layout.layout_base_knowledge_version_diff_fillprovider);
    }
}
