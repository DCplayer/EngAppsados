package com.engappsados.engappsadosapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class NoticiasActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView puntos;
    private Button botonMenos,botonMas;
    private FirebaseUser usuario = FirebaseAuth.getInstance().getCurrentUser();
    private DatabaseReference mDatabaseRef = FirebaseDatabase.getInstance().getReference();
    private int points;
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

        String Id = usuario.getUid();
        Query query = mDatabaseRef.child(Id);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                for(DataSnapshot data: dataSnapshot.getChildren()){

                    int puntos = data.child("Puntos").getValue(Integer.class);
                    points = puntos;
                }

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        if (R.id.btnMas == v.getId()) {

            points = points + 1;
            mDatabaseRef.child(usuario.getUid()).child("Puntos").setValue(points);

        }
        else if(R.id.btnMenos == v.getId()){
            points = points - 1;
            mDatabaseRef.child(usuario.getUid()).child("Puntos").setValue(points);
        }

    }
}
