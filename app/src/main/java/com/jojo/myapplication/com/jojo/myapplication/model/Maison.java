package com.jojo.myapplication.com.jojo.myapplication.model;

import java.io.Serializable;

/**
 * Created by jojo on 24/09/2014.
 */
public class Maison implements Serializable {


    private String nom;
    private String adresse;
    private String telephone;



    public Maison(String nom, String adresse, String telephone) {
        this.nom = nom;
        this.adresse = adresse;
        this.telephone = telephone;

    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String toString(){

        return nom;
    }

}
