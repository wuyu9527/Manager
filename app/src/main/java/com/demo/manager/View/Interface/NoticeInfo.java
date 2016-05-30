package com.demo.manager.View.Interface;

/**
 * Created by Android on 2016/5/30.
 */
public interface NoticeInfo {
    String getTime();
    String getNoticeInfo();
    String getNoticeName();
    String getNotcieText();
    String getNotcieTags();
    void setAll(String name,String text,String tags,String time);
}
