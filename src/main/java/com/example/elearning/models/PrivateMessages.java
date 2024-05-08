/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.elearning.models;

import java.sql.Connection;
import java.sql.PreparedStatement;


 
public class PrivateMessages {
    private int idFormateur;
    private int idApprenant; 

    public int getIdFormateur() {
        return idFormateur;
    }

    public void setIdFormateur(int idFormateur) {
        this.idFormateur = idFormateur;
    }

    public int getIdApprenant() {
        return idApprenant;
    }

    public void setIdApprenant(int idApprenant) {
        this.idApprenant = idApprenant;
    }

    public PrivateMessages(int idFormateur, int idApprenant) {
        this.idFormateur = idFormateur;
        this.idApprenant = idApprenant;
    }

    public PrivateMessages() {
    }
    
    public void insertMessagePrive(int idFormateur, int idApprenant) throws Exception {
    Connection connection = null;
    PreparedStatement statement = null;
    try {
         FonctionBase connect= new FonctionBase();
        connection = connect.connect();
        String query = "insert into PrivateChats (idFormateur, idApprenant) values (?,?)";
        
        connection.createStatement().execute("SET NAMES 'UTF8'"); // Pour PostgreSQL
        statement = connection.prepareStatement(query);
        statement.setInt(1, idFormateur);
        statement.setInt(2, idApprenant);
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

