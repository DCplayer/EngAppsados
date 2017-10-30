//Proyecto Engappsado
// Rodrigo Arriaza,  Sebastian Galindo, Diego Castaneda y Johnny Juarez
// Actividad principal del programa
package com.engappsados.engappsadosapp;

import android.*;
import android.Manifest;
import android.app.AppOpsManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.firebase.auth.*;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity implements View.OnClickListener,GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = "SignInActivity";
    private static final int REQ_CODE = 9001;
    private LinearLayout Prof_secction;
    private SignInButton SignIn;
    private GoogleApiClient mGoogleApiClient;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    //private Button botonMenu;
    private Button btn_politicas;
    private DatabaseReference mDatabaseRef = FirebaseDatabase.getInstance().getReference();

    /*
    * tomado de github de google, disponible en https://github.com/googlesamples/google-services/blob/master/android/signin/app/src/main/java/com/google/samples/quickstart/signin/SignInActivity.java#L51-L55
    * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Check if permission enabled/*
        boolean granted = false;
        AppOpsManager appOps = (AppOpsManager) this
                .getSystemService(this.APP_OPS_SERVICE);
        int mode = appOps.checkOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS,
                android.os.Process.myUid(), this.getPackageName());

        if (mode == AppOpsManager.MODE_DEFAULT) {
            granted = (this.checkCallingOrSelfPermission(android.Manifest.permission.PACKAGE_USAGE_STATS) == PackageManager.PERMISSION_GRANTED);
        } else {
            granted = (mode == AppOpsManager.MODE_ALLOWED);
        }

        if(!granted){
            //startActivity(new Intent(MainActivity.this, Permissions.class));
            Toast.makeText(MainActivity.this, "Necesito permisos!" , Toast.LENGTH_LONG).show();
            startActivity(new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS));
        }else{
            Toast.makeText(MainActivity.this, "Si hay!" , Toast.LENGTH_LONG).show();
        }

        Prof_secction = (LinearLayout) findViewById(R.id.SingIn_panel);
        SignIn = (SignInButton) findViewById(R.id.btn_SignIn);
        SignIn.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
        //hacia politicas de uso
        btn_politicas = (Button)findViewById(R.id.btn_LinkContrato);
        btn_politicas.setOnClickListener(this);


        //botonMenu = (Button) findViewById(R.id.btnMenu);
       // botonMenu.setOnClickListener(this);
        //configuracion del inicio de sesion
        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        //se crea el objeto del clientte de tip GoogleApliClient
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, signInOptions)
                .build();
        //para login en firebase
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged( FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }

            }
        };
    }

    @Override
    public void onClick(View v) {

        if (R.id.btn_SignIn == v.getId()) {
            signInMethod();
        }
       // else if(R.id.btnMenu == v.getId()){
        //    updateGUI(true);

      //  }
        else if(R.id.btn_LinkContrato == v.getId()){
            startActivity(new Intent(MainActivity.this, politicaActivity.class));

        }

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    /*
    * metodo que verifica si se ha iniciado sesion con anterioridad*/
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
        if (opr.isDone()) {
            // If the user's cached credentials are valid, the OptionalPendingResult will be "done"
            // and the GoogleSignInResult will be available instantly.
            GoogleSignInResult result = opr.get();
            handleResult(result);
        } else {
            // If the user has not previously signed in on this device or the sign-in has expired,
            // this asynchronous branch will attempt to sign in the user silently.  Cross-device
            // single sign-on will occur in this branch.
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(GoogleSignInResult googleSignInResult) {
                    handleResult(googleSignInResult);
                }        });
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());
            ProgressDialog.show(this, "Cargando", "por favor espera a que nuestros monos altamente calificados terminen...");
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete( Task<AuthResult> task) {
                        Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithCredential", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else {
                            //Codigo para la creacion de usuarios en la base de datos
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            Log.d(TAG, "getinstance:" + user);
                            if (user != null) {
                                String uid = user.getUid();
                                String nombre = user.getDisplayName();
                                String email = user.getEmail();
                                Uri photoURL = user.getPhotoUrl();
                                Log.d(TAG, "Url Obtenida"+photoURL);
                                Log.d(TAG, "Obtuvo los datos del usuario");
                                DatabaseReference current_user_db = mDatabaseRef.child("usuarios").child(uid);
                                Log.d(TAG, "curente_user_db" + current_user_db);
                                current_user_db.child("username").setValue(nombre);
                                current_user_db.child("e-mail").setValue(email);
                                current_user_db.child("Imagen").setValue(photoURL.toString());
                                current_user_db.child("Puntos").setValue(0);
                                updateGUI(true);
                            }
                        }
                        //
                    }
                });
    }

    private void signInMethod() {
        Intent intent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(intent, REQ_CODE);
    }

    private void handleResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            // login exitoso,
            GoogleSignInAccount account = result.getSignInAccount();
            assert account != null;
            updateGUI(true);
        } else {
            updateGUI(false);
        }
    }

    private void updateGUI(boolean result) {
        if (result) {
            startActivity(new Intent(MainActivity.this, MenuTabbed.class));
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ_CODE) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
            }
        }
    }
}
