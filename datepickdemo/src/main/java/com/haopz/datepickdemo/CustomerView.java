package com.haopz.datepickdemo;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Scroller;
import android.widget.TextView;

import static android.support.constraint.Constraints.TAG;

/**
 * Created by HaoPz on 2018/6/14.
 */

public class CustomerView extends android.support.v7.widget.AppCompatTextView {

    private final String TAG = "CustomerView";

    private Scroller scroller;

    public CustomerView(Context context) {
        this(context, null);
    }

    public CustomerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        scroller = new Scroller(context); // 1.走到自身构造.创建Scroller 对象
    }

    @Override
    public void computeScroll() { // 3.重绘View 会在此调用 computeScroll
        super.computeScroll();
        if (scroller.computeScrollOffset()) { // 调用 Scroll 计算偏移量的方法
            ((View) getParent()).scrollTo(scroller.getCurrX(), scroller.getCurrY()); // 要调用父布局ScrollTo, 如果本身控件是包裹大小，一移动你就看不见了
            invalidate(); // 重绘View让其再次调用 computeScroll
        }
    }

    public void startCustomerScroll(int toX, int toY) { // 2.创建开始执行动画入口
        int scrollX = getScrollX();
        int delta = toX - scrollX; // 调用 scrollTo 移动 x 和 y 分别为负数.计算距离
        Log.d(TAG, "scroll:" + scrollX + "    toX -scrollX = date:" + delta);
        scroller.startScroll(scrollX, 0, delta, 0, 4000); // 执行动画
        invalidate();  // 重绘View
    }
}
