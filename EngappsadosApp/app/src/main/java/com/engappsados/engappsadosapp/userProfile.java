package com.engappsados.engappsadosapp;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

// para fireBase
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;


public class userProfile extends AppCompatActivity {
    private static final String TAG = "";
    private ImageView user_Picture;
    private TextView user_Name;
    private TextView user_Points;
    private FirebaseUser usuario = FirebaseAuth.getInstance().getCurrentUser();
    private DatabaseReference mDatabaseRef = FirebaseDatabase.getInstance().getReference();
    private int points;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        user_Picture = (ImageView) findViewById(R.id.ImgV_usePicture);
        user_Name = (TextView) findViewById(R.id.nombreDeUsuario);
        user_Points = (TextView) findViewById(R.id.puntosDeUsuario);


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


    }

}
