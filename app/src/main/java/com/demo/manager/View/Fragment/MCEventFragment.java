package com.demo.manager.View.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.manager.R;

/**
 * Created by Android on 2016/5/24. 活动
 */
public class MCEventFragment extends Fragment {
    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.activity_mc_event,container,false);
        initUI(view);
        return view;
    }

    private void initUI(View view) {

    }
}
