/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.elearning.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

import java.util.Date;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Messages {
    private String senderName;
    private String receiverName;
    private String message;
    private String date;
    private Status status;
}


/*    private long idMessage;
    private int idFormateur;
    private int idApprenant;   
    private String message;
    private Timestamp date;
    private Status status;

    
    public long getIdMessage() {
        return idMessage;
    }

    public void setIdMessage(long idMessage) {
        this.idMessage = idMessage;
    }

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }



    public void insertMessage(int idFormateur, int idApprenant, String messages) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            FonctionBase connect = new FonctionBase();
            connection = connect.connect();
            String query = "insert into messages(idFormateur, idApprenant, messages) values (?,?,?)";
            connection.createStatement().execute("SET NAMES 'UTF8'"); // Pour PostgreSQL
            statement = connection.prepareStatement(query);

            statement.setInt(1, idFormateur);
            statement.setInt(2, idApprenant);
            statement.setString(3, messages);
            int rowsAffected = statement.executeUpdate();
        if (rowsAffected == 0) {
            // Gérer le cas où aucune ligne n'a été affectée (aucune insertion n'a probablement eu lieu)
            System.err.println("Aucune ligne affectée lors de l'insertion du message !");
        }
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
/* if(isFormateursend==true){
            statement.setInt(1,idSender);
            statement.setInt(2,idrecever);
        }else{
            statement.setInt(1,idRecever);
            statement.setInt(2,idSender);
            

        }*/
        
             
