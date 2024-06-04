package com.example.elearning.models;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Langues {
    private int  idLangues;
    private String nom;

    public int getIdLangues() {
        return idLangues;
    }

    public void setIdLangues(int idLangues) {
        this.idLangues = idLangues;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Langues(int idLangues, String nom) {
        this.idLangues = idLangues;
        this.nom = nom;
    }

    public Langues() {
    }
    
    
    public void insertLangue(String nom) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            FonctionBase connect = new FonctionBase();
            connection = connect.connect();
            String query = "insert into langues(nom) values (?)";

            statement = connection.prepareStatement(query);
            statement.setString(1, nom);
  
            statement.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

}
