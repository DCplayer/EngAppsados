package com.engappsados.engappsadosapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

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
    public View getView(int position, View convertView, ViewGroup parent) { //(int position, View convertView, ViewGroup parent)

        View v = View.inflate(mContext, R.layout.item_noticia,null);
        TextView titulo = (TextView) v.findViewById(R.id.category);
        TextView descripcion = (TextView) v.findViewById(R.id.textoD);
        TextView link = (TextView) v.findViewById(R.id.link);
        //poner textos correspondientes
        titulo.setText(items.get(position).getTitle());
        descripcion.setText(items.get(position).getDescription());
        link.setText(items.get(position).getLeermas());

        //guardar
        //v.setTag(items.get(position).getId());


        /*
        View v = convertView;

        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inf.inflate(R.layout.activity_noticias, null);
        }

        NoticiaModelo dir = items.get(position);

        TextView title = (TextView) v.findViewById(R.id.category);
        title.setText(dir.getTitle());

        TextView description = (TextView) v.findViewById(R.id.texto);
        description.setText(dir.getDescription());

        TextView leermas = (TextView) v.findViewById(R.id.leermas);
        leermas.setText(dir.getLeermas());
*/
        return v;
    }
}