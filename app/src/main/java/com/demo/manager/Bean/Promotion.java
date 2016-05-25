package com.demo.manager.Bean;

import com.demo.manager.R;

/**
 * Created by Android on 2016/5/25.
 */
public class Promotion {
    private int [] images={R.drawable.quanicon3x,R.drawable.xiaoxi3x,R.drawable.daishouhuo3x};
    private String [] name ={"优惠券","活动公告","广告推送"};

    public int[] getImages() {
        return images;
    }

    public void setImages(int[] images) {
        this.images = images;
    }

    public String[] getIconName() {
        return name;
    }

    public void setIconName(String[] name) {
        this.name = name;
    }
}
