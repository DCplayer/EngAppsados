package com.engappsados.engappsadosapp;


import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

// para fireBase
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.List;


public class userProfile extends AppCompatActivity {
    private static final String TAG = "";
    private ImageView user_Picture;
    private TextView user_Name;
    private TextView user_Points;
    private TextView user_mail;
    private Button btnOut;
    public Context mContext;
    //para base de datps
    private FirebaseUser usuario = FirebaseAuth.getInstance().getCurrentUser();
    private DatabaseReference mDatabaseRef = FirebaseDatabase.getInstance().getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        user_Picture = (ImageView) findViewById(R.id.ImgV_usePicture);
        user_Name = (TextView) findViewById(R.id.nombreDeUsuario);
        user_Points = (TextView) findViewById(R.id.puntosDeUsuario);
        user_mail = (TextView) findViewById(R.id.user_txemail);
        btnOut  = (Button) findViewById(R.id.user_btnSignOut);
        btnOut.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v){
                Ustats u = new Ustats();
                u.getTimeUstats(mContext);

                //aca se debe de cerrar sesion
                //temporal para obtenr el paquete de las aplicaciones
                final PackageManager pm = getPackageManager();
                List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);

                for (ApplicationInfo packageInfo : packages) {
                    Log.d("Packages", "" + packageInfo.packageName);
                }

            }
        });



        String uID = usuario.getUid();

        mDatabaseRef.child("usuarios").child(uID).child("Imagen").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String imgUrl = dataSnapshot.getValue().toString();
                Picasso.with(userProfile.this).load(imgUrl).transform(new CircleTransform()).into(user_Picture);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e("Hey", "Failed to read user name.", error.toException());
            }
        });


        mDatabaseRef.child("usuarios").child(uID).child("username").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String userName = dataSnapshot.getValue().toString();
                user_Name.setText(userName);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e("Hey", "Failed to read user name.", error.toException());
            }
        });

        mDatabaseRef.child("usuarios").child(uID).child("Puntos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String userPoints = dataSnapshot.getValue().toString();
                user_Points.setText(userPoints);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e("Hey", "Failed to read user name.", error.toException());
            }
        });


        mDatabaseRef.child("usuarios").child(uID).child("e-mail").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String email = dataSnapshot.getValue().toString();
                user_mail.setText(email);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e("Hey", "Failed to read user name.", error.toException());
            }
        });

    }

}
