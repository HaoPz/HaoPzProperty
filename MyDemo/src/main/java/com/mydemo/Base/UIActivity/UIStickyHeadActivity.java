package com.mydemo.Base.UIActivity;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mydemo.Base.Base2Activity;
import com.mydemo.R;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by HaoPz on 2018/6/27.
 */

public class UIStickyHeadActivity extends Base2Activity {

    private TextView uiStickyHeadTv;
    private RecyclerView uiStickyRecycler;

    private ArrayList<HeadStickyModel> dataList = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;
    private UiStickyHeadAdapter uiStickyHeadAdapter;

    @Override
    public void setDate() {
        initData();

        linearLayoutManager = new LinearLayoutManager(UIStickyHeadActivity.this);
        uiStickyRecycler.setLayoutManager(linearLayoutManager);
        uiStickyHeadAdapter = new UiStickyHeadAdapter(dataList, UIStickyHeadActivity.this);
        uiStickyRecycler.setAdapter(uiStickyHeadAdapter);
        uiStickyRecycler.addOnScrollListener(new UIStickyHeadScrollListener());
    }

    @Override
    public void setView(View contentView) {
        uiStickyRecycler = (RecyclerView) contentView.findViewById(R.id.uiStickyRecycler);
        uiStickyHeadTv = (TextView) contentView.findViewById(R.id.uiStickyHeadTv);
    }

    @Override
    protected View getContentView() {
        return inflateView(R.layout.activity_ui_sticky_head);
    }

    private void initData() {
        for (int i = 0; i < 5; i++) {
            HeadStickyModel model = new HeadStickyModel("2018-06-24", "德国(2)-瑞典(1)   英格兰(6)-巴拿马(1)   日本(2)-塞加内尔(2)");
            dataList.add(model);
        }

        for (int i = 0; i < 5; i++) {
            HeadStickyModel model = new HeadStickyModel("2018-06-25", "波兰(0)-哥伦比亚(3)   沙特阿拉伯(2)-埃及(1)   乌拉圭(3)-俄罗斯(0)");
            dataList.add(model);
        }

        for (int i = 0; i < 5; i++) {
            HeadStickyModel model = new HeadStickyModel("2018-06-26", "伊朗(1)-葡萄牙(1)   西班牙(2)-摩洛哥(2)   澳大利亚(0)-秘鲁(2)");
            dataList.add(model);
        }

        for (int i = 0; i < 5; i++) {
            HeadStickyModel model = new HeadStickyModel("2018-06-27", "韩国()-德国()   墨西哥()-瑞典()   冰岛()-克罗地亚()");
            dataList.add(model);
        }
    }

    /**
     * ScrollListener
     */
    public class UIStickyHeadScrollListener extends RecyclerView.OnScrollListener {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            // 获取可见第一条的view，在Adapter中设置 setCotnetnDescription,要不然 null
            View childAt = recyclerView.getChildAt(0);
            if (childAt != null && childAt.getContentDescription() != null) {
                uiStickyHeadTv.setVisibility(View.VISIBLE); // 显示并附上第一个默认的值
                uiStickyHeadTv.setText(childAt.getContentDescription());
            }

            // 被覆盖的下一个item
            View childViewUnder = recyclerView.findChildViewUnder(uiStickyHeadTv.
                    getMeasuredWidth() / 2, uiStickyHeadTv.getMeasuredHeight() + 1);
            int top = childViewUnder.getTop(); // 距离顶部高度
            int detalY = top - uiStickyHeadTv.getMeasuredHeight(); // 距离顶部高度 - 覆盖物高度 = 覆盖物偏移量

            // 被覆盖下面的 item 与被覆盖的item 的头类型一致？一致不一致在 setAdapter 时通过tag 设置了
            if (((int) childViewUnder.getTag()) == 1) {
                if (top > 0) { // 如果 top >0 在覆盖物底下一部分
                    uiStickyHeadTv.setTranslationY(detalY);
                } else { // 全部移除
                    uiStickyHeadTv.setTranslationY(0);
                }
            } else {
                uiStickyHeadTv.setTranslationY(0);
            }


        }
    }

    /**
     * model
     */

    private class HeadStickyModel {
        private String head;
        private String content;
        private String time;

        public HeadStickyModel(String head, String content, String time) {
            this.head = head;
            this.content = content;
            this.time = time;
        }

        public HeadStickyModel(String head, String content) {
            this.head = head;
            this.content = content;
            this.time = "22:00";
        }
    }

    /**
     * adapter
     */
    public class UiStickyHeadAdapter extends RecyclerView.Adapter<UiStickyHeadAdapter.BaseViewHolder> {
        private ArrayList<HeadStickyModel> modelArrayList;
        private Context context;
        private final int SAME_STICKY_VIEW = 0;
        private final int DIFF_STICKY_VIEW = 1;
        private final int FIRST_STICKY_VIEW = 0;


        public UiStickyHeadAdapter(ArrayList<HeadStickyModel> modelArrayList, Context context) {
            this.modelArrayList = modelArrayList;
            this.context = context;
        }

        @Override
        public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_ui_sticky_head, parent, false);
            BaseViewHolder baseViewHolder = new BaseViewHolder(view);
            return baseViewHolder;
        }

        @Override
        public void onBindViewHolder(BaseViewHolder holder, int position) {
            HeadStickyModel headStickyModel = modelArrayList.get(position);
            if (position == 0) {
                holder.uiItemStickyHead.setTag(FIRST_STICKY_VIEW);
                holder.uiItemStickyHeadTv.setVisibility(View.VISIBLE);
            } else {
                if (modelArrayList.get(position - 1).head.equals(modelArrayList.get(position).head)) {
                    holder.uiItemStickyHead.setTag(SAME_STICKY_VIEW);
                    holder.uiItemStickyHeadTv.setVisibility(View.GONE);
                } else {
                    holder.uiItemStickyHead.setTag(DIFF_STICKY_VIEW);
                    holder.uiItemStickyHeadTv.setVisibility(View.VISIBLE);
                }
            }
            holder.uiItemStickyHead.setContentDescription(headStickyModel.head);
            holder.uiItemStickyHeadTv.setContentDescription(headStickyModel.head);
            holder.uiItemStickyHeadTv.setText(headStickyModel.head);
            holder.uiItemStickyConTv.setText(headStickyModel.content);
            holder.uiItemStickyTimeTv.setText(headStickyModel.time);
        }

        @Override
        public int getItemCount() {
            return modelArrayList.size();
        }

        public class BaseViewHolder extends RecyclerView.ViewHolder {
            public LinearLayout uiItemStickyHead;
            public TextView uiItemStickyHeadTv;
            public TextView uiItemStickyConTv;
            public TextView uiItemStickyTimeTv;

            public BaseViewHolder(View itemView) {
                super(itemView);
                uiItemStickyHead = (LinearLayout) itemView.findViewById(R.id.uiItemStickyHead);
                uiItemStickyHeadTv = (TextView) itemView.findViewById(R.id.uiItemStickyHeadTv);
                uiItemStickyConTv = (TextView) itemView.findViewById(R.id.uiItemStickyConTv);
                uiItemStickyTimeTv = (TextView) itemView.findViewById(R.id.uiItemStickyTimeTv);
            }
        }
    }
}
