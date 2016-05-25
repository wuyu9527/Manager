package com.demo.manager.P;

import com.demo.manager.Model.Interface.PromotionMI;
import com.demo.manager.Model.PromotionM;
import com.demo.manager.View.Interface.Promotion;

/**
 * Created by Android on 2016/5/25.
 */
public class PromotionP {

    private Promotion promotion;
    private PromotionM promotionM;

    public PromotionP(Promotion p) {
        this.promotion = p;
        this.promotionM= new PromotionM();
    }

    public void savePromotion(){

    }
    public void loadPromotion(){
        com.demo.manager.Bean.Promotion  p= promotionM.getPromotion();
        promotion.setPromotion(p.getImages(),p.getIconName());
    }
}
