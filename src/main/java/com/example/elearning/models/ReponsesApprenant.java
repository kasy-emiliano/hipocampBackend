package com.example.elearning.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ReponsesApprenant {

    private int idReponseApprenant;
    private int idExamen;
    private int idApprenant;
    private int idQuestion;

    private int idReponse;
    private List<Integer> idreponses;
    private String token;

    private String reponselibre;

    public int getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(int idQuestion) {
        this.idQuestion = idQuestion;
    }

    public String getReponselibre() {
        return reponselibre;
    }

    public void setReponselibre(String reponselibre) {
        this.reponselibre = reponselibre;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<Integer> getIdreponses() {
        return idreponses;
    }

    public void setIdreponses(List<Integer> idreponses) {
        this.idreponses = idreponses;
    }

    public int getIdReponseApprenant() {
        return idReponseApprenant;
    }

    public void setIdReponseApprenant(int idReponseApprenant) {
        this.idReponseApprenant = idReponseApprenant;
    }

    public int getIdExamen() {
        return idExamen;
    }

    public void setIdExamen(int idExamen) {
        this.idExamen = idExamen;
    }

    public int getIdApprenant() {
        return idApprenant;
    }

    public void setIdApprenant(int idApprenant) {
        this.idApprenant = idApprenant;
    }

    public int getIdReponse() {
        return idReponse;
    }

    public void setIdReponse(int idReponse) {
        this.idReponse = idReponse;
    }

    public ReponsesApprenant(int idReponseApprenant, int idExamen, int idApprenant, int idReponse) {
        this.idReponseApprenant = idReponseApprenant;
        this.idExamen = idExamen;
        this.idApprenant = idApprenant;
        this.idReponse = idReponse;
    }

    public ReponsesApprenant() {

    }

    public void insertReponsesApprenant(int idExamen, int idApprenant, int idReponse) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            FonctionBase connect = new FonctionBase();
            connection = connect.connect();
            String query = "insert into ReponsesApprenant(idExamen,idApprenant,idReponse) values (?,?,?)";

            connection.createStatement().execute("SET NAMES 'UTF8'"); // Pour PostgreSQL
            statement = connection.prepareStatement(query);
            statement.setInt(1, idExamen);
            statement.setInt(2, idApprenant);
            statement.setInt(3, idReponse);
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

    public void insertReponseLibre(int idExamen, int idApprenant, int idQuestion, String reponselibre) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            FonctionBase connect = new FonctionBase();
            connection = connect.connect();
            String query = "insert into ReponsesApprenant(idExamen,idApprenant,idQuestion,reponseLibre) values (?,?,?,?)";

            connection.createStatement().execute("SET NAMES 'UTF8'"); // Pour PostgreSQL
            statement = connection.prepareStatement(query);
            statement.setInt(1, idExamen);
            statement.setInt(2, idApprenant);
            statement.setInt(3, idQuestion);
            statement.setString(4, reponselibre);
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

    public static List<String> reponseLibre(int idExamen) throws Exception {
        List<String> reponses = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            FonctionBase connect = new FonctionBase();
            connection = connect.connect();

            // Modifiez la requête en fonction des conditions que vous souhaitez appliquer
            String sql = "select reponsLibre from ReponsesApprenant where idexamen=?";
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

    public void updateReponseLibre(int idquestion, String reponselibre) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            FonctionBase connect = new FonctionBase();
            connection = connect.connect();

            // Requête paramétrée avec des ?
            String query = "update reponsesapprenant set reponselibre= ? where idquestion= ?";

            // Création du PreparedStatement
            statement = connection.prepareStatement(query);

            // Assignation des valeurs aux paramètres
            statement.setString(1, reponselibre);
            statement.setInt(2, idquestion);
            // Exécution de la mise à jour
            statement.executeUpdate();
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

    }
}
