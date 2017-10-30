package com.engappsados.engappsadosapp;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class GoogleForm_Activity extends AppCompatActivity {
    //atributos
    WebView mWebView;
    // para base de datos
    public FirebaseUser usuario = FirebaseAuth.getInstance().getCurrentUser();
    public DatabaseReference mDatabaseRef = FirebaseDatabase.getInstance().getReference();
    public String uID = usuario.getUid();
    public int puntos;
    public int puntosASumar;
    public boolean booleano = true;
    public String url= "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_form_);
        //crea vista de la web view
        mWebView = (WebView) findViewById(R.id.navegadorEncuesta);
        mWebView.getSettings().setJavaScriptEnabled(true);

        mDatabaseRef.child("usuarios").child(uID).child("Puntos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                puntos = Integer.parseInt(dataSnapshot.getValue().toString());
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        Bundle b = getIntent().getExtras();
        if(b != null)
            url = b.getString("link");
            puntosASumar = Integer.parseInt(b.getString("puntos"));

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (url.endsWith("formResponse") && booleano){
                    //actualizar los puntos del usuario
                    puntos = puntos + puntosASumar;
                    mDatabaseRef.child("usuarios").child(uID).child("Puntos").setValue(puntos);
                    booleano = false;
                    //cierra la webView
                    finish();
                }
            }
        });
        // habilita la posibilidad de que la pagina sea responsive
        mWebView.getSettings().setUseWideViewPort(true);
        // Cargar la URL
        mWebView.loadUrl(url);

    }
}

