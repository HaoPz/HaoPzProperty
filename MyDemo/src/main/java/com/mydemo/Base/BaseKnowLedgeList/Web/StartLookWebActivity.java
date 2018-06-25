package com.mydemo.Base.BaseKnowLedgeList.Web;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.mydemo.Base.Base2Activity;
import com.mydemo.R;

/**
 * Created by HaoPz on 2018/4/13.
 */

public class StartLookWebActivity extends Base2Activity {

    private String webUrl;
    private String webTitle;
    private TextView startLookToolBar;
    private WebView startLookWebView;

    @Override
    public void setDate() {
        webUrl = getIntent().getStringExtra("webUrl");
        webTitle = getIntent().getStringExtra("webTitle");
        startLookToolBar.setText(webTitle);
        startLookWebView.loadUrl(webUrl);

        //启用支持javascript
        WebSettings settings = startLookWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        startLookWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
    }

    @Override
    public void setView(View contentView) {
        startLookToolBar = (TextView) findViewById(R.id.startLookToolBar);
        startLookWebView = (WebView) findViewById(R.id.startLookWebView);
    }

    @Override
    protected View getContentView() {
        return LayoutInflater.from(this).inflate(R.layout.layout_base_knowledge_start_look_web, null);
    }

    public static void startActivity(Context context , Activity activity,String webUrl, String webTitle){
        Intent startLookIntent = new Intent(activity, StartLookWebActivity.class);
        startLookIntent.putExtra("webUrl" , webUrl );
        startLookIntent.putExtra("webTitle" , webTitle );
        context.startActivity(startLookIntent);
    }
}
