package com.example.elearning.models;

public class Categorie {

    private int  idCategorie;
    private String nom;

    public Categorie(){


    }

    public Categorie(int idCategorie, String nom) {
        this.idCategorie = idCategorie;
        this.nom = nom;
    }

    public void inserer(String n) throws Exception {

        String sql="Insert into Categorie(nom)values('"+n+"')";
        FonctionBase.execute(sql);
    }

    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

}
