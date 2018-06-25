package com.mydemo.Base.BaseKnowLedgeList.StorageTechnology;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.mydemo.Base.Base2Activity;
import com.mydemo.R;

/**
 * 持久化技术列表
 * Created by HaoPz on 2018/4/25.
 */

public class StorageTechnologyList extends Base2Activity {

    private TextView sp;
    private TextView file;
    private TextView sqLite;

    @Override
    public void setDate() {
        sp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoActivity(BaseStorageTechnologySharePreference.class);
            }
        });
        file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoActivity(BaseStorageTechnologyFile.class);
            }
        });
        sqLite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoActivity(BaseStorageTechnologySQLite.class);
            }
        });
    }

    @Override
    public void setView(View contentView) {
        TextView toolBar = (TextView) contentView.findViewById(R.id.toolBar);
        toolBar.setText("Android 持久化存储");

        sp = (TextView) contentView.findViewById(R.id.SP);
        sp.setText("SharePreference Storage");
        file = (TextView) contentView.findViewById(R.id.File);
        file.setText("File Storage");
        sqLite = (TextView) contentView.findViewById(R.id.SQLLite);
        sqLite.setText("SQLite Storage");
    }

    @Override
    protected View getContentView() {
        return LayoutInflater.from(this).inflate(R.layout.layout_baseknowledge_list_storage_technology, null);
    }
}
