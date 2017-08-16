package com.engappsados.engappsadosapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.Button;

/**
 * Created by sebas on 8/15/2017.
 */

public class Apps_Tab extends Fragment{
    private Button boton1;
    private Button boton2;
    private Button boton3;
    private Button boton4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_tareas, container, false);

        boton1 = (Button) rootView.findViewById(R.id.button2);
        boton2 = (Button) rootView.findViewById(R.id.button3);
        boton3 = (Button) rootView.findViewById(R.id.button4);
        boton4 = (Button) rootView.findViewById(R.id.button5);

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


        return rootView;

    }

}

