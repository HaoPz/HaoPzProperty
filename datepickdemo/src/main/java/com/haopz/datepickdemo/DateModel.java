package com.haopz.datepickdemo;

/**
 * Created by HaoPz on 2018/6/11.
 */

public class DateModel {
    public String getIsMonthType() {
        return isMonthType;
    }

    public void setIsMonthType(String isMonthType) {
        this.isMonthType = isMonthType;
    }

    private String isMonthType; // 月份type: 0:月 1:日期
    private boolean isChick; // 是否可以点击
    private boolean isCheck; // 是否选中
    private String dateNumber; // 日期
    private String isFromMonth; // 所属月份

    public DateModel() {

    }

    public DateModel(boolean isChick, boolean isCheck, String dateNumber, String isFromMonth, String isMonthType) {
        this.isChick = isChick;
        this.isCheck = isCheck;
        this.dateNumber = dateNumber;
        this.isFromMonth = isFromMonth;
        this.isMonthType = isMonthType;
    }

    public DateModel(boolean isChick, boolean isCheck, String dateNumber, String isFromMonth) {
        this.isChick = isChick;
        this.isCheck = isCheck;
        this.dateNumber = dateNumber;
        this.isFromMonth = isFromMonth;
        this.isMonthType = "1";
    }

    public boolean isChick() {
        return isChick;
    }

    public void setChick(boolean chick) {
        isChick = chick;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public String getDateNumber() {
        return dateNumber;
    }

    public void setDateNumber(String dateNumber) {
        this.dateNumber = dateNumber;
    }

    public String getIsFromMonth() {
        return isFromMonth;
    }

    public void setIsFromMonth(String isFromMonth) {
        this.isFromMonth = isFromMonth;
    }
}
