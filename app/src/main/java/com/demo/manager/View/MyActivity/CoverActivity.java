package com.demo.manager.View.MyActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.demo.manager.R;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;

/**
 * Created by Android on 2016/5/23.
 */
public class CoverActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cover);
        new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                        Intent it = new Intent(CoverActivity.this, LoginActivity.class);
                        startActivity(it);
                        CoverActivity.this.finish();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, 1000);
    }
}
