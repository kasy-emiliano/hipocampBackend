package com.example.elearning.models;

import java.util.ArrayList;

public class civilite {

 private int idcivilite;
 private String nom;

    public int getIdcivilite() {
        return idcivilite;
    }

    public void setIdcivilite(int idcivilite) {
        this.idcivilite = idcivilite;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public civilite(int idcivilite, String nom) {
        this.idcivilite = idcivilite;
        this.nom = nom;
    }


}
