package ssw.com.myapplication;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

public class BaiduActivity extends AppCompatActivity {
    private WebView web;
    private String url = "https://wappass.baidu.com/passport/login?sms=1&adapter=3&u=%2F%2Fwappass.baidu.com%2Fv3%2Flogin%2Fapi%2Fauth%2F%3Freturn_type%3D5%26tpl%3Dblockchain%26u%3Dhttps%253A%252F%252Fpet-chain.baidu.com%252Fdata%252Fuser%252Fsign%253Fu%253Dhttps%25253A%25252F%25252Fpet-chain.baidu.com%25252Fchain%25252Fpersonal";
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baidu);
        web = findViewById(R.id.webView);
        web.loadUrl(url);
    }
    public class MyWebViewClient extends WebViewClient {
        String url;
        WebView view;
        Activity activity;
        TextView textView;
        public MyWebViewClient(Activity activity)
        {
            //  this.textView =textView;
            this.activity =activity;
        }

        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
        @Override
        protected void onPageFinished(WebView view, String url) {
            CookieManager cookieManager = CookieManager.getInstance();
            String CookieStr = cookieManager.getCookie(url);
            //Common.cookie = CookieStr;
            Toast.makeText(activity,CookieStr,Toast.LENGTH_SHORT).show();
            super.onPageFinished(view, url);
        }
    }

}
