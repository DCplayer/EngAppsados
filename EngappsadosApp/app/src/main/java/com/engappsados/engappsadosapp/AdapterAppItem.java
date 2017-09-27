package com.engappsados.engappsadosapp;
import android.app.AppOpsManager;
import android.app.usage.*;
import android.app.usage.UsageStats;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by Diego Castaneda on 03/09/2017.
 */

public class AdapterAppItem extends BaseAdapter {

    protected Activity activity;
    protected List<AppModelo> items;
    private Context mContext;
    private Bitmap bitmap;
    boolean isAppInstalled;
    String paquete;

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
        final String developer = items.get(position).getDesarrollador();
        TextView verDev = (TextView) v.findViewById(R.id.verDev);
        verDev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intento = new Intent(mContext, Developer.class);
                    intento.putExtra("DEV", developer);
                    mContext.startActivity(intento);
                } catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });

        //verifica si la app esta installada
        paquete = items.get(position).getPackageName();
        isAppInstalled = appInstalledOrNot(paquete);

        ImageView imagen = (ImageView) v.findViewById(R.id.imageView2);
        //poner textos correspondientes
        titulo.setText(items.get(position).getTitle());
        descripcion.setText(items.get(position).getDescription());


        //Colocar la imagen de la aplicacion
        String imgUrl = items.get(position).getImagen();
        /*Colocando dimensiones de la imagen del app y la forma que puede tener*/
        Picasso.with(mContext).load(imgUrl).transform(new RoundedTransformation(280,10)).into(imagen);


        //Diseñar la entrada de cada boton
        Button botoneta = (Button) v.findViewById(R.id.button6);
        //cambia el texto si el paquete esta instalado
        if(isAppInstalled) {
            botoneta.setText("Abrir");
        }

        botoneta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if (appInstalledOrNot(items.get(position).getPackageName())){
                    Intent LaunchIntent = mContext.getPackageManager()
                            .getLaunchIntentForPackage(items.get(position).getPackageName());
                    mContext.startActivity(LaunchIntent);
                }
                else {
                    Uri uri = Uri.parse(items.get(position).getLink());
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    mContext.startActivity(intent);
                }
            }
        });
        return v;
    }
    private boolean appInstalledOrNot(String uri) {
        final PackageManager pm = mContext.getPackageManager();
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
        }
        return false;
    }


}