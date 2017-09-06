package com.engappsados.engappsadosapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by sebas on 9/5/2017.
 */

public class AdapterRewardItem extends BaseAdapter {

    private Context mContext;
    protected List<Recompensa> recompensaList;

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
        TextView titulo = (TextView)v.findViewById(R.id.reward_name);
        TextView precio = (TextView)v.findViewById(R.id.reward_price);
        ImageView imagen = (ImageView) v.findViewById(R.id.reward_Image);
        Button boton = (Button) v.findViewById(R.id.reward_btnCanjear);

        titulo.setText(recompensaList.get(position).getName());
        precio.setText(String.valueOf(recompensaList.get(position).getPrice()));

        //Colocar la imagen de la aplicacion
        String imgUrl = recompensaList.get(position).getImagen();
        /*Colocando dimensiones de la imagen del app y la forma que puede tener*/
        Picasso.with(mContext).load(imgUrl).transform(new RoundedTransformation(280,10)).into(imagen);

        return null;
    }
}
