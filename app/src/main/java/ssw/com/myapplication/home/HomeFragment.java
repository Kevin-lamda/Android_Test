package ssw.com.myapplication.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ssw.com.myapplication.R;

public class HomeFragment extends Fragment{
    private static Fragment fragment;
    public static  Fragment newInstance(String from){
        if(fragment == null){
            fragment = new HomeFragment();
        }
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
       View view = inflater.inflate(R.layout.fragment_home,container,false);
       return  view;
    }
}
