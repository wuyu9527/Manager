package com.demo.manager.View.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.graphics.ColorUtils;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Toast;

import com.demo.manager.R;
import com.demo.manager.View.Adapter.MarketingCenterVPAdqpter;
import com.demo.mylibrary.View.NavigationTabBar;

import java.util.ArrayList;

/**
 * Created by Android on 2016/5/23. 营销中心
 */
public class MarketingCenterFragment extends Fragment {
    View view;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_marketing_center, container, false);

        InitViewPager();
        initUI();

        return view;
    }


    ViewPager vp;
    ArrayList<Fragment> fragmentList;

    /**
     * 初始化ViewPager
     */
    public void InitViewPager() {
        vp = (ViewPager) view.findViewById(R.id.vpMarketingCenter);
        fragmentList = new ArrayList<Fragment>();
        Fragment brandFragment = new MCBrandFragment();
        Fragment eventFragment = new MCEventFragment();
        Fragment promotionFragment = new MCPromotionFragment();

        fragmentList.add(brandFragment);
        fragmentList.add(promotionFragment);
        fragmentList.add(eventFragment);

        //给ViewPager设置适配器
        vp.setAdapter(new MarketingCenterVPAdqpter(getFragmentManager(), fragmentList));
        vp.setCurrentItem(0);//设置当前显示标签页为第一页

        vp.setOnPageChangeListener(new MyOnPageChangeListener());//页面变化时的监听器
    }


    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {



        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {


        }

        @Override
        public void onPageScrollStateChanged(int arg0) {


        }

        @Override
        public void onPageSelected(int arg0) {
            ntbSample6.setModelIndex(arg0);
        }
    }


    @Override
    public void onStart() {
        super.onStart();
    }

    NavigationTabBar ntbSample6;

    private void initUI() {

        ntbSample6 = (NavigationTabBar) view.findViewById(R.id.ntb_sample_6);
        final ArrayList<NavigationTabBar.Model> models6 = new ArrayList<>();
        models6.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_fifth), randomColor()
                ).build()
        );
        models6.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_first), randomColor()
                ).build()
        );
        models6.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_fourth), randomColor()
                ).build()
        );
        ntbSample6.setModels(models6);
        ntbSample6.setOnTabBarSelectedIndexListener(new NavigationTabBar.OnTabBarSelectedIndexListener() {
            @Override
            public void onStartTabSelected(final NavigationTabBar.Model model, final int index) {
                vp.setCurrentItem(index);

            }

            @Override
            public void onEndTabSelected(final NavigationTabBar.Model model, final int index) {

            }
        });
        ntbSample6.setModelIndex(0);
    }

    private int randomColor() {
        float[] TEMP_HSL = new float[]{0, 0, 0};
        float[] hsl = TEMP_HSL;
        hsl[0] = (float) (Math.random() * 360);
        hsl[1] = (float) (40 + (Math.random() * 60));
        hsl[2] = (float) (40 + (Math.random() * 60));
        return ColorUtils.HSLToColor(hsl);
    }

}
