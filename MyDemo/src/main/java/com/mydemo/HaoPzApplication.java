package com.mydemo;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.tencent.bugly.Bugly;
import com.tencent.bugly.crashreport.CrashReport;


/**
 * Created by HaoPz on 2018/3/30.
 */

public class HaoPzApplication extends Application {

    private final String TAG = getClass().getSimpleName();

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        // 方式0 ：如果您没有替换 Application 类，请编辑清单文件，按如下方式设置 <application> 标记中的 android:name：
        // <applicatioandroid:name="android.support.multidex.MultiDexApplication" >

        // 方式一 ： 自动的 Application extends MultiDexApplication,如果已经集成别的Application，
        // 那么通诺重写 attachBaseContext()方式初始化 Multidex.install(this); 构建应用后，
        MultiDex.install(this);

        CrashReport.initCrashReport(getApplicationContext(), "c3be65e8c4", false);
        Bugly.init(getApplicationContext(), "c3be65e8c4", false);
    }

    @Override
    public void onCreate() {
        super.onCreate();


    }
}
