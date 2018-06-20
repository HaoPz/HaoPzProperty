package com.haopz.androidpaomadeng;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by HaoPz on 2018/6/1.
 */

public class FouceTextView extends android.support.v7.widget.AppCompatTextView {
    public FouceTextView(Context context) {
        super(context);
    }

    public FouceTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FouceTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean isFocused() {
        return true;
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {

    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {

    }
}
