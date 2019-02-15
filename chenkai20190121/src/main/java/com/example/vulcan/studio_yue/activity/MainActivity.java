package com.example.vulcan.studio_yue.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.vulcan.studio_yue.R;
import com.example.vulcan.studio_yue.activity.BaseActivity;
import com.example.vulcan.studio_yue.fragment.Afragment;
import com.example.vulcan.studio_yue.fragment.Bfragment;
import com.example.vulcan.studio_yue.fragment.Cfragment;

/**
 * 主Activity
 */

public class MainActivity extends BaseActivity {




    //点击framelayout切换页面
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    transaction.show(afragment).hide(bfragment).hide(cfragment);
                    break;
                case R.id.navigation_dashboard:
                    transaction.show(bfragment).hide(afragment).hide(cfragment);
                    break;
                case R.id.navigation_notifications:
                    transaction.show(cfragment).hide(bfragment).hide(afragment);
                    break;
            }
            transaction.commit();
            return true;
        }
    };
    private Afragment afragment;
    private Bfragment bfragment;
    private Cfragment cfragment;


    @Override
    public void inid() {

    }

    @Override
    public void iniv() {

        //获取fragment
        afragment = new Afragment();
        bfragment = new Bfragment();
        cfragment = new Cfragment();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.framelayout,afragment);
        transaction.add(R.id.framelayout,bfragment);
        transaction.add(R.id.framelayout,cfragment);
        transaction.show(afragment).hide(bfragment).hide(cfragment);
        transaction.commit();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);




    }

    @Override
    public int getview() {
        return R.layout.activity_main;
    }

}
