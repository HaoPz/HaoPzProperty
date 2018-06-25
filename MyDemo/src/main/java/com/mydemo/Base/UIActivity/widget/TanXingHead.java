package com.mydemo.Base.UIActivity.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * Created by HaoPz on 2018/6/25.
 */

public class TanXingHead extends ScrollView {

    private final float scrollNumber = 0.5f;
    private boolean isCanScroll = false;

    /**
     * ScrollView 里面只有一个Children，
     * onFinishInflate() 什么时候加载？
     * 比如你 自定义一个view叫myView ,路径是，com.test.view.MyView,此view是继承LinearLayout，定义的布局文件是my_view.xml
     * 里面内容是：
     * <com.test.view.MyView>
     * <xxxx />
     * </com.test.view.MyView>
     * <p>
     * 当你在使用的时候，可以这样使用
     * MyView mv = (MyView)View.inflate (context,R.layout.my_view,null);
     * 当加载完成xml后，就会执行那个方法。
     */
    private View contentView;
    private ObjectAnimator objectAnimator;

    public TanXingHead(Context context) {
        this(context, null);
    }

    public TanXingHead(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TanXingHead(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() > 0) {
            contentView = getChildAt(0);
        }
    }


    float downY = 0;
    float moveY = 0;

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downY = ev.getY();
                break;

            case MotionEvent.ACTION_MOVE:
                moveY = ev.getY();
                float detalY = moveY - downY; // 移动距离
                if (moveY >= downY) { // 向下移动
                    isCanScroll = true;

                    float realDetalY = scrollNumber * detalY;

//                    if(objectAnimator != null && objectAnimator.isRunning()){
                        Log.i("tianxingHead", "     downY:" + downY + " moveRawY:" + moveY + "  detalY:" + detalY);
                        contentView.layout(getLeft(), ((int) (getTop() + realDetalY)), getRight(), ((int) (getHeight() + getTop() + realDetalY)));
                        Log.i("tianxingHeadlayou",
                                "  left:" + getLeft() +
                                        "  top:" + (getTop() + realDetalY) +
                                        "  right:" + getRight() +
                                        "  bottom:" + ((int) (getHeight() + getTop() + realDetalY)));
//                    }

                }
                break;

            case MotionEvent.ACTION_UP:
                Log.i("tianxingHeadlayou", "isCanScroll:" + isCanScroll);
                if (isCanScroll) {
                    objectAnimator = ObjectAnimator.ofFloat(contentView, "translationY", -contentView.getTop());
                    objectAnimator.setDuration(300);
                    objectAnimator.start();

                    // contentView.layout(contentView.getLeft(),contentView.getTop(),contentView.getRight(),contentView.getBottom());
//                    objectAnimator.addListener(new AnimatorListenerAdapter() {
//                        @Override
//                        public void onAnimationStart(Animator animation) {
//                            super.onAnimationStart(animation);
//                            isCanScroll = false;
//                        }
//
//                        @Override
//                        public void onAnimationEnd(Animator animation) {
//                            super.onAnimationEnd(animation);
//                            isCanScroll = true;
//                        }
//                    });
                }
                break;
        }
        return super.onTouchEvent(ev);
    }
}
