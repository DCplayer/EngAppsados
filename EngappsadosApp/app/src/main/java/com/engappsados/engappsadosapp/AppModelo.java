package com.engappsados.engappsadosapp;

/**
 * Created by DiegoCastaneda on 03/09/2017.
 */

public class AppModelo {
    private String title;
    private String link;
    private String description;
    private String imagen;
    private String desarrollador;
    private String packageName;
    private int tiempoenMins;
    private int puntos;

    public AppModelo(){
        super();
    }

    public AppModelo(String title, String description, String imagen, String link, String desarrollador, String paguete, int tiempoenMins, int puntos) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.imagen = imagen;
        this.desarrollador = desarrollador;
        this.packageName = paguete;
        this.tiempoenMins = tiempoenMins;
        this.puntos = puntos;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
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

    public String getDesarrollador() {
        return desarrollador;
    }

    public void setDesarrollador(String desarrollador) {
        this.desarrollador = desarrollador;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public int getTiempoenMins() {
        return tiempoenMins;
    }

    public void setTiempoenMins(int tiempoenMins) {
        this.tiempoenMins = tiempoenMins;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
}
