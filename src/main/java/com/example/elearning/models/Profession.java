package com.example.elearning.models;

public class Profession {
    private int idProfession;
    private String nom;

    public int getIdProfession() {
        return idProfession;
    }

    public void setIdProfession(int idProfession) {
        this.idProfession = idProfession;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Profession(int idProfession, String nom) {
        this.idProfession = idProfession;
        this.nom = nom;
    }


}

