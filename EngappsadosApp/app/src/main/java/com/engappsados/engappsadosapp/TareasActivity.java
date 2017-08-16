package com.engappsados.engappsadosapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TabWidget;
import android.widget.TextView;

public class TareasActivity extends AppCompatActivity {
    private TextView texto;
    private Button boton1;
    private Button boton2;
    private Button boton3;
    private Button boton4;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tareas);

        texto = (TextView)findViewById(R.id.textView3);

        boton1 = (Button)findViewById(R.id.button2);
        boton2 = (Button)findViewById(R.id.button3);
        boton3 = (Button)findViewById(R.id.button4);
        boton4 = (Button)findViewById(R.id.button5);

        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.renap.pub&hl=es"));

                startActivity(intent);
            }
        });

        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=gt.com.cs.mistermenu&hl=es"));

                startActivity(intent);
            }
        });

        boton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=adn.guatemala.com&hl=es"));

                startActivity(intent);
            }
        });

        boton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.muniguate.consulta&hl=es"));

                startActivity(intent);
            }
        });





    }



}
