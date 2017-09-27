/*
Adapter customizado para noticias tab
 */

package com.engappsados.engappsadosapp;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
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
        link.setClickable(true);
        link.setMovementMethod(LinkMovementMethod.getInstance());
        ImageView foto = (ImageView) v.findViewById(R.id.imagen);
        /*
        String imgUrl = dataSnapshot.getValue().toString();
                Picasso.with(userProfile.this).load(imgUrl).transform(new CircleTransform()).into(user_Picture);
         */

        //poner textos correspondientes
        titulo.setText(items.get(position).getTitle());                 //cambiar el titulo
        descripcion.setText(items.get(position).getDescription());      //cambiar la descripcion
        String url = items.get(position).getLeermas();
        String imgUrl = items.get(position).getImagen();
        Picasso.with(mContext).load(imgUrl).into(foto);
        /*
        String text = "<a href='http://www.google.com'> Google </a>";
         */
        String text = "<a href='"+url+ "'> Leer mas </a>";
        link.setText(Html.fromHtml(text));

        /*
        String link = noticia.getLeermas();
            leerMas.setText(Html.fromHtml(link));
         */
        return v;
    }
}