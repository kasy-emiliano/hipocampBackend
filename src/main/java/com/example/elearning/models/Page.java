package com.example.elearning.models;

import static com.example.elearning.models.FonctionBase.connect;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Page {

    private int idPage ;
    private int idFormateur ;
    private String logo;
    private String couleurPrincipale;
    private String couleurArrierePlan ;
    private String CouleurTitre ;
    private String couleurText ;
    private String couleurBouton ;
    private String couleurtextBouton ; 
  
    public int getIdPage() {
        return idPage;
    }

    public void setIdPage(int idPage) {
        this.idPage = idPage;
    }

    public int getIdFormateur() {
        return idFormateur;
    }

    public void setIdFormateur(int idFormateur) {
        this.idFormateur = idFormateur;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getCouleurPrincipale() {
        return couleurPrincipale;
    }

    public void setCouleurPrincipale(String couleurPrincipale) {
        this.couleurPrincipale = couleurPrincipale;
    }

    public String getCouleurArrierePlan() {
        return couleurArrierePlan;
    }

    public void setCouleurArrierePlan(String couleurArrierePlan) {
        this.couleurArrierePlan = couleurArrierePlan;
    }

    public String getCouleurTitre() {
        return CouleurTitre;
    }

    public void setCouleurTitre(String CouleurTitre) {
        this.CouleurTitre = CouleurTitre;
    }

    public String getCouleurText() {
        return couleurText;
    }

    public void setCouleurText(String couleurText) {
        this.couleurText = couleurText;
    }

    public String getCouleurBouton() {
        return couleurBouton;
    }

    public void setCouleurBouton(String couleurBouton) {
        this.couleurBouton = couleurBouton;
    }

    public String getCouleurtextBouton() {
        return couleurtextBouton;
    }

    public void setCouleurtextBouton(String couleurtextBouton) {
        this.couleurtextBouton = couleurtextBouton;
    }

    
    
    public Page() {
    }

    public Page(int idFormateur, String logo, String couleurPrincipale, String couleurArrierePlan, String CouleurTitre, String couleurText, String couleurBouton, String couleurtextBouton) {
        this.idFormateur = idFormateur;
        this.logo = logo;
        this.couleurPrincipale = couleurPrincipale;
        this.couleurArrierePlan = couleurArrierePlan;
        this.CouleurTitre = CouleurTitre;
        this.couleurText = couleurText;
        this.couleurBouton = couleurBouton;
        this.couleurtextBouton = couleurtextBouton;
    }
 
     
    
          public void insertConfigPage(int idFormateur) throws Exception {
    Connection connection = null;
    PreparedStatement statement = null;
    try {
         FonctionBase connect= new FonctionBase();
        connection = connect.connect();
        String query = "insert into Page(idFormateur) values (?)";
        
        connection.createStatement().execute("SET NAMES 'UTF8'"); // Pour PostgreSQL
        statement = connection.prepareStatement(query);
        statement.setInt(1, idFormateur);
 
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
      
    
             public void updateConfigPage(int idFormateur, String logo, String couleurPrincipale, String couleurArrierePlan, String CouleurTitre, String couleurText, String couleurBouton, String couleurtextBouton) throws Exception {
    Connection connection = null;
    PreparedStatement statement = null;

    try {
        FonctionBase connect = new FonctionBase();
        connection = connect.connect();
        
        // Requête paramétrée avec des ?
        String query = "UPDATE page SET logo=?,couleurPrincipale=?,couleurArrierePlan=?,CouleurTitre=?,couleurText=?,couleurBouton=?,couleurtextBouton=? WHERE idformateur=?";
        
        // Création du PreparedStatement
        statement = connection.prepareStatement(query);
        
        // Assignation des valeurs aux paramètres
        statement.setString(1, logo);
        statement.setString(2, couleurPrincipale);
        statement.setString(3, couleurArrierePlan);
        statement.setString(4, CouleurTitre);
        statement.setString(5, couleurText);
        statement.setString(6, couleurBouton);
        statement.setString(7, couleurtextBouton);
        statement.setInt(8, idFormateur);

        
        // Exécution de la mise à jour
        statement.executeUpdate();
    } catch (Exception ex) {
        throw ex;
    } finally {
        if (statement != null) {
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }
}
             
             public boolean checkIIdPage(int idFormateur) throws Exception {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        boolean informationExists = false;
        try {
           FonctionBase connect = new FonctionBase();
            connection = connect.connect();
            String query = "SELECT COUNT(*) FROM page WHERE idFormateur=?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, idFormateur);
            result = statement.executeQuery();
            if (result.next()) {
                int rowCount = result.getInt(1);
                informationExists = (rowCount > 0);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        } finally {

            try {
                if (result != null) {
                    result.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return informationExists;
    }   
             
    
}
