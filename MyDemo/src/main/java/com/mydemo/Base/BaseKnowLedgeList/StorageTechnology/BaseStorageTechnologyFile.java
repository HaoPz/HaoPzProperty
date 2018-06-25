package com.mydemo.Base.BaseKnowLedgeList.StorageTechnology;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.mydemo.Base.Base2Activity;
import com.mydemo.R;

/**
 * Created by HaoPz on 2018/4/25.
 */

public class BaseStorageTechnologyFile extends Base2Activity {
    @Override
    public void setDate() {

    }

    @Override
    public void setView(View contentView) {
        TextView toolBar = (TextView) contentView.findViewById(R.id.toolBar);
        toolBar.setText("持久化存储 File");

    }

    @Override
    protected View getContentView() {
        return LayoutInflater.from(this).inflate(R.layout.layout_baseknowledge_list_storage_technology_file, null);
    }
}
