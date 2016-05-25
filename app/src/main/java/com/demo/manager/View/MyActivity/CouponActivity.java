package com.demo.manager.View.MyActivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

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
    private void initUI() {
        lv= (ListView) findViewById(R.id.lvCoupon);

    }
}
