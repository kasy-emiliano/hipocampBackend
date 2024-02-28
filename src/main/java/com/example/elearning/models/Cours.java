package com.example.elearning.models;

import java.util.ArrayList;

public class Cours {

    private String suivant;
   private  ArrayList<ContenuSousChapitres>c;

    public Cours(String suivant, ArrayList<ContenuSousChapitres> c) {
        this.suivant = suivant;
        this.c = c;
    }

    public ArrayList<ContenuSousChapitres> getC() {
        return c;
    }

    public void setC(ArrayList<ContenuSousChapitres> c) {
        this.c = c;
    }

    public String getSuivant() {
        return suivant;
    }

    public void setSuivant(String suivant) {
        this.suivant = suivant;
    }

}
