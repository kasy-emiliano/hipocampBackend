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
    Date dateexamen;
    private String phraseCertificat;


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

    public Date getDateexamen() {
        return dateexamen;
    }

    public void setDateexamen(Date dateexamen) {
        this.dateexamen = dateexamen;
    }

    public Certificat(double noteapprenant, int idapprenant, String nom, String prenom, String nomformateur, String prenomformateur, String nomorgannisme, int idexamen, int idformation, String titreformation, Date dateexamen) {
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
        this.dateexamen = dateexamen;
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
            String query = " SELECT SUM(note) AS noteApprenant,idApprenant,nom,prenom,idFormateur,NomFormateur,PrenomFormateur,nomorgannisme,idExamen,idFormation,TitreFormation,DateExamen,phraseCertificat FROM ResultatExamen WHERE idExamen =? AND idApprenant =? GROUP BY idApprenant,nom,prenom,idformateur, NomFormateur,PrenomFormateur,nomorgannisme,idExamen,idFormation,TitreFormation,DateExamen,phraseCertificat HAVING SUM(note) >= (SELECT SUM(note) / 2 AS noteExam FROM totalNoteExam WHERE idExamen =?) ";
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
                com.setDateexamen(result.getDate("dateexamen"));
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
    

}


