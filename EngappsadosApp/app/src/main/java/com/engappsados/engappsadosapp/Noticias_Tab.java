package com.engappsados.engappsadosapp;

/**
 * Created by sebas on 8/15/2017.
 */


import android.support.v4.app.Fragment;
import android.os.Bundle;
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


public class Noticias_Tab extends Fragment implements View.OnClickListener{
    private List<NoticiaModelo> noticias;
    private ListView lvNoticia;
    private AdapterItem adapter;
    //private int cantNoticias;
    public String titulo1 = " ";
    public DatabaseReference mDatabaseRef = FirebaseDatabase.getInstance().getReference();

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        final View rootView = inflater.inflate(R.layout.activity_noticias, container, false);
        lvNoticia = (ListView)rootView.findViewById(R.id.listview_noticia);
        noticias = new ArrayList<>();


        mDatabaseRef.child("newsfeed").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                fillNoticias(dataSnapshot);
                adapter = new AdapterItem(noticias, rootView.getContext());
                lvNoticia.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

            //puede ser que tenga que hacer un onDataChange por todos
            public void fillNoticias(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    String titulo = child.child("titulo").getValue().toString();
                    String descripcion = child.child("descripcion").getValue().toString();
                    String link = child.child("link").getValue().toString();
                    String img = child.child("imagen").getValue().toString();
                    NoticiaModelo unaNoticia = new NoticiaModelo(titulo, link, descripcion, img);
                    unaNoticia.setTitle(titulo);
                    if (!noticias.contains(unaNoticia)) {
                        noticias.add(unaNoticia);
                    }
                }
            }
        });

        return rootView;

    }

    @Override
    public void onClick(View v) {

    }
}
