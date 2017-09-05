package com.engappsados.engappsadosapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by sebas on 9/5/2017.
 */

public class AdapterRewardItem extends BaseAdapter {

    private Context mContext;
    private List<Recompensa> recompensaList;

    public AdapterRewardItem(Context mContext, List<Recompensa> recompensaList) {
        this.mContext = mContext;
        this.recompensaList = recompensaList;
    }

    @Override
    public int getCount() {
        return recompensaList.size();
    }

    @Override
    public Object getItem(int position) {
        return recompensaList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(mContext, R.layout.item_recompensa, null);
        TextView rewardName = (TextView)v.findViewById(R.id.reward_name);
        TextView rewardPrice = (TextView)v.findViewById(R.id.reward_price);
        rewardName.setText(recompensaList.get(position).getName());
        rewardPrice.setText(String.valueOf(recompensaList.get(position).getPrice()));
        return null;
    }
}
