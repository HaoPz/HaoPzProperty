package com.mydemo.Base.UIActivity.ViewPager.Weight;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.mydemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HaoPz on 2018/8/2.
 */

public class TopView extends FrameLayout implements ViewPager.OnPageChangeListener {
    private ViewPager vPager;
    private List<ImageView> imgViews;
    private List<String> datas;// 数据源

    private LinearLayout navLayout;
    private LinearLayout.LayoutParams layoutParams;//线性布局中子控件使用的布局参数，作用设置子控件大小，外边距
    private Context mContext;
//    private DownLoadImage imageLoader;
    private int currentPosition=0;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            currentPosition = (++currentPosition) % imgViews.size();
            vPager.setCurrentItem(currentPosition,false);

            //处理完之前的消息之后再次发送一个3秒之后的消息  如此  可实现每隔三秒轮播
//            if(handler!=null){
//                handler.sendEmptyMessageDelayed(0,3000);
//            }
        }
    };

    public TopView(Context context) {
        this(context,null);
    }

    public TopView(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public TopView(Context context,AttributeSet attrs,int defStyle) {
        super(context,attrs,defStyle);
        mContext=context;
        // 第二个参数this: 布局资源中根标签内声明的布局参数参考的父控件对象
        // 第三个参数true: 代表是将第一个参数中声明的子控件归属到第二个参数对象中，false不归属
        LayoutInflater.from(context).inflate(R.layout.topview, this, true);
        initView();
    }

    private void initView() {
        // 查找相关的UI控件
        vPager = (ViewPager) findViewById(R.id.viewPager);
        navLayout = (LinearLayout) findViewById(R.id.navLayout);
    }

    public void setData(List<String> datas) {
        this.datas = datas;
        createViews();
    }

    private void createViews() {
        // 根据数据源创建ViewPager中显示的UI
        imgViews = new ArrayList<ImageView>();//加入数据源
        layoutParams =new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        //  layoutParams.leftMargin=20;
        layoutParams.rightMargin=10;//px
        layoutParams.width=20;
        layoutParams.height=20;
        ImageView imgView = null;
        ImageView navImg = null;
        for (int i=0;i<datas.size();i++)
        {
            imgView = new ImageView(getContext());
            imgView.setScaleType(ImageView.ScaleType.FIT_XY);
            imgView.setTag(datas.get(i));

            imgViews.add(imgView);

            navImg = new ImageView(getContext());
            navImg.setScaleType(ImageView.ScaleType.CENTER_CROP);
            if(i==0){
                //navImg.setImageResource(R.drawable.page_now);

            } else{

            }
                //navImg.setImageResource(R.drawable.page);

            //设置导航图片的标签： 当前导航图片的位置
            navImg.setTag(i);

            navImg.setLayoutParams(layoutParams);
            navLayout.addView(navImg);
        }

        vPager.setAdapter(new ImageAdapter());
        vPager.addOnPageChangeListener(this);
        loadImgs();
        //开启handler的线程 3秒之后发出此消息
//        if(handler!=null){
//            handler.sendEmptyMessageDelayed(0,3000);
//        }
    }

    private void loadImgs() {
        // 加载网络图片加载后放入imageView
        for (int i=0;i<datas.size();i++) {
            String data=datas.get(i);
            for (ImageView imageView : imgViews) {
                if(imageView.getTag().equals(data)){
                    getImageView(imageView,data,i);
                }
            }
        }
    }

    class
    ImageAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return datas.size()+2;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            // TODO Auto-generated method stub
            return arg0 == arg1;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int actPosition) {
            //对Viewpager页号求模去除View列表中要显示的项
            actPosition %= datas.size();
            ImageView view = imgViews.get(actPosition);
            //如果View已经在之前添加到了一个父组件，则必须先remove，否则会抛出IllegalStateException。
            ViewParent viewParent = view.getParent();
            if (viewParent!=null){
                ViewGroup parent = (ViewGroup)viewParent;
                parent.removeView(view);
            }
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int actPosition, Object object) {
            //注意不在此方法进行removeView
//            container.removeView((View) object);
        }

        @Override
        public void finishUpdate(ViewGroup container) {
            super.finishUpdate(container);

            int position = vPager.getCurrentItem();
            Log.i("-->Top", String.valueOf(position));
            if (position == 0) {
                position = datas.size();
                currentPosition=position;
//                if(handler!=null){
//                    handler.removeCallbacksAndMessages(null);
//                    handler.sendEmptyMessageDelayed(0,3000);
//                }
                vPager.setCurrentItem(position,false);
            } else if (position == (datas.size()+2) - 1) {
                position = 1;
                currentPosition=position;
//                if(handler!=null){
//                    handler.removeCallbacksAndMessages(null);
//                    handler.sendEmptyMessageDelayed(0,3000);
//                }
                vPager.setCurrentItem(position,false);
            }

        }

    }

    @Override
    public void onPageScrollStateChanged(int arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onPageSelected(int position) {
        position=position % datas.size();
//        if(handler!=null){
//            handler.removeCallbacksAndMessages(null);
//        }
        currentPosition=position;
        ImageView navImg  = null;
        //遍历 导航布局中所有的子控件，判断子控件的位置是否为选择位置，若是，则改变图片的内容
        for(int i=0;i<navLayout.getChildCount();i++){
            navImg = (ImageView) navLayout.getChildAt(i );//获取布局中指定位置的子控件
            if(i==position){
                // navImg.setImageResource(R.drawable.page_now);
            }
            else{}
                // navImg.setImageResource(R.drawable.page);
        }

//        if(handler!=null){
//            handler.sendEmptyMessageDelayed(0,3000);
//        }

    }

    public interface TopViewClickListener{
        public void onTopViewClick(int position);
    }

    public TopViewClickListener mTopViewClickListener;
    public void setTopViewClickListener(TopViewClickListener topViewClickListener){
        mTopViewClickListener=topViewClickListener;
    }

    private ImageView getImageView(ImageView imageView, String url, final int position) {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTopViewClickListener != null) {
                    mTopViewClickListener.onTopViewClick(position);
                }
            }
        });

        // if(imageView.getTag().equals(url)){
            imageView.setTag(null);
            Glide.with(mContext).load(url).into(imageView);
            imageView.setTag(url);
        //}

//        if (imageLoader == null) {
//            imageLoader = new DownLoadImage(mContext, R.drawable.bannerdefault, R.drawable.bannerdefault, null);
//        }
//        imageLoader.displayImage(url, imageView, null);

        // imageView.setImageResource(R.mipmap.ic_launcher);
        return imageView;
    }



}