package com.mydemo.Base.UIActivity.ViewPager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.mydemo.Base.Base2Activity;
import com.mydemo.Base.UIActivity.ViewPager.Weight.LoopViewPager;
import com.mydemo.Base.UIActivity.ViewPager.Weight.TopView;
import com.mydemo.R;

import java.util.ArrayList;

/**
 * 无线轮播的View
 * Created by HaoPz on 2018/7/27.
 */

public class LoopViewPagerActivity extends AppCompatActivity {
    ArrayList<Bean> arrayList = new ArrayList<>( );

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loop_viewpager);

        setView();
    }

    public void setView() {

        LoopViewPager loopViewPager = (LoopViewPager) findViewById(R.id.loopViewPager);
        arrayList.add(new Bean("11","21","31","41","51"));
        arrayList.add(new Bean("21","22","32","42","52"));
        arrayList.add(new Bean("13","23","33","43","53"));
        arrayList.add(new Bean("14","24","34","44","54"));
        arrayList.add(new Bean("14","25","35","45","55"));
        loopViewPager.setViewPagerDate(arrayList);

        TopView topView = (TopView) findViewById(R.id.topView);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("http://pic36.photophoto.cn/20150901/0036036183291092_b.jpg");
        arrayList.add("http://pic36.photophoto.cn/20150831/0036036855775483_b.jpg");
        arrayList.add("http://pic36.photophoto.cn/20150901/0036036869689711_b.jpg");

        topView.setData(arrayList);
    }

    // -----------
    public class Bean{
        public Bean(String url, String str1, String str2, String str3, String str4) {
            this.url = url;
            this.str1 = str1;
            this.str2 = str2;
            this.str3 = str3;
            this.str4 = str4;
        }

        private String url ;
        private String str1;
        private String str2;
        private String str3;
        private String str4;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getStr1() {
            return str1;
        }

        public void setStr1(String str1) {
            this.str1 = str1;
        }

        public String getStr2() {
            return str2;
        }

        public void setStr2(String str2) {
            this.str2 = str2;
        }

        public String getStr3() {
            return str3;
        }

        public void setStr3(String str3) {
            this.str3 = str3;
        }

        public String getStr4() {
            return str4;
        }

        public void setStr4(String str4) {
            this.str4 = str4;
        }
    }
}
