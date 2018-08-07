package com.mydemo.Base.UIActivity.DialogAndBottomSheetDialog;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mydemo.Base.Base2Activity;
import com.mydemo.Base.UIActivity.DialogAndBottomSheetDialog.Fragment.HaopzBottomSheetDialogFragment;
import com.mydemo.R;


/**
 * api 23 Design Support Library 新加了一个 BottomSheets,
 * BottomSheet 包括 BottomSheets、BottomSheetsDialog、BottomSheetsDialogFragment
 * <p>
 * 作用: 在页面底图弹出可滑动关闭视图
 * <p>
 * 实现: 关键也是 Behavior 和 CoordinatorLayout
 * <p>
 * 注意: 1.采用View形式展示，View类必须支持滚动
 * 2.View 类必须是 CoordinatorLayout 的直接子类
 */

/**
 *
 * Created by HaoPz on 2018/7/30.
 */

public class BottomSheetDialogActivity extends Base2Activity {

    private TextView openBottomSheet;
    private final String TAG = getClass().getSimpleName();

    private BottomSheetDialog bottomSheetDialog;
    private BottomSheetBehavior<View> bottomSheetBehavior;

    @Override
    public void setDate() {

    }

    @Override
    public void setView(View contentView) {
        TextView toolBar1 = (TextView) contentView.findViewById(R.id.toolBar1);
        toolBar1.setText("BottomSheetDialog 演示");


        // -----------------------------------------------------------------------------------------
        openBottomSheet = (TextView) findViewById(R.id.openBottomSheet);
        final View bottomSheetView = findViewById(R.id.bottom_sheet);
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetView);
        openBottomSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                } else {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
            }
        });
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int i) {
                Log.i(TAG, "BottomSheetBehavior.STATE_COLLAPSED" + BottomSheetBehavior.STATE_COLLAPSED);
                Log.i(TAG, "BottomSheetBehavior.STATE_DRAGGING" + BottomSheetBehavior.STATE_DRAGGING);
                Log.i(TAG, "BottomSheetBehavior.STATE_EXPANDED" + BottomSheetBehavior.STATE_EXPANDED);
                Log.i(TAG, "BottomSheetBehavior.STATE_HIDDEN" + BottomSheetBehavior.STATE_HIDDEN);
                Log.i(TAG, "BottomSheetBehavior.STATE_SETTLING" + BottomSheetBehavior.STATE_SETTLING);

                Log.i(TAG, "onStateChanged" + "" + i);
            }

            @Override
            public void onSlide(@NonNull View view, float v) {
                Log.i(TAG, "onSlide" + "offsetOnSlide " + v);
            }
        });

        // -----------------------------------------------------------------------------------------
        createBottomSheetDialog();
        bottomSheetDialog.setCanceledOnTouchOutside(true);
        TextView openBottomSheetDialog = (TextView) findViewById(R.id.openBottomSheetDialog);
        openBottomSheetDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.show();
            }
        });

        // -----------------------------------------------------------------------------------------
        TextView openBottomSheetDialogFragment = (TextView) findViewById(R.id.openBottomSheetDialogFragment);
        openBottomSheetDialogFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new HaopzBottomSheetDialogFragment().show(getSupportFragmentManager(), "Dialog");
            }
        });

        TextView bottomSheetDialogDemo = (TextView) findViewById(R.id.bottomSheetDialogDemo);
        bottomSheetDialogDemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBottomSheetDialogDemo();
            }
        });
    }

    private void openBottomSheetDialogDemo() {
        View inflate = LayoutInflater.from(BottomSheetDialogActivity.this).inflate(R.layout.
                view_bottom_sheet_dialog_demo, null);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams
                (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(10, 0, 10, 30);
        inflate.setLayoutParams(layoutParams);
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
                BottomSheetDialogActivity.this,R.style.dialogCommonStyle);
        bottomSheetDialog.setContentView(inflate);
        bottomSheetDialog.show();
        Window window = bottomSheetDialog.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = (int) (getWindow().getDecorView().getDisplay().getWidth() * 0.9);
        window.setAttributes(attributes);
    }

    private void createBottomSheetDialog() {
        bottomSheetDialog = new BottomSheetDialog(this);
        View view = getLayoutInflater().inflate(R.layout.item_bottom_sheet_dialog, null);
        view.findViewById(R.id.bottomSheetDialogTwo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bottomSheetDialog != null && bottomSheetDialog.isShowing()) {
                    bottomSheetDialog.dismiss();
                }
            }
        });
        bottomSheetDialog.setContentView(view);
    }


    @Override
    protected View getContentView() {
        return inflateView(R.layout.activity_bottom_sheet_dialog);
    }
}
