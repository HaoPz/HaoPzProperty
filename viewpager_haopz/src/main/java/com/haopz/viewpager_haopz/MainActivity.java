package com.haopz.viewpager_haopz;

import android.content.ContentValues;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.TreeMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = findViewById(R.id.viewPager);

        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(R.drawable.icon_vip_one);
        arrayList.add(R.drawable.icon_vip_two);
        arrayList.add(R.drawable.icon_vip_three);

        viewPager.setOffscreenPageLimit(2); // 设置预加载的页数,默认情况下是1，我们需要俩边都得显示，所以为2
        viewPager.setPageTransformer(false,new ScaleTransformer());
        viewPager.setAdapter(new MyViewPager(MainActivity.this, arrayList));
    }

    class MyViewPager extends PagerAdapter{
        private Context context ;
        private ArrayList<Integer> arrayList ;

        public MyViewPager(Context context, ArrayList<Integer> arrayList) {
            this.context = context;
            this.arrayList = arrayList;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.item_viewpager, null, false);
            container.addView(inflate);

            ImageView itemIv = inflate.findViewById(R.id.itemIv);
            itemIv.setBackgroundResource(arrayList.get(position));

            return inflate;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    public class ScaleTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.85f;
        private static final float MIN_ALPHA = 0.5f;

        @Override
        public void transformPage(View page, float position) {
            if (position < -1 || position > 1) {
                page.setAlpha(MIN_ALPHA);
                page.setScaleX(MIN_SCALE);
                page.setScaleY(MIN_SCALE);
            } else if (position <= 1) { // [-1,1]
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                if (position < 0) {
                    float scaleX = 1 + 0.15f * position;
                    Log.i("google_lenve_fb", "transformPage: scaleX:" + scaleX);
                    page.setScaleX(scaleX);
                    page.setScaleY(scaleX);
                } else {
                    float scaleX = 1 - 0.15f * position;
                    page.setScaleX(scaleX);
                    page.setScaleY(scaleX);
                }
                // page.setAlpha(MIN_ALPHA + (scaleFactor - MIN_SCALE) / (1 - MIN_SCALE) * (1 - MIN_ALPHA));
            }
        }
    }
}
