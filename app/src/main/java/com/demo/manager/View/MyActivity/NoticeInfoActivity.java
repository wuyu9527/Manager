package com.demo.manager.View.MyActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.demo.manager.R;
import com.demo.manager.View.Interface.NoticeInfo;

/**
 * Created by Android on 2016/5/26. 活动详情
 */
public class NoticeInfoActivity extends Activity implements NoticeInfo{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mc_brand_notice_info);
        initUI();
    }
    TextView tvNoticeInfoEdit,tvTimeNoticeInfo,tvNameNoticeInfo,tvTagsNoticeInfo,tvTextNoticeInfo;

    private void initUI() {
        tvTimeNoticeInfo= (TextView) findViewById(R.id.tvTimeNoticeInfo);
        tvNameNoticeInfo= (TextView) findViewById(R.id.tvNameNoticeInfo);
        tvTagsNoticeInfo= (TextView) findViewById(R.id.tvTagsNoticeInfo);
        tvTextNoticeInfo= (TextView) findViewById(R.id.tvTextNoticeInfo);
        tvNoticeInfoEdit= (TextView) findViewById(R.id.tvNoticeInfoEdit);
        tvNoticeInfoEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(NoticeInfoActivity.this,NoticeInfoEditActivity.class);
                intent.putExtra("name",getNoticeName());
                intent.putExtra("text",getNotcieText());
                intent.putExtra("tags",getNotcieTags());
                intent.putExtra("time",getTime());
                startActivity(intent);
            }
        });
    }

    @Override
    public String getTime() {
        return tvTimeNoticeInfo.getText().toString();
    }

    @Override
    public String getNoticeInfo() {
        return null;
    }

    @Override
    public String getNoticeName() {
        return tvNameNoticeInfo.getText().toString();
    }

    @Override
    public String getNotcieText() {
        return tvTextNoticeInfo.getText().toString();
    }

    @Override
    public String getNotcieTags() {
        return tvTagsNoticeInfo.getText().toString();
    }

    @Override
    public void setAll(String name, String text, String tags, String time) {
        tvTimeNoticeInfo.setText(time);
        tvNameNoticeInfo.setText(name);
        tvTagsNoticeInfo.setText(tags);
        tvTextNoticeInfo.setText(text);
    }
}
