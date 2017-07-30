package com.engappsados.engappsadosapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuPrincipalActivity extends AppCompatActivity {

    public Button buttonReward;
    public Button buttonNoticias;
    public void init(){
        buttonReward = (Button)findViewById(R.id.buttonReward);
        buttonNoticias=(Button)findViewById(R.id.buttonNoticias);
        buttonReward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent recompensa = new Intent(MenuPrincipalActivity.this, Rewards.class);
                startActivity(recompensa);
            }
        });
        buttonNoticias.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent noticias = new Intent(MenuPrincipalActivity.this, NoticiasActivity.class);
                startActivity(noticias);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        init();

    }
}
