package com.mydemo.Base.BaseKnowLedgeList.Service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.mydemo.Base.Base2Activity;
import com.mydemo.Base.BaseKnowLedgeList.Web.StartLookWebActivity;
import com.mydemo.R;

/**
 * Created by HaoPz on 2018/4/13.
 */

public class BaseKnowLedgeService extends Base2Activity {


    private TextView serviceManiFestProperty;

    @Override
    public void setDate() {
        serviceManiFestProperty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BaseKnowLedgeService.this, StartLookWebActivity.class);
                intent.putExtra("webTitle", "Service属性大全");
                intent.putExtra("webUrl", BaseKnowLedgeService.this.getResources().getString(R.string.serviceManiFestProperty));
                startActivity(intent);
            }
        });
    }

    @Override
    public void setView(View contentView) {
        serviceManiFestProperty = (TextView) findViewById(R.id.serviceManiFestProperty);
    }

    @Override
    protected View getContentView() {
        return LayoutInflater.from(this).inflate(R.layout.layout_base_knowledge_service, null);
    }

    public class MyService extends Service{
        private final String TAG = getClass().getSimpleName();

        public MyBinder myBinder = new MyBinder() ;

        private class MyBinder extends Binder{
            public void getProcess(){
                Log.d(TAG, " --> getProcess");
            }
        }

        @Nullable
        @Override
        public IBinder onBind(Intent intent) {
            return myBinder;
        }

        @Override
        public void onStart(Intent intent, int startId) {
            Log.d(TAG, " --> onStart");
            super.onStart(intent, startId);
        }

        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            Log.d(TAG, " --> onStartCommand");
            return super.onStartCommand(intent, flags, startId);
        }

        @Override
        public void onDestroy() {
            Log.d(TAG, " --> onDestroy");
            super.onDestroy();
        }
    }
}
