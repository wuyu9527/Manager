package com.demo.manager.View.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.demo.manager.R;



/**
 * Created by Android on 2016/5/24. 品牌中心
 */
public class MCBrandFragment extends Fragment {


    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.activity_mc_brand,container,false);
        initUI(view);
        return view;
    }

    ListView lv;
    private void initUI(View view) {
        lv= (ListView) view.findViewById(R.id.lvBrand);

    }

}
