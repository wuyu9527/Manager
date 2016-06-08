package com.demo.manager.View.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.demo.manager.P.PromotionP;
import com.demo.manager.R;
import com.demo.manager.View.Adapter.MCPromotionAdapter;
import com.demo.manager.View.Interface.Promotion;
import com.demo.manager.View.MyActivity.AdvertisementActivity;
import com.demo.manager.View.MyActivity.CouponActivity;
import com.demo.manager.View.MyActivity.NoticeActivity;

/**
 * Created by Android on 2016/5/24. 促销
 */
public class MCPromotionFragment extends Fragment implements Promotion{
    View view;
    ListView lv;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.activity_mc_promotion,container,false);
        initUI(view);
        return view;
    }

    private void initUI(View view) {
        lv= (ListView) view.findViewById(R.id.lvPromotion);
        PromotionP p=new PromotionP(this);
        p.loadPromotion();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = null;
                switch (position){
                    case 0:
                        intent=new Intent(getContext(), CouponActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent=new Intent(getContext(), NoticeActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent=new Intent(getContext(), AdvertisementActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        });
    }


    @Override
    public void setPromotion(int[] images, String[] name) {
        MCPromotionAdapter mcPromotionAdapter=new MCPromotionAdapter(getActivity());
        mcPromotionAdapter.setImages(images);
        mcPromotionAdapter.setName(name);
        lv.setAdapter(mcPromotionAdapter);
    }
}
