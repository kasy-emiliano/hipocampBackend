/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.elearning.models;



import com.example.elearning.generic.Attr;
import com.example.elearning.generic.ClassAnotation;
import com.example.elearning.generic.GenericDAO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import lombok.*;

/**
 *
 * @author Cedrick
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 *
 */
/**
 *
 *
 */
@Entity
@ClassAnotation(table = "Commentaire")
public class Commentaire {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Attr(isPrimary = true)
    int idCommentaire;
    @Attr
    int idFormation;
    @Attr
    int idApprenant;
    @Attr
    String commentaire;
    Date dateCommentaire;
    int idFormateur;
    Status status;

    public int getIdFormateur() {
        return idFormateur;
    }

    public void setIdFormateur(int idFormateur) {
        this.idFormateur = idFormateur;
    }
    

    public Date getDateCommentaire() {
        return dateCommentaire;
    }

    public void setDateCommentaire(Date dateCommentaire) {
        this.dateCommentaire = dateCommentaire;
    }
    
    public Commentaire(int idFormation, int idApprenant, String commentaire) {
        this.idFormation = idFormation;
        this.idApprenant = idApprenant;
        this.commentaire = commentaire;
    }

    
    
    
    public int getIdCommentaire() {
        return idCommentaire;
    }

    public void setIdCommentaire(int idCommentaire) {
        this.idCommentaire = idCommentaire;
    }
    
    public int getIdFormation() {
        return idFormation;
    }

    public void setIdFormation(int idFormation) {
        this.idFormation = idFormation;
    }

    public int getIdApprenant() {
        return idApprenant;
    }

    public void setIdApprenant(int idApprenant) {
        this.idApprenant = idApprenant;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }


    
    
    public Commentaire() {
    }

 
    
    public ArrayList<Commentaire> ListeCommentaire(int idCommentaire) throws Exception {
        ArrayList<Commentaire> listeEmp = GenericDAO.findBySql(new Commentaire(), "select * from Commentaire where idCommentaire="+idCommentaire, new FonctionBase().connect());
        return listeEmp;
    }
    

    public void insertCommentaire1(Commentaire art) throws Exception {
        GenericDAO.save(new Commentaire(idFormation, idApprenant, commentaire), new FonctionBase().connect());
    }

  
      public void insertCommentaireFormateur(int idFormation, int idApprenant, String commentaire) throws Exception {
    Connection connection = null;
    PreparedStatement statement = null;
    try {
         FonctionBase connect= new FonctionBase();
        connection = connect.connect();
        String query = "insert into commentaire(idFormation, idApprenant, commentaire) values (?, ?, ?)";
        
        connection.createStatement().execute("SET NAMES 'UTF8'"); // Pour PostgreSQL
        statement = connection.prepareStatement(query);
        statement.setInt(1, idFormation);
        statement.setInt(2, idApprenant);
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
      
      public Commentaire findById(int idCommentaire) throws Exception {
        Commentaire info = null;
        Connection connection = null;
        PreparedStatement statement = null;

        try {
           FonctionBase connect= new FonctionBase();
        connection = connect.connect();
            String query = "SELECT * FROM Commentaire WHERE idCommentaire = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, idCommentaire);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                info = new Commentaire();
                info.setIdCommentaire(resultSet.getInt("idcommentaire"));
                info.setIdFormation(resultSet.getInt("idformation"));
                info.setIdApprenant(resultSet.getInt("idapprenant"));
                info.setIdFormateur(resultSet.getInt("idformateur"));
                info.setCommentaire(resultSet.getString("commentaire"));
                info.setDateCommentaire(resultSet.getDate("DateCommentaire"));
                
                

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return info;

        // ...
    }

     

    
    
    //  public void insertEmp(Genre emp) throws Exception {
    // GenericDAO.save(new Genre(nom, pseudo), new Econnect().connexion());  
    // }
    //**************************DELETE********************
    public void delete(int id) throws Exception {
        Connection connection = null;
        PreparedStatement stat = null;
        try {
            FonctionBase connect = new FonctionBase();
            connection = connect.connect();
            String query = "delete from Commentaire where id= ?";
            stat = connection.prepareStatement(query);
            stat.setObject(1, id);
            stat.executeUpdate();
        } catch (Exception ex) {
            throw ex;
        } finally {
            stat.close();
            connection.close();
        }
    }

    public int findDCodeDisp(String code) throws Exception {
        int listeBandCommande = 0;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            FonctionBase connect = new FonctionBase();
            connection = connect.connect();
            statement = connection.prepareStatement("select id from Commentaire where code=?");
            statement.setString(1, code);
            result = statement.executeQuery();
            while (result.next()) {
               listeBandCommande=result.getInt("id");
               
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
        return listeBandCommande;
    }

    //*********************UPDATE*****************
    /*public void update(int id, String titre, String body, String hoto) throws Exception {
        Connection connection = null;
        Statement stat = null;
        try {
            Connexion connect = new Connexion();
            connection = connect.con();
            String query = "update information set titre='" + titre + "',body= '" + body + "',photo='" + photo + "' where id=" + id;
            stat = connection.createStatement();
            stat.executeUpdate(query);
        } catch (Exception ex) {
            throw ex;
        } finally {
            stat.close();
            connection.close();
        }
    }*/
//***************** detail*************
   
    //findByIdTab
    /*public ArrayList<Commentaire> getActe() throws Exception {
        ArrayList<Commentaire> listeDept = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            Connexion connect = new Connexion();
            connection = connect.con();
            String query = "Select * from Acte";
            statement = connection.prepareStatement(query);
         
            result = statement.executeQuery();
            while (result.next()) {
                Commentaire bandL = new Commentaire();
                bandL.setId(result.getInt("id"));
                bandL.setNom(result.getString("nom"));
             

                listeDept.add(bandL);
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
    }*/
//*************SELECT All*************
}
