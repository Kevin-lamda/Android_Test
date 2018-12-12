package ssw.com.myapplication;

import android.app.Activity;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

public class BaiduWebView extends WebViewClient {
    String url;
    WebView view;
    Activity activity;
    TextView textView;
    public BaiduWebView(Activity activity)
    {
        //  this.textView =textView;
        this.activity =activity;
    }

    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }
    @Override
    public void onPageFinished(WebView view, String url) {
        CookieManager cookieManager = CookieManager.getInstance();
        String CookieStr = cookieManager.getCookie(url);
        //Common.cookie = CookieStr;
        Toast.makeText(activity,CookieStr,Toast.LENGTH_SHORT).show();
        super.onPageFinished(view, url);
    }
}
