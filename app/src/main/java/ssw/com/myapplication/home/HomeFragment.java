package ssw.com.myapplication.home;

import android.support.v4.app.Fragment;

public class HomeFragment extends Fragment{
    public static  Fragment newInstance(String from){
        Fragment fragment = new HomeFragment();
        return fragment;
    }
}
