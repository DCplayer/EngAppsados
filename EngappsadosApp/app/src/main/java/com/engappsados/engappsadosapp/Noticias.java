package com.engappsados.engappsadosapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;


public class Noticias extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticias);
        //noticia 1
        TextView textView1 =(TextView)findViewById(R.id.leerMas);
        textView1.setClickable(true);
        textView1.setMovementMethod(LinkMovementMethod.getInstance());
        String text1 = "<a href='https://www.guatemala.com/noticias/tecnologia/ingenieros-guatemaltecos-que-han-destacado-a-nivel-internacional.html'> Leer mas </a>";
        textView1.setText(Html.fromHtml(text1));
        //noticia 2
        TextView textView2 = (TextView)findViewById(R.id.leerMas2);
        textView2.setClickable(true);
        textView2.setMovementMethod(LinkMovementMethod.getInstance());
        String text2 = "<a href='https://www.guatemala.com/noticias/tecnologia/guatemaltecos-participaron-como-conferencistas-en-droidcon-2017.html'> Leer mas </a>";
        textView2.setText(Html.fromHtml(text2));
    }

}
