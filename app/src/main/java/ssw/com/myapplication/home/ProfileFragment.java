package ssw.com.myapplication.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.androidannotations.annotations.Click;

import ssw.com.myapplication.R;
import ssw.com.myapplication.baidu.BaiduActivity;

public class ProfileFragment extends Fragment {
    private static Fragment fragment;
    public static  Fragment newInstance(String from){
        if(fragment == null) {
            fragment = new ProfileFragment();
        }
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_profile,container,false);
        Button btLogin = view.findViewById(R.id.bt_login);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),BaiduActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
