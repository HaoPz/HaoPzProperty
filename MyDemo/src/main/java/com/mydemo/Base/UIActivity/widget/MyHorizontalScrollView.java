package com.mydemo.Base.UIActivity.widget;

import android.content.Context;
import android.icu.util.Measure;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by HaoPz on 2018/7/10.
 * <p>
 * 包含子View，可滑动切换子View的View Group，
 * <p>
 * 思想步骤：
 * 1. 重写 Measure 几种情况:主要是对 wrap_content 属性处理
 * 1-1. 测量子孩子
 * 1-2. 没有子孩子，自己测量自己
 * 1-3. 如果自己都是 wrap_content,根据需求左右滑动，宽度为 孩子数量 * 第一个孩子的宽度 高度 第一个孩子的宽度
 * 1-4. 仅宽度为 wrap_content
 * 1-5. 仅高度为 wrap_content
 * <p>
 * 2. 重写 Layout,对于子孩子的进行布局.
 * <p>
 * 3. 处理滑动冲突
 * 3-1. 包含ListView 或者 RecyclerView 等滑动冲突，那么就需要重写拦截，判断是否需要拦截
 * 3-2. 如果拦截，重写onTouchEvent,设置滑动， MOVE：当前 X - lastX 差值，
 * 3-3. 优化：UP: 滑动距离判断,滑动超过一半，自动切换，注意计算，getScroll 位置和是否超过孩子宽度一半，计算完位置，根据位置移动距离
 * <p>
 * 4. 弹性滑动，手指抬起时候要判断滑动位置，然后根据滑动角标计算滑动距离，滑动调用的 Scroller 滑动
 * <p>
 * 5. 快速滑动处理:VelocityTracker，不能每次都要在滑动到了一半才能滑动，如果用户快速滑动的话。
 * <p>
 * 6. 阻止在滑动过程中，再次触摸拦截。在拦截 down 中判断，如果还没有执行完毕，说明上一次滑动还在执行直接中断
 */

public class MyHorizontalScrollView extends ViewGroup {

    private Scroller scroller;
    private VelocityTracker velocityTracker;

    public MyHorizontalScrollView(Context context) {
        this(context, null);
    }

    public MyHorizontalScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyHorizontalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        scroller = new Scroller(getContext());
        velocityTracker = VelocityTracker.obtain();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        /** 测量子孩子*/
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        // 没有子元素
        if (getChildCount() == 0) {
            setMeasuredDimension(0, 0);
        }

        // 宽 和 高都是AT_MOST(包裹内容)
        if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            View childAt = getChildAt(0);
            setMeasuredDimension(childAt.getMeasuredWidth() * getChildCount(),
                    childAt.getMeasuredHeight());
        }

        // 仅仅宽度为 wrap_content
        if (widthMode == MeasureSpec.AT_MOST) {
            View childAt = getChildAt(0);
            setMeasuredDimension(childAt.getMeasuredWidth() * getChildCount(),
                    heightSize);
        }

        // 仅仅高度为 wrap_content
        if (widthMode == MeasureSpec.AT_MOST) {
            View childAt = getChildAt(0);
            setMeasuredDimension(widthSize, childAt.getMeasuredHeight());
        }
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    int childWidth = 0;
    int childIndex = 0;

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        int left = 0; // 默认从屏幕左上角开始
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() != View.GONE) {
                int measuredChildHeight = childAt.getMeasuredHeight();
                int measuredChildWidth = childAt.getMeasuredWidth();
                childWidth = measuredChildWidth;
                childAt.layout(left, 0, left + measuredChildWidth, measuredChildHeight);
                left = left + measuredChildWidth;
            }
        }
    }

    int lastInterceptX = 0;
    int lastInterceptY = 0;

    int lastX = 0;
    int lastY = 0;

    // 3.1 判断是否是 横向滑动
    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        boolean isIntercept = false;
        int interceptX = (int) event.getX();
        int interceptY = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // 6.如果还没有执行完毕，说明上一次滑动还在执行直接中断
                if (!scroller.isFinished()) {
                    scroller.abortAnimation();
                    isIntercept = false;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                int detalX = interceptX - lastInterceptX; // 横向移动距离
                int detalY = interceptY - lastInterceptY; // 竖向移动距离
                if (Math.abs(detalX) - Math.abs(detalY) > 0) { // 横向移动 大于 竖向移动那就认为是横着滑动
                    isIntercept = true;
                }
                break;

            case MotionEvent.ACTION_UP:
                lastInterceptX = interceptX;
                lastInterceptY = interceptY;
                break;
        }

        lastX = interceptX;
        lastY = interceptY;

        return isIntercept;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        // 将移动事件交给 VelocityTrack 实例对象
        if (velocityTracker != null) {
            velocityTracker.clear();
        } else {
            velocityTracker = VelocityTracker.obtain();
        }
        velocityTracker.addMovement(event);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                int detalX = x - lastX;
                scrollBy(-detalX, 0);

                velocityTracker.computeCurrentVelocity(1000);// 规定时间内移动的像素点
                float xVelocity = velocityTracker.getXVelocity();
                Log.i("-->","xVelocity:"+ String.valueOf(xVelocity));
                if (Math.abs(xVelocity) > 300) { // 300 px 
                    if (xVelocity > 0) { // 大于零 向左滑动
                        childIndex--;
                    } else {
                        childIndex++;
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                // 滑动 1/2 要自动切换，不到1/2 返回原状态
                int distance = getScrollX() - childIndex * childWidth;
                if (Math.abs(distance) > (childWidth / 2)) { // 大于孩子的一半
                    if (distance > 0) {
                        childIndex++;
                    } else {
                        childIndex--;
                    }
                } else { // invaild, 应该放在 Move 事件中
//                    velocityTracker.computeCurrentVelocity(1000);// 规定时间内移动的像素点
//                    float xVelocity = velocityTracker.getXVelocity();
//                    Log.i("-->","xVelocity:"+ String.valueOf(xVelocity));
//                    if (Math.abs(xVelocity) > 50) {
//                        if (xVelocity > 0) { // 大于零 向左滑动
//                            childIndex--;
//                        } else {
//                            childIndex++;
//                        }
//                    }
                }

                childIndex = childIndex < 0 ? 0 :
                        (childIndex > getChildCount() - 1 ? getChildCount() - 1 : childIndex);

                smoothScrollTo(childIndex * childWidth, 0); // 指定移动到哪个位置

                if(velocityTracker != null){
                    velocityTracker.clear();
                    velocityTracker.recycle();
                    velocityTracker = null;
                }
                break;

        }

        lastX = x;
        lastY = y;
        return true;
    }

    private void smoothScrollTo(int i, int i1) {
        scroller.startScroll(getScrollX(), getScrollY(), i - getScrollX(), 0 - i1, 1000);
        invalidate();
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (scroller.computeScrollOffset()) {
            scrollTo(scroller.getCurrX(), scroller.getCurrY());
            postInvalidate();
        }
    }
}
