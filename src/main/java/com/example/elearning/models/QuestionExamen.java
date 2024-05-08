package com.example.elearning.models;

import static com.example.elearning.models.FonctionBase.connect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class QuestionExamen {

    private int idQuestion;
    private int idExamen;
    private int idTypeQuestion;
    private String Question;

    private int marina;
    private ArrayList<ReponsesExamen> mesReponses;

    public ArrayList<ReponsesExamen> getMesReponses() {
        return mesReponses;
    }

    public void setMesReponses(ArrayList<ReponsesExamen> mesReponses) {
        this.mesReponses = mesReponses;
    }

    public int getMarina() {
        return marina;
    }

    public void setMarina(int marina) {
        this.marina = marina;
    }

    public int getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(int idQuestion) {
        this.idQuestion = idQuestion;
    }

    public int getIdExamen() {
        return idExamen;
    }

    public void setIdExamen(int idExamen) {
        this.idExamen = idExamen;
    }

    public int getIdTypeQuestion() {
        return idTypeQuestion;
    }

    public void setIdTypeQuestion(int idTypeQuestion) {
        this.idTypeQuestion = idTypeQuestion;
    }

    public QuestionExamen(int idQuestion, int idExamen, int idTypeQuestion, String Question) throws Exception {
        this.idQuestion = idQuestion;
        this.idExamen = idExamen;
         Question = Question;
        
        mesReponses=ReponsesExamen.allrep(idQuestion);

        marina=0;

        for (int i=0;i<mesReponses.size();i++){


            if(mesReponses.get(i).getNote()!=0.0){

                marina++;

            }
        }
 
    }

     

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String Question) {
        this.Question = Question;
    }

    public QuestionExamen() {
    }

    public void insertQuestionExamen(int idExamen, int idTypeQuestion, String Question) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            FonctionBase connect = new FonctionBase();
            connection = connect.connect();
            String query = "insert into QuestionExamen(idExamen, idTypeQuestion,Question) values (?,?,?)";

            connection.createStatement().execute("SET NAMES 'UTF8'"); // Pour PostgreSQL
            statement = connection.prepareStatement(query);
            statement.setInt(1, idExamen);
            statement.setInt(2, idTypeQuestion);
            statement.setString(3, Question);
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

    public static int maderniereq(int idExamen) throws Exception {
        String sql = "Select * from QuestionExamen where idexamen =" + idExamen + " order by idquestion";
        int rep = 0;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = connect();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                rep = resultSet.getInt(1);
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
    
    public static ArrayList<QuestionExamen> mesQ(int idExamen) throws Exception {
    String sql = "Select * from QuestionExamen where idExamen =" + idExamen;
    ArrayList<QuestionExamen> rep = new ArrayList<QuestionExamen>();
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    try {
        connection = connect();
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            rep.add(new QuestionExamen(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getString(4)));
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

 
}
