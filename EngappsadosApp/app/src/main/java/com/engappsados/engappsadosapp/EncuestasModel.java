package com.engappsados.engappsadosapp;

/**
 * Created by Alejanddro on 05/09/2017.
 */

public class EncuestasModel {

        private String title;
        private String link;
        private String description;
        //private String imagen;

        public EncuestasModel(){
            super();
        }

        public EncuestasModel(String title, String link, String description) {
            this.title = title;
            this.link = link;
            this.description = description;
            //this.imagen = imagen;
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
/*
        public String getImagen() {
            return imagen;
        }

        public void setImagen(String imagen) {
            this.imagen = imagen;
        }
    }

*/

}
