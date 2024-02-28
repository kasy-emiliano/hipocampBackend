package com.example.elearning.models;

import java.util.ArrayList;

public class Moi {

    private ArrayList<mouvementChapitres>m;
    private Formation f;

    public Moi(ArrayList<mouvementChapitres> m, Formation f) {
        this.m = m;
        this.f = f;
    }

    public ArrayList<mouvementChapitres> getM() {
        return m;
    }

    public void setM(ArrayList<mouvementChapitres> m) {
        this.m = m;
    }

    public Formation getF() {
        return f;
    }

    public void setF(Formation f) {
        this.f = f;
    }
}
