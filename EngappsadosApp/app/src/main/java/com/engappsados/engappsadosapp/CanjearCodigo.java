package com.engappsados.engappsadosapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CanjearCodigo extends AppCompatActivity implements  View.OnClickListener {

    private Button botonIngreso;
    private TextView codigoIngresado;
    private String codigo;
    private boolean respuesta;
    private String uID;
    private int duracion;
    private Context context;
    private boolean novato;


    private FirebaseUser usuario;
    private DatabaseReference mDatabaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canjear_codigo);

        duracion= Toast.LENGTH_LONG;
        context = getApplicationContext();
        usuario = FirebaseAuth.getInstance().getCurrentUser();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference();





        botonIngreso = (Button)findViewById(R.id.botonInvitacion);
        codigoIngresado = (TextView)findViewById(R.id.CodigoInvitacion);

        botonIngreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                codigo = String.valueOf(codigoIngresado.getText());
                codigoIngresado.setText("");
                inicioCodigo();

            }
        });
    }

    /*Si hay interaccion con el boton, comenzara la busqueda del usuario que dio el codigo y se
    * revisaran ciertos factores para otorgar puntos a los usuarios involucrados*/
    public void inicioCodigo(){
        uID = usuario.getUid();

        final SumadorRestadorPuntos invitado = new SumadorRestadorPuntos(200, uID);
        mDatabaseRef.child("usuarios").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean chequeo = false;
                String donante = "";
                if(revisarNuevo()) {
                    for (DataSnapshot uniqueUserSnapshot : dataSnapshot.getChildren()){
                        if(decoder(uniqueUserSnapshot.getKey()).equals(codigo)){
                            donante = uniqueUserSnapshot.child("username").getValue().toString();
                            chequeo = true;
                            SumadorRestadorPuntos invitador = new SumadorRestadorPuntos(400, uniqueUserSnapshot.getKey());
                            invitador.sumar();
                            invitado.sumar();
                            cambiarNovato();

                        }
                    }
                }
                else {
                    String texto="Usted no es un Usuario nuevo para la aplicación.";
                    Toast.makeText(context, texto, duracion).show();

                    /*Pop Up: Usted no es un nuevo usuario*/
                    /*IGUAL SE PLANEA: Mantener como hidden el boton de agregar recomendado mientras
                    * el usuario no sea nuevo*/
                }

                if (chequeo){
                    String mensaje = "Te damos la bienvenida a EngAppsados. Se han acreditado puntos" +
                            " en tu cuenta y en la cuenta de " + donante;
                }
                else{
                    String mensaje = "El codigo que ingreso no es válido";
                    Toast.makeText(context, mensaje, duracion).show();
                }
            }


            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e("Hey", "Failed to read user name.", error.toException());
            }
        });

    }

    /*Metodo que permite obtener el arreglo para saber el usuairo al que se refiere el codigo
    * ingresado*/
    public String decoder(String uid){
        String regreso = "";
        regreso = regreso + uid.substring(0,5) + uid.substring(uid.length()-5, uid.length());
        return regreso;
    }

    /*Metodo que revisa si el usuario actual, que intenta ingresar un codigo, es nuevo o no*/
    public boolean revisarNuevo(){

        mDatabaseRef.child("usuarios").child(uID).child("NuevoUsuario").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                novato = Boolean.parseBoolean(dataSnapshot.getValue().toString());
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e("Hey", "Failed to read user name.", error.toException());
            }
        });

        return novato;

    }

    public void cambiarNovato(){
        boolean dato = false;
        if(novato){
            dato = !novato;
        }

        mDatabaseRef.child("usuarios").child(uID).child("NuevoUsuario").setValue(dato);

    }

    @Override
    public void onClick(View v) {

    }
}
