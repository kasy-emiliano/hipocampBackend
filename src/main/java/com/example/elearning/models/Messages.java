/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.elearning.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import java.util.Date;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Messages {
    private long idMessage;
    private String senderName;
    private String receiverName;
    private String message;
    private Timestamp date;
    private Status status;
    private int idFormateur;
    private int idApprenant; 
    private int type;
    
    private String nom_apprenant;
    private String prenom_apprenant;
    private String nom_formateur;
    private String prenom_formateur;

    
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



    public void insertMessage(int idFormateur, int idApprenant, String messages,int Type) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            FonctionBase connect = new FonctionBase();
            connection = connect.connect();
            String query = "insert into messages(idFormateur, idApprenant, messages,type) values (?,?,?,?)";
            connection.createStatement().execute("SET NAMES 'UTF8'"); // Pour PostgreSQL
            statement = connection.prepareStatement(query);

            statement.setInt(1, idFormateur);
            statement.setInt(2, idApprenant);
            statement.setString(3, messages);
            statement.setInt(4, Type);
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
    
    public ArrayList<Messages> ListMessagePri(int idFormateur,int idApprenant) throws Exception {
        ArrayList<Messages> listeDept = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
           FonctionBase connect = new FonctionBase();
            connection = connect.connect();

            // Modifiez la requête en fonction des conditions que vous souhaitez appliquer
            String query = "select*from messages where idformateur=? and idapprenant=? order by idmessage asc";
            statement = connection.prepareStatement(query);
            // Paramètres de condition
            statement.setInt(1, idFormateur);
            statement.setInt(2, idApprenant);
            result = statement.executeQuery();

            while (result.next()) {
                Messages com = new Messages();
                com.setIdFormateur(result.getInt("idformateur"));
                com.setIdApprenant(result.getInt("idapprenant")); 
                com.setType(result.getInt("type")); 
                com.setIdMessage(result.getInt("idmessage")); 
                com.setMessage(result.getString("messages")); 
                com.setDate(result.getTimestamp("date")); 
 
                
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
    
       public void updateVue(int idapprenant) throws Exception {
    Connection connection = null;
    PreparedStatement statement = null;

    try {
        FonctionBase connect = new FonctionBase();
        connection = connect.connect();
        
        // Requête paramétrée avec des ?
        String query = "update messages set vue=1 where idapprenant=?";
        
        // Création du PreparedStatement
        statement = connection.prepareStatement(query);
        
        // Assignation des valeurs aux paramètres
        statement.setInt(1, idapprenant);
        
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
}
/* if(isFormateursend==true){
            statement.setInt(1,idSender);
            statement.setInt(2,idrecever);
        }else{
            statement.setInt(1,idRecever);
            statement.setInt(2,idSender);
            

        }*/
        
             
