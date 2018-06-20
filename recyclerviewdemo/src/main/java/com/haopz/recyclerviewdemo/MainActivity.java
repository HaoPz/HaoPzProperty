package com.haopz.recyclerviewdemo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> arrayList;
    private final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getDate();

        final TextView textView = findViewById(R.id.animation);

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(textView, "TranslationY", 10, 100, 600);
        objectAnimator.setDuration(3000);
        objectAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                Log.i(TAG, "动画执行完成");
            }
        });
        //objectAnimator.start();

        ValueAnimator valueAnimation = ValueAnimator.ofInt(0xff000000, 0xffffffff, 0xff000000);
        valueAnimation.setEvaluator(new ArgbEvaluator());
        valueAnimation.setDuration(3000);
        valueAnimation.setRepeatCount(1);
        // valueAnimation.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int color = (int) animation.getAnimatedValue();
                textView.setTextColor(color);
            }
        });

        //valueAnimation.start();

        AnimatorSet animatorSet = new AnimatorSet();
//        animatorSet.play(objectAnimator).with(valueAnimation);
        animatorSet.play(valueAnimation).with(objectAnimator);
        animatorSet.start();

//        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0,3,50);
//        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                float f = (float) animation.getAnimatedValue();
//                Log.i("anim Value" , String.valueOf(f));
//            }
//        });
//        valueAnimator.setDuration(3000);
////        animation.startAnimation(valueAnimator);
//        valueAnimator.start();


        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            int dividerHeight = 1;

            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                Log.d(TAG, "itemOffsets");
//                if (parent.getChildAdapterPosition(view) != 0) {
//                    ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
//                    outRect.top = dividerHeight;
//                }
                outRect.right = dividerHeight;
                outRect.bottom = dividerHeight;
            }

            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDraw(c, parent, state);

            }

            @Override
            public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDrawOver(c, parent, state);

                int childCount = parent.getChildCount();
//                for (int i = 0; i < childCount; i++) {
//                    View childAt = parent.getChildAt(i);
//                    int paddingLeft = parent.getPaddingLeft();
//                    int bottom = childAt.getBottom();
//                    int top = childAt.getTop() - dividerHeight;
//
//                    // 画一个圆
//                    int radiuY = (bottom - top) / 2;
//                    int radiuX = (paddingLeft) / 2;
//                    int circleRadius = 10;
//                    Paint circlePaint = new Paint();
//                    circlePaint.setColor(Color.RED);
//                    circlePaint.setAntiAlias(true);
//                    circlePaint.setStyle(Paint.Style.STROKE);
//                    circlePaint.setStrokeWidth(5);
//                    c.drawCircle(radiuX, childAt.getTop() + radiuY, circleRadius, circlePaint);
//
//                    int dividerTop = childAt.getTop() - dividerHeight;
//                    if (i == 0) {
//                        dividerTop = childAt.getTop();
//                    }
//
//                    int startUpX = radiuX;
//                    int startUpY = dividerTop;
//                    int endUpX = radiuX;
//                    int endUpY = radiuY - circleRadius + childAt.getTop();
//                    Paint linePaint = new Paint();
//                    linePaint.setColor(Color.RED);
//                    linePaint.setAntiAlias(true);
//                    linePaint.setStyle(Paint.Style.FILL);
//                    linePaint.setStrokeWidth(5);
//                    c.drawLine(startUpX, startUpY, endUpX, endUpY, linePaint);
//
//                    int startDownX = radiuX;
//                    int startDownY = endUpY + circleRadius + circleRadius;
//                    int endDownX = radiuX;
//                    int endDownY = childAt.getBottom();
//                    c.drawLine(startDownX, startDownY, endDownX, endDownY, linePaint);
//                }
            }
        });

        recyclerView.setHasFixedSize(true);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, StaggeredGridLayoutManager.VERTICAL, false);
//        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(int i) {
//                if (i % 8 == 0) { // 整除 8 就单独显示一行
//                    return 2;
//                } else {
//                    return 1;
//                }
//            }
//        });

//        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
//        staggeredGridLayoutManager.
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(new TRecyclerViewAdapter(MainActivity.this, arrayList));
    }

    private void getDate() {
        arrayList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            arrayList.add("item --> " + i);
        }
    }

    class TRecyclerViewAdapter extends RecyclerView.Adapter<TRecyclerViewAdapter.TViewHolder> {
        private Context mContext;
        private ArrayList<String> arrayList;

        public TRecyclerViewAdapter(Context mContext, ArrayList<String> arrayList) {
            this.mContext = mContext;
            this.arrayList = arrayList;
        }

        @Override
        public TViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            TViewHolder tViewHolder = new TViewHolder(LayoutInflater.from(mContext)
                    .inflate(R.layout.item_recycler, viewGroup, false));
            return tViewHolder;
        }

        @Override
        public void onBindViewHolder(TViewHolder tViewHolder, int i) {
            tViewHolder.itemTv.setText(arrayList.get(i));
        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }

        public class TViewHolder extends RecyclerView.ViewHolder {

            public final TextView itemTv;

            public TViewHolder(View itemView) {
                super(itemView);
                itemTv = itemView.findViewById(R.id.itemTv);
            }
        }
    }
}
