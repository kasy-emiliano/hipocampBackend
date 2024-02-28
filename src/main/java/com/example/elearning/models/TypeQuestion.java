package com.example.elearning.models;

public class TypeQuestion {

 private int idTypeQuestion  ;
 private String nom;

    public int getIdTypeQuestion() {
        return idTypeQuestion;
    }

    public void setIdTypeQuestion(int idTypeQuestion) {
        this.idTypeQuestion = idTypeQuestion;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public TypeQuestion(int idTypeQuestion, String nom) {
        this.idTypeQuestion = idTypeQuestion;
        this.nom = nom;
    }

}
