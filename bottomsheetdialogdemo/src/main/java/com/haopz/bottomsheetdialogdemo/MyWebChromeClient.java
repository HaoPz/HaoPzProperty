package com.haopz.bottomsheetdialogdemo;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.webkit.PermissionRequest;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.TextView;


/**
 * Created by shengwei on 2016/11/18.
 */

public class MyWebChromeClient extends WebChromeClient {
    private TextView action_bar_title;
    private ProgressBar webview_progress;
    private boolean isFirstInit;
    private Context context ;

    public MyWebChromeClient(TextView action_bar_title, ProgressBar webview_progress) {
        this.action_bar_title = action_bar_title;
        this.webview_progress = webview_progress;
        isFirstInit=false;

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onPermissionRequest(final PermissionRequest request) {
        request.grant(request.getResources());
    }

    /***
     * 用于解决首次进入HOMEfragment加载webview的title覆盖收银台标题的BUG
     *
     * */
    public void setIsFirstInit(boolean isFirstInit, Context context){
        this.isFirstInit=isFirstInit;
        this.context=context;

    }
    @Override
    public void onReceivedTitle(WebView view, String title) {
        super.onReceivedTitle(view, title);
        if (action_bar_title==null){
            return;
        }
        if (title != null) {
            if (title.length() > 7) {
                String subTitle = title.substring(0, 7);
                action_bar_title.setText(subTitle + "...");
            } else {
                action_bar_title.setText(title + "");
            }
        }
        if (isFirstInit){
            isFirstInit=false;
            action_bar_title.setText("");
        }

    }

    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        if (newProgress == 100) {
            webview_progress.setVisibility(View.GONE);
        } else {
            if (webview_progress.getVisibility() == View.GONE)
                webview_progress.setVisibility(View.VISIBLE);
            webview_progress.setProgress(newProgress);
        }
        super.onProgressChanged(view, newProgress);
    }

}
