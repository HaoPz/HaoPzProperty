package com.mydemo.Base.BaseKnowLedgeList.BroadCastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.mydemo.Base.Base2Activity;
import com.mydemo.Base.MainActivity2;
import com.mydemo.R;

/**
 * Created by HaoPz on 2018/4/12.
 */

public class BaseKnowLedgeBroadCastReceiver extends Base2Activity {
    @Override
    public void setDate() {

    }

    @Override
    public void setView(View contentView) {

    }

    @Override
    protected View getContentView() {
        return LayoutInflater.from(this).inflate(R.layout.layout_base_knowledge_broadcast, null);
    }

    public class OrderBroadCast1 extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "我是有序广播-- 在ManiFest注册的", Toast.LENGTH_SHORT).show();
            Log.d("OrderBroadCast1 --> ","maniFest注册 ");
        }
    }
}
