package ssw.com.myapplication.utils;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginUtils {
    public static String getDefaultParameter(){
        JSONObject json = new JSONObject();
        try {
            json.put("requestId",System.currentTimeMillis());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json.toString();
    }
}
