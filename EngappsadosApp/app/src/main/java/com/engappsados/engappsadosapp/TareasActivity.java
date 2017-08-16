package com.engappsados.engappsadosapp;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.TabWidget;
import android.widget.TextView;

public class TareasActivity extends AppCompatActivity {
    private TextView texto;
    private ScrollView elListado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tareas);

        texto = (TextView)findViewById(R.id.textView3);
        elListado = (ScrollView)findViewById(R.id.scrollView2);





    }
}
