package com.demo.manager.View.MyActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.demo.manager.R;
import com.demo.manager.View.Fragment.HomeActivity;

/**
 * Created by Android on 2016/5/27.
 */
public class ExitWindow extends Activity {

    private LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.up_window_white);
        layout = (LinearLayout) findViewById(R.id.exit_layout);
        layout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(getApplicationContext(), "提示：点击窗口外部关闭窗口！",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        finish();
        return true;
    }

    public void exitbutton1(View v) {
        this.finish();
    }

    public void exitbutton0(View v) {
        this.finish();
        HomeActivity.instance.finish();//关闭Main 这个Activity
    }
}
