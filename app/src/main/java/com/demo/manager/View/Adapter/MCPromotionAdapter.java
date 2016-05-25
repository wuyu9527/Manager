package com.demo.manager.View.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.manager.R;
import com.demo.manager.View.Interface.Promotion;

import java.util.ArrayList;

/**
 * Created by Android on 2016/5/24.
 */
public class MCPromotionAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;


    private int [] images={R.drawable.quanicon3x,R.drawable.xiaoxi3x,R.drawable.daishouhuo3x};
    private String [] name ={"优惠券","活动公告","广告推送"};


    public MCPromotionAdapter(Context context) {
        this.context=context;
        this.inflater = LayoutInflater.from(context);
    }



    public void setImages(int[] images) {
        this.images = images;
    }

    public void setName(String[] name) {
        this.name = name;
    }

    @Override
    public int getCount() {
        return images==null?0:images.length;
    }

    @Override
    public Object getItem(int position) {
        return images[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.item_promotion_list, null);
            holder = new ViewHolder();
            holder.ivArrowItemPromotion= (ImageView) convertView.findViewById(R.id.ivArrowItemPromotion);
            holder.ivIconItemPromotion= (ImageView) convertView.findViewById(R.id.ivIconItemPromotion);
            holder.tvItemPromotion = (TextView) convertView.findViewById(R.id.tvItemPromotion);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.ivIconItemPromotion.setImageResource(images[position]);
        holder.tvItemPromotion.setText(name[position]);
        holder.ivArrowItemPromotion.setImageResource(R.drawable.arrowblack3x);
        return convertView;
    }



    class ViewHolder {
        TextView tvItemPromotion;
        ImageView ivIconItemPromotion;
        ImageView ivArrowItemPromotion;
    }
}
