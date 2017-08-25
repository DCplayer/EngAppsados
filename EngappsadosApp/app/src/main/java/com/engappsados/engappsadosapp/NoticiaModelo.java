package com.engappsados.engappsadosapp;
import android.graphics.drawable.Drawable;

/**
 * Created by Rodrigo on 23/08/2017.
 */

public class NoticiaModelo {
    private String title;
    private String leermas;
    private String description;
    private String imagen;

    public NoticiaModelo(){
        super();
    }

    public NoticiaModelo(String title, String leermas, String description, String imagen) {
        this.title = title;
        this.leermas = leermas;
        this.description = description;
        this.imagen = imagen;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLeermas() {
        return leermas;
    }

    public void setLeermas(String leermas) {
        this.leermas = leermas;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
