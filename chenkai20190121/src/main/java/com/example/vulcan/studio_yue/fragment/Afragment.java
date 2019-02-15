package com.example.vulcan.studio_yue.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vulcan.studio_yue.R;
import com.example.vulcan.studio_yue.adapter.TabAdapter;
import com.example.vulcan.studio_yue.tablayout.Aafragment;
import com.example.vulcan.studio_yue.tablayout.Bafragment;
import com.example.vulcan.studio_yue.tablayout.Cafragment;

import java.util.ArrayList;
import java.util.List;

/**
 * TabLayout
 */
public class Afragment extends Fragment {

    private TabLayout tab;
    private ViewPager viewpager;
    private List<Fragment> fragmentList = new ArrayList<Fragment>();
    private List<String> stringList = new ArrayList<String>();
    private TabAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1, container, false);
        tab = view.findViewById(R.id.tab);
        viewpager = view.findViewById(R.id.viewpager);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        iniData();
        adapter = new TabAdapter(getChildFragmentManager(), fragmentList, stringList);
        viewpager.setAdapter(adapter);
        tab.setTabMode(TabLayout.MODE_SCROLLABLE);
        tab.setupWithViewPager(viewpager);

    }

    private void iniData() {

        stringList.add("娱乐");
        fragmentList.add(new Aafragment());
        stringList.add("新闻");
        fragmentList.add(new Bafragment());
        stringList.add("视频");
        fragmentList.add(new Cafragment());

    }
}
