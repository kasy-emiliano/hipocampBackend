package com.example.elearning.models;

import static com.example.elearning.models.FonctionBase.connect;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CheckExamen {

    private int idExamen;
    private int idFormation ;
    private int idApprenant ;

    public int getIdApprenant() {
        return idApprenant;
    }

    public void setIdApprenant(int idApprenant) {
        this.idApprenant = idApprenant;
    }

    public CheckExamen(int idExamen, int idFormation, int idApprenant) {
        this.idExamen = idExamen;
        this.idFormation = idFormation;
        this.idApprenant = idApprenant;
    }
    

 

     
    public int getIdExamen() {
        return idExamen;
    }

    public void setIdExamen(int idExamen) {
        this.idExamen = idExamen;
    }

    public int getIdFormation() {
        return idFormation;
    }

    public void setIdFormation(int idFormation) {
        this.idFormation = idFormation;
    }

    

    public CheckExamen() {
    }
 
     
    
          public void insertCheckExamen(int idApprenant ,int idExamen) throws Exception {
    Connection connection = null;
    PreparedStatement statement = null;
    try {
         FonctionBase connect= new FonctionBase();
        connection = connect.connect();
        String query = "insert into ChekExamen(idApprenant,idExamen) values (?,?)";
        
        connection.createStatement().execute("SET NAMES 'UTF8'"); // Pour PostgreSQL
        statement = connection.prepareStatement(query);
        statement.setInt(1, idApprenant);
        statement.setInt(2, idExamen);
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
      
    
          public boolean checkIfDejaFaitExam(int idApprenant ,int idExamen) throws Exception {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        boolean informationExists = false;
        try {
           FonctionBase connect = new FonctionBase();
            connection = connect.connect();
            String query = "SELECT COUNT(*) FROM ChekExamen WHERE idApprenant=? and idExamen=? ";
            statement = connection.prepareStatement(query);
            statement.setInt(1, idApprenant);
            statement.setInt(2, idExamen);
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
 
  

 
}
