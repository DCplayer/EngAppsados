package com.engappsados.engappsadosapp;

/**
 * Created by sebas on 8/15/2017.
 */

import android.app.ActivityGroup;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class Noticias_Tab extends Fragment {
    private ArrayList<NoticiaModelo> noticias = new ArrayList<>();
    //private int cantNoticias;
    public String titulo1 = "fracaso";
    private DatabaseReference mDatabaseRef = FirebaseDatabase.getInstance().getReference();

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_noticias, container, false);
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
//puede ser que tenga que hacer un onDataChange por todos
            public void fillNoticias(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    String titulo = child.child("titulo").getValue().toString();
                    String descripcion = child.child("descripcion").getValue().toString();
                    String link = child.child("link").getValue().toString();
                    String img = child.child("imagen").getValue().toString();
                    NoticiaModelo unaNoticia = new NoticiaModelo(titulo, link, descripcion, img);
                    noticias.add(unaNoticia);
                }
            }

        });

        //noticia1

        TextView textView1 =(TextView)rootView.findViewById(R.id.leermas);
        textView1.setClickable(true);
        textView1.setMovementMethod(LinkMovementMethod.getInstance());
        String text1 = "<a href='https://www.guatemala.com/noticias/tecnologia/ingenieros-guatemaltecos-que-han-destacado-a-nivel-internacional.html'> Leer mas </a>";
        textView1.setText(Html.fromHtml(text1));
        TextView title = (TextView)rootView.findViewById(R.id.category);
        title.setText(titulo1);
        /*
        TextView title = (TextView)rootView.findViewById(R.id.category);
        title.setText(noticias.get(0).getTitle());

        TextView descripcion = (TextView)rootView.findViewById(R.id.texto);
        descripcion.setText(noticias.get(0).getDescription());
*/
/*
        //noticia2
        TextView textView2 =(TextView)rootView.findViewById(R.id.leerMas2);
        textView2.setClickable(true);
        textView2.setMovementMethod(LinkMovementMethod.getInstance());
        String text2 = "<a href='https://www.guatemala.com/noticias/tecnologia/guatemaltecos-participaron-como-conferencistas-en-droidcon-2017.html'> Leer mas </a>";
        textView2.setText(Html.fromHtml(text2));

        //noticia3
        TextView textView3 =(TextView)rootView.findViewById(R.id.leerMas3);
        textView3.setClickable(true);
        textView3.setMovementMethod(LinkMovementMethod.getInstance());
        String text3 = "<a href='https://www.guatemala.com/noticias/tecnologia/guatemaltecos-triunfan-en-competencia-de-seguridad-informatica-en-latinoamerica-2016.html'> Leer mas </a>";
        textView3.setText(Html.fromHtml(text3));
*/


        return rootView;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // setContentView(R.layout.activity_noticias);


        mDatabaseRef.child("newsfeed").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                fillNoticias(dataSnapshot);
                cargarNoticas();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

            public void fillNoticias(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
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

    public void cargarNoticas() {
        //   View relative = findViewById(R.id.text_list_view);

        for (NoticiaModelo noticia : noticias) {
            TextView titulo = new TextView(getContext());
            titulo.setText(noticia.getTitle());
            titulo.setTextColor(5);
            titulo.setLayoutParams(new ActionBar.LayoutParams(
                    GridLayout.LayoutParams.FILL_PARENT,
                    android.app.ActionBar.LayoutParams.WRAP_CONTENT
            ));

            TextView desc = new TextView(getContext());
            desc.setText(noticia.getDescription());
            desc.setLayoutParams(new ActionBar.LayoutParams(
                    GridLayout.LayoutParams.FILL_PARENT,
                    android.app.ActionBar.LayoutParams.WRAP_CONTENT
            ));
            TextView leerMas = new TextView(getContext());
            String link = noticia.getLeermas();
            leerMas.setText(Html.fromHtml(link));
        }


    }
}
