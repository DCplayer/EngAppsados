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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticias);


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

        /*
        //noticia 1
        TextView textView1 =(TextView)findViewById(R.id.leerMas);
        textView1.setClickable(true);
        textView1.setMovementMethod(LinkMovementMethod.getInstance());
        String text1 = "<a href='https://www.guatemala.com/noticias/tecnologia/ingenieros-guatemaltecos-que-han-destacado-a-nivel-internacional.html'> Leer mas </a>";
        textView1.setText(Html.fromHtml(text1));
        //noticia 2
        TextView textView2 = (TextView)findViewById(R.id.leerMas2);
        textView2.setClickable(true);
        textView2.setMovementMethod(LinkMovementMethod.getInstance());
        String text2 = "<a href='https://www.guatemala.com/noticias/tecnologia/guatemaltecos-participaron-como-conferencistas-en-droidcon-2017.html'> Leer mas </a>";
        textView2.setText(Html.fromHtml(text2));
        // noticia 3
        TextView textView3 = (TextView)findViewById(R.id.leerMas3);
        textView3.setClickable(true);
        textView3.setMovementMethod(LinkMovementMethod.getInstance());
        String text3 = "<a href='https://www.guatemala.com/noticias/tecnologia/guatemaltecos-triunfan-en-competencia-de-seguridad-informatica-en-latinoamerica-2016.html'> Leer mas </a>";
        textView3.setText(Html.fromHtml(text3));
    */

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
