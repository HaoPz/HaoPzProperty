package com.haopz.bottomsheetdialogdemo;

import android.content.Intent;
import android.net.Uri;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private final String TAG = getClass().getSimpleName();

    private TextView openBottomSheet;
    private BottomSheetBehavior<View> bottomSheetBehavior;

    private TextView openBottomSheetDialog;
    private BottomSheetDialog bottomSheetDialog;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // -----------------------------------------------------------------------------------------
        openBottomSheet = findViewById(R.id.openBottomSheet);
        final View bottomSheetView = findViewById(R.id.bottom_sheet);
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetView);
        openBottomSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED){
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }else{
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
        openBottomSheetDialog = findViewById(R.id.openBottomSheetDialog);
        openBottomSheetDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.show();
            }
        });

        // -----------------------------------------------------------------------------------------
        TextView openBottomSheetDialogFragment = findViewById(R.id.openBottomSheetDialogFragment);
        openBottomSheetDialogFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new HaopzBottomSheetDialogFragment().show(getSupportFragmentManager(), "Dialog");
            }
        });
    }

    private void createBottomSheetDialog() {
        bottomSheetDialog = new BottomSheetDialog(this);
        View view = getLayoutInflater().inflate(R.layout.item_bottom_sheet_dialog, null);
        view.findViewById(R.id.bottomSheetDialogTwo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bottomSheetDialog != null && bottomSheetDialog.isShowing()){
                    bottomSheetDialog.dismiss();
                }
            }
        });
        bottomSheetDialog.setContentView(view);
    }
}
