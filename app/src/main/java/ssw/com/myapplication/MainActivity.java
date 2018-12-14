package ssw.com.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private  Context mcontext;
    TextView textView4;
    EditText editText2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = findViewById(R.id.textView);
      //  textView.setText(new HelloLibrary().sayHello());
        editText2 = findViewById(R.id.editText2);
        textView4 = findViewById(R.id.textView4);
        textView4.setMovementMethod(ScrollingMovementMethod.getInstance());
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,BaiduActivity.class);
                startActivity(intent);
            }
        });
        mcontext = this;
        Button button1 = findViewById(R.id.button2);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    new Thread(){
                        public void run(){

                            String url = editText2.getText().toString();

                            try {
                                JSONObject json = new JSONObject();
                                json.put("requestId",System.currentTimeMillis());
                                sendPOSTRequest(getApplicationContext(),url,json.toString(),null);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();
            }
        });
        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences spf = getSharedPreferences("SPF",0);
                SharedPreferences.Editor editor = spf.edit();
                editor.remove("baiduCookie");
                if(editor.commit()) {
                    Toast.makeText(mcontext, "Cookieq清除完成", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    /**
     * 发送请求
     * @param context
     * @param baseUrl
     * @param params
     * @param handler
     * @return
     * @throws Exception
     */
    public  boolean sendPOSTRequest(Context context,String baseUrl,String params, Handler handler) throws Exception{

       // mContext = context;

        // 设置参数
        byte[] entity = params.getBytes();

        // 设置请求链接
        HttpURLConnection conn = onSetConn(mcontext, baseUrl, entity);

        // 得到返回值
        int responseCode = conn.getResponseCode();

        if (HttpURLConnection.HTTP_OK == responseCode) {
            String charset = getResponseCharset(conn.getContentType());
            String result = getStreamAsString(conn.getInputStream(), charset);
            textView4.setText(result);
        } else {
            textView4.setText("errorcode："+responseCode);
        }

        if(conn!=null){
            conn.disconnect();
        }
        return false;
    }
  /**
            * 参数拼接
 * @return
         * @throws UnsupportedEncodingException
 */
    private  byte[] onParams(Map<String, Object> params) throws UnsupportedEncodingException {
        if (params == null || params.isEmpty()){
            params = new HashMap<>();
        }
        // 添加必须携带的参数信息
      //  params.put("phone", getPhone());
      //  params.put("password", getPassword());

        //StringBuilder是用来组拼请求参数
        StringBuilder sb = new StringBuilder();
        if(params != null && params.size() != 0){
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                sb.append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue().toString(), "utf-8"));
                sb.append("&");
            }
            sb.deleteCharAt(sb.length()-1);
        }
        return sb.toString().getBytes();
    }
    private  HttpURLConnection onSetConn(Context context, String baseUrl, byte[] entity) throws IOException {
        URL url = new URL(baseUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(30000);
        conn.setReadTimeout(5000);
        // 设置以POST方式
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Charset","utf-8");
        //要向外输出数据，要设置这个
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setUseCaches(false);
        // 1、POST请求这个一定要设置
        conn.setRequestProperty("Content-Type", "application/json");
        // 2、添加cookie信息
        SharedPreferences spf = getSharedPreferences("SPF",Context.MODE_PRIVATE);

        String temp_cookie = spf.getString("baiduCookie","");
        Log.e("test_cookie","temp_cookie is " + temp_cookie);
        if (!TextUtils.isEmpty(temp_cookie)){
            conn.setRequestProperty("Cookie",temp_cookie);
        }
        conn.setRequestProperty("Content-Length", String.valueOf(entity.length));
        // 3、参数信息
        OutputStream out = conn.getOutputStream();
        //写入参数值
        out.write(entity);
        //刷新、关闭
        out.flush();
        out.close();
        return conn;
    }
    private static String getStreamAsString(InputStream stream, String charset) throws IOException {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream, charset));
            StringWriter writer = new StringWriter();

            char[] chars = new char[256];
            int count = 0;
            while ((count = reader.read(chars)) > 0) {
                writer.write(chars, 0, count);
            }

            return writer.toString();
        } finally {
            if (stream != null) {
                stream.close();
            }
        }
    }
    private  String getResponseCharset(String ctype) {
        String charset = "UTF-8";
        if (null != ctype && ctype != "") {
            String[] params = ctype.split(";");
            for (String param : params) {
                param = param.trim();
                if (param.startsWith("charset")) {
                    String[] pair = param.split("=", 2);
                    if (pair.length == 2) {
                        if (null != pair[1] && "" != pair[1]) {
                            charset = pair[1].trim();
                        }
                    }
                    break;
                }
            }
        }

        return charset;
    }
}
