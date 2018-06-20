package com.haopz.status_android;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.ColorInt;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by HaoPz on 2018/5/28.
 */

public class ColorStatusActivity extends AppCompatActivity{

    @IdRes
    private int STATUS_BAR_VIEW_ID;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_status);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.color_red));
            getWindow().setNavigationBarColor(getResources().getColor(R.color.color_red));
        }else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            ViewGroup decorView = (ViewGroup) getWindow().getDecorView();

            View statusBarView = new View(this);
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,getStatusViewHeight());
            statusBarView.setLayoutParams(layoutParams);
            statusBarView.setBackgroundColor(getResources().getColor(R.color.color_red));
            statusBarView.setId(STATUS_BAR_VIEW_ID);
            statusBarView.setAlpha(1);

            decorView.addView(statusBarView);
        }


    }

    public int getStatusViewHeight(){
        /**
         * 获取状态栏高度——方法1
         * */
        int statusBarHeight1 = -1;
        //获取status_bar_height资源的ID
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            statusBarHeight1 = getResources().getDimensionPixelSize(resourceId);
        }
        Log.e("statusHeight->", "状态栏-方法1:" + statusBarHeight1);
        return statusBarHeight1;
    }

//    /**
//     * 计算状态栏颜色
//     *
//     * @param color color值
//     * @param alpha alpha值
//     * @return 最终的状态栏颜色
//     */
//    private static int calculateStatusColor(@ColorInt int color, int alpha) {
//        if (alpha == 0) {
//            return color;
//        }
//        float a = 1 - alpha / 255f;
//        int red = color >> 16 & 0xff;
//        int green = color >> 8 & 0xff;
//        int blue = color & 0xff;
//        red = (int) (red * a + 0.5);
//        green = (int) (green * a + 0.5);
//        blue = (int) (blue * a + 0.5);
//        return 0xff << 24 | red << 16 | green << 8 | blue;
//    }
}
