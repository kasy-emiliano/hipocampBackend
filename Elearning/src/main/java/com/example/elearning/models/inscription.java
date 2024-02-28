package com.example.elearning.models;

import java.util.ArrayList;

public class inscription {
private ArrayList<civilite> allcivilite;
private ArrayList<Profession> allprofession;
private ArrayList<modeDexercice> allmodeDexercice;

    public ArrayList<civilite> getAllcivilite() {
        return allcivilite;
    }

    public void setAllcivilite(ArrayList<civilite> allcivilite) {
        this.allcivilite = allcivilite;
    }

    public ArrayList<Profession> getAllprofession() {
        return allprofession;
    }

    public void setAllprofession(ArrayList<Profession> allprofession) {
        this.allprofession = allprofession;
    }

    public ArrayList<modeDexercice> getAllmodeDexercice() {
        return allmodeDexercice;
    }

    public void setAllmodeDexercice(ArrayList<modeDexercice> allmodeDexercice) {
        this.allmodeDexercice = allmodeDexercice;
    }
}
