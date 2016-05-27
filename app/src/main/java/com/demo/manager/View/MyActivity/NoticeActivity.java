package com.demo.manager.View.MyActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.demo.manager.R;

/**
 * Created by Android on 2016/5/26.
 */
public class NoticeActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mc_brand_notice);
        initUI();
    }
    RelativeLayout rl;
    TextView fabuyouhuiquan;
    private void initUI() {
        rl= (RelativeLayout) findViewById(R.id.rlNotice);
        rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(NoticeActivity.this,NotiveInfoActivity.class);
                startActivity(intent);
            }
        });
        fabuyouhuiquan= (TextView) findViewById(R.id.fabuyouhuiquan);
        fabuyouhuiquan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(NoticeActivity.this,ReleaseActivity.class);
                startActivity(intent);
            }
        });

    }
}
