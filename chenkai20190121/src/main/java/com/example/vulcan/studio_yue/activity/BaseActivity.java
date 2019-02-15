package com.example.vulcan.studio_yue.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * 抽象类
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getview());
        inid();
        iniv();
    }
    public abstract void inid();
    public abstract void iniv();
    public abstract int getview();
}
