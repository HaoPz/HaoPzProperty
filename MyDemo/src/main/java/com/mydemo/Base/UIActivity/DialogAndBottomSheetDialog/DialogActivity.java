package com.mydemo.Base.UIActivity.DialogAndBottomSheetDialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.mydemo.Base.Base2Activity;
import com.mydemo.R;

/**
 * Created by HaoPz on 2018/7/31.
 */

public class DialogActivity extends Base2Activity {

    @Override
    public void setDate() {

    }

    @Override
    public void setView(View contentView) {
        TextView showDialogOne = (TextView) contentView.findViewById(R.id.showDialogOne);
        showDialogOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog1();
            }
        });
    }

    @Override
    protected View getContentView() {
        return inflateView(R.layout.actitvity_dialog);
    }

    public void showDialog1() {
        AlertDialog.Builder builder = new AlertDialog.Builder(DialogActivity.this, R.style.dialogCommonStyle);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        Window window = alertDialog.getWindow();
        View inflate = LayoutInflater.from(DialogActivity.this).inflate(R.layout.view_dialog_one, null);
        window.setContentView(inflate);
        window.setGravity(Gravity.CENTER);
    }
}
