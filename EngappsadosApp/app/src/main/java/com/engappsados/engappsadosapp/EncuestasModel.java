package com.engappsados.engappsadosapp;

/**
 * Created by Alejanddro on 05/09/2017.
 */

public class EncuestasModel {

        private String title;
        private String link;
        private String description;


    private String puntos;

        public EncuestasModel(){
            super();
        }

        public EncuestasModel(String title, String link, String description, String puntos) {
            this.title = title;
            this.link = link;
            this.description = description;
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

    public String getPuntos() {
        return puntos;
    }

    public void setPuntos(String puntos) {
        this.puntos = puntos;
    }


}
