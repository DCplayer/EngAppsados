package com.engappsados.engappsadosapp;

/**
 * Created by sebas on 9/5/2017.
 */

public class Recompensa {
    private int id;
    private String name;
    private int price;
    private String imagen;

    public Recompensa(int id, String name, int price, String imagen) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imagen = imagen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
