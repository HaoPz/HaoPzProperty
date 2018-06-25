package com.mydemo.Base.BaseKnowLedgeList.Provider;

import android.view.LayoutInflater;
import android.view.View;

import com.mydemo.Base.Base2Activity;
import com.mydemo.R;

/**
 * Created by HaoPz on 2018/4/25.
 */

public class BaseKnowLedgeContentProvider extends Base2Activity {
    @Override
    public void setDate() {

    }

    @Override
    public void setView(View contentView) {

    }

    @Override
    protected View getContentView() {
        return LayoutInflater.from(this).inflate(R.layout.layout_base_knowledge_provider, null);
    }
}
