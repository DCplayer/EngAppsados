package com.engappsados.engappsadosapp;

/**
 * Created by sebas on 9/5/2017.
 */

public class Recompensa {
    private String name;
    private String price;
    private String imagen;

    public Recompensa( String name, String price, String imagen) {
        this.name = name;
        this.price = price;
        this.imagen = imagen;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

}
