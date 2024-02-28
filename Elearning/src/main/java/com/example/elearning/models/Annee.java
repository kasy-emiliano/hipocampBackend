package com.example.elearning.models;

import java.util.ArrayList;

public class Annee {
    private String annee;
    private ArrayList<Mois>mesMois;

    public String getAnnee() {
        return annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }

    public ArrayList<Mois> getMesMois() {
        return mesMois;
    }

    public void setMesMois(ArrayList<Mois> mesMois) {
        this.mesMois = mesMois;
    }

    public Annee( ){

        mesMois=new ArrayList<Mois>();

        mesMois.add(new Mois(1,"Janvier",0,0.0));
        mesMois.add(new Mois(2,"Fevrier",0,0.0));
        mesMois.add(new Mois(3,"Mars",0,0.0));
        mesMois.add(new Mois(4,"Avril",0,0.0));
        mesMois.add(new Mois(5,"Mai",0,0.0));
        mesMois.add(new Mois(6,"Juin",0,0.0));
        mesMois.add(new Mois(7,"Juillet",0,0.0));
        mesMois.add(new Mois(8,"Aout",0,0.0));
        mesMois.add(new Mois(9,"Septembre",0,0.0));
        mesMois.add(new Mois(10,"Octobre",0,0.0));
        mesMois.add(new Mois(11,"Novembre",0,0.0));
        mesMois.add(new Mois(12,"Decembre",0,0.0));

    }

}
