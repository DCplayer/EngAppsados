package com.engappsados.engappsadosapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by sebas on 9/5/2017.
 */

public class AdapterRewardItem extends BaseAdapter {

    private Context mContext;
    protected List<Recompensa> recompensaList;
    public FirebaseUser usuario = FirebaseAuth.getInstance().getCurrentUser();
    public DatabaseReference mDatabaseRef = FirebaseDatabase.getInstance().getReference();
    public String uID = usuario.getUid();
    public String correo = usuario.getEmail();
    public int puntos;

    public AdapterRewardItem(Context mContext, List<Recompensa> recompensaList) {
        this.mContext = mContext;
        this.recompensaList = recompensaList;
    }

    @Override
    public int getCount() {
        return recompensaList.size();
    }

    @Override
    public Object getItem(int position) {
        return recompensaList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = View.inflate(mContext, R.layout.item_recompensa, null);
        TextView titulo = (TextView)v.findViewById(R.id.reward_name);
        TextView precio = (TextView)v.findViewById(R.id.reward_price);
        ImageView imagen = (ImageView) v.findViewById(R.id.reward_Image);
        Button boton = (Button) v.findViewById(R.id.reward_btnCanjear);

        titulo.setText(recompensaList.get(position).getName());
        precio.setText(recompensaList.get(position).getPrice() + " pts.");

        //Colocar la imagen de la recompensa
        String imgUrl = recompensaList.get(position).getImagen();
        /*Colocando dimensiones de la imagen del app y la forma que puede tener*/
        Picasso.with(mContext).load(imgUrl).into(imagen);

        mDatabaseRef.child("usuarios").child(uID).child("Puntos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                puntos = Integer.parseInt(dataSnapshot.getValue().toString());
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                int costo = Integer.parseInt(recompensaList.get(position).getPrice());
                int duracion=Toast.LENGTH_LONG;
                String texto="";
                if(puntos>=costo){
                    puntos = puntos - costo;
                    mDatabaseRef.child("usuarios").child(uID).child("Puntos").setValue(puntos);
                    texto="Has canjeado " + costo + " puntos por " + recompensaList.get(position).getName() +".";
                    Toast.makeText(mContext, texto, duracion).show();
//enviar correo con el codigo promocional
                    //Intent i = new Intent(Intent.ACTION_SEND);
                    //i.setType("message/rfc822");
                    //i.putExtra(Intent.EXTRA_EMAIL  , new String[]{correo});
                    //i.putExtra(Intent.EXTRA_SUBJECT, "Recompensa de EngAppsados");
                    //i.putExtra(Intent.EXTRA_TEXT   , "Este es el codigo 123456");
                    //Toast.makeText(mContext, correo, duracion).show();

                    try {
                        //i.createChooser(i, "Send email...");

                        SendMail sm = new SendMail(mContext, correo, "EngAppsados: Recompensa", "has ganado tu codigo es: 2+2=4-1=3");
                        sm.execute();
                        Toast.makeText(mContext, "Se ha enviado un correo a "+correo+" con tu recompensa", duracion).show();
                        //sendEmail.startA(Intent.createChooser(i, "Send email..."));
                    } catch (android.content.ActivityNotFoundException ex) {
                        Toast.makeText(mContext, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    texto="No tienes puntos suficientes para esta recompensa.";
                    Toast.makeText(mContext, texto, duracion).show();
                }


            }
        });


        return v;
    }
}
