package com.engappsados.engappsadosapp;

import android.net.Uri;

import java.net.URI;

/**
 * Created by Diego Castañeda on 30/07/2017.
 */

public class User {
    /*Informacion del usuario mismo*/
    public String nombre;
    public String email;
    public Uri photoUrl;


    /*Datos del usuario dentro de la aplicacion */
    public int puntos;

    public User(String nombre, String email, Uri photoUrl){
        this.nombre = nombre;
        this.email = email;
        this.photoUrl = photoUrl;

    }
}
