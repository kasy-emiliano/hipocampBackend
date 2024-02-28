package com.example.elearning.models;

public class modeDexercice {

    private int  idmodeDexercice;
    private String nom;

    public modeDexercice(int idmodeDexercice, String nom) {
        this.idmodeDexercice = idmodeDexercice;
        this.nom = nom;
    }

    public int getIdmodeDexercice() {
        return idmodeDexercice;
    }

    public void setIdmodeDexercice(int idmodeDexercice) {
        this.idmodeDexercice = idmodeDexercice;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
