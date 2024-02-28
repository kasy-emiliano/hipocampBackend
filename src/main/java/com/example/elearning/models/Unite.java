package com.example.elearning.models;

public class Unite {
    private  int idUnite;
    private String nom;

    public Unite(int idUnite, String nom) {

        this.idUnite = idUnite;
        this.nom = nom;
    }

    public int getIdUnite() {
        return idUnite;
    }

    public void setIdUnite(int idUnite) {
        this.idUnite = idUnite;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
