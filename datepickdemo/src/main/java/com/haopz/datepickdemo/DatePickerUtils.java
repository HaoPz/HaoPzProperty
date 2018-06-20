package com.haopz.datepickdemo;

import android.content.ContentValues;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by HaoPz on 2018/6/11.
 */

public class DatePickerUtils {
    /**
     * 账单日
     * */
    private String zhangDanDate;

    public String getZhangDanDate() {
        return zhangDanDate;
    }

    public void setZhangDanDate(String zhangDanDate) {
        this.zhangDanDate = zhangDanDate;
    }

    public String getHuanKuanDate() {
        return huanKuanDate;
    }

    public void setHuanKuanDate(String huanKuanDate) {
        this.huanKuanDate = huanKuanDate;
    }

    /**
     * 还款日
     * */
    private String huanKuanDate;

    /**
     * 是否显示俩个月View
     * */
    public boolean isShowTwoMonth;
    private Context mContext ;

    public DatePickerUtils(String zhangDanDate, String huanKuanDate, Context mContext) {
        this.zhangDanDate = zhangDanDate;
        this.huanKuanDate = huanKuanDate;
        this.mContext = mContext ;

        /**
         * 账单日 > 还款日  25 -- 9 ==》 俩个月
         * 账单日 < 还款日  8  -- 24 ==》 一个月
         * */
        if (Integer.parseInt(zhangDanDate) > Integer.parseInt(huanKuanDate)) {
            isShowTwoMonth = true;
        } else {
            isShowTwoMonth = false;
        }
    }

    private View getDatePickView(){
        View datePickView = null ;
        if(isShowTwoMonth){
            datePickView = LayoutInflater.from(mContext).inflate(R.layout.item_month,null);
        }else{
            datePickView = LayoutInflater.from(mContext).inflate(R.layout.item_two_month,null);
        }
        return datePickView;
    }
}
