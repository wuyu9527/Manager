package com.demo.manager.View.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.SimpleAdapter;


import com.demo.manager.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Android on 2016/5/24. 工作台
 */
public class WorkbenchFragment extends Fragment {
    private GridView gv1,gv2;
    private List<Map<String, Object>> data_list1,data_list2;
    private SimpleAdapter sim_adapter1,sim_adapter2;
    // 图片封装为一个数组
    private int[] icon = { R.drawable.add_friend3x, R.drawable.duihuan3x,
            R.drawable.fenlei3x, R.drawable.homebtnfour3x, R.drawable.homebtnone3x,
            R.drawable.homebtnthree3x, R.drawable.mobileicon3x, R.drawable.homebtntwo2x,
             };
    private String[] iconName = { "新增", "开单", "入库", "订货", "调拨", "盘点", "收支",
            "客户"};

    private int[] icon1={R.drawable.smallmb3x,R.drawable.daishouhuo3x,R.drawable.phoneicon3x,
            R.drawable.quanicon3x,R.drawable.shipping_icon3x,R.drawable.xiaoxi3x,
            R.drawable.shouye3x,R.drawable.smallmb3x};
    private String[] iconName1={"明细", "批次", "货品", "统计", "厂商", "新厂", "客户",
            "账单"};
    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_workbench, container, false);
        initUI(view);
        return view;
    }

    private void initUI(View view) {
        gv1 = (GridView) view.findViewById(R.id.gvWork1);
        gv2 = (GridView) view.findViewById(R.id.gvWork2);

        //新建List
        data_list1 = new ArrayList<Map<String, Object>>();
        data_list2 = new ArrayList<Map<String, Object>>();
        //获取数据
        getData();
        //新建适配器
        String [] from ={"image","text"};
        int [] to = {R.id.ivGWorkbench,R.id.tvGWorkbench};
        sim_adapter1 = new SimpleAdapter(getActivity(), data_list1,  R.layout.grid_workbench, from, to);
        sim_adapter2 = new SimpleAdapter(getActivity(), data_list2,  R.layout.grid_workbench, from, to);
        //配置适配器
        gv1.setAdapter(sim_adapter1);
        gv2.setAdapter(sim_adapter2);
    }


    public List<Map<String, Object>> getData(){
        //cion和iconName的长度是相同的，这里任选其一都可以
        for(int i=0;i<icon.length;i++){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", icon[i]);
            map.put("text", iconName[i]);
            data_list1.add(map);
        }

        for(int i=0;i<icon1.length;i++){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", icon1[i]);
            map.put("text", iconName1[i]);
            data_list2.add(map);
        }

        return data_list1;
    }
}

