package com.engappsados.engappsadosapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Diego Castaneda on 03/09/2017.
 */

public class AdapterAppItem extends BaseAdapter {

    protected Activity activity;
    protected List<AppModelo> items;
    private Context mContext;
    private Bitmap bitmap;

    public AdapterAppItem(List<AppModelo> items, Context mContext) {
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

    public void addAll(ArrayList<AppModelo> category) {
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

    public Bitmap getBitmapFromURL(String src){
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;

        }catch (Exception e){
            e.printStackTrace();
            return null;


        }
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) { //(int position, View convertView, ViewGroup parent)

        View v = View.inflate(mContext, R.layout.item_apps,null);
        TextView titulo = (TextView) v.findViewById(R.id.textView11);
        TextView descripcion = (TextView) v.findViewById(R.id.textView12);
        Button boton = (Button) v.findViewById(R.id.button6);
        ImageView imagen = (ImageView) v.findViewById(R.id.imageView2);
        //poner textos correspondientes
        titulo.setText(items.get(position).getTitle());
        descripcion.setText(items.get(position).getDescription());

        bitmap = getBitmapFromURL(items.get(position).getImagen());
        imagen.setImageBitmap(bitmap);


        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(items.get(position).getLink()));

                activity.startActivity(intent);
            }
        });





        return v;
    }
}
