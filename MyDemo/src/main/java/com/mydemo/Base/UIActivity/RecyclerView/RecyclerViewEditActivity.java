package com.mydemo.Base.UIActivity.RecyclerView;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.mydemo.Base.Base2Activity;
import com.mydemo.R;

import java.util.ArrayList;

/**
 * Item 中有EditText时候,Recycler 滑动 Item复用时候EditText 的数据会错乱显示，
 * <p>
 * 解决办法:
 * 通过EditText 的 TextWatcher 进行监听,保存数据源，给 EditText 设置TextWatcher 为 Tag
 * 在每次 Bind 绑定和复用时候,判断Tag，如果是TextWatcher类型，先移除对应监听
 * <p>
 * Created by HaoPz on 2018/7/31.
 */

public class RecyclerViewEditActivity extends Base2Activity {

    private ArrayList<String> arrayList;

    @Override
    public void setDate() {

    }

    @Override
    public void setView(View contentView) {
        getData();

        TextView toolBar1 = (TextView) contentView.findViewById(R.id.toolBar1);
        toolBar1.setText("RecyclerView Item 为EditText 数据错乱问题");
        RecyclerView recyclerViewEdit = (RecyclerView) contentView.findViewById(R.id.recyclerViewEdit);
        recyclerViewEdit.setLayoutManager(new LinearLayoutManager(RecyclerViewEditActivity.this));
        RecyclerViewEditAdapter recyclerViewEditAdapter = new RecyclerViewEditAdapter(arrayList, RecyclerViewEditActivity.this);
        recyclerViewEdit.setAdapter(recyclerViewEditAdapter);
    }

    private void getData() {
        arrayList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            arrayList.add("当前条目： " + i);
        }
    }

    @Override
    protected View getContentView() {
        return inflateView(R.layout.activity_recycler_edit);
    }

    // -----------------Adapter
    class RecyclerViewEditAdapter extends RecyclerView.Adapter<RecyclerViewEditAdapter.RecyclerViewEditViewHolder> {
        private ArrayList<String> arrayList;
        private Context context;

        public RecyclerViewEditAdapter(ArrayList<String> arrayList, Context context) {
            this.arrayList = arrayList;
            this.context = context;
        }

        @Override
        public RecyclerViewEditViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.item_recyclerview_edit, parent, false);
            RecyclerViewEditViewHolder viewHolder = new RecyclerViewEditViewHolder(inflate);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(RecyclerViewEditViewHolder holder, final int position) {
            if (holder.editText.getTag() instanceof TextWatcher) {
                holder.editText.removeTextChangedListener((TextWatcher) holder.editText.getTag());
            }

            holder.editText.setText(arrayList.get(position));
            TextWatcher textWatcher = new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (s.length() > 0) {
                        arrayList.set(position, s.toString());
                    } else {
                        arrayList.set(position, "");
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            };

            holder.editText.setTag(textWatcher);
            holder.editText.addTextChangedListener(textWatcher);
        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }

        class RecyclerViewEditViewHolder extends RecyclerView.ViewHolder {
            public EditText editText;

            public RecyclerViewEditViewHolder(View itemView) {
                super(itemView);
                editText = (EditText) itemView.findViewById(R.id.edit);
            }
        }
    }
}
