/*
Adapter customizado para noticias tab
 */

package com.engappsados.engappsadosapp;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;


public class AdapterItem extends BaseAdapter {

    protected Activity activity;
    protected List<NoticiaModelo> items;
    private Context mContext;

    public AdapterItem(List<NoticiaModelo> items, Context mContext) {
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

    public void addAll(ArrayList<NoticiaModelo> category) {
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
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = View.inflate(mContext, R.layout.item_noticia,null);    // inflar el contexto correspondiente
        TextView titulo = (TextView) v.findViewById(R.id.category);     // buscar el titulo
        TextView descripcion = (TextView) v.findViewById(R.id.textoD);  //buscar la descripcion de noticia
        TextView link = (TextView) v.findViewById(R.id.link);           //buscar el texto para la noticia
        //poner textos correspondientes
        titulo.setText(items.get(position).getTitle());                 //cambiar el titulo
        descripcion.setText(items.get(position).getDescription());      //cambiar la descripcion
        link.setText(items.get(position).getLeermas());                 //cambiar el link
        return v;
    }
}