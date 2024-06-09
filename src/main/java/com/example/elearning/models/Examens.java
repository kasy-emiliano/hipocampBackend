package com.example.elearning.models;

import static com.example.elearning.models.FonctionBase.connect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Examens {

    private int idExamen;
    private int idFormation;
    private String TitreExamen;
    private Timestamp DateDebutExamen;
    private Timestamp DateFinExamen;
    private String etat;
    private String etatExamen;
    private String voirResultat;

    public String getVoirResultat() {
        return voirResultat;
    }

    public void setVoirResultat(String voirResultat) {
        this.voirResultat = voirResultat;
    }

    public String getEtatExamen() {
        return etatExamen;
    }

    public void setEtatExamen(String etatExamen) {
        this.etatExamen = etatExamen;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Timestamp getDateDebutExamen() {
        return DateDebutExamen;
    }

    public void setDateDebutExamen(Timestamp DateDebutExamen) {
        this.DateDebutExamen = DateDebutExamen;
    }

    public Timestamp getDateFinExamen() {
        return DateFinExamen;
    }

    public void setDateFinExamen(Timestamp DateFinExamen) {
        this.DateFinExamen = DateFinExamen;
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

    public Examens() {
    }

    public Examens(int idFormation, String TitreExamen, Timestamp DateDebutExamen, Timestamp DateFinExamen) {
        this.idFormation = idFormation;
        this.TitreExamen = TitreExamen;
        this.DateDebutExamen = DateDebutExamen;
        this.DateFinExamen = DateFinExamen;
    }

    public void insertExamens(int idFormation, String TitreExamen, Timestamp DateDebutExamen, Timestamp DateFinExamen) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            FonctionBase connect = new FonctionBase();
            connection = connect.connect();
            String query = "insert into Examens(idFormation, TitreExamen,DateDebutExamen,DateFinExamen) values (?,?,?,?)";

            connection.createStatement().execute("SET NAMES 'UTF8'"); // Pour PostgreSQL
            statement = connection.prepareStatement(query);
            statement.setInt(1, idFormation);
            statement.setString(2, TitreExamen);
            statement.setTimestamp(3, DateDebutExamen);
            statement.setTimestamp(4, DateFinExamen);
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
        ArrayList<Examens> listeExamens = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            FonctionBase connect = new FonctionBase();
            connection = connect.connect();

            String query = "SELECT * FROM Examens WHERE idFormation=?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, idFormation);
            result = statement.executeQuery();

            Timestamp now = new Timestamp(System.currentTimeMillis());

            while (result.next()) {
                Examens examen = new Examens();
                examen.setIdExamen(result.getInt("idexamen"));
                examen.setIdFormation(result.getInt("idformation"));
                examen.setTitreExamen(result.getString("titreexamen"));
                examen.setDateDebutExamen(result.getTimestamp("datedebutexamen"));
                examen.setDateFinExamen(result.getTimestamp("datefinexamen"));

                Timestamp dateDebut = examen.getDateDebutExamen();
                Timestamp dateFin = examen.getDateFinExamen();

                if (now.before(dateDebut)) {
                    // Si l'heure actuelle est avant la date de début de l'examen
                    Date dateDebutAsDate = new Date(dateDebut.getTime());

    // Créer un SimpleDateFormat pour formatter la date
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy ' à ' HH'h'mm", Locale.FRENCH);

    // Formater la date
    String formattedDate = dateFormat.format(dateDebutAsDate);

    // Définir l'état de l'examen
    examen.setEtat("Examen le " + formattedDate);
                } else if (now.after(dateFin)) {
                    // Si l'heure actuelle est après la date de fin de l'examen
                    examen.setEtat("Examen terminé");
                    examen.setEtatExamen(examen.getTitreExamen());
                    examen.setVoirResultat("Voir resultat");

                } else {
                    // Si l'heure actuelle est entre la date de début et la date de fin de l'examen
                    examen.setEtat("Examen en cours");
                    examen.setEtatExamen(examen.getTitreExamen());
                    examen.setVoirResultat("Voir resultat");
                }

                listeExamens.add(examen);
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
        return listeExamens;
    }

    public ArrayList<Examens> ListeExamensDispo(int idFormation) throws Exception {
        ArrayList<Examens> listeDept = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            FonctionBase connect = new FonctionBase();
            connection = connect.connect();

            // Modifiez la requête en fonction des conditions que vous souhaitez appliquer
            String query = "select * from Examens where datedebutexamen <= now() and idFormation=?";
            statement = connection.prepareStatement(query);
            // Paramètres de condition
            statement.setInt(1, idFormation);
            result = statement.executeQuery();

            while (result.next()) {
                Examens com = new Examens();
                com.setIdExamen(result.getInt("idexamen"));
                com.setIdFormation(result.getInt("idformation"));
                com.setTitreExamen(result.getString("titreexamen"));
                com.setDateDebutExamen(result.getTimestamp("DateDebutExamen"));
                com.setDateFinExamen(result.getTimestamp("DateFinExamen"));

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
             if (resultSet.next()) { // Changement de while en if car un seul résultat est attendu
            rep = new Examens(resultSet.getInt("idExamen"), 
                                  resultSet.getString("titreexamen"),
                                  resultSet.getTimestamp("datedebutexamen"), 
                                  resultSet.getTimestamp("datefinexamen"));
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
                // com.setTimer(result.getInt("timer"));
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
    
    public boolean isExamenDejaTermine(int idExamen) throws Exception {
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet result = null;
    boolean examenDejaTermine = false;
    
    try {
        FonctionBase connect = new FonctionBase();
        connection = connect.connect();

        String query = "SELECT datefinexamen FROM Examens WHERE idExamen=?";
        statement = connection.prepareStatement(query);
        statement.setInt(1, idExamen);
        result = statement.executeQuery();
        
        if (result.next()) {
            Timestamp dateFinExamen = result.getTimestamp("datefinexamen");
            Timestamp now = new Timestamp(System.currentTimeMillis());
            
            if (now.after(dateFinExamen)) {
                examenDejaTermine = true;
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
    
    return examenDejaTermine;
}
     
     public Duration getExamTimeDifference(int idExamen) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            FonctionBase connect = new FonctionBase();
            connection = connect.connect();

            String query = "SELECT datefinexamen " +
                           "FROM examens " +
                           "WHERE idexamen = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, idExamen);
            result = statement.executeQuery();

            if (result.next()) {
                LocalDateTime datefinexamen = result.getTimestamp("datefinexamen").toLocalDateTime();
                LocalDateTime now = LocalDateTime.now();

                return Duration.between(now, datefinexamen);
            } else {
                throw new Exception("Examen not found with id: " + idExamen);
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
    }
     

}
