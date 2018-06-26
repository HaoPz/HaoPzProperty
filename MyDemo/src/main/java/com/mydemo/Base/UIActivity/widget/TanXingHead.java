package com.mydemo.Base.UIActivity.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * Created by HaoPz on 2018/6/25.
 */

public class TanXingHead extends ScrollView {

    private final float scrollNumber = 0.5f;
    private boolean isCanHeadScroll = false;

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
    private float realDetalY;

    // 用于记录正常的布局位置
    private Rect originalRect = new Rect();

    float downY = 0;
    float moveY = 0;

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

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (contentView == null) {
            return;
        }
        originalRect.set(contentView.getLeft(), contentView.getTop(), contentView.getRight(), contentView.getBottom());
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downY = ev.getY();
                Log.i("tianxianHeadDown", "getScrollY:" + getScrollY());
                if (getScrollY() == originalRect.top) {
                    isCanHeadScroll = true;
                } else {
                    isCanHeadScroll = false;
                }
                break;

            case MotionEvent.ACTION_MOVE:
                moveY = ev.getY();
                float detalY = moveY - downY; // 移动距离
                Log.i("tianxingHead", "     downY:" + downY + " moveRawY:" + moveY + "  detalY:" + detalY);
                if (moveY >= downY) { // 向下移动 同时满足

                    realDetalY = scrollNumber * detalY;

                    if (isCanHeadScroll) {
                        contentView.layout(getLeft(),
                                ((int) (getTop() + realDetalY)),
                                getRight(),
                                ((int) (getHeight() + getTop() + realDetalY)));
                    }

                } else {
                    // 向上滑动
                    Log.i("tianxiaHeadMove", "向上滑动" + getScrollY());
                    isCanHeadScroll = false;
                }
                break;

            case MotionEvent.ACTION_UP:
                if (isCanHeadScroll) {
                    TranslateAnimation translateAnimation = new TranslateAnimation(0, 0, contentView.getTop(), originalRect.top);
                    translateAnimation.setDuration(500);
                    translateAnimation.setFillAfter(true);
                    contentView.startAnimation(translateAnimation);
                    contentView.layout(originalRect.left, originalRect.top, originalRect.right, originalRect.bottom);

                    // 在duration 时间内向下平移 contentView.getTop 距离,瞬时完成 originalRect.top 位置
//                    objectAnimator = ObjectAnimator.ofFloat(contentView, "translationY", contentView.getTop(), originalRect.top);
//                    objectAnimator = ObjectAnimator.ofFloat(contentView, "translationY", originalRect.top); // 过了duration时间，瞬间完成
//                    objectAnimator.setDuration(5000);
//                    objectAnimator.start();
//                    objectAnimator.addListener(new AnimatorListenerAdapter() {
//                        @Override
//                        public void onAnimationEnd(Animator animation) {
//                            super.onAnimationEnd(animation);
//                            contentView.layout(originalRect.left, originalRect.top, originalRect.right, originalRect.bottom);
//                        }
//                    });

                } else {
                    Log.i("tianxiaHeadUp", "向上滑动结束，手指抬起" + contentView.getTop());
                }
                break;
        }
        return super.onTouchEvent(ev);
    }
}
