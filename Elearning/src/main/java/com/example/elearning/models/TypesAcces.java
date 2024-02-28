package com.example.elearning.models;

public class TypesAcces {
    private int  idTypesAcces;
    private String nom;

    public int getIdTypesAcces() {
        return idTypesAcces;
    }

    public void setIdTypesAcces(int idTypesAcces) {
        this.idTypesAcces = idTypesAcces;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public TypesAcces(int idTypesAcces, String nom) {
        this.idTypesAcces = idTypesAcces;
        this.nom = nom;
    }



}
