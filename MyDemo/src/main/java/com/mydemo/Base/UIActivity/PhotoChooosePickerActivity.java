package com.mydemo.Base.UIActivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.mydemo.Base.Base2Activity;
import com.mydemo.R;

import java.util.ArrayList;

/**
 * Created by HaoPz on 2018/6/27.
 */

public class PhotoChooosePickerActivity extends Base2Activity implements View.OnClickListener {

    private ImageView photoChooseIv;
    private LinearLayout photoChooseLL;

    private ArrayList<Integer> photoList = new ArrayList<>();

    @Override
    public void setDate() {
        photoList.add(R.drawable.taile1);
        photoList.add(R.drawable.taile2);
        photoList.add(R.drawable.taile3);
        photoList.add(R.drawable.taile4);
        photoList.add(R.drawable.taile5);

        photoChooseIv.setImageResource(R.drawable.taile1);

        addImageView();
    }

    private void addImageView() {
        ImageView imageView1 = new ImageView(PhotoChooosePickerActivity.this);
        imageView1.setLayoutParams(new FrameLayout.LayoutParams(450, ViewGroup.LayoutParams.MATCH_PARENT));
        imageView1.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageView1.setImageResource(R.drawable.taile1);
        imageView1.setOnClickListener(this);
        imageView1.setTag(1);
        photoChooseLL.addView(imageView1);

        ImageView imageView2 = new ImageView(PhotoChooosePickerActivity.this);
        imageView2.setLayoutParams(new FrameLayout.LayoutParams(450, ViewGroup.LayoutParams.MATCH_PARENT));
        imageView2.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageView2.setImageResource(R.drawable.taile2);
        imageView2.setOnClickListener(this);
        imageView2.setTag(2);
        photoChooseLL.addView(imageView2);

        ImageView imageView3 = new ImageView(PhotoChooosePickerActivity.this);
        imageView3.setLayoutParams(new FrameLayout.LayoutParams(450, ViewGroup.LayoutParams.MATCH_PARENT));
        imageView3.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageView3.setImageResource(R.drawable.taile3);
        imageView3.setOnClickListener(this);
        imageView3.setTag(3);
        photoChooseLL.addView(imageView3);

        ImageView imageView4 = new ImageView(PhotoChooosePickerActivity.this);
        imageView4.setLayoutParams(new FrameLayout.LayoutParams(450, 600));
        imageView4.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageView4.setImageResource(R.drawable.taile4);
        imageView4.setOnClickListener(this);
        imageView4.setTag(4);
        photoChooseLL.addView(imageView4);

        ImageView imageView5 = new ImageView(PhotoChooosePickerActivity.this);
        imageView5.setLayoutParams(new FrameLayout.LayoutParams(450, ViewGroup.LayoutParams.MATCH_PARENT));
        imageView5.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageView5.setImageResource(R.drawable.taile5);
        imageView5.setOnClickListener(this);
        imageView5.setTag(5);
        photoChooseLL.addView(imageView5);
    }

    @Override
    public void setView(View contentView) {
        photoChooseIv = (ImageView) contentView.findViewById(R.id.photoChooseIv);
        photoChooseLL = (LinearLayout) contentView.findViewById(R.id.photoChooseLL);
    }

    @Override
    protected View getContentView() {
        return inflateView(R.layout.activity_photo_choose_pick);
    }

    @Override
    public void onClick(View v) {
        int tag = (int) v.getTag();
        switch (tag){
            case 1:
                photoChooseIv.setImageResource(photoList.get(0));
                break;

            case 2:
                photoChooseIv.setImageResource(photoList.get(1));
                break;

            case 3:
                photoChooseIv.setImageResource(photoList.get(2));
                break;

            case 4:
                photoChooseIv.setImageResource(photoList.get(3));
                break;

            case 5:
                photoChooseIv.setImageResource(photoList.get(4));
                break;
        }
    }

}
