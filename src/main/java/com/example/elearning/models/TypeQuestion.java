package com.example.elearning.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TypeQuestion {

 private int idTypeQuestion  ;
 private String nom;

    public int getIdTypeQuestion() {
        return idTypeQuestion;
    }

    public void setIdTypeQuestion(int idTypeQuestion) {
        this.idTypeQuestion = idTypeQuestion;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public TypeQuestion(int idTypeQuestion, String nom) {
        this.idTypeQuestion = idTypeQuestion;
        this.nom = nom;
    }

    public TypeQuestion() {
    }
    
    
    public ArrayList<TypeQuestion> ListeTypeQuestion() throws Exception {
        ArrayList<TypeQuestion> listeDept = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
           FonctionBase connect = new FonctionBase();
            connection = connect.connect();

            // Modifiez la requête en fonction des conditions que vous souhaitez appliquer
            String query = "select * from TypeQuestion";
            statement = connection.prepareStatement(query);
            // Paramètres de condition
            result = statement.executeQuery();

            while (result.next()) {
                TypeQuestion com = new TypeQuestion();
                com.setIdTypeQuestion(result.getInt("idtypequestion"));
                com.setNom(result.getString("nom"));
                
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
