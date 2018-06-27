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
    }

    @Override
    protected View getContentView() {
        return LayoutInflater.from(UIBaseActivity.this).inflate(R.layout.activity_ui_base,null);
    }
}
