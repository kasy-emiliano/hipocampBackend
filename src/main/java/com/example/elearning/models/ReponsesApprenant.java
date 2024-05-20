package com.example.elearning.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class ReponsesApprenant {
   private int  idReponseApprenant;
   private int idExamen;
   private int idApprenant ;
   private int  idReponse;
   private List<Integer> idreponses;
   private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<Integer> getIdreponses() {
        return idreponses;
    }

    public void setIdreponses(List<Integer> idreponses) {
        this.idreponses = idreponses;
    }

    public int getIdReponseApprenant() {
        return idReponseApprenant;
    }

    public void setIdReponseApprenant(int idReponseApprenant) {
        this.idReponseApprenant = idReponseApprenant;
    }

    public int getIdExamen() {
        return idExamen;
    }

    public void setIdExamen(int idExamen) {
        this.idExamen = idExamen;
    }

    public int getIdApprenant() {
        return idApprenant;
    }

    public void setIdApprenant(int idApprenant) {
        this.idApprenant = idApprenant;
    }
 
    public int getIdReponse() {
        return idReponse;
    }

    public void setIdReponse(int idReponse) {
        this.idReponse = idReponse;
    }

    public ReponsesApprenant(int idReponseApprenant, int idExamen, int idApprenant, int idReponse) {
        this.idReponseApprenant = idReponseApprenant;
        this.idExamen = idExamen;
        this.idApprenant = idApprenant;
        this.idReponse = idReponse;
    }

   
    public ReponsesApprenant() {

    }

    public void insertReponsesApprenant(int idExamen, int idApprenant, int idReponse) throws Exception {
    Connection connection = null;
    PreparedStatement statement = null;
    try {
         FonctionBase connect= new FonctionBase();
        connection = connect.connect();
        String query = "insert into ReponsesApprenant(idExamen,idApprenant,idReponse) values (?,?,?)";
        
        connection.createStatement().execute("SET NAMES 'UTF8'"); // Pour PostgreSQL
        statement = connection.prepareStatement(query);
        statement.setInt(1, idExamen);
        statement.setInt(2, idApprenant);
        statement.setInt(3, idReponse);
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
