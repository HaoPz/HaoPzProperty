package com.mydemo.Base.UIActivity;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
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

public class PhotoChooosePickerSolveOOMActivity extends Base2Activity implements View.OnClickListener {
    private ImageView photoChooseSolveIv;
    private LinearLayout photoChooseSolveLL;
    private ArrayList<Integer> photoList = new ArrayList<>();

    @Override
    public void setDate() {
        photoList.add(R.drawable.taile1);
        photoList.add(R.drawable.taile2);
        photoList.add(R.drawable.taile3);
        photoList.add(R.drawable.taile4);
        photoList.add(R.drawable.taile5);

        photoChooseSolveIv.setImageResource(R.drawable.taile1);

        addImageView();
    }

    private void addImageView() {
        ImageView imageView1 = new ImageView(PhotoChooosePickerSolveOOMActivity.this);
        imageView1.setLayoutParams(new FrameLayout.LayoutParams(450, ViewGroup.LayoutParams.MATCH_PARENT));
        imageView1.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageView1.setImageResource(R.drawable.taile1);
        imageView1.setOnClickListener(this);
        imageView1.setTag(1);
        photoChooseSolveLL.addView(imageView1);

        ImageView imageView2 = new ImageView(PhotoChooosePickerSolveOOMActivity.this);
        imageView2.setLayoutParams(new FrameLayout.LayoutParams(450, ViewGroup.LayoutParams.MATCH_PARENT));
        imageView2.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageView2.setImageResource(R.drawable.taile2);
        imageView2.setOnClickListener(this);
        imageView2.setTag(2);
        photoChooseSolveLL.addView(imageView2);

        ImageView imageView3 = new ImageView(PhotoChooosePickerSolveOOMActivity.this);
        imageView3.setLayoutParams(new FrameLayout.LayoutParams(450, ViewGroup.LayoutParams.MATCH_PARENT));
        imageView3.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageView3.setImageResource(R.drawable.taile3);
        imageView3.setOnClickListener(this);
        imageView3.setTag(3);
        photoChooseSolveLL.addView(imageView3);

        ImageView imageView4 = new ImageView(PhotoChooosePickerSolveOOMActivity.this);
        imageView4.setLayoutParams(new FrameLayout.LayoutParams(450, 600));
        imageView4.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageView4.setImageResource(R.drawable.taile4);
        imageView4.setOnClickListener(this);
        imageView4.setTag(4);
        photoChooseSolveLL.addView(imageView4);

        ImageView imageView5 = new ImageView(PhotoChooosePickerSolveOOMActivity.this);
        imageView5.setLayoutParams(new FrameLayout.LayoutParams(450, ViewGroup.LayoutParams.MATCH_PARENT));
        imageView5.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageView5.setImageResource(R.drawable.taile5);
        imageView5.setOnClickListener(this);
        imageView5.setTag(5);
        photoChooseSolveLL.addView(imageView5);
    }
    @Override
    public void setView(View contentView) {
        photoChooseSolveIv = (ImageView) contentView.findViewById(R.id.photoChooseSolveIv);
        photoChooseSolveLL = (LinearLayout) contentView.findViewById(R.id.photoChooseSolveLL);
    }

    @Override
    protected View getContentView() {
        return inflateView(R.layout.activity_photo_choose_pick_solve_oom);
    }

    @Override
    public void onClick(View v) {
        
    }
    public class ca1 extends BaseAdapter{


        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return null;
        }
    }

    class MyHorizontalScrollAdapter {
        private ArrayList<Integer> photoList;
        private Context context ;

        public MyHorizontalScrollAdapter(ArrayList<Integer> photoList, Context context) {
            this.photoList = photoList;
            this.context = context;
        }

        public int getCount() {
            return photoList.size();
        }

        public Object getItem(int position) {
            return photoList.get(position);
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            return null;
        }

    }

}
