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

public class Tienda_tab extends Fragment implements View.OnClickListener{

    private ListView lvRewards;
    private AdapterRewardItem adapter;
    private List<Recompensa> recompensaList;
    public DatabaseReference mDatabaseRef = FirebaseDatabase.getInstance().getReference();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.tienda_tab, container, false);
        lvRewards = (ListView)rootView.findViewById(R.id.listview_tienda);
        recompensaList = new ArrayList<>();

        mDatabaseRef.child("recompensas").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                fillRecompensa(dataSnapshot); //obtener noticias de la base de datos, y rellenar la lista de noticias
                adapter = new AdapterRewardItem(rootView.getContext(), recompensaList); //Llamar al adapter personalizado
                lvRewards.setAdapter(adapter); //llenar la listview con el adapter

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

            //puede ser que tenga que hacer un onDataChange por todos
            public void fillRecompensa(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    String titulo = child.child("titulo").getValue().toString();
                    String precio = child.child("precio").getValue().toString();
                    String img = child.child("imagen").getValue().toString();
                    Recompensa unaRecompensa = new Recompensa(titulo, precio, img);
                    unaRecompensa.setName(titulo);
                    if(!recompensaList.contains(unaRecompensa)){
                        recompensaList.add(unaRecompensa);
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