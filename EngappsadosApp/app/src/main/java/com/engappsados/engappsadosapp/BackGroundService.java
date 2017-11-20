package com.engappsados.engappsadosapp;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by sebas on 10/30/2017.
 */

public class BackGroundService extends IntentService {

    //Bundle b = new Bundle();
    public Context mContext ;
    ArrayList<String> array = new ArrayList<>();
    ArrayList<Integer> arrayt = new ArrayList<>();
    ArrayList<Integer> arrayp = new ArrayList<>();
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public BackGroundService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent workIntent) {

        Toast.makeText(BackGroundService.this, "Iniciando..." , Toast.LENGTH_LONG).show();
        String dataString = workIntent.getDataString();
        Bundle b = workIntent.getExtras();
        if(b != null){
            array = b.getStringArrayList("aplicaciones");
            arrayt= b.getIntegerArrayList("tiempo");
            arrayp= b.getIntegerArrayList("puntos");
            Ustats u = new Ustats();
            u.getTimeUstats(mContext, array, arrayt,arrayp);
        }

    }
}
