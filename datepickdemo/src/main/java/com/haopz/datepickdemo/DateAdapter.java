package com.haopz.datepickdemo;


import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by HaoPz on 2018/6/11.
 */

public class DateAdapter extends RecyclerView.Adapter<DateAdapter.DateViewHolder> {
    private final int FLAG_MONTH = 0;
    private final int FLAG_DATE = 1;

    private ArrayList<DateModel> list;
    private Context mContext;

    private View monthView;
    private View dateView;


    public DateAdapter(ArrayList<DateModel> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;


    }

    @Override
    public int getItemViewType(int position) {
        if (list.get(position).getIsMonthType().equals("0")) { // 月份
            return FLAG_MONTH;
        } else if(list.get(position).getIsMonthType().equals("1")){ // 日期
            return FLAG_DATE;
        }
        return super.getItemViewType(position);
    }

    @NonNull
    @Override
    public DateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        DateViewHolder dateViewHolder = null;
        if (viewType == FLAG_MONTH) {
            monthView = LayoutInflater.from(mContext).inflate(R.layout.item_head, parent,false);
            dateViewHolder = new HeadViewHolder(monthView);
        } else if (viewType == FLAG_DATE) {
            dateView = LayoutInflater.from(mContext).inflate(R.layout.item_date, parent,false);
            dateViewHolder = new ContentViewHolder(dateView);
        }
        return dateViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final DateViewHolder holder, final int position) {
        int itemViewType = holder.getItemViewType();
        if(itemViewType == FLAG_MONTH){
            ((HeadViewHolder)holder).monthTv.setText(list.get(position).getDateNumber());
        }else if(itemViewType == FLAG_DATE){
            ((ContentViewHolder)holder).content.setText(list.get(position).getDateNumber());

            if(!list.get(position).isChick()){
                ((ContentViewHolder)holder).content.setTextColor(Color.GRAY);
                ((ContentViewHolder)holder).content.setClickable(false);
                ((ContentViewHolder)holder).content.setEnabled(false);
            }

            if(list.get(position).getDateNumber().equals("账单日") || list.get(position).getDateNumber().equals("还款日")){
                ViewGroup.LayoutParams layoutParams = ((ContentViewHolder) holder).content.getLayoutParams();
                layoutParams.width = mContext.getResources().getDisplayMetrics().widthPixels;
                ((ContentViewHolder) holder).content.setLayoutParams(layoutParams);
                ((ContentViewHolder) holder).content.setTextColor(mContext.getResources().getColor(R.color.color_f66867));
            }

            if(list.get(position).isCheck()){
                ((ContentViewHolder)holder).content.setSelected(true);
            }else{
                ((ContentViewHolder)holder).content.setSelected(false);
            }

            ((ContentViewHolder)holder).content.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((ContentViewHolder)holder).content.setSelected(!list.get(position).isCheck());
                    list.get(position).setCheck(!list.get(position).isCheck());
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class DateViewHolder extends RecyclerView.ViewHolder {
        public DateViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    private class HeadViewHolder extends DateViewHolder {

        public TextView monthTv;

        public HeadViewHolder(@NonNull View itemView) {
            super(itemView);

            monthTv = itemView.findViewById(R.id.monthTv);
        }
    }

    private class ContentViewHolder extends DateViewHolder {

        public TextView content ;
        public ContentViewHolder(@NonNull View itemView) {
            super(itemView);
            content = itemView.findViewById(R.id.content);
        }
    }
}
