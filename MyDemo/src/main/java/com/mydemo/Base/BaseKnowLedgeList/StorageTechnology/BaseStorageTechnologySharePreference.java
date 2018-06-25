package com.mydemo.Base.BaseKnowLedgeList.StorageTechnology;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.mydemo.Base.Base2Activity;
import com.mydemo.Base.BaseKnowLedgeList.Web.StartLookWebActivity;
import com.mydemo.R;

/**
 * SP 存储
 * Created by HaoPz on 2018/4/25.
 */

public class BaseStorageTechnologySharePreference extends Base2Activity {

    private TextView spyuanlifenxi;

    @Override
    public void setDate() {
        spyuanlifenxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartLookWebActivity.startActivity(BaseStorageTechnologySharePreference.this,BaseStorageTechnologySharePreference.this,
                        "https://www.processon.com/diagraming/5ad7f41be4b046910641d3ea", "SharePreference 源码解析");
            }
        });
    }

    @Override
    public void setView(View contentView) {
        TextView toolBar = (TextView) contentView.findViewById(R.id.toolBar);
        spyuanlifenxi = (TextView) contentView.findViewById(R.id.spyuanlifenxi);
        toolBar.setText("持久化存储 SharePreference");
    }

    @Override
    protected View getContentView() {
        return LayoutInflater.from(this).inflate(R.layout.layout_baseknowledge_list_storage_technology_sharepreference, null );
    }
}
