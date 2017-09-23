package com.engappsados.engappsadosapp;

import android.content.Intent;
import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

/**
 * Created by Rodrigo on 22/09/2017.
 */

public class Developer extends AppCompatActivity {
    public DatabaseReference mDatabaseRef = FirebaseDatabase.getInstance().getReference();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devs);
        final TextView titulo = (TextView) findViewById(R.id.tituloDev);
        final ImageView foto = (ImageView) findViewById(R.id.imagenDeveloper);
        final TextView informacion = (TextView) findViewById(R.id.dataDeveloper);
        Intent intento = getIntent();
        final String developer = intento.getStringExtra("DEV");
        final Context mContext = this.getApplicationContext();
        mDatabaseRef.child("developers").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snap : dataSnapshot.getChildren()){
                    if (snap.getKey().equals(developer)){
                        String title = snap.child("Nombre").getValue().toString();
                        String historia = snap.child("Historia").getValue().toString();
                        String imgUrl = snap.child("linkImagen").getValue().toString();
                        titulo.setText(title);
                        informacion.setText(historia);
                        Picasso.with(mContext).load(imgUrl).resize(300,300).into(foto);
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {            }
        });
    }
}
