package com.mydemo.Base.UIActivity;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.mydemo.Base.Base2Activity;
import com.mydemo.R;

/**
 * Created by HaoPz on 2018/6/25.
 */

public class UIBaseActivity extends Base2Activity {

    private TextView toolBarTextView;

    private TextView item_tan_xing_head;
    private TextView photo_choose_picker;
    private TextView photo_choose_picker2;

    private TextView sticky_header1;
    private TextView dialog_fragment_demo;
    private TextView diy_viewgroup;

    @Override
    public void setDate() {
        item_tan_xing_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goTanXingHead = new Intent(UIBaseActivity.this, TanXingHeadActivity.class);
                startActivity(goTanXingHead);
            }
        });

        photo_choose_picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goPhotoChoosePicker = new Intent(UIBaseActivity.this, PhotoChooosePickerActivity.class);
                startActivity(goPhotoChoosePicker);
            }
        });

        photo_choose_picker2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goPhotoChoosePicker = new Intent(UIBaseActivity.this, PhotoChooosePickerSolveOOMActivity.class);
                startActivity(goPhotoChoosePicker);
            }
        });

        sticky_header1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goPhotoChoosePicker = new Intent(UIBaseActivity.this, UIStickyHeadActivity.class);
                startActivity(goPhotoChoosePicker);
            }
        });

        dialog_fragment_demo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goDialogFragmentActivity = new Intent(UIBaseActivity.this, DialogFragmentActivity.class);
                startActivity(goDialogFragmentActivity);
            }
        });

        diy_viewgroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goDialogFragmentActivity = new Intent(UIBaseActivity.this, MyHorizontalScrollViewActivity.class);
                startActivity(goDialogFragmentActivity);
            }
        });
    }

    @Override
    public void setView(View contentView) {
        toolBarTextView = (TextView) contentView.findViewById(R.id.toolBar);
        toolBarTextView.setText("Android UI 效果");

        item_tan_xing_head = (TextView) contentView.findViewById(R.id.item_tan_xing_head);
        item_tan_xing_head.setText("弹性View");

        photo_choose_picker = (TextView) contentView.findViewById(R.id.photo_choose_picker);
        photo_choose_picker.setText("卡卫士--> 分享，图片选择器，horizontalscrollview 加载多图会 OOM");

        photo_choose_picker2 = (TextView) contentView.findViewById(R.id.photo_choose_picker2);
        photo_choose_picker2.setText("自定义 horizontalscrollview 防止OOM");

        sticky_header1 = (TextView) contentView.findViewById(R.id.sticky_header1);
        sticky_header1.setText("列表粘性头布局方式一:滑动监听");

        dialog_fragment_demo = (TextView) contentView.findViewById(R.id.dialog_fragment_demo);
        dialog_fragment_demo.setText("DialogFragment Demo");

        diy_viewgroup = (TextView) contentView.findViewById(R.id.diy_viewgroup);
        diy_viewgroup.setText("横向滑动的ViewGroup,包含子View滑动");
    }

    @Override
    protected View getContentView() {
        return LayoutInflater.from(UIBaseActivity.this).inflate(R.layout.activity_ui_base,null);
    }
}
