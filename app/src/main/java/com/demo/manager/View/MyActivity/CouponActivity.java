package com.demo.manager.View.MyActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.demo.manager.R;

/**
 * Created by Android on 2016/5/25.
 */
public class CouponActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mc_brand_coupon);
        initUI();
    }

    ListView lv;
    TextView fabuyouhuiquan;
    private void initUI() {
        lv= (ListView) findViewById(R.id.lvCoupon);
        fabuyouhuiquan= (TextView) findViewById(R.id.fabuyouhuiquan);
        fabuyouhuiquan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CouponActivity.this,PublishCouponsActivity.class);
                startActivity(intent);
            }
        });
    }
}
