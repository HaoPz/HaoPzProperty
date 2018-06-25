package com.mydemo.Base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.mydemo.Utils.ActivityCollector;

/**
 * Created by HaoPz on 2017/11/28.
 */

public abstract class Base2Activity extends AppCompatActivity {

    private View contentView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityCollector.addActivity(this);

        contentView = getContentView();
        setContentView(contentView);

        setView(contentView);

        setDate();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    public abstract void setDate();

    public abstract void setView(View contentView);

    protected abstract View getContentView();

    public void gotoActivity(Class cls) {
        startActivity(new Intent(Base2Activity.this, cls));
    }

    protected <T> T inflateView(@LayoutRes int layoutResource) {

        //noinspection unchecked
        return (T) LayoutInflater.from(this).inflate(layoutResource, null);
    }
}
