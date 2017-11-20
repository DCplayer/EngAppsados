package com.engappsados.engappsadosapp;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    public static final int TEST_suma = 100;
    public static final String userID = "KfdHUM6cPLQ7LgrJmM53gZ0ZDOb2";
    public static final DatabaseReference mDatabaseRef = FirebaseDatabase.getInstance().getReference();
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.engappsados.engappsadosapp", appContext.getPackageName());
    }
    @Test
    public void testSumaPuntos(){
        final SumadorRestadorPuntos sumador = new SumadorRestadorPuntos(50, userID);

        //
        mDatabaseRef.child("usuarios").child(userID).child("Puntos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int points = Integer.parseInt(dataSnapshot.getValue().toString());
                int puntosAntes = Integer.parseInt(String.valueOf(points));
                int res = sumador.sumarP(puntosAntes, 50);
                int expectedRes = puntosAntes + 50;
                assertEquals(res, expectedRes);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        //

    }
}
