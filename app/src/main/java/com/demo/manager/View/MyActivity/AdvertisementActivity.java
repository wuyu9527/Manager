package com.demo.manager.View.MyActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.demo.manager.R;

/**
 * Created by Android on 2016/5/30.
 */
public class AdvertisementActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mc_brand_advertisement);
        initUI();
    }
    TextView fabuguanggao;
    RelativeLayout rlAdvertisement;
    private void initUI() {
        fabuguanggao= (TextView) findViewById(R.id.fabuguanggao);
        rlAdvertisement= (RelativeLayout) findViewById(R.id.rlAdvertisement);
        rlAdvertisement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdvertisementActivity.this,AInfoActivity.class);
                startActivity(intent);
            }
        });
        fabuguanggao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdvertisementActivity.this,AReleaseActivity.class);
                startActivity(intent);
            }
        });
    }
}
