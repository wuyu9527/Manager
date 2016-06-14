package com.demo.manager.View.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.demo.manager.R;
import com.demo.mylibrary.Refresh.CanRefreshLayout;


/**
 * Created by Android on 2016/5/24. 品牌中心
 */
public class MCBrandFragment extends Fragment implements CanRefreshLayout.OnRefreshListener, CanRefreshLayout.OnLoadMoreListener {


    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_mc_brand, container, false);
        initUI(view);
        return view;
    }

    ListView lv;

    private void initUI(View view) {
        lv = (ListView) view.findViewById(R.id.lv);
        refresh = (CanRefreshLayout) view.findViewById(R.id.refresh);
        refresh.setOnRefreshListener(this);
        refresh.setOnLoadMoreListener(this);
    }

    CanRefreshLayout refresh;

    @Override
    public void onLoadMore() {
        lv.postDelayed(new Runnable() {
            @Override
            public void run() {
                refresh.loadMoreComplete();
            }
        }, 2000);
    }

    @Override
    public void onRefresh() {
        lv.postDelayed(new Runnable() {
            @Override
            public void run() {
                refresh.refreshComplete();
            }
        }, 2000);
    }

}
