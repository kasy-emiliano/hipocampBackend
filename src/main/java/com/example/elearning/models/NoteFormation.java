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
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Cedrick
 */
@Entity
@ClassAnotation(table = "NoteFormation")
public class NoteFormation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Attr(isPrimary = true)
    int idNote;
    @Attr
    int idFormation;
    @Attr
    int idApprenant;
    @Attr
    int note;
    Date dateNote;
    double moyenne;

    public double getMoyenne() {
        return moyenne;
    }

    public void setMoyenne(double moyenne) {
        this.moyenne = moyenne;
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

    public int getIdNote() {
        return idNote;
    }

    public void setIdNote(int idNote) {
        this.idNote = idNote;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public Date getDateNote() {
        return dateNote;
    }

    public void setDateNote(Date dateNote) {
        this.dateNote = dateNote;
    }

    
    public NoteFormation(int idFormation, int idApprenant, int note) {
        this.idFormation = idFormation;
        this.idApprenant = idApprenant;
        this.note = note;
    }

    
    public NoteFormation() {
    }

   


    
    
   
    public ArrayList<NoteFormation> ApprenantNote(int idApprenant,int idFormation) throws Exception {
        ArrayList<NoteFormation> listeEmp = GenericDAO.findBySql(new NoteFormation(), "select * from NoteFormation where idApprenant='"+idApprenant+"' and idFormation="+idFormation,  new FonctionBase().connect());
        return listeEmp;
    }
    
            public double Moyenne(int idFormation) throws Exception {
        double moyenne = 0;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
          FonctionBase connect = new FonctionBase();
            connection = connect.connect();
            String query = "SELECT avg(note) AS moyenne FROM noteformation where idFormation=?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, idFormation);
            result = statement.executeQuery();
            if (result.next()) {
                moyenne = result.getDouble("moyenne");
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
        return moyenne;
    }


  
      public void insertnoteformation(int idFormation, int idApprenant, int note) throws Exception {
    Connection connection = null;
    PreparedStatement statement = null;
    try {
        FonctionBase connect = new FonctionBase();
            connection = connect.connect();
        String query = "insert into noteformation(idFormation, idApprenant, note) values (?, ?, ?)";
        
       
        statement = connection.prepareStatement(query);
        statement.setInt(1, idFormation);
        statement.setInt(2, idApprenant);
        statement.setInt(3, note);
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

   
 public boolean checkIfDejaNoter(int idApprenant,int idformation) throws Exception {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        boolean informationExists = false;
        try {
           FonctionBase connect = new FonctionBase();
            connection = connect.connect();
            String query = "SELECT COUNT(*) FROM NoteFormation WHERE idApprenant=? and idformation=?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, idApprenant);
            statement.setInt(2, idformation);
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
