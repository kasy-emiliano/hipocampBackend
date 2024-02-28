package com.example.elearning.models;

import java.util.ArrayList;

public class Statistique {

    private String an;
    private int totalnombre;


public Statistique(){

}

    public Statistique(String an, int totalnombre) {
        this.an = an;
        this.totalnombre = totalnombre;
    }

    public String getAn() {
        return an;
    }

    public void setAn(String an) {
        this.an = an;
    }

    public int getTotalnombre() {
        return totalnombre;
    }

    public void setTotalnombre(int totalnombre) {
        this.totalnombre = totalnombre;
    }
}
