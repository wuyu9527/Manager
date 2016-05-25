package com.demo.manager.Model.Interface;

import com.demo.manager.View.Interface.Promotion;

/**
 * Created by Android on 2016/5/25.
 */
public interface PromotionMI {

    void setImages(int [] images);
    void setIconName(String [] name);
    com.demo.manager.Bean.Promotion getPromotion();
}
