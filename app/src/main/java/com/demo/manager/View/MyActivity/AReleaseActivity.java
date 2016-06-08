package com.demo.manager.View.MyActivity;

import android.app.Activity;
import android.os.Bundle;
import android.os.PatternMatcher;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.SimpleAdapter;

import com.demo.manager.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Android on 2016/5/30.
 */
public class AReleaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mc_brand_a_release);
        initUI();
    }

    String[] gv1name = {"大爆炸组", "大包子组", "打包子组", "大宝子组"};
    String[] gv2name = {"辽宁", "吉林", "黑龙江", "河北", "山西", "陕西", "甘肃", "青海", "山东", "安徽", "江苏", "浙江", "河南", "湖北", "湖南", "江西", "台湾", "福建", "云南", "海南", "四川", "贵州", "广东", "内蒙古", "新疆", "广西", "西藏", "宁夏", "北京", "上海", "天津", "重庆", "香港", "澳门"};
    EditText etLettingDown;
    LinearLayout llLettingDown;
    GridView gv1, gv2;

    private void initUI() {
        etLettingDown = (EditText) findViewById(R.id.etLettingDown);
        llLettingDown = (LinearLayout) findViewById(R.id.llLettingDown);
        gv1 = (GridView) findViewById(R.id.gvARelease1);
        gv2 = (GridView) findViewById(R.id.gvARelease2);
        etLettingDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("whx", v.getId() + "");
                PopupMenu popupMenu = new PopupMenu(AReleaseActivity.this, v);
                MenuInflater inflater = popupMenu.getMenuInflater();
                inflater.inflate(R.menu.popup_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.provinces:
                                etLettingDown.setText("按省市");
                                gv1.setVisibility(View.GONE);
                                gv2.setVisibility(View.VISIBLE);
                                return true;
                            case R.id.grouping:
                                etLettingDown.setText("按分组");
                                gv1.setVisibility(View.VISIBLE);
                                gv2.setVisibility(View.GONE);
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                popupMenu.show();
            }
        });
        //将图标图片和图标名称存入ArrayList中
        // Author:博客园-依旧淡然
        ArrayList<HashMap<String, Object>> item1 = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < gv1name.length; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("itemName", gv1name[i]);
            item1.add(map);
        }
        ArrayList<HashMap<String, Object>> item2 = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < gv2name.length; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("itemName", gv2name[i]);
            item2.add(map);
        }
        //SimpleAdapter对象，匹配ArrayList中的元素
        //Author : 博客园-依旧淡然
        SimpleAdapter simpleAdapter1 = new SimpleAdapter(this, item1, R.layout.grid_advertisement_grouping, new String[]{"itemName"}, new int[]{R.id.btGroupingName}) {};
        SimpleAdapter simpleAdapter2 = new SimpleAdapter(this, item2, R.layout.grid_advertisement_grouping, new String[]{"itemName"}, new int[]{R.id.btGroupingName}) {};
        gv1.setAdapter(simpleAdapter1);
        gv2.setAdapter(simpleAdapter2);
    }
}
