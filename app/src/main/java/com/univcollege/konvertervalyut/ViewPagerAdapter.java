package com.univcollege.konvertervalyut;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Aman on 5/13/2017.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    ArrayList<Fragment> mFragments= new ArrayList<>();
    ArrayList<String> tabTitles= new ArrayList<>();

    public void addFragments (Fragment fragment, String titles)
    {
        this.mFragments.add(fragment);
        this.tabTitles.add(titles);
    }

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    public CharSequence getPageTitle(int position){
        return tabTitles.get(position);
    }
}
