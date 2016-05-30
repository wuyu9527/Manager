package com.demo.manager.View.MyActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.demo.manager.R;
import com.demo.manager.Util.DateTimePickDialogUtil;
import com.demo.manager.View.Interface.NoticeInfo;

/**
 * Created by Android on 2016/5/30. 活动详情编辑
 */
public class NoticeInfoEditActivity extends Activity implements NoticeInfo{
    EditText etName,etText,etTags,etTime;
    String name,text,tags,time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mc_brand_notice_edit);
        name=getIntent().getStringExtra("name");
        text=getIntent().getStringExtra("text");
        tags=getIntent().getStringExtra("tags");
        time=getIntent().getStringExtra("time");
        initUI();
    }

    private void initUI() {
        etName= (EditText) findViewById(R.id.etNoticeName);
        etTags= (EditText) findViewById(R.id.etTagsNoticeInfo);
        etText= (EditText) findViewById(R.id.etTextNoticeInfo);
        setAll(name,text,tags,time);
        myTime();
    }


    private EditText startDateTime;
    private EditText endDateTime;
    private String initStartDateTime = ""; // 初始化开始时间
    private String initEndDateTime = ""; // 初始化结束时间
    private void myTime() {

        // 两个输入框
        startDateTime = (EditText) findViewById(R.id.niEditInputDate1);
        endDateTime = (EditText) findViewById(R.id.niEditInputDate2);

        startDateTime.setText(initStartDateTime);
        endDateTime.setText(initEndDateTime);

        startDateTime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(
                        NoticeInfoEditActivity.this, initEndDateTime);
                dateTimePicKDialog.dateTimePicKDialog(startDateTime);

            }
        });

        endDateTime.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(
                        NoticeInfoEditActivity.this, initEndDateTime);
                dateTimePicKDialog.dateTimePicKDialog(endDateTime);
            }
        });
    }

    @Override
    public String getTime() {
        return null;
    }

    @Override
    public String getNoticeInfo() {
        return null;
    }

    @Override
    public String getNoticeName() {
        return null;
    }

    @Override
    public String getNotcieText() {
        return null;
    }

    @Override
    public String getNotcieTags() {
        return null;
    }

    @Override
    public void setAll(String name, String text, String tags, String time) {
        String [] myTime=time.split("至");
        initStartDateTime= myTime[0].split(":")[1];
        initEndDateTime =myTime[myTime.length-1];
        String [] myTags = tags.split(":");
        for (String myTag : myTags) {
            Log.i("whx",myTag);
        }
        etTags.setText(myTags[myTags.length-1].replace(" ",";")+";");
        etName.setText(name);
        etText.setText(text);

    }
}
