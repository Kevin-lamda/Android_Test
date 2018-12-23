package ssw.com.myapplication.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.List;

import ssw.com.myapplication.R;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class DogRecycleAdapter extends RecyclerView.Adapter {
    private Context mcontext;
    private List<?> dataList;
    public DogRecycleAdapter(Context context,List<?> dataList){
        this.mcontext = context;
        this.dataList = dataList;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyle_item_dog_list,viewGroup,false);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        Object dataJson = dataList.get(i);

    }

    @Override
    public int getItemCount() {
        return dataList ==null ? 0 : dataList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{

        public MyHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
