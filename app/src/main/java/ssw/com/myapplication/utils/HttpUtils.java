package ssw.com.myapplication.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.orhanobut.logger.Logger;

import java.io.IOException;

import okhttp3.Cookie;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpUtils {
    private static OkHttpClient client = new OkHttpClient();
    public static String post(String url,String paramters,String mediaTypeStr,String cookieStr){
        HttpUrl httpUrl = HttpUrl.parse(url);
        Request request;
        RequestBody body;
        MediaType mediaType;
        Response response;
        try {
            if(!StringUtils.isEmpty(mediaTypeStr)){
                mediaType = MediaType.parse(mediaTypeStr);
                body = RequestBody.create(mediaType,paramters);
                request = new Request.Builder().url(httpUrl).post(body).header("Cookie",cookieStr).build();
                response = client.newCall(request).execute();
                if(!response.isSuccessful()){
                    throw new IOException("Unexpected code " + response);
                }
                return response.body().string();
            }else{
                throw new IOException("MediaType is not found");
            }
        }catch (Exception e){
            Logger.e("OkHttp Post:",e.getMessage());
        }
        return  null;
    }
    public static String get(String url,String paramters){
        Request request;
        Response response;
        try {
                url+="?"+paramters;
                request = new Request.Builder().url(url).get().build();
                response = client.newCall(request).execute();
                if(!response.isSuccessful()){
                    throw new IOException("Unexpected code " + response);
                }
                return response.body().string();
        }catch (Exception e){
            Logger.e("OkHttp GET:",e.getMessage());
        }
        return  null;
    }
}
