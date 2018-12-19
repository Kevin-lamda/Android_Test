package ssw.com.myapplication.home;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import ssw.com.myapplication.R;

@EActivity(R.layout.activity_home)
public class HomeActivity extends AppCompatActivity {
    @ViewById(R.id.bottom_tab_layout)
    TabLayout bottomTabLayout;
    Fragment[] mfragments;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mfragments = DataGenerator.getFragments("TabLayout Tab");
    }
    public void initView(){
        bottomTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                onTabItemSelected(tab.getPosition());
                for(int i = 0; i < bottomTabLayout.getTabCount(); i++){
                    if(i == tab.getPosition()){
                        bottomTabLayout.getTabAt(i).setIcon(getResources().getDrawable((DataGenerator.mTabResPressed[i])));
                    }else {
                        bottomTabLayout.getTabAt(i).setIcon(getResources().getDrawable(DataGenerator.mTabRes[i]));
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
        bottomTabLayout.addTab(bottomTabLayout.newTab().setIcon(getResources().getDrawable(DataGenerator.mTabRes[0])).setText(DataGenerator.mTabTitle[0]));
        bottomTabLayout.addTab(bottomTabLayout.newTab().setIcon(getResources().getDrawable(DataGenerator.mTabRes[1])).setText(DataGenerator.mTabTitle[1]));
        bottomTabLayout.addTab(bottomTabLayout.newTab().setIcon(getResources().getDrawable(DataGenerator.mTabRes[2])).setText(DataGenerator.mTabTitle[2]));
        bottomTabLayout.addTab(bottomTabLayout.newTab().setIcon(getResources().getDrawable(DataGenerator.mTabRes[3])).setText(DataGenerator.mTabTitle[3]));
    }
    private void onTabItemSelected(int position){
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = mfragments[0];
                break;
            case 1:
                fragment = mfragments[1];
                break;
            case 2:
                fragment = mfragments[2];
                break;
            case 3:
                fragment = mfragments[3];
                break;
        }
        if(fragment != null){
            getSupportFragmentManager().beginTransaction().replace(R.id.home_container,fragment).commit();
        }
    }
}
