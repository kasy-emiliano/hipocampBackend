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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
@ClassAnotation(table = "ReponseCommentaire")
public class ReponseCommentaire {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Attr(isPrimary = true)
    int idReponse;
    @Attr
    int idCommentaire; 
    @Attr
    int idApprenant;
    @Attr
    String reponsecommentaire;
    Date datereponsecommentaire;
    int idFormateur;

    public int getIdFormateur() {
        return idFormateur;
    }

    public void setIdFormateur(int idFormateur) {
        this.idFormateur = idFormateur;
    }


    public int getIdReponse() {
        return idReponse;
    }

    public void setIdReponse(int idReponse) {
        this.idReponse = idReponse;
    }

    public String getReponsecommentaire() {
        return reponsecommentaire;
    }

    public void setReponsecommentaire(String reponsecommentaire) {
        this.reponsecommentaire = reponsecommentaire;
    }

    public Date getDatereponsecommentaire() {
        return datereponsecommentaire;
    }

    public void setDatereponsecommentaire(Date datereponsecommentaire) {
        this.datereponsecommentaire = datereponsecommentaire;
    }
    
    
    public int getIdCommentaire() {
        return idCommentaire;
    }

    public void setIdCommentaire(int idCommentaire) {
        this.idCommentaire = idCommentaire;
    }
    
   
    public int getIdApprenant() {
        return idApprenant;
    }

    public void setIdApprenant(int idApprenant) {
        this.idApprenant = idApprenant;
    }

    

        public ReponseCommentaire(int idCommentaire, int idApprenant, String reponsecommentaire) {
        this.idCommentaire = idCommentaire;
        this.idApprenant = idApprenant;
        this.reponsecommentaire = reponsecommentaire;
    }

    
    public ReponseCommentaire() {
    }

    public void insertReponseCommentaireApprenant(ReponseCommentaire art) throws Exception {
        GenericDAO.save(new ReponseCommentaire(idCommentaire, idApprenant, reponsecommentaire),  new FonctionBase().connect());
    }
    
    public ArrayList<ReponseCommentaire> ReponseCommentaireApprenant(int idFormation) throws Exception {
        ArrayList<ReponseCommentaire> listeEmp = GenericDAO.findBySql(new ReponseCommentaire(), "select * from FormationCommentaireApprenant where idFormation="+idFormation, new FonctionBase().connect());
        return listeEmp;
    }

    

  
      public void insertReponsesCommentaireFormateur(int idCommentaire, int idFormateur, String reponsecommentaire) throws Exception {
    Connection connection = null;
    PreparedStatement statement = null;
    try {
       FonctionBase connect= new FonctionBase();
        connection = connect.connect();
        String query = "insert into ReponseCommentaire(idCommentaire, idFormateur, reponsecommentaire) values (?, ?, ?)";
       
        statement = connection.prepareStatement(query);
        statement.setInt(1, idCommentaire);
        statement.setInt(2, idFormateur);
        statement.setString(3, reponsecommentaire);
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

    
    
    //  public void insertEmp(Genre emp) throws Exception {
    // GenericDAO.save(new Genre(nom, pseudo), new Econnect().connexion());  
    // }
    //**************************DELETE********************
    public void delete(int id) throws Exception {
        Connection connection = null;
        PreparedStatement stat = null;
        try {
           FonctionBase connect= new FonctionBase();
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
            FonctionBase connect= new FonctionBase();
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
  /*  public Commentaire findById(int id) throws Exception {
        Commentaire info = null;
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            Connexion connect = new Connexion();
            connection = connect.con();
            String query = "SELECT * FROM Commentaire WHERE id = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                info = new Commentaire();
                info.setId(resultSet.getInt("id"));
                info.setNom(resultSet.getString("nom"));
                info.setType(resultSet.getInt("type"));
                info.setCode(resultSet.getString("code"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return info;

        // ...
    }*/

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
