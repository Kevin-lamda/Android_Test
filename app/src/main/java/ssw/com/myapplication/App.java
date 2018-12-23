package ssw.com.myapplication;

import android.app.Application;
import android.content.Intent;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import ssw.com.myapplication.baidu.BaiduActivity;
import ssw.com.myapplication.home.HomeActivity;
import ssw.com.myapplication.utils.HttpUtils;

public class App extends Application {
    private static Application app;
    public static String LOGION_URL = "https://wappass.baidu.com/passport/login?sms=1&adapter=3&u=%2F%2Fwappass.baidu.com%2Fv3%2Flogin%2Fapi%2Fauth%2F%3Freturn_type%3D5%26tpl%3Dblockchain%26u%3Dhttps%253A%252F%252Fpet-chain.baidu.com%252Fdata%252Fuser%252Fsign%253Fu%253Dhttps%25253A%25252F%25252Fpet-chain.baidu.com%25252Fchain%25252Fpersonal";
    public static String PET_CHAIN_URL = "https://pet-chain.baidu.com/data/user/get";
    public static Application newInstance(){
        if(app == null) {
            app = new App();
        }
        return app;
    }
    @Override
    public void onCreate(){
        super.onCreate();
        Logger.addLogAdapter(new AndroidLogAdapter());
        Logger.d("hello");
    }
}
