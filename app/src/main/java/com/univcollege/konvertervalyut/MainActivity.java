package com.univcollege.konvertervalyut;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ViewPagerAdapter mViewPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTabLayout= (TabLayout) findViewById(R.id.tabLayout);
        mViewPager= (ViewPager) findViewById(R.id.viewPager);
        mViewPagerAdapter= new ViewPagerAdapter(getSupportFragmentManager());

        mViewPagerAdapter.addFragments(new Home(),"конвертер");
        mViewPagerAdapter.addFragments(new List(),"Список валют");
        mViewPagerAdapter.addFragments(new Setting(),"настройка");
        mViewPager.setAdapter(mViewPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

        saveShared("begin", 2);
    }
    public void saveShared(String s, double a){
        SharedPreferences mSharedPreferences= getSharedPreferences("currency", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= mSharedPreferences.edit();
        editor.putFloat(s, (float) a);
        editor.commit();
    }

}
