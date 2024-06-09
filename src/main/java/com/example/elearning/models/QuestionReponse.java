package com.example.elearning.models;

import static com.example.elearning.models.FonctionBase.connect;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestionReponse {

    private int question_id;
    private String question_text ;
    private int examen_id ;
    private int response_id;
    private String response_text;
    private double response_note;
       private double typeReponses;

    public double getTypeReponses() {
        return typeReponses;
    }

    public void setTypeReponses(double typeReponses) {
        this.typeReponses = typeReponses;
    }

     private List<ReponsesExamen> responses; // Liste des réponses associées à la question
    public List<ReponsesExamen> getResponses() {
        return responses;
    }

    public void setResponses(List<ReponsesExamen> responses) {
        this.responses = responses;
    }

    public QuestionReponse() {
        this.responses = new ArrayList<>(); // Initialisez la liste des réponses pour éviter les NullPointerException
    }
    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public String getQuestion_text() {
        return question_text;
    }

    public void setQuestion_text(String question_text) {
        this.question_text = question_text;
    }

    public int getExamen_id() {
        return examen_id;
    }

    public void setExamen_id(int examen_id) {
        this.examen_id = examen_id;
    }

    public int getResponse_id() {
        return response_id;
    }

    public void setResponse_id(int response_id) {
        this.response_id = response_id;
    }

    public String getResponse_text() {
        return response_text;
    }

    public void setResponse_text(String response_text) {
        this.response_text = response_text;
    }

    public double getResponse_note() {
        return response_note;
    }

    public void setResponse_note(double response_note) {
        this.response_note = response_note;
    }
 

    public QuestionReponse(int question_id, String question_text, int examen_id, int response_id, String response_text, double response_note) {
        this.question_id = question_id;
        this.question_text = question_text;
        this.examen_id = examen_id;
        this.response_id = response_id;
        this.response_text = response_text;
        this.response_note = response_note;
    }



     
          
    public ArrayList<QuestionReponse> ListeQuestionReponses(int examen_id) throws Exception {
        ArrayList<QuestionReponse> listeDept = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
           FonctionBase connect = new FonctionBase();
            connection = connect.connect();

            // Modifiez la requête en fonction des conditions que vous souhaitez appliquer
            String query = "select*from QuestionReponse where idexamen = ?";
 statement = connection.prepareStatement(query);
            // Paramètres de condition
            statement.setInt(1, examen_id);
            result = statement.executeQuery();

        Map<Integer, QuestionReponse> questionMap = new HashMap<>();

        while (result.next()) {
            int questionId = result.getInt("idQuestion");
            QuestionReponse question = questionMap.get(questionId);
            if (question == null) {
                question = new QuestionReponse();
                question.setExamen_id(examen_id);
                question.setQuestion_id(questionId);
                question.setQuestion_text(result.getString("question"));
                question.setResponses(new ArrayList<>());
                questionMap.put(questionId, question);
                listeDept.add(question);
            }

                ReponsesExamen response = new ReponsesExamen();
            response.setIdReponse(result.getInt("idResponse"));
            response.setReponse(result.getString("response"));
            response.setNote(result.getDouble("note"));
            response.setTypeReponses(result.getDouble("typeReponses"));
            question.getResponses().add(response);
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
