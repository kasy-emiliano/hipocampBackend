package com.example.elearning.models;

import static com.example.elearning.models.FonctionBase.connect;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Examens {

    private int idExamen;
    private int idFormation ;
    private int timer;
    private String TitreExamen ;
    private Date DateExamen;

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    
    private ArrayList<QuestionExamen> mesQuestion;

    public ArrayList<QuestionExamen> getMesQuestion() {
        return mesQuestion;
    }

    public void setMesQuestion(ArrayList<QuestionExamen> mesQuestion) {
        this.mesQuestion = mesQuestion;
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

    public String getTitreExamen() {
        return TitreExamen;
    }

    public void setTitreExamen(String TitreExamen) {
        this.TitreExamen = TitreExamen;
    }

    public Date getDateExamen() {
        return DateExamen;
    }

    public void setDateExamen(Date DateExamen) {
        this.DateExamen = DateExamen;
    }

    public Examens() {
    }

    public Examens(int idExamen, int idFormation, String TitreExamen, Date DateExamen) {
        this.idExamen = idExamen;
        this.idFormation = idFormation;
        this.TitreExamen = TitreExamen;
        this.DateExamen = DateExamen;
    }

     
    
          public void insertExamens(int idFormation, String TitreExamen,int timer) throws Exception {
    Connection connection = null;
    PreparedStatement statement = null;
    try {
         FonctionBase connect= new FonctionBase();
        connection = connect.connect();
        String query = "insert into Examens(idFormation, TitreExamen,timer) values (?,?,?)";
        
        connection.createStatement().execute("SET NAMES 'UTF8'"); // Pour PostgreSQL
        statement = connection.prepareStatement(query);
        statement.setInt(1, idFormation);
        statement.setString(2, TitreExamen);
        statement.setInt(3, timer);
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
          
    public ArrayList<Examens> ListeExamens(int idFormation) throws Exception {
        ArrayList<Examens> listeDept = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
           FonctionBase connect = new FonctionBase();
            connection = connect.connect();

            // Modifiez la requête en fonction des conditions que vous souhaitez appliquer
            String query = "select * from Examens where idFormation=?";
            statement = connection.prepareStatement(query);
            // Paramètres de condition
            statement.setInt(1, idFormation);
            result = statement.executeQuery();

            while (result.next()) {
                Examens com = new Examens();
                com.setIdExamen(result.getInt("idexamen"));
                com.setIdFormation(result.getInt("idformation"));
                com.setTitreExamen(result.getString("titreexamen"));
                com.setDateExamen(result.getDate("dateexamen"));
                
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
    
    public static Examens MonExam(int idExamen) throws Exception {
    String sql = "Select * from examens where idExamen=" + idExamen;
    Examens rep = new Examens();
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    try {
        connection = connect();
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            rep = new Examens(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3),resultSet.getDate(4));
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        // Fermeture de la connexion, du statement et du resultSet dans le bloc finally
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    return rep;
}

    public ArrayList<Examens> Timer(int idExamen) throws Exception {
    ArrayList<Examens> listeDept = new ArrayList<>();
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet result = null;
    
    try {
       FonctionBase connect = new FonctionBase();
            connection = connect.connect();
        
        // Modifiez la requête en fonction des conditions que vous souhaitez appliquer
        String query = "select * from Examens where idexamen=? LIMIT 1";
        statement = connection.prepareStatement(query);
        // Paramètres de condition
        statement.setInt(1, idExamen);
        result = statement.executeQuery();
        
        while (result.next()) {
             Examens com = new Examens();            
            com.setTimer(result.getInt("timer"));
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
 
}
