package com.engappsados.engappsadosapp;

import java.net.URI;

/**
 * Created by Diego Castañeda on 30/07/2017.
 */

public class User {
    /*Informacion del usuario mismo*/
    public String nombre;
    public String email;
    public URI photoUrl;
    public String ubicacion;

    /*Datos del usuario dentro de la aplicacion */
    public int puntos;

    public User(String nombre, String email, URI photoUrl){
        this.nombre = nombre;
        this.email = email;
        this.photoUrl = photoUrl;

    }
}
