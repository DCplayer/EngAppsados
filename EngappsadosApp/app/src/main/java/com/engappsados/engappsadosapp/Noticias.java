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
        TextView textView =(TextView)findViewById(R.id.leerMas);
        textView.setClickable(true);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        String text = "<a href='https://www.guatemala.com/noticias/tecnologia/ingenieros-guatemaltecos-que-han-destacado-a-nivel-internacional.html'> Leer mas </a>";
        textView.setText(Html.fromHtml(text));
    }

}
