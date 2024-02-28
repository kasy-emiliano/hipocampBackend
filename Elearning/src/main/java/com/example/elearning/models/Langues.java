package com.example.elearning.models;

public class Langues {
    private int  idLangues;
    private String nom;

    public int getIdLangues() {
        return idLangues;
    }

    public void setIdLangues(int idLangues) {
        this.idLangues = idLangues;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Langues(int idLangues, String nom) {
        this.idLangues = idLangues;
        this.nom = nom;
    }
}
