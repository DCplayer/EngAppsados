package com.engappsados.engappsadosapp;

/**
 * Created by DiegoCastaneda on 03/09/2017.
 */

public class AppModelo {
    private String title;
    private String link;
    private String description;
    private String imagen;

    public AppModelo(){
        super();
    }

    public AppModelo(String title, String description, String imagen, String link) {
        this.title = title;
        this.link = link;
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
        return link;
    }

    public void setLeermas(String leermas) {
        this.link = leermas;
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
