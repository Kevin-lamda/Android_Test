package ssw.com.myapplication.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import ssw.com.myapplication.R;
import ssw.com.myapplication.adapter.DogRecycleAdapter;
import ssw.com.myapplication.baidu.BaiduActivity_;
import ssw.com.myapplication.model.PetDogModel;
import ssw.com.myapplication.utils.CookieUtils;
import ssw.com.myapplication.utils.HttpUtils;

public class HomeFragment extends Fragment{
    private Activity activity;
    private static Fragment fragment;
    public static  Fragment newInstance(String from){
        if(fragment == null){
            fragment = new HomeFragment();
        }
        return fragment;
    }
    private RecyclerView rVDogList;
    private List<PetDogModel> dataList;
    private DogRecycleAdapter dogRecycleAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
       activity = getActivity();
       View view = inflater.inflate(R.layout.fragment_home,container,false);

       rVDogList = view.findViewById(R.id.rv_dog_list);
       dogRecycleAdapter = new DogRecycleAdapter(getContext(),dataList);

       GridLayoutManager layoutManager = new GridLayoutManager(getContext(),2);
       rVDogList.setLayoutManager(layoutManager);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if(!HttpUtils.checkLogin(getContext())){
                        Intent intent = new Intent(activity,BaiduActivity_.class);
                        startActivity(intent);
                    }
                        getPetData();
                }catch (Exception e){
                    Logger.e(e.getMessage());
                }
            }
        }).start();
       rVDogList.setAdapter(dogRecycleAdapter);

       return  view;
    }
    private void getPetData() throws Exception{
        String cookie = CookieUtils.getCookie(getContext(),"baiduCookie");
        String url = "https://pet-chain.baidu.com/data/user/pet/list";
        String method = "POST";
        JSONObject param = new JSONObject();
        param.put("requestId",System.currentTimeMillis());
        String response = HttpUtils.post(url,param.toString(),method,cookie);
        JSONObject rep = new JSONObject(response);
        JSONObject data = rep.getJSONObject("data");
        JSONArray list = rep.getJSONArray("dataList");
        Gson gson = new Gson();
        for(int i = 0; i < list.length(); i++) {
            PetDogModel model = gson.fromJson(list.get(i).toString(), PetDogModel.class);
            this.dataList.add(model);
        }
    }
}
