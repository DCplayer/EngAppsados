package com.engappsados.engappsadosapp;


import android.app.usage.UsageEvents;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.SortedMap;
import java.util.StringTokenizer;
import java.util.TreeMap;

/**
 * Created by sebas on 9/26/2017.
 */

public class Ustats {
    public FirebaseUser usuario = FirebaseAuth.getInstance().getCurrentUser();
    public DatabaseReference mDatabaseRef = FirebaseDatabase.getInstance().getReference();
    public String uID = usuario.getUid();
    public int puntos;
    public boolean booleano = true;



    public Ustats() {

        mDatabaseRef.child("usuarios").child(uID).child("Puntos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                puntos = Integer.parseInt(dataSnapshot.getValue().toString());
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void getTimeUstats(Context mContext, ArrayList<String> array, ArrayList<Integer> arrayt, ArrayList<Integer> arrayp){
        // UsageStats usageStats;
        String PackageName = "Nothing" ;

        long TimeInforground = 500 ;

        int minutes=500,seconds=500,hours=500 ;
        UsageStatsManager mUsageStatsManager = (UsageStatsManager)mContext.getSystemService(Context.USAGE_STATS_SERVICE);
        long time = System.currentTimeMillis();

        List<UsageStats> stats = mUsageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, time - 1000*10, time);
        if(stats != null) {
            SortedMap<Long, UsageStats> mySortedMap = new TreeMap<Long, UsageStats>();
            for (UsageStats usageStats : stats) {
                if(array.contains(usageStats.getPackageName())){
                    TimeInforground = usageStats.getTotalTimeInForeground();
                    PackageName = usageStats.getPackageName();
                    minutes = (int) ((TimeInforground / (1000 * 60)) % 60);
                    seconds = (int) (TimeInforground / 1000) % 60;
                    hours = (int) ((TimeInforground / (1000 * 60 * 60)) % 24);
                    int indice = array.indexOf(PackageName);
                    //si la aplicacion ya corio por mas de lo especificado en la base de datos.
                    if(minutes >= arrayt.get(indice)){
                        //actualizar los puntos del usuario
                        puntos = puntos + arrayp.get(indice);
                        mDatabaseRef.child("usuarios").child(uID).child("Puntos").setValue(puntos);
                        booleano = false;
                        //toast que muestra la informacion
                        int duracion=Toast.LENGTH_LONG;
                        String texto = "Has obtenido " + arrayp.get(indice) + " puntos por haber estado " + arrayt.get(indice) +" o mas minutos usando: "+ array.get(indice);
                        Toast.makeText(mContext, texto, duracion).show();
                    }else{
                        //toast que muestra la informacion
                        int duracion=Toast.LENGTH_LONG;
                        String texto = "No se pueden otorgar puntos aún.";
                        Toast.makeText(mContext, texto, duracion).show();
                    }

                    Log.i("BAC", "PackageName is" + PackageName + "Time is: " + hours + "h" + ":" + minutes + "m" + seconds + "s");
                }
            }
        }
    }
}

