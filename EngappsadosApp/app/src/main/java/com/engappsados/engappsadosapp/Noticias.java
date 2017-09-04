package com.engappsados.engappsadosapp;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;


public class Noticias extends AppCompatActivity {
        private ArrayList<NoticiaModelo> noticias = new ArrayList<>();
    private int cantNoticias;
    private DatabaseReference mDatabaseRef = FirebaseDatabase.getInstance().getReference();
    private String titulo1="fracaso";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticias);

        mDatabaseRef.child("newsfeed").child("noticia1").child("titulo").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                titulo1 = dataSnapshot.getValue().toString();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        mDatabaseRef.child("newsfeed").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                fillNoticias(dataSnapshot);
                cargarNoticas();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

            public void fillNoticias (DataSnapshot dataSnapshot){
                for (DataSnapshot child : dataSnapshot.getChildren()){
                    String titulo = child.child("titulo").getValue().toString();
                    String descripcion = child.child("descripcion").getValue().toString();
                    String link = child.child("link").getValue().toString();
                    String img = child.child("imagen").getValue().toString();
                    NoticiaModelo unaNoticia = new NoticiaModelo(titulo, link, descripcion, img);
                    noticias.add(unaNoticia);
                }
            }


                                                                                      });
    }

    public void cargarNoticas()
    {
     //   View relative = findViewById(R.id.text_list_view);
        for (NoticiaModelo noticia : noticias){
            TextView titulo = new TextView(this);
            titulo.setText(noticia.getTitle());
            titulo.setTextColor(5);
            titulo.setLayoutParams(new ActionBar.LayoutParams(
                    GridLayout.LayoutParams.FILL_PARENT,
                    android.app.ActionBar.LayoutParams.WRAP_CONTENT
            ));

            TextView desc = new TextView(this);
            desc.setText(noticia.getDescription());
            desc.setLayoutParams(new ActionBar.LayoutParams(
                    GridLayout.LayoutParams.FILL_PARENT,
                    android.app.ActionBar.LayoutParams.WRAP_CONTENT
            ));
            TextView leerMas = new TextView(this);
            String link = noticia.getLeermas();
            leerMas.setText(Html.fromHtml(link));

        }
    }


}
