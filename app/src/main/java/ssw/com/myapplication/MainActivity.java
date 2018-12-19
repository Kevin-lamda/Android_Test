package ssw.com.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.logger.Logger;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.json.JSONObject;

import ssw.com.myapplication.utils.CookieUtils;
import ssw.com.myapplication.utils.HttpUtils;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {
    private  Context mcontext;
    @ViewById(R.id.tv_content)
    TextView tvContent;
    @ViewById(R.id.et_url)
    EditText etUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mcontext = this;
    }
    @Override
    protected void onStart(){
        super.onStart();
        //  textView.setText(new HelloLibrary().sayHello());
        tvContent.setMovementMethod(ScrollingMovementMethod.getInstance());

    }
    @Click(R.id.bt_login)
    public void onbuttonClick(View view) {
        Intent intent = new Intent(MainActivity.this,BaiduActivity_.class);
        startActivity(intent);
    }
    @Click(R.id.bt_clear_cookie)
    public void onbutton3Click(View view) {
        SharedPreferences spf = getSharedPreferences("SPF",0);
        SharedPreferences.Editor editor = spf.edit();
        editor.remove("baiduCookie");
        if(editor.commit()) {
            Toast.makeText(mcontext, "Cookieq清除完成", Toast.LENGTH_SHORT).show();
        }
    }
    @Click(R.id.bt_visit_url)
    public void onbutton1Click(View view) {
        new Thread(){
            public void run(){

                String url = etUrl.getText().toString();

                try {
                    JSONObject json = new JSONObject();
                    json.put("requestId",System.currentTimeMillis());
                    String contentType = "application/json; charset=utf-8";
                    String cookie = CookieUtils.getCookie(getBaseContext(),"baiduCookie");
                    String req = HttpUtils.post(url,json.toString(),contentType,cookie);
                    Logger.i(req);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
