package com.engappsados.engappsadosapp;

/**
 * Created by sebas on 8/15/2017.
 */

import android.app.ActivityGroup;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.support.v7.app.AppCompatActivity;


public class Noticias_Tab extends Fragment{


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_noticias, container, false);
/*
        //noticia1
        TextView textView1 =(TextView)rootView.findViewById(R.id.leerMas);
        textView1.setClickable(true);
        textView1.setMovementMethod(LinkMovementMethod.getInstance());
        String text1 = "<a href='https://www.guatemala.com/noticias/tecnologia/ingenieros-guatemaltecos-que-han-destacado-a-nivel-internacional.html'> Leer mas </a>";
        textView1.setText(Html.fromHtml(text1));

        //noticia2
        TextView textView2 =(TextView)rootView.findViewById(R.id.leerMas2);
        textView2.setClickable(true);
        textView2.setMovementMethod(LinkMovementMethod.getInstance());
        String text2 = "<a href='https://www.guatemala.com/noticias/tecnologia/guatemaltecos-participaron-como-conferencistas-en-droidcon-2017.html'> Leer mas </a>";
        textView2.setText(Html.fromHtml(text2));

        //noticia3
        TextView textView3 =(TextView)rootView.findViewById(R.id.leerMas3);
        textView3.setClickable(true);
        textView3.setMovementMethod(LinkMovementMethod.getInstance());
        String text3 = "<a href='https://www.guatemala.com/noticias/tecnologia/guatemaltecos-triunfan-en-competencia-de-seguridad-informatica-en-latinoamerica-2016.html'> Leer mas </a>";
        textView3.setText(Html.fromHtml(text3));

*/
        return rootView;

    }


}
