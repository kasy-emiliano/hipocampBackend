package com.example.elearning.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Mois {
    private int chiffre;
    private String nom;
    private int nombre;
    private double pourcent;
    private int id;
    private String mois;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMois() {
        return mois;
    }

    public void setMois(String mois) {
        this.mois = mois;
    }

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

    public Mois() {
    }
    
    
    
public ArrayList<Mois> ListeMois() throws Exception {
        ArrayList<Mois> listeDept = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
           FonctionBase connect = new FonctionBase();
            connection = connect.connect();

            // Modifiez la requête en fonction des conditions que vous souhaitez appliquer
            String query = "select * from mois";
            statement = connection.prepareStatement(query);
            // Paramètres de condition
            result = statement.executeQuery();

            while (result.next()) {
                Mois com = new Mois();
                com.setId(result.getInt("id"));
                com.setMois(result.getString("mois"));
                listeDept.add(com);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (result != null) {
                result.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return listeDept;
    }
}

