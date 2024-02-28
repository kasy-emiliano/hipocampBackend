package com.example.elearning.models;

public class Details {
private String categorie;
private  int nombre;

    public Details(String categorie, int nombre) {
        this.categorie = categorie;
        this.nombre = nombre;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }
}
