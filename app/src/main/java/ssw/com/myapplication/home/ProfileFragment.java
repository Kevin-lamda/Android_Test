package ssw.com.myapplication.home;

import android.support.v4.app.Fragment;

public class ProfileFragment extends Fragment {
    public static  Fragment newInstance(String from){
        Fragment fragment = new ProfileFragment();
        return fragment;
    }
}
