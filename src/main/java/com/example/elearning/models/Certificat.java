package com.example.elearning.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class Certificat {

    double noteapprenant;
    int idapprenant;
    String nom;
    String prenom;
    String nomformateur;
    String prenomformateur;
    String nomorgannisme;
    int idexamen;
    int idformation;
    int idformateur;
    String titreformation;
    Date datedebutexamen;
    private String phraseCertificat;
    double pourcentageAdmis;
    double pourcentageNonAdmis;
    double totalinscrits;
    double admis;
    double nonadmis;
 

    public double getTotalinscrits() {
        return totalinscrits;
    }

    public void setTotalinscrits(double totalinscrits) {
        this.totalinscrits = totalinscrits;
    }

    public double getAdmis() {
        return admis;
    }

    public void setAdmis(double admis) {
        this.admis = admis;
    }

    public double getNonadmis() {
        return nonadmis;
    }

    public void setNonadmis(double nonadmis) {
        this.nonadmis = nonadmis;
    }

    public double getPourcentageAdmis() {
        return pourcentageAdmis;
    }

    public void setPourcentageAdmis(double pourcentageAdmis) {
        this.pourcentageAdmis = pourcentageAdmis;
    }

    public double getPourcentageNonAdmis() {
        return pourcentageNonAdmis;
    }

    public void setPourcentageNonAdmis(double pourcentageNonAdmis) {
        this.pourcentageNonAdmis = pourcentageNonAdmis;
    }

 

    public String getPhraseCertificat() {
        return phraseCertificat;
    }

    public void setPhraseCertificat(String phraseCertificat) {
        this.phraseCertificat = phraseCertificat;
    }
    
    public int getIdformateur() {
        return idformateur;
    }

    public void setIdformateur(int idformateur) {
        this.idformateur = idformateur;
    }

    
    public double getNoteapprenant() {
        return noteapprenant;
    }

    public void setNoteapprenant(double noteapprenant) {
        this.noteapprenant = noteapprenant;
    }

    public int getIdapprenant() {
        return idapprenant;
    }

    public void setIdapprenant(int idapprenant) {
        this.idapprenant = idapprenant;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNomformateur() {
        return nomformateur;
    }

    public void setNomformateur(String nomformateur) {
        this.nomformateur = nomformateur;
    }

    public String getPrenomformateur() {
        return prenomformateur;
    }

    public void setPrenomformateur(String prenomformateur) {
        this.prenomformateur = prenomformateur;
    }

    public String getNomorgannisme() {
        return nomorgannisme;
    }

    public void setNomorgannisme(String nomorgannisme) {
        this.nomorgannisme = nomorgannisme;
    }

    public int getIdexamen() {
        return idexamen;
    }

    public void setIdexamen(int idexamen) {
        this.idexamen = idexamen;
    }

    public int getIdformation() {
        return idformation;
    }

    public void setIdformation(int idformation) {
        this.idformation = idformation;
    }

    public String getTitreformation() {
        return titreformation;
    }

    public void setTitreformation(String titreformation) {
        this.titreformation = titreformation;
    }

    public Date getDatedebutexamen() {
        return datedebutexamen;
    }

    public void setDatedebutexamen(Date datedebutexamen) {
        this.datedebutexamen = datedebutexamen;
    }

    public Certificat(double noteapprenant, int idapprenant, String nom, String prenom, String nomformateur, String prenomformateur, String nomorgannisme, int idexamen, int idformation, String titreformation, Date datedebutexamen) {
        this.noteapprenant = noteapprenant;
        this.idapprenant = idapprenant;
        this.nom = nom;
        this.prenom = prenom;
        this.nomformateur = nomformateur;
        this.prenomformateur = prenomformateur;
        this.nomorgannisme = nomorgannisme;
        this.idexamen = idexamen;
        this.idformation = idformation;
        this.titreformation = titreformation;
        this.datedebutexamen = datedebutexamen;
    }

    public Certificat() {
    }
    
    public ArrayList<Certificat> CertificatAdmis(int idExamen,int idApprenant) throws Exception {
        ArrayList<Certificat> listeDept = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
           FonctionBase connect = new FonctionBase();
            connection = connect.connect();

            // Modifiez la requête en fonction des conditions que vous souhaitez appliquer
            String query = " SELECT SUM(note) AS noteApprenant,idApprenant,nom,prenom,idFormateur,NomFormateur,PrenomFormateur,nomorgannisme,idExamen,idFormation,TitreFormation,datedebutexamen,phraseCertificat FROM ResultatExamen WHERE idExamen =? AND idApprenant =? GROUP BY idApprenant,nom,prenom,idformateur, NomFormateur,PrenomFormateur,nomorgannisme,idExamen,idFormation,TitreFormation,datedebutexamen,phraseCertificat HAVING SUM(note) >= (SELECT SUM(note) / 2 AS noteExam FROM totalNoteExam WHERE idExamen =?) ";
            statement = connection.prepareStatement(query);
            // Paramètres de condition
            statement.setInt(1, idExamen);
            statement.setInt(2, idApprenant);
            statement.setInt(3, idExamen);
            result = statement.executeQuery();

            while (result.next()) {
                Certificat com = new Certificat();
                com.setIdexamen(result.getInt("idexamen"));
                com.setIdapprenant(result.getInt("idapprenant"));
                com.setIdformation(result.getInt("idformation"));
                com.setIdformateur(result.getInt("idformateur"));
                com.setTitreformation(result.getString("titreformation"));
                com.setNom(result.getString("nom"));
                com.setPrenom(result.getString("prenom"));
                com.setPrenomformateur(result.getString("prenomformateur"));
                com.setNomorgannisme(result.getString("nomorgannisme"));
                com.setNomformateur(result.getString("nomformateur"));
                com.setNoteapprenant(result.getDouble("noteapprenant"));
                com.setDatedebutexamen(result.getDate("datedebutexamen"));
                com.setPhraseCertificat(result.getString("phrasecertificat")); 

                
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
    
    public ArrayList<Certificat> ListAdmis(int idFormation,int idExamen ) throws Exception {
        ArrayList<Certificat> listeDept = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
           FonctionBase connect = new FonctionBase();
            connection = connect.connect();

            // Modifiez la requête en fonction des conditions que vous souhaitez appliquer
            String query = " SELECT SUM(note) AS noteApprenant,idApprenant,nom,prenom,idFormateur,\n" +
"    NomFormateur,PrenomFormateur,nomorgannisme,idExamen,idFormation,TitreFormation,datedebutexamen,phraseCertificat FROM ResultatExamen WHERE idformation = ? GROUP BY idApprenant, nom, prenom, idformateur, NomFormateur, PrenomFormateur, nomorgannisme, idExamen, idFormation, TitreFormation, datedebutexamen, phraseCertificat HAVING SUM(note) >= (SELECT SUM(note) / 2 FROM totalNoteExam WHERE idExamen = ?) ORDER BY noteApprenant DESC";
            statement = connection.prepareStatement(query);
            // Paramètres de condition
            statement.setInt(1, idFormation);
            statement.setInt(2, idExamen);
            result = statement.executeQuery();

            while (result.next()) {
                Certificat com = new Certificat();
                com.setIdexamen(result.getInt("idexamen"));
                com.setIdapprenant(result.getInt("idapprenant"));
                com.setIdformation(result.getInt("idformation"));
                com.setIdformateur(result.getInt("idformateur"));
                com.setTitreformation(result.getString("titreformation"));
                com.setNom(result.getString("nom"));
                com.setPrenom(result.getString("prenom"));
                com.setPrenomformateur(result.getString("prenomformateur"));
                com.setNomorgannisme(result.getString("nomorgannisme"));
                com.setNomformateur(result.getString("nomformateur"));
                com.setNoteapprenant(result.getDouble("noteapprenant"));
                com.setDatedebutexamen(result.getDate("datedebutexamen"));

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
    
    public ArrayList<Certificat> note(int idExamen) throws Exception {
        ArrayList<Certificat> listeDept = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
           FonctionBase connect = new FonctionBase();
            connection = connect.connect();

            // Modifiez la requête en fonction des conditions que vous souhaitez appliquer
            String query = " select sum(note) as note from reponsesexamen join questionexamen on reponsesexamen.idquestion=questionexamen.idquestion where idExamen=? ";
            statement = connection.prepareStatement(query);
            // Paramètres de condition
            statement.setInt(1, idExamen);
            result = statement.executeQuery();

            while (result.next()) {
                Certificat com = new Certificat();
                com.setNoteapprenant(result.getDouble("note"));
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
    
        public int noteTotal(int idExamen) throws Exception {
    int note = 0;
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet result = null;

    try {
        FonctionBase connect = new FonctionBase();
        connection = connect.connect();

        String query = "select sum(note) as note from reponsesexamen join questionexamen on reponsesexamen.idquestion=questionexamen.idquestion where idExamen=?";
        statement = connection.prepareStatement(query);
        statement.setInt(1, idExamen);
        result = statement.executeQuery();

        if (result.next()) {
            
            note = result.getInt("note");
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
    return note;
}

    
    public ArrayList<Certificat> pourcentage(int idFormation, int idExamen) throws Exception {
           ArrayList<Certificat> listeDept = new ArrayList<>();

    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet result = null;

    try {
        FonctionBase connect = new FonctionBase();
        connection = connect.connect();

        String query = "WITH totalInscrits AS (\n" +
"    SELECT COUNT(*) as total\n" +
"    FROM inscritformation\n" +
"    WHERE idformation = ?\n" +
"),\n" +
"totalAdmis AS (\n" +
"    SELECT COUNT(*) as total\n" +
"    FROM (\n" +
"        SELECT idApprenant\n" +
"        FROM resultatexamen\n" +
"        WHERE idformation = ?\n" +
"        GROUP BY idApprenant\n" +
"        HAVING SUM(note) >= (SELECT SUM(note) / 2 FROM totalNoteExam WHERE idExamen = ?)\n" +
"    ) as admis\n" +
"),\n" +
"totalNonAdmis AS (\n" +
"    SELECT (ti.total - ta.total) as total\n" +
"    FROM totalInscrits ti, totalAdmis ta\n" +
")\n" +
"SELECT\n" +
"    ti.total as totalInscrits,\n" +
"    ta.total as Admis,\n" +
"    tn.total as NonAdmis,\n" +
"    (ta.total::float / ti.total::float) * 100 AS pourcentageAdmis,\n" +
"    (tn.total::float / ti.total::float) * 100 AS pourcentageNonAdmis\n" +
"FROM\n" +
"    totalInscrits ti, totalAdmis ta, totalNonAdmis tn;";
        
        statement = connection.prepareStatement(query);
        
        statement.setInt(1, idFormation);
        statement.setInt(2, idFormation);
        statement.setInt(3, idExamen);

        result = statement.executeQuery();

        if (result.next()) {
           Certificat com = new Certificat();
           com.setPourcentageAdmis(result.getDouble("pourcentageadmis"));
           com.setPourcentageNonAdmis(result.getDouble("pourcentagenonadmis"));
           com.setTotalinscrits(result.getDouble("totalinscrits"));
           com.setAdmis(result.getDouble("admis"));
           com.setNonadmis(result.getDouble("nonadmis"));
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


