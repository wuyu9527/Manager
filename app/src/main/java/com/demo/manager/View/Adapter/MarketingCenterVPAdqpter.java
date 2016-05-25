package com.demo.manager.View.Adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import java.util.ArrayList;

/**
 * Created by Android on 2016/5/24.
 */
public class MarketingCenterVPAdqpter extends FragmentPagerAdapter {
    ArrayList<Fragment> list;
    public MarketingCenterVPAdqpter(FragmentManager fm, ArrayList<Fragment> list) {
        super(fm);
        this.list = list;

    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Fragment getItem(int arg0) {
        return list.get(arg0);
    }
}
