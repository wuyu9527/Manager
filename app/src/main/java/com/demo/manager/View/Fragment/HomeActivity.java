package com.demo.manager.View.Fragment;



import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.demo.manager.Bean.ControlCenter;
import com.demo.manager.R;
import com.demo.manager.View.MyActivity.ExitWindow;
import com.demo.mylibrary.View.NavigationTabBar;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Android on 2016/5/17. 主页中心
 */
public class HomeActivity extends FragmentActivity {

    public static Activity instance;//其他Activity获取上下文

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager = getSupportFragmentManager();
        setContentView(R.layout.activity_horizontal_top_ntb);
        instance=this;
        initUI();
        HomeIn();//显示首页
        navigationTabBar.setModelIndex(0);
    }
    NavigationTabBar navigationTabBar = null;
    private void initUI() {


        final String[] colors = getResources().getStringArray(R.array.default_preview);

        navigationTabBar = (NavigationTabBar) findViewById(R.id.ntb_horizontal);
        final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_first),
                        Color.parseColor(colors[0]))
                        .title("营销中心")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_second),
                        Color.parseColor(colors[1]))
                        .title("工作台")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_third),
                        Color.parseColor(colors[2]))
                        .title("生意圈")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_fourth),
                        Color.parseColor(colors[3]))
                        .title("设置")
                        .build()
        );
//        models.add(
//                new NavigationTabBar.Model.Builder(
//                        getResources().getDrawable(R.drawable.ic_fifth),
//                        Color.parseColor(colors[4]))
//                        .title("Medal")
//                        .build()
//        );
        navigationTabBar.setModels(models);


        navigationTabBar.post(new Runnable() {
            @Override
            public void run() {
                final View bgNavigationTabBar = findViewById(R.id.bg_ntb_horizontal);
                bgNavigationTabBar.getLayoutParams().height = (int) navigationTabBar.getBarHeight();
                bgNavigationTabBar.requestLayout();
            }
        });
        navigationTabBar.setOnTabBarSelectedIndexListener(new NavigationTabBar.OnTabBarSelectedIndexListener() {
            @Override
            public void onStartTabSelected(final NavigationTabBar.Model model, final int index) {
                Log.i("whx",index+"");
                if (index==0){
                    HomeIn();
                }else if (index==1){
                    wIn();
                }else if (index==2){
                    bcIn();
                }else {
                    suIn();
                }
            }

            @Override
            public void onEndTabSelected(final NavigationTabBar.Model model, final int index) {
                model.hideBadge();
            }
        });

        findViewById(R.id.mask).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Log.i("whx",v.getId()+"");
                for (int i = 0; i < navigationTabBar.getModels().size(); i++) {
                    final NavigationTabBar.Model model = navigationTabBar.getModels().get(i);
                    navigationTabBar.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            final String title = String.valueOf(new Random().nextInt(15));
                            if (!model.isBadgeShowed()) {
                                model.setBadgeTitle(title);
                                model.showBadge();
                            } else {
                                model.updateBadgeTitle(title);
                            }
                        }
                    }, i * 100);
                }
            }
        });
    }

    /** 对Fragment进行管理  */
    private FragmentManager fragmentManager;


    /**  营销中心，生意圈，工作台，设置 */
    private MarketingCenterFragment mca;
    private BusinessCircleFragment bc;
    private WorkbenchFragment w;
    private SetUpFragment su;


    /** 设置开启的tab首页页面 */
    public  void HomeIn() {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragments(transaction);
        if (mca == null) {
            mca = new MarketingCenterFragment();
            transaction.add(R.id.wrapper_ntb_horizontal1, mca);
        } else {
            transaction.show(mca);
        }
        transaction.commitAllowingStateLoss();
    }
    /** 设置开启的tab首页页面 */
    public  void bcIn() {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragments(transaction);
        if (bc == null) {
            bc = new BusinessCircleFragment();
            transaction.add(R.id.wrapper_ntb_horizontal1, bc);
        } else {
            transaction.show(bc);
        }
        transaction.commitAllowingStateLoss();
    }
    /** 设置开启的tab首页页面 */
    public  void wIn() {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragments(transaction);
        if (w == null) {
            w = new WorkbenchFragment();
            transaction.add(R.id.wrapper_ntb_horizontal1, w);
        } else {
            transaction.show(w);
        }
        transaction.commitAllowingStateLoss();
    }
    /** 设置开启的tab首页页面 */
    public  void suIn() {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragments(transaction);
        if (su == null) {
            su = new SetUpFragment();
            transaction.add(R.id.wrapper_ntb_horizontal1, su);
        } else {
            transaction.show(su);
        }
        transaction.commitAllowingStateLoss();
    }


    /**
     * 隐藏所有的fragment
     * @param transaction 用于对fragment进行操作的事务
     */
    private void hideFragments(FragmentTransaction transaction) {
        if (mca != null) {
            transaction.hide(mca);
        }
        if (bc != null) {
            transaction.hide(bc);
        }
        if (w != null) {
            transaction.hide(w);
        }
        if (su != null) {
            transaction.hide(su);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    // 定义一个变量，来标识是否退出
    private static boolean isExit = false;
    Handler exitHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };
    private void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            exitHandler.sendEmptyMessageDelayed(0, 2000);
        } else {
            Intent intent=new Intent(this, ExitWindow.class);
            startActivity(intent);
        }
    }

}
