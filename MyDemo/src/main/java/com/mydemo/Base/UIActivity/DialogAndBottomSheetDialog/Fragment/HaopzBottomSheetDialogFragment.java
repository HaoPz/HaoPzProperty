package com.mydemo.Base.UIActivity.DialogAndBottomSheetDialog.Fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.View;

import com.mydemo.R;

/**
 * Created by HaoPz on 2018/6/1.
 */

public class HaopzBottomSheetDialogFragment extends BottomSheetDialogFragment {

    private BottomSheetBehavior<View> viewBottomSheetBehavior;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        View view = View.inflate(getContext(), R.layout.fragment_bottom_sheet_dialog, null);
        bottomSheetDialog.setContentView(view);
        viewBottomSheetBehavior = BottomSheetBehavior.from((View) view.getParent());
        return bottomSheetDialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        // 默认全屏展开
        viewBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }

}
