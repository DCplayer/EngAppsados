package com.engappsados.engappsadosapp;

/**
 * Created by Jonnathan on 05/09/2017.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;


public class AdapterPollItem extends BaseAdapter {


    protected Activity activity;
    protected List<EncuestasModel> items;
    private Context mContext;

    public AdapterPollItem(List<EncuestasModel> items, Context mContext) {
        this.items = items;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    public void clear() {
        items.clear();
    }

    public void addAll(ArrayList<EncuestasModel> category) {
        for (int i = 0; i < category.size(); i++) {
            items.add(category.get(i));
        }
    }

    @Override
    public Object getItem(int arg0) {
        return items.get(arg0);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {

        View v = View.inflate(mContext, R.layout.item_poll,null);    // inflar el contexto correspondiente
        TextView titulo = (TextView) v.findViewById(R.id.poll_Title);     // buscar el titulo
        TextView descripcion = (TextView) v.findViewById(R.id.poll_Desc);  //buscar la descripcion de noticia
        TextView puntos = (TextView) v.findViewById(R.id.poll_points);
        Button boton = (Button) v.findViewById(R.id.poll_btnDoPoll);


        //poner textos correspondientes
        titulo.setText(items.get(position).getTitle());                 //cambiar el titulo
        descripcion.setText(items.get(position).getDescription());      //cambiar la descripcion
        puntos.setText(items.get(position).getPuntos()+" pts.");
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                Intent intent = new Intent(mContext, GoogleForm_Activity.class);
                Bundle b = new Bundle();
                b.putString("link",items.get(position).getLink()); //Your id
                b.putString("puntos",items.get(position).getPuntos()); //Your id
                intent.putExtras(b); //Put your id to your next Intent
                mContext.startActivity(intent);

            }
        });

        return v;
    }

}
