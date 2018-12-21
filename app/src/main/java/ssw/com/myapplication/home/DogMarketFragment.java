package ssw.com.myapplication.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ssw.com.myapplication.R;
import ssw.com.myapplication.other.CMakeListsHello;
import ssw.com.myapplication.other.HelloLibrary;

public class DogMarketFragment extends Fragment {
    private static Fragment fragment;
    public static  Fragment newInstance(String from){
        if(fragment == null) {
            fragment = new DogMarketFragment();
        }
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_dog_market,container,false);
        TextView cmake = view.findViewById(R.id.tv_cpp_cmake);
        TextView mk = view.findViewById(R.id.tv_cpp_mk);

        mk.setText(new HelloLibrary().sayHello());
        cmake.setText(new CMakeListsHello().stringFromJNI());
        return view;
    }
}
