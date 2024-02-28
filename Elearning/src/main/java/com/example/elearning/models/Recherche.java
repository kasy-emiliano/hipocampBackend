package com.example.elearning.models;

import java.util.ArrayList;

public class Recherche {

    private ArrayList<Formation> recherche;
    private DetailsFormation F;

    public Recherche(){

    }

    public Recherche(ArrayList<Formation> recherche, DetailsFormation f) {
        this.recherche = recherche;
        F = f;
    }

    public ArrayList<Formation> getRecherche() {
        return recherche;
    }

    public void setRecherche(ArrayList<Formation> recherche) {
        this.recherche = recherche;
    }

    public DetailsFormation getF() {
        return F;
    }

    public void setF(DetailsFormation f) {
        F = f;
    }
}
