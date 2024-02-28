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
@ClassAnotation(table = "FormationCommentaire")
public class FormationCommentaire {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Attr(isPrimary = true)
    int idCommentaire;
    @Attr
    int idFormation;
    @Attr
    int idApprenant;
    @Attr
    int idFormateur;
    @Attr
    String commentaire;
    @Attr
    Date datecommentaire;
    @Attr
    String nomFormateur;
    @Attr
    String prenomFormateur;
    @Attr
    String nomApprenant;
    @Attr
    String prenomApprenant;

    public FormationCommentaire(int idCommentaire, int idFormation, int idApprenant, int idFormateur, String commentaire, Date datecommentaire, String nomFormateur, String prenomFormateur, String nomApprenant, String prenomApprenant) {
        this.idCommentaire = idCommentaire;
        this.idFormation = idFormation;
        this.idApprenant = idApprenant;
        this.idFormateur = idFormateur;
        this.commentaire = commentaire;
        this.datecommentaire = datecommentaire;
        this.nomFormateur = nomFormateur;
        this.prenomFormateur = prenomFormateur;
        this.nomApprenant = nomApprenant;
        this.prenomApprenant = prenomApprenant;
    }

    public int getIdFormateur() {
        return idFormateur;
    }

    public void setIdFormateur(int idFormateur) {
        this.idFormateur = idFormateur;
    }

   

    public String getNomFormateur() {
        return nomFormateur;
    }

    public void setNomFormateur(String nomFormateur) {
        this.nomFormateur = nomFormateur;
    }

    public String getPrenomFormateur() {
        return prenomFormateur;
    }

    public void setPrenomFormateur(String prenomFormateur) {
        this.prenomFormateur = prenomFormateur;
    }

    public String getNomApprenant() {
        return nomApprenant;
    }

    public void setNomApprenant(String nomApprenant) {
        this.nomApprenant = nomApprenant;
    }

    public String getPrenomApprenant() {
        return prenomApprenant;
    }

    public void setPrenomApprenant(String prenomApprenant) {
        this.prenomApprenant = prenomApprenant;
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

    public Date getDatecommentaire() {
        return datecommentaire;
    }

    public void setDatecommentaire(Date datecommentaire) {
        this.datecommentaire = datecommentaire;
    }

   

    
    
    public FormationCommentaire() {
    }

    
   public ArrayList<FormationCommentaire> FormationCommentaire(int idFormation) throws Exception {
    ArrayList<FormationCommentaire> listeDept = new ArrayList<>();
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet result = null;
    
    try {
       FonctionBase connect = new FonctionBase();
            connection = connect.connect();
        
        // Modifiez la requête en fonction des conditions que vous souhaitez appliquer
        String query = "select * from FormationCommentaire where idFormation=?  ORDER BY idcommentaire asc ";
        statement = connection.prepareStatement(query);
        // Paramètres de condition
        statement.setInt(1, idFormation);
        result = statement.executeQuery();
        
        while (result.next()) {
             FormationCommentaire com = new FormationCommentaire();            
            com.setCommentaire(result.getString("commentaire"));
            com.setDatecommentaire(result.getDate("datecommentaire"));
            com.setNomApprenant(result.getString("NomApprenant"));
            com.setNomFormateur(result.getString("nomformateur"));
            com.setPrenomApprenant(result.getString("PrenomApprenant"));
            com.setPrenomFormateur(result.getString("PrenomFormateur"));
            com.setIdApprenant(result.getInt("IdApprenant"));
            com.setIdCommentaire(result.getInt("IdCommentaire"));
            com.setIdFormateur(result.getInt("IdFormateur"));
            com.setIdFormation(result.getInt("IdFormation"));
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

   public ArrayList<FormationCommentaire> ListeCommentaire(int idCommentaire) throws Exception {
    ArrayList<FormationCommentaire> listeDept = new ArrayList<>();
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet result = null;
    
    try {
       FonctionBase connect = new FonctionBase();
            connection = connect.connect();
        
        // Modifiez la requête en fonction des conditions que vous souhaitez appliquer
        String query = "select * from FormationCommentaire where idCommentaire=?  ORDER BY idcommentaire asc ";
        statement = connection.prepareStatement(query);
        // Paramètres de condition
        statement.setInt(1, idCommentaire);
        result = statement.executeQuery();
        
        while (result.next()) {
             FormationCommentaire com = new FormationCommentaire();            
            com.setCommentaire(result.getString("commentaire"));
            com.setDatecommentaire(result.getDate("datecommentaire"));
            com.setNomApprenant(result.getString("NomApprenant"));
            com.setNomFormateur(result.getString("nomformateur"));
            com.setPrenomApprenant(result.getString("PrenomApprenant"));
            com.setPrenomFormateur(result.getString("PrenomFormateur"));
            com.setIdApprenant(result.getInt("IdApprenant"));
            com.setIdCommentaire(result.getInt("IdCommentaire"));
            com.setIdFormateur(result.getInt("IdFormateur"));
            com.setIdFormation(result.getInt("IdFormation"));
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
    
   /* public ArrayList<FormationCommentaireApprenant> FormationCommentaireApprenat(int idFormation) throws Exception {
        ArrayList<FormationCommentaireApprenant> listeEmp = GenericDAO.findBySql(new FormationCommentaireApprenant(), "select * from FormationCommentaireApprenant where idFormation=" + idFormation, new Econnect().connexion());
        return listeEmp;
    }

    public ArrayList<FormationCommentaireApprenant> findCommentaireInviduel() throws Exception {
        ArrayList<FormationCommentaireApprenant> listeEmp = GenericDAO.findBySql(new FormationCommentaireApprenant(), "select * from Commentaire where type=1", new Econnect().connexion());
        return listeEmp;
    }

    public ArrayList<FormationCommentaireApprenant> findCommentaireCollectif() throws Exception {
        ArrayList<FormationCommentaireApprenant> listeEmp = GenericDAO.findBySql(new FormationCommentaireApprenant(), "select * from Commentaire where type=2", new Econnect().connexion());
        return listeEmp;
    }*/

    /* public void insertCommentaire(FormationCommentaireApprenant art) throws Exception {
        GenericDAO.save(new FormationCommentaireApprenant(idFormation, idApprenant, commentaire), new Econnect().connexion());
    }*/
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
                listeBandCommande = result.getInt("id");

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
