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
/*@Getter
/*@Setter*/
@ToString
public class MessagePrive {
    private long idMessage;
    private String senderName;
    private String receiverName;
    private String message;
    private Timestamp date;
    private Status status;
    private int idFormateur;
    private int idApprenant; 
    private int type;
    private int vue;
    private String nom_apprenant;
    private String prenom_apprenant;
    private String nom_formateur;
    private String prenom_formateur;
    private String tokenApprenant;
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getVue() {
        return vue;
    }

    public void setVue(int vue) {
        this.vue = vue;
    }

    
    public String getTokenApprenant() {
        return tokenApprenant;
    }

    public void setTokenApprenant(String tokenApprenant) {
        this.tokenApprenant = tokenApprenant;
    }
    

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getNom_apprenant() {
        return nom_apprenant;
    }

    public void setNom_apprenant(String nom_apprenant) {
        this.nom_apprenant = nom_apprenant;
    }

    public String getPrenom_apprenant() {
        return prenom_apprenant;
    }

    public void setPrenom_apprenant(String prenom_apprenant) {
        this.prenom_apprenant = prenom_apprenant;
    }

    public String getNom_formateur() {
        return nom_formateur;
    }

    public void setNom_formateur(String nom_formateur) {
        this.nom_formateur = nom_formateur;
    }

    public String getPrenom_formateur() {
        return prenom_formateur;
    }

    public void setPrenom_formateur(String prenom_formateur) {
        this.prenom_formateur = prenom_formateur;
    }

    
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



   public ArrayList<MessagePrive> MessagePri(int idFormateur) throws Exception {
        ArrayList<MessagePrive> listeDept = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
           FonctionBase connect = new FonctionBase();
            connection = connect.connect();

            // Modifiez la requête en fonction des conditions que vous souhaitez appliquer
            String query = "SELECT idApprenant, nom_apprenant, prenom_apprenant, messages,tokenApprenant,vue,type,date FROM messagePrive WHERE (idApprenant, date) IN (SELECT idApprenant, MAX(date) AS max_date FROM messagePrive WHERE idformateur =? GROUP BY idApprenant)ORDER BY date desc";
            statement = connection.prepareStatement(query);
            // Paramètres de condition
            statement.setInt(1, idFormateur);
            result = statement.executeQuery();

            while (result.next()) {
                MessagePrive com = new MessagePrive();
                //com.setIdFormateur(result.getInt("idformateur"));
                com.setIdApprenant(result.getInt("idapprenant")); 
                com.setVue(result.getInt("vue")); 
                com.setType(result.getInt("type")); 
                //com.setIdMessage(result.getInt("idmessage")); 
                com.setNom_apprenant(result.getString("nom_apprenant")); 
                //com.setNom_formateur(result.getString("nom_formateur")); 
                com.setPrenom_apprenant(result.getString("Prenom_apprenant")); 
                //com.setPrenom_formateur(result.getString("Prenom_formateur")); 
                com.setTokenApprenant(result.getString("tokenapprenant")); 
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
    
      public ArrayList<MessagePrive> MessagePriApp(int idApprenant) throws Exception {
        ArrayList<MessagePrive> listeDept = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
           FonctionBase connect = new FonctionBase();
            connection = connect.connect();

            // Modifiez la requête en fonction des conditions que vous souhaitez appliquer
            String query = "    SELECT idformateur,idApprenant,nom_formateur ,prenom_formateur, messages,token,vue,type,date FROM messagePrive WHERE (idFormateur, date) IN (SELECT idformateur, MAX(date) AS max_date FROM messagePrive WHERE idapprenant =? GROUP BY idformateur)ORDER BY date desc;";
            statement = connection.prepareStatement(query);
            // Paramètres de condition
            statement.setInt(1, idApprenant);
            result = statement.executeQuery();

            while (result.next()) {
                MessagePrive com = new MessagePrive();
                com.setIdFormateur(result.getInt("idformateur"));
                com.setIdApprenant(result.getInt("idapprenant")); 
                com.setVue(result.getInt("vue")); 
                com.setType(result.getInt("type")); 
                //com.setIdMessage(result.getInt("idmessage")); 
                //com.setNom_apprenant(result.getString("nom_apprenant")); 
                com.setNom_formateur(result.getString("nom_formateur")); 
                //com.setPrenom_apprenant(result.getString("Prenom_apprenant")); 
                com.setPrenom_formateur(result.getString("Prenom_formateur")); 
                //com.setTokenApprenant(result.getString("tokenapprenant")); 
                com.setToken(result.getString("token")); 
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
    public static ArrayList<MessagePrive> ListMessagePri(int idFormateur,int idApprenant) throws Exception {
        ArrayList<MessagePrive> listeDept = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
           FonctionBase connect = new FonctionBase();
            connection = connect.connect();

            // Modifiez la requête en fonction des conditions que vous souhaitez appliquer
            String query = "select*from messagePrive where idformateur=? and idapprenant=? order by idmessage asc";
            statement = connection.prepareStatement(query);
            // Paramètres de condition
            statement.setInt(1, idFormateur);
            statement.setInt(2, idApprenant);
            result = statement.executeQuery();

            while (result.next()) {
                MessagePrive com = new MessagePrive();
                com.setIdFormateur(result.getInt("idformateur"));
                com.setIdApprenant(result.getInt("idapprenant")); 
                com.setIdMessage(result.getInt("idmessage")); 
                com.setType(result.getInt("type")); 
                com.setNom_apprenant(result.getString("nom_apprenant")); 
                com.setNom_formateur(result.getString("nom_formateur")); 
                com.setPrenom_apprenant(result.getString("Prenom_apprenant")); 
                com.setPrenom_formateur(result.getString("Prenom_formateur")); 
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
    
    public int countVue(int idFormateur) throws Exception {
    int count = 0;
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet result = null;

    try {
        FonctionBase connect = new FonctionBase();
        connection = connect.connect();

        String query = "SELECT COUNT(*) AS nombre_de_vues " +
                       "FROM (SELECT idApprenant " +
                             "FROM messagePrive " +
                             "WHERE (idApprenant, date) IN (SELECT idApprenant, MAX(date) AS max_date " +
                                                           "FROM messagePrive " +
                                                           "WHERE idformateur = ? " +
                                                           "GROUP BY idApprenant) " +
                             "AND vue = 0 and type=2) AS sous_requete";
        statement = connection.prepareStatement(query);
        statement.setInt(1, idFormateur);
        result = statement.executeQuery();

        if (result.next()) {
            count = result.getInt("nombre_de_vues");
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
    return count;
}

}
/* if(isFormateursend==true){
            statement.setInt(1,idSender);
            statement.setInt(2,idrecever);
        }else{
            statement.setInt(1,idRecever);
            statement.setInt(2,idSender);
            

        }*/
        
             
