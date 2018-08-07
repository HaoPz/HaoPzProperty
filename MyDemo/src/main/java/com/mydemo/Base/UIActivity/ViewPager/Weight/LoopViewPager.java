package com.mydemo.Base.UIActivity.ViewPager.Weight;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mydemo.Base.UIActivity.ViewPager.LoopViewPagerActivity;
import com.mydemo.R;
import com.mydemo.Utils.DensityUtil;

import java.util.ArrayList;

/**
 * Created by HaoPz on 2018/7/27.
 * <p>
 * 无线循环的ViewPager
 * 1.继承 FrameLayout，方便拓展内容
 * 2.
 */

public class LoopViewPager extends FrameLayout {

    /**
     * ViewPager 对象
     */
    private ViewPager viewPager;
    private LinearLayout viewPagerIndicator;

    /**
     * 数据对象
     */
    private ArrayList<LoopViewPagerActivity.Bean> mListAdd;

    private Context mContext;

    private int viewPagerIndicatorLastChoosePos = 0;

    public LoopViewPager(@NonNull Context context) {
        this(context, null);
    }

    public LoopViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoopViewPager(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        LayoutInflater.from(mContext).inflate(R.layout.view_loop_view_pager, this, true);
        init();
    }

    private void init() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPagerIndicator = (LinearLayout) findViewById(R.id.viewPagerIndicator);
    }

    public void setViewPagerDate(ArrayList<LoopViewPagerActivity.Bean> t) {
        this.mListAdd = t;

        createIndicator();

        LoopViewPagerAdpater loopViewPagerAdpater = new LoopViewPagerAdpater(mListAdd);
        viewPager.setAdapter(loopViewPagerAdpater);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.i("%%", "currentPos : " + String.valueOf(position) + "   lasPos:" + viewPagerIndicatorLastChoosePos);
                int linshi = position % mListAdd.size();
                viewPagerIndicator.getChildAt(viewPagerIndicatorLastChoosePos).setSelected(false);
                viewPagerIndicator.getChildAt(linshi).setSelected(true);
                viewPagerIndicatorLastChoosePos = linshi;

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * ViewPager 指示器
     */
    private void createIndicator() {
        for (int i = 0; i < mListAdd.size(); i++) {
            View view = new View(mContext);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(DensityUtil.dip2px(mContext, 5),
                    DensityUtil.dip2px(mContext, 5));
            layoutParams.gravity = Gravity.CENTER_VERTICAL;
            layoutParams.setMargins(5, 5, 5, 5);
            view.setLayoutParams(layoutParams);
            view.setBackground(mContext.getResources().getDrawable(R.drawable.selector_loop_circle));
            view.setSelected(false);
            viewPagerIndicator.addView(view);
        }

        if (mListAdd.size() > 0) {
            viewPagerIndicator.getChildAt(0).setSelected(true);
            viewPagerIndicatorLastChoosePos = 0;
        }
    }

    public class LoopViewPagerAdpater extends PagerAdapter {
        private ArrayList<LoopViewPagerActivity.Bean> mAdapterListAdd;

        public LoopViewPagerAdpater(ArrayList<LoopViewPagerActivity.Bean> mAdapterListAdd) {
            this.mAdapterListAdd = mAdapterListAdd;
        }

        @Override
        public int getCount() {
            return mAdapterListAdd.size() + 2;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            position %= mAdapterListAdd.size(); // 范围永远在 mAdapterListAdd  0 - mAdapterListSize-1  之间
            View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_loop_viewpager, container, false);
            LinearLayout loopViewBg = (LinearLayout) inflate.findViewById(R.id.loopViewBg);
            TextView str1 = (TextView) inflate.findViewById(R.id.str1);
            TextView str2 = (TextView) inflate.findViewById(R.id.str2);
            TextView str3 = (TextView) inflate.findViewById(R.id.str3);
            TextView str4 = (TextView) inflate.findViewById(R.id.str4);

            str1.setText(((LoopViewPagerActivity.Bean) mAdapterListAdd.get(position)).getStr1());
            str2.setText(((LoopViewPagerActivity.Bean) mAdapterListAdd.get(position)).getStr2());
            str3.setText(((LoopViewPagerActivity.Bean) mAdapterListAdd.get(position)).getStr3());
            str4.setText(((LoopViewPagerActivity.Bean) mAdapterListAdd.get(position)).getStr4());

            container.addView(inflate);
            return inflate;
        }

        /**
         * 重点就在于finishUpdate(ViewGroup)这个方法 其实无论是创建view添加到容器中  还是 销毁view 都是在此方法结束之后执行的
         */
        @Override
        public void finishUpdate(ViewGroup viewGroup) { // 0 - 7
            super.finishUpdate(viewGroup);
            int position = viewPager.getCurrentItem();
            Log.i("-->pos:", String.valueOf(position));
            if (position == 0) {
                position = mAdapterListAdd.size();
                viewPager.setCurrentItem(position, false);
            } else if (position == (mAdapterListAdd.size() + 2) - 1) {
                position = 1;
                viewPager.setCurrentItem(position, false);
            }

        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

}
