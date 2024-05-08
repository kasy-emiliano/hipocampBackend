/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.elearning.models;


import java.sql.Connection;
import java.sql.PreparedStatement;
import lombok.*;

import java.util.Date;


public class MessageToken {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    private int idRecever;
    private boolean isFormateurSend;
    private String message;
    private Date date;
    private Status status;

    public int getIdRecever() {
        return idRecever;
    }

    public void setIdRecever(int idRecever) {
        this.idRecever = idRecever;
    }

    public boolean isIsFormateurSend() {
        return isFormateurSend;
    }

    public void setIsFormateurSend(boolean isFormateurSend) {
        this.isFormateurSend = isFormateurSend;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

         public void insertCommentaireFormateur(int idFormation, int idFormateur, String commentaire) throws Exception {
    Connection connection = null;
    PreparedStatement statement = null;
    try {
         FonctionBase connect= new FonctionBase();
        connection = connect.connect();
        String query = "insert into messages(idFormateur, idApprenant, isFormateurSend, messages, date) values (?, ?, ?)";
        
        connection.createStatement().execute("SET NAMES 'UTF8'"); // Pour PostgreSQL
        statement = connection.prepareStatement(query);
        /* if(isFormateursend==true){
            //insert into message(idformateur, idApprenant,...) values (?, ?, ?)
            statement.setInt(1,idSender);
            statement.setInt(2,idrecever);
        }else{
            statement.setInt(1,idRecever);
            statement.setInt(2,idSender);
            

        }
        
        */
        statement.setInt(1, idFormation);
        statement.setInt(2, idFormateur);
        statement.setString(3, commentaire);
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

