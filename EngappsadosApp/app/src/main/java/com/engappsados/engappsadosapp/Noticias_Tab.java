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
import android.widget.ListView;
import android.widget.TextView;
import android.support.v7.app.AppCompatActivity;

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
        final Context elContexto = rootView.getContext();
        //DataSnapshot foto = new DataSnapshot(FirebaseDatabase.getInstance().getReference());

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

        //noticia1
/*
        TextView textView1 = (TextView) rootView.findViewById(R.id.link);
        textView1.setClickable(true);
        textView1.setMovementMethod(LinkMovementMethod.getInstance());
        String text1 = "<a href='https://www.guatemala.com/noticias/tecnologia/ingenieros-guatemaltecos-que-han-destacado-a-nivel-internacional.html'> Leer mas </a>";
        textView1.setText(Html.fromHtml(text1));
        TextView title = (TextView) rootView.findViewById(R.id.category);
        title.setText(titulo1);
*/
        return rootView;

    }
/*
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // setContentView(R.layout.activity_noticias);
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
                //cargarNoticas();
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
*/
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



    @Override
    public void onClick(View v) {

    }
}
