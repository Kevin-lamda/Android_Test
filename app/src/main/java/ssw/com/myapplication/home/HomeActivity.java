package ssw.com.myapplication.home;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import ssw.com.myapplication.R;

public class HomeActivity extends AppCompatActivity {
    TabLayout bottomTabLayout;
    Fragment[] mfragments;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mfragments = DataGenerator.getFragments("TabLayout Tab");
        setContentView(R.layout.activity_home);
        initView();
    }
    @Override
    public void onStart(){
        super.onStart();
    }
    public void initView(){
        bottomTabLayout = findViewById(R.id.bottom_tab_layout);
        bottomTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                onTabItemSelected(tab.getPosition());
                for(int i = 0; i < bottomTabLayout.getTabCount(); i++){
                    View view = bottomTabLayout.getTabAt(i).getCustomView();
                    ImageView icon = view.findViewById(R.id.tab_content_image);
                    TextView text = view.findViewById(R.id.tab_content_text);
                    if(i == tab.getPosition()){
                        icon.setImageResource(DataGenerator.mTabResPressed[i]);
                        text.setTextColor(getResources().getColor(android.R.color.black));
                    }else {
                        icon.setImageResource(DataGenerator.mTabRes[i]);
                        text.setTextColor(getResources().getColor(android.R.color.darker_gray));
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        for(int i = 0; i < mfragments.length; i++){
            bottomTabLayout.addTab(bottomTabLayout.newTab().setCustomView(DataGenerator.getTabView(this,i)));
        }
      }
    private void onTabItemSelected(int position){
        Fragment fragment = null;
        for(int i = 0; i < mfragments.length; i++){
            if(position == i){
                fragment = mfragments[i];
                break;
            }
        }
        if(fragment != null){
            getSupportFragmentManager().beginTransaction().replace(R.id.home_container,fragment).commit();
        }
    }
}
