package ssw.com.myapplication.baidu;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.CookieManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.orhanobut.logger.Logger;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.HashMap;
import java.util.Map;

import ssw.com.myapplication.App;
import ssw.com.myapplication.R;
import ssw.com.myapplication.utils.CookieUtils;

@EActivity(R.layout.activity_baidu)
public class BaiduActivity extends AppCompatActivity {
    @ViewById(R.id.webView)
    WebView webView;
    private String TAG = "BaiduActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public void onStart(){
        super.onStart();
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
        webView.loadUrl(App.LOGION_URL);
        webView.setWebViewClient(new MyWebViewClient());
    }
    private class MyWebViewClient extends WebViewClient {

        /**
         * 在开始加载网页时会回调
         */
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            Logger.d(TAG,"onPageStarted");
        }
        /**
         * 在结束加载网页时会回调
         */
        public void onPageFinished(WebView view, String url) {
            CookieManager cookieManager = CookieManager.getInstance();
            String CookieStr = cookieManager.getCookie(url);
            if (CookieStr != null) {
                if(url.startsWith(App.PET_CHAIN_URL)) {
                    Map<String,String> cookieMap = resolveCookie(CookieStr);
                    String BDUSS = cookieMap.get("BDUSS");
                    String STOKEN = cookieMap.get("STOKEN");
                    if(isNoBlank(BDUSS) && isNoBlank(STOKEN)){
                        boolean status = CookieUtils.setCookie(getBaseContext(),"baiduCookie", "BDUSS="+BDUSS+";STOKEN="+STOKEN);
                        if(status) {
                            Toast.makeText(getBaseContext(), "Cookieq存储完成", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                Logger.i(TAG+":url",url);
                Logger.i(TAG+":cookie", CookieStr);
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
            Logger.e(TAG,"出错误了");
        }
        /**
         * 当接收到https错误时，会回调此函数，在其中可以做错误处理
         */
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error){
            Logger.e(TAG,"出错误了");
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
