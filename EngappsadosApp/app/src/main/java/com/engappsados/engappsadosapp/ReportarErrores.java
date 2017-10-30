package com.engappsados.engappsadosapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

// para fireBase
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class ReportarErrores extends AppCompatActivity {

    private FirebaseUser usuario = FirebaseAuth.getInstance().getCurrentUser();
    private DatabaseReference mDatabaseRef = FirebaseDatabase.getInstance().getReference();
    public Context mContext;
    public String uID = usuario.getUid();
    public String correo = usuario.getEmail();
    public int puntos;
    public boolean booleano = true;
    //lista con las aplicaciones.
    final List<String> apps = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportar_errores);
        apps.add(" - ");
        apps.add("EngAppsados");
        final EditText etxtDescripcion = (EditText)findViewById(R.id.textInput_bug);
        Button btnEnviar = (Button) findViewById(R.id.enviarError);
        final Spinner appsSpinner = (Spinner) findViewById(R.id.apps_spinner);
        mDatabaseRef.child("aplicaciones").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {
                    String areaName = areaSnapshot.child("nombre").getValue(String.class);
                    apps.add(areaName);
                }


                ArrayAdapter<String> appsAdapter = new ArrayAdapter<String>(ReportarErrores.this, android.R.layout.simple_spinner_item, apps);
                appsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                appsSpinner.setAdapter(appsAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        appsSpinner.setSelection(0);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (String.valueOf(appsSpinner.getSelectedItem()).equals(" - ")){
                    Toast.makeText(ReportarErrores.this, "Debes de selecionar la aplicacion a reportar", Toast.LENGTH_LONG).show();
                }
                else if(String.valueOf(etxtDescripcion.getText()).equals("")){
                    Toast.makeText(ReportarErrores.this, "Debes de escribir una descripcion del error!", Toast.LENGTH_LONG).show();
                }
                else {
                    String appSelected = "Error en la aplicacion: " + String.valueOf(appsSpinner.getSelectedItem());
                    String descError = "La descripcion que el usuario dio del error fue:\n\n" + "\"" + String.valueOf(etxtDescripcion.getText()) + "\"" + "\n\nEngAppsados Team.";
                    enviarCorreo(appSelected, descError);
                    appsSpinner.setSelection(0);
                    etxtDescripcion.setText("");
                }
            }
        });
    }

    public void enviarCorreo(String subject, String mensaje){
        mContext = ReportarErrores.this;
        Date currentTime = Calendar.getInstance().getTime();
        String dateSuject = currentTime.toString() + " - Engappsados BugReport - "+ subject;
        try {
            //enviar correo a desarrollador
            SendMail sm = new SendMail(mContext, ConfigMail.getEMAIL(), dateSuject, mensaje);
            sm.execute();
            //enviar copia a usuario.
            SendMail sm2 = new SendMail(mContext,correo, dateSuject, mensaje);
            sm2.execute();
            int duracion = Toast.LENGTH_LONG;
            sumarPuntos();
            Toast.makeText(mContext, "+15 pts. por tu reporte. ", duracion).show();
        }
        catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(mContext, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }
    private void sumarPuntos(){
        mDatabaseRef.child("usuarios").child(uID).child("Puntos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                puntos = Integer.parseInt(dataSnapshot.getValue().toString());
                if (booleano){
                    puntos = puntos + 15;
                    mDatabaseRef.child("usuarios").child(uID).child("Puntos").setValue(puntos);
                    booleano = false;
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
