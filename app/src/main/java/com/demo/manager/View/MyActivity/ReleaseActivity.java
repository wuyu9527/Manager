package com.demo.manager.View.MyActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.demo.manager.R;
import com.demo.manager.Util.DateTimePickDialogUtil;

/**
 * Created by Android on 2016/5/26. 发布活动
 */
public class ReleaseActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mc_brand_n_release);
        initUI();
    }

    private void initUI() {
        myTime();
    }


    private EditText startDateTime;
    private EditText endDateTime;

    private String initStartDateTime = ""; // 初始化开始时间
    private String initEndDateTime = ""; // 初始化结束时间
    private void myTime() {

        // 两个输入框
        startDateTime = (EditText) findViewById(R.id.releaseInputDate1);
        endDateTime = (EditText) findViewById(R.id.releaseInputDate2);

        startDateTime.setText(initStartDateTime);
        endDateTime.setText(initEndDateTime);

        startDateTime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(
                        ReleaseActivity.this, initEndDateTime);
                dateTimePicKDialog.dateTimePicKDialog(startDateTime);

            }
        });

        endDateTime.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(
                        ReleaseActivity.this, initEndDateTime);
                dateTimePicKDialog.dateTimePicKDialog(endDateTime);
            }
        });
    }
}
