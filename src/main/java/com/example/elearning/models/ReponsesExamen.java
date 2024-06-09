package com.example.elearning.models;

import static com.example.elearning.models.FonctionBase.connect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ReponsesExamen {

   private int idReponse;
   private int idQuestion;
   private String Reponse;
   private double note;
   private double typeReponses;

    public double getTypeReponses() {
        return typeReponses;
    }

    public void setTypeReponses(double typeReponses) {
        this.typeReponses = typeReponses;
    }

    public int getIdReponse() {
        return idReponse;
    }

    public void setIdReponse(int idReponse) {
        this.idReponse = idReponse;
    }

    public int getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(int idQuestion) {
        this.idQuestion = idQuestion;
    }

    public String getReponse() {
        return Reponse;
    }

    public void setReponse(String Reponse) {
        this.Reponse = Reponse;
    }

 
    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }

   
    public ReponsesExamen() {

    }

    public ReponsesExamen(int idReponse, int idQuestion, String Reponse, double note) {
        this.idReponse = idReponse;
        this.idQuestion = idQuestion;
        this.Reponse = Reponse;
        this.note = note;
    }

     
    
     public void insertReponsesExamen(int idQuestion, String Reponse, double note,double typeReponses) throws Exception {
    Connection connection = null;
    PreparedStatement statement = null;
    try {
         FonctionBase connect= new FonctionBase();
        connection = connect.connect();
        String query = "insert into ReponsesExamen(idQuestion,Reponse,note,typeReponses) values (?,?,?,?)";
        
        connection.createStatement().execute("SET NAMES 'UTF8'"); // Pour PostgreSQL
        statement = connection.prepareStatement(query);
        statement.setInt(1, idQuestion);
        statement.setString(2, Reponse);
        statement.setDouble(3, note);
        statement.setDouble(4, typeReponses);
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
 
     
     public static ArrayList<ReponsesExamen> allrep(int idQuestion) throws Exception {
    String sql = "select*from ReponsesExamen where idQuestion=" + idQuestion;
    ArrayList<ReponsesExamen> rep = new ArrayList<ReponsesExamen>();
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    try {
        connection = connect();
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            rep.add(new ReponsesExamen(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3), resultSet.getDouble(4)));
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

     public static ReponsesExamen Mareponse(int parseInt) throws Exception {
    ReponsesExamen rep = null;
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    try {
        String sql = "SELECT * FROM ReponsesExamen WHERE idreponse = " + parseInt;

        // Établir une connexion et exécuter la requête
        connection = connect();
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);

        // Récupérer les données
        if (resultSet.next()) {
            rep = new ReponsesExamen(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3), resultSet.getDouble(4));
        }
    } catch (Exception e) {
        e.printStackTrace(); // Gérer les exceptions de manière appropriée
    } finally {
        // Fermer les ressources dans un bloc finally pour s'assurer qu'elles sont libérées
        if (resultSet != null) {
            resultSet.close();
        }
        if (statement != null) {
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }

    return rep;
}
     
        public int nombreAccepte(int idQuestion) throws Exception {
    int count = 0;
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet result = null;

    try {
        FonctionBase connect = new FonctionBase();
        connection = connect.connect();

        String query = "select count(note) as nombreAccepte from reponsesexamen where idquestion=? and  note !=0";
        statement = connection.prepareStatement(query);
        statement.setInt(1, idQuestion);
        result = statement.executeQuery();

        if (result.next()) {
            count = result.getInt("nombreAccepte");
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

public static List<String> reponseLibre(int idExamen) throws Exception {
        List <String> reponses= new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            FonctionBase connect = new FonctionBase();
            connection = connect.connect();

            // Modifiez la requête en fonction des conditions que vous souhaitez appliquer
            String sql ="select reponsLibre from ReponsesApprenant where idexamen=?";
            statement = connection.prepareStatement(sql);
            // Paramètres de condition
            statement.setInt(1, idExamen);
            result = statement.executeQuery();

            while (result.next()) {
            String reponse = result.getString("reponsLibre");
            if (reponse != null && !reponse.trim().isEmpty()) { // Vérifier si la réponse n'est ni null ni vide
                reponses.add(reponse);
            }
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
        return reponses;
    }

     
 }
