package ssw.com.myapplication.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class CookieUtils {
    public static String getCookie(Context context,String key){
        key = StringUtils.isEmpty(key) ? "baiduCookie" : key;
        SharedPreferences spf = context.getSharedPreferences("SPF",Context.MODE_PRIVATE);
        String cookie = spf.getString(key,"");
        return cookie;
    }
    public static boolean setCookie(Context context,String key,String cookie){
        key = StringUtils.isEmpty(key) ? "baiduCookie" : key;
        SharedPreferences spf = context.getSharedPreferences("SPF",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = spf.edit();
        editor.putString(key,cookie);
        return editor.commit() ? true : false;
    }
    public static boolean clearCookie(Context context,String key){
        key = StringUtils.isEmpty(key) ? "baiduCookie" : key;
        SharedPreferences spf = context.getSharedPreferences("SPF",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = spf.edit();
        editor.remove(key);
        return editor.commit() ? true : false;
    }
}
