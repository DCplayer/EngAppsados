package com.engappsados.engappsadosapp;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by sebas on 8/15/2017.
 */

public class Encuestas_tab extends Fragment{

    private List<EncuestasModel> encuestas;
    private ListView lvEnuesta; //listview especial para encuesta
    private AdapterPollItem adapter; //adapter personalizado para noticias
    //private SwipeRefreshLayout mSwipeRefreshLayout;
    public DatabaseReference mDatabaseRef = FirebaseDatabase.getInstance().getReference();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.encuestas_tab, container, false);

        lvEnuesta = (ListView)rootView.findViewById(R.id.listview_polls);
        encuestas = new ArrayList<>();
        mDatabaseRef.child("encuestas").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                fillEncuesta(dataSnapshot); //obtener noticias de la base de datos, y rellenar la lista de noticias
                adapter = new AdapterPollItem(encuestas, rootView.getContext()); //Llamar al adapter personalizado
                lvEnuesta.setAdapter(adapter); //llenar la listview con el adapter

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

            //puede ser que tenga que hacer un onDataChange por todos
            public void fillEncuesta(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    String titulo = child.child("titulo").getValue().toString();
                    String descripcion = child.child("descripcion").getValue().toString();
                    String link = child.child("link").getValue().toString();
                    String puntos = child.child("puntos").getValue().toString();
                    EncuestasModel unaEncuesta = new EncuestasModel(titulo, link, descripcion, puntos);
                    //unaEncuesta.setTitle(titulo);
                    if (!encuestas.contains(unaEncuesta)) {
                        encuestas.add(unaEncuesta);
                    }
                }
            }
        });

        return rootView;
    }


}