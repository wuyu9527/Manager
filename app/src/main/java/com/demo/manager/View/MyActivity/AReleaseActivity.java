package com.demo.manager.View.MyActivity;

import android.app.Activity;
import android.os.Bundle;
import android.os.PatternMatcher;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupMenu;

import com.demo.manager.R;

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
    EditText etLettingDown;
    LinearLayout llLettingDown;
    private void initUI() {
        etLettingDown= (EditText) findViewById(R.id.etLettingDown);
        llLettingDown= (LinearLayout) findViewById(R.id.llLettingDown);
        etLettingDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("whx",v.getId()+"");
                PopupMenu popupMenu=new PopupMenu(AReleaseActivity.this,v);
                MenuInflater inflater=popupMenu.getMenuInflater();
                inflater.inflate(R.menu.popup_menu,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.provinces:
                                etLettingDown.setText("按省市");
                                return true;
                            case R.id.grouping:
                                etLettingDown.setText("按分组");
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                popupMenu.show();
            }
        });
    }
}
