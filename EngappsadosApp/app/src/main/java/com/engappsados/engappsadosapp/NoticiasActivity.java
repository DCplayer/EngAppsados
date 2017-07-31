package com.engappsados.engappsadosapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class NoticiasActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView puntos;
    private Button botonMenos,botonMas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticias);
        puntos = (TextView) findViewById(R.id.txt_cantPuntos);
        botonMas = (Button) findViewById(R.id.btnMas);
        botonMenos = (Button) findViewById(R.id.btnMenos);
        botonMas.setOnClickListener(this);
        botonMenos.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {

        if (R.id.btnMas == v.getId()) {
            //do somethin
        }
        else if(R.id.btnMenos == v.getId()){
            //do other thing
        }

    }
}
