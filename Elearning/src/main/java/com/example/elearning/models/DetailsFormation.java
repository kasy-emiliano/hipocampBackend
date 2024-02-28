package com.example.elearning.models;

import java.util.ArrayList;

public class DetailsFormation {
    private ArrayList<Categorie> allCategorie;
    private ArrayList<Unite> allUnite;
    private ArrayList<TypesAcces> allTypesAcces;
    private ArrayList<Langues> allLangues;

    public ArrayList<Categorie> getAllCategorie() {
        return allCategorie;
    }

    public void setAllCategorie(ArrayList<Categorie> allCategorie) {
        this.allCategorie = allCategorie;
    }

    public ArrayList<Unite> getAllUnite() {
        return allUnite;
    }

    public void setAllUnite(ArrayList<Unite> allUnite) {
        this.allUnite = allUnite;
    }

    public ArrayList<TypesAcces> getAllTypesAcces() {
        return allTypesAcces;
    }

    public void setAllTypesAcces(ArrayList<TypesAcces> allTypesAcces) {
        this.allTypesAcces = allTypesAcces;
    }

    public ArrayList<Langues> getAllLangues() {
        return allLangues;
    }

    public void setAllLangues(ArrayList<Langues> allLangues) {
        this.allLangues = allLangues;
    }
}
