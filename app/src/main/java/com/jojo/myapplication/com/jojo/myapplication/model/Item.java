package com.jojo.myapplication.com.jojo.myapplication.model;

import java.io.Serializable;

/**
 * Created by jojo on 25/09/2014.
 */
public class Item implements Serializable {

    private String nom;

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    private Integer image;


}
