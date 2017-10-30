package com.engappsados.engappsadosapp;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Christian12 on 28/10/2017.
 */

public class SumadorRestadorPuntos {
    private int costo;
    private String uID;
    private int puntos;

    public FirebaseUser usuario = FirebaseAuth.getInstance().getCurrentUser();
    public DatabaseReference mDatabaseRef = FirebaseDatabase.getInstance().getReference();


    public SumadorRestadorPuntos(int costo, String uID){
        this.costo = costo;
        this.uID = uID;
    }

    public void sumar(){
        mDatabaseRef.child("usuarios").child(uID).child("Puntos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                puntos = Integer.parseInt(dataSnapshot.getValue().toString());
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        puntos = puntos + costo;

        mDatabaseRef.child("usuarios").child(uID).child("Puntos").setValue(puntos);

    }

    public void restar(){
        mDatabaseRef.child("usuarios").child(uID).child("Puntos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                puntos = Integer.parseInt(dataSnapshot.getValue().toString());
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        puntos = puntos - costo;

        mDatabaseRef.child("usuarios").child(uID).child("Puntos").setValue(puntos);

    }

}
