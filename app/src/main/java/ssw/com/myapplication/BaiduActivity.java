package ssw.com.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class BaiduActivity extends AppCompatActivity {
    private WebView webView;
    private String TAG = "BaiduActivity";
    //private String url = "https://www.baidu.com";
    private String url = "https://wappass.baidu.com/passport/login?sms=1&adapter=3&u=%2F%2Fwappass.baidu.com%2Fv3%2Flogin%2Fapi%2Fauth%2F%3Freturn_type%3D5%26tpl%3Dblockchain%26u%3Dhttps%253A%252F%252Fpet-chain.baidu.com%252Fdata%252Fuser%252Fsign%253Fu%253Dhttps%25253A%25252F%25252Fpet-chain.baidu.com%25252Fchain%25252Fpersonal";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baidu);

        webView = findViewById(R.id.webView);
        webView.requestFocus();
        webView.setHorizontalScrollBarEnabled(false);
        webView.setVerticalScrollBarEnabled(false);
        WebSettings web = webView.getSettings();
        web.setJavaScriptEnabled(true);
        web.setBuiltInZoomControls(true);
        web.setSupportZoom(true);
        web.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        web.setUseWideViewPort(true);
        web.setLoadWithOverviewMode(true);
        web.setSavePassword(true);
        web.setSaveFormData(true);
        webView.loadUrl(url);
        webView.setWebViewClient(new MyWebViewClient());
    }

    private class MyWebViewClient extends WebViewClient {

        /**
         * 在开始加载网页时会回调
         */
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            Log.d(TAG,"onPageStarted");
        }
        /**
         * 在结束加载网页时会回调
         */
        public void onPageFinished(WebView view, String url) {
            CookieManager cookieManager = CookieManager.getInstance();
            String CookieStr = cookieManager.getCookie(url);
            if (CookieStr != null) {
                if(url.startsWith("https://pet-chain.baidu.com")) {

                    Map<String,String> cookieMap = resolveCookie(CookieStr);
                    String BDUSS = cookieMap.get("BDUSS");
                    String STOKEN = cookieMap.get("STOKEN");
                    if(isNoBlank(BDUSS) && isNoBlank(STOKEN)){
                        SharedPreferences spf = getSharedPreferences("SPF", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = spf.edit();
                        editor.putString("baiduCookie", "BDUSS="+BDUSS+";STOKEN="+STOKEN);
                        if(editor.commit()) {
                            Toast.makeText(getApplicationContext(), "Cookieq存储完成", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                Log.i(TAG+":url",url);
                Log.i(TAG+":cookie", CookieStr);
            }
            super.onPageFinished(view, url);
        }

        /**
         * 拦截 url 跳转,在里边添加点击链接跳转或者操作
         */
        public boolean shouldOverrideUrlLoading(WebView view, String url){
            view.loadUrl(url);
            return  true;
        }
        /**
         * 加载错误的时候会回调，在其中可做错误处理，比如再请求加载一次，或者提示404的错误页面
         */
        public void onReceivedError(WebView view, int errorCode,String description, String failingUrl){
            Log.e(TAG,"出错误了");
        }
        /**
         * 当接收到https错误时，会回调此函数，在其中可以做错误处理
         */
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error){
                Log.e(TAG,"出错误了");
                super.onReceivedSslError(view,handler,error);
        }
        /**
         * 在每一次请求资源时，都会通过这个函数来回调
         */
        public WebResourceResponse shouldInterceptRequest(WebView view,String url) {
            return null;
        }


    }
    public Map<String,String> resolveCookie(String orgCookie){
        Map<String,String> cookieMap = new HashMap<>();
        String[] orgCookies = orgCookie.split(";");
        for(int i = 0; i < orgCookies.length; i++){
            String[] items = orgCookies[i].split("=");
            cookieMap.put(items[0].trim(),items[1].trim());
        }
        return cookieMap;
    }
    public boolean isNoBlank(String str){
        if(null == str){
            return false;
        }
        if("".equals(str.trim())){
            return false;
        }
        return true;
    }
}
