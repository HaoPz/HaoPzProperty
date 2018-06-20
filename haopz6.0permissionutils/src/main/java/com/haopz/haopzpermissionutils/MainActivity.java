package com.haopz.haopzpermissionutils;

import android.Manifest;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity{

    private final String TAG = MainActivity.this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.startPermission).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PermissionUtils.getInstance()
                        .setRequestPermissionCode(100)
                        .setRequestPermission(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.CALL_PHONE}, MainActivity.this);
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        PermissionUtils.onRequestPermissionsResult(requestCode, permissions, grantResults, new PermissionUtils.OnRequestPermissionResult() {
            @Override
            public void onRequestPerSuccess() {
                Log.i(TAG, "权限请求成功" );
            }

            @Override
            public void onRequestPerFail() {
                Log.i(TAG, "权限请求失败" );
            }
        });
    }

}
