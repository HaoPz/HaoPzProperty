package com.haopz.datepickdemo;

import android.app.DatePickerDialog;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.GetChars;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private final String TAG = getClass().getSimpleName();
    private DatePickerUtils datePickerUtils;
    private ArrayList<DateModel> dateModelArrayList;
    private int month;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CustomerView customView = findViewById(R.id.customView);
        customView.startCustomerScroll(-500,-500);





        TextView chooseZhangDanDialog = findViewById(R.id.chooseZhangDan);
        chooseZhangDanDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                DatePickerDialog.Builder datePickerDialogBuilder =new DatePickerDialog.Builder(MainActivity.this);
//                datePickerDialogBuilder.
            }
        });

        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            Toast.makeText(this, "versionName:"+packageInfo.versionName
                    +"versionCode:"+packageInfo.versionCode, Toast.LENGTH_SHORT).show();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }



        DatePicker datePicker = findViewById(R.id.chooseZhangDanDialog);
        datePicker.init(Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Log.i(TAG,"year:" +year +"  month:" + monthOfYear +"  dayOfMonth:"+dayOfMonth);
                    }
                });

    }

    public void openDateBottomSheetDialog(View view) {

        View inflate = LayoutInflater.from(this).inflate(R.layout.item_month, null);
        RecyclerView dateRecyclerview = inflate.findViewById(R.id.dateRecyclerview);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 7);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (dateModelArrayList.get(position).getIsMonthType().equals("0")) {
                    return 7;
                } else {
                    return 1;
                }
            }
        });

        dateRecyclerview.setLayoutManager(gridLayoutManager);

        dateModelArrayList = new ArrayList();
        dateModelArrayList.clear();

        Calendar aCalendar = Calendar.getInstance();
        aCalendar.set(2018,12,30);



        // Calendar aCalendar = Calendar.getInstance(Locale.CHINA);
        int year = aCalendar.get(Calendar.YEAR);//年份
        month = aCalendar.get(Calendar.MONTH) + 1;//月份
        int day = aCalendar.getActualMaximum(Calendar.DATE);
        if (!datePickerUtils.isShowTwoMonth) {
            getDayListOfMonth(aCalendar, year, month, day);
        } else {
            getDayListOfMonth(aCalendar, year, month, day);
            if (month == 12) {
                month = 1;
                getDayListOfMonth(aCalendar, year + 1, month, day);
            } else {
                getDayListOfMonth(aCalendar, year, month + 1, day);
            }
        }

        DateAdapter dateAdapter = new DateAdapter(dateModelArrayList, MainActivity.this);
        dateRecyclerview.setAdapter(dateAdapter);


        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(inflate);
        bottomSheetDialog.show();
    }


    //java获取当前月的天数
    public int getDayOfMonth() {
        Calendar aCalendar = Calendar.getInstance(Locale.CHINA);
        int day = aCalendar.getActualMaximum(Calendar.DATE);
        return day;
    }

    //java获取当前月每天的日期
    public ArrayList<DateModel> getDayListOfMonth(Calendar aCalendar, int year, int month, int day) {


        dateModelArrayList.add(new DateModel(false, false, year + "年" + month + "月", String.valueOf(month), "0"));

        Date time = aCalendar.getTime();
        time.setDate(1);
        String weekOfDate = getWeekOfDate(time);
        addStringDate(weekOfDate, dateModelArrayList, month);

        for (int i = 1; i <= day; i++) {
            if (i == Integer.parseInt(datePickerUtils.getZhangDanDate())) {  // 账单日
                dateModelArrayList.add(new DateModel(false, false, "账单日", String.valueOf(month)));
            } else if (i == Integer.parseInt(datePickerUtils.getHuanKuanDate())) { // 还款日
                dateModelArrayList.add(new DateModel(false, false, "还款日", String.valueOf(month)));
            } else if (i > Integer.parseInt(datePickerUtils.getZhangDanDate()) && i < Integer.parseInt(datePickerUtils.getHuanKuanDate())) { // 账单日和还款日之间
                dateModelArrayList.add(new DateModel(true, false, String.valueOf(i), String.valueOf(month)));
            } else {
                dateModelArrayList.add(new DateModel(false, false, String.valueOf(i), String.valueOf(month)));
            }
        }
        return dateModelArrayList;
    }

    // 拼接前面数据
    private void addStringDate(String weekOfDate, ArrayList<DateModel> list, int month) {
        if ("星期一".equals(weekOfDate)) {
            list.add(new DateModel(false, false, "", String.valueOf(month)));
        } else if ("星期二".equals(weekOfDate)) {
            list.add(new DateModel(false, false, "", String.valueOf(month)));
            list.add(new DateModel(false, false, "", String.valueOf(month)));
        } else if ("星期三".equals(weekOfDate)) {
            list.add(new DateModel(false, false, "", String.valueOf(month)));
            list.add(new DateModel(false, false, "", String.valueOf(month)));
            list.add(new DateModel(false, false, "", String.valueOf(month)));
        } else if ("星期四".equals(weekOfDate)) {
            list.add(new DateModel(false, false, "", String.valueOf(month)));
            list.add(new DateModel(false, false, "", String.valueOf(month)));
            list.add(new DateModel(false, false, "", String.valueOf(month)));
            list.add(new DateModel(false, false, "", String.valueOf(month)));
        } else if ("星期五".equals(weekOfDate)) {
            list.add(new DateModel(false, false, "", String.valueOf(month)));
            list.add(new DateModel(false, false, "", String.valueOf(month)));
            list.add(new DateModel(false, false, "", String.valueOf(month)));
            list.add(new DateModel(false, false, "", String.valueOf(month)));
            list.add(new DateModel(false, false, "", String.valueOf(month)));
        } else if ("星期六".equals(weekOfDate)) {
            list.add(new DateModel(false, false, "", String.valueOf(month)));
            list.add(new DateModel(false, false, "", String.valueOf(month)));
            list.add(new DateModel(false, false, "", String.valueOf(month)));
            list.add(new DateModel(false, false, "", String.valueOf(month)));
            list.add(new DateModel(false, false, "", String.valueOf(month)));
            list.add(new DateModel(false, false, "", String.valueOf(month)));
        }
    }

    /**
     * 获取当前日期是星期几<br>
     *
     * @param date
     * @return 当前日期是星期几
     */
    public String getWeekOfDate(Date date) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

}
