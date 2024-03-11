package com.example.elearning.models;
 
import com.example.elearning.generic.Attr;
import com.example.elearning.generic.ClassAnotation;
import com.example.elearning.generic.GenericDAO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.example.elearning.generic.ClassAnotation;
import java.sql.Timestamp;
import javax.persistence.Entity;

@Entity
@ClassAnotation(table = "Publicite")
public class Publicite {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Attr(isPrimary = true)
    private int idPublicite;
    @Attr
    private String NomOrganisme;
    @Attr
    private String sary;
    @Attr
    private String lien;
    @Attr
    private String email;
    @Attr
    private String contact;
    @Attr
    private Timestamp dateDebut;
    @Attr
    private Timestamp dateFin;
    @Attr
    private int duree;
    @Attr
    private String titre;
    @Attr
    private double montantParJours;
    @Attr
    private String resumer;
    
    private int status;
    
    private String message;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public int getIdPublicite() {
        return idPublicite;
    }

    public void setIdPublicite(int idPublicite) {
        this.idPublicite = idPublicite;
    }

    public String getNomOrganisme() {
        return NomOrganisme;
    }

    public void setNomOrganisme(String NomOrganisme) {
        this.NomOrganisme = NomOrganisme;
    }

    public String getSary() {
        return sary;
    }

    public void setSary(String sary) {
        this.sary = sary;
    }

    public String getLien() {
        return lien;
    }

    public void setLien(String lien) {
        this.lien = lien;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public double getMontantParJours() {
        return montantParJours;
    }

    public void setMontantParJours(double montantParJours) {
        this.montantParJours = montantParJours;
    }

    public String getResumer() {
        return resumer;
    }

    public void setResumer(String resumer) {
        this.resumer = resumer;
    }

    public Timestamp getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Timestamp dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Timestamp getDateFin() {
        return dateFin;
    }

    public void setDateFin(Timestamp dateFin) {
        this.dateFin = dateFin;
    }

    
    public Publicite() {
    }

    public Publicite(String NomOrganisme, String sary, String lien, String email, String contact, Timestamp dateDebut, Timestamp dateFin, int duree, String titre, double montantParJours, String resumer) {
        this.NomOrganisme = NomOrganisme;
        this.sary = sary;
        this.lien = lien;
        this.email = email;
        this.contact = contact;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.duree = duree;
        this.titre = titre;
        this.montantParJours = montantParJours;
        this.resumer = resumer;
    }

    

     
    
    public ArrayList<Publicite> listePublicitePrensent() throws Exception {
        ArrayList<Publicite> listeEmp = GenericDAO.findBySql(new Publicite(), "select * from Publicite where datefin >=now()", new FonctionBase().connect());
        return listeEmp;
    }
    
          public void insertPublicite2(String NomOrganisme, String sary, String lien, String email, String contact, Timestamp dateDebut, Timestamp dateFin, int duree, String titre, double montantParJours, String resumer) throws Exception {
    Connection connection = null;
    PreparedStatement statement = null;
    try {
        FonctionBase connect= new FonctionBase();
        connection = connect.connect();
        String query = "insert into Publicite(NomOrganisme,sary, lien, email, contact, dateDebut, dateFin, duree, titre, montantParJours, resumer) values (?,?, ?,?,?,?,?,?,?,?,?)";
        
       
        statement = connection.prepareStatement(query);
        statement.setString(1, NomOrganisme);
        statement.setString(2, sary);
        statement.setString(3, lien);
        statement.setString(4, email);
        statement.setString(5, contact);
        statement.setTimestamp(6, dateDebut);
        statement.setTimestamp(7, dateFin);
        statement.setInt(8, duree);
        statement.setString(9, titre);
        statement.setDouble(10, montantParJours);
        statement.setString(11, resumer);
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
    

}
