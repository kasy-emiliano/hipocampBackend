package com.example.elearning.models;

public class Mois {
    private int chiffre;
    private String nom;
    private int nombre;
    private double pourcent;

    public double getPourcent() {
        return pourcent;
    }

    public void setPourcent(double pourcent) {
        this.pourcent = pourcent;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }

    public Mois(int chiffre,String nom, int nombre,double p) {
       this.chiffre=chiffre;
        this.nom = nom;
        this.nombre = nombre;
        this.pourcent=p;
    }

    public int getChiffre() {
        return chiffre;
    }

    public void setChiffre(int chiffre) {
        this.chiffre = chiffre;
    }
}

