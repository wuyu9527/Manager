package com.demo.manager.Model;

import com.demo.manager.Model.Interface.PromotionMI;
import com.demo.manager.Bean.Promotion;

/**
 * Created by Android on 2016/5/25.
 */
public class PromotionM implements PromotionMI {

    private int [] images;
    private String [] name;

    @Override
    public void setImages(int[] images) {
        this.images=images;
    }

    @Override
    public void setIconName(String[] name) {
        this.name=name;
    }

    @Override
    public Promotion getPromotion() {
        Promotion promotion=new Promotion();
        return promotion;
    }
}
