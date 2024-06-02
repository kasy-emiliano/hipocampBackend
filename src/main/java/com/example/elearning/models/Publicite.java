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
    private String titre;
    @Attr
    private String resumer;
    
    private double jourxmontant;
    private int idmois;
    private int annee;
    private String nom_mois;
    private double totalrevenue;
    private double somme_totalrevenue;

    public double getSomme_totalrevenue() {
        return somme_totalrevenue;
    }

    public void setSomme_totalrevenue(double somme_totalrevenue) {
        this.somme_totalrevenue = somme_totalrevenue;
    }

    public double getTotalrevenue() {
        return totalrevenue;
    }

    public void setTotalrevenue(double totalrevenue) {
        this.totalrevenue = totalrevenue;
    }
    
    public double getJourxmontant() {
        return jourxmontant;
    }

    public void setJourxmontant(double jourxmontant) {
        this.jourxmontant = jourxmontant;
    }


    public int getIdmois() {
        return idmois;
    }

    public void setIdmois(int idmois) {
        this.idmois = idmois;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public String getNom_mois() {
        return nom_mois;
    }

    public void setNom_mois(String nom_mois) {
        this.nom_mois = nom_mois;
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

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
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

    public Publicite(String NomOrganisme, String sary, String lien, String email, String contact, Timestamp dateDebut, Timestamp dateFin, String titre, String resumer) {
        this.NomOrganisme = NomOrganisme;
        this.sary = sary;
        this.lien = lien;
        this.email = email;
        this.contact = contact;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.titre = titre;
        this.resumer = resumer;
    }

    public ArrayList<Publicite> listePublicitePrensent() throws Exception {
        ArrayList<Publicite> listeEmp = GenericDAO.findBySql(new Publicite(), "select * from Publicite where datefin >=now()", new FonctionBase().connect());
        return listeEmp;
    }

    public void insertPublicite2(String NomOrganisme, String sary, String lien, String email, String contact, Timestamp dateDebut, Timestamp dateFin, String titre, String resumer) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            FonctionBase connect = new FonctionBase();
            connection = connect.connect();
            String query = "insert into Publicite(NomOrganisme,sary, lien, email, contact, dateDebut, dateFin, titre, resumer) values (?,?,?,?,?,?,?,?,?)";

            statement = connection.prepareStatement(query);
            statement.setString(1, NomOrganisme);
            statement.setString(2, sary);
            statement.setString(3, lien);
            statement.setString(4, email);
            statement.setString(5, contact);
            statement.setTimestamp(6, dateDebut);
            statement.setTimestamp(7, dateFin);
            statement.setString(8, titre);
            statement.setString(9, resumer);
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

    public ArrayList<Publicite> TableauBordPub(int idmois, int annee) throws Exception {
        ArrayList<Publicite> listeDept = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            FonctionBase connect = new FonctionBase();
            connection = connect.connect();

            // Modifiez la requête en fonction des conditions que vous souhaitez appliquer
            String query = "select*from tableaubord where idmois=? and annee=?";
            statement = connection.prepareStatement(query);
            // Paramètres de condition
            statement.setInt(1, idmois);
            statement.setInt(2, annee);
            result = statement.executeQuery();

            while (result.next()) {
                Publicite com = new Publicite();
                com.setIdPublicite(result.getInt("idpublicite"));
                com.setNomOrganisme(result.getString("nomorganisme"));
                com.setTitre(result.getString("titre"));
                com.setDateDebut(result.getTimestamp("datedebut"));
                com.setJourxmontant(result.getDouble("jourxmontant"));
                com.setIdmois(result.getInt("idmois"));
                com.setAnnee(result.getInt("annee"));
                com.setNom_mois(result.getString("nom_mois"));

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
    
    public double Totalrevenue(int idmois, int annee) throws Exception {
        double Totalrevenue = 0;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
          FonctionBase connect = new FonctionBase();
            connection = connect.connect();
            String query = "SELECT\n" +
"    ma.mois AS nom_mois,\n" +
"    COALESCE(SUM(tb.jourxmontant), 0) AS totalrevenue\n" +
"FROM\n" +
"    moisAnnee ma\n" +
"LEFT JOIN\n" +
"    tableaubord tb\n" +
"ON\n" +
"    ma.annee = tb.annee AND\n" +
"    ma.id = tb.mois\n" +
"WHERE\n" +
"    ma.id=? and ma.annee = ?\n" +
"GROUP BY\n" +
"    ma.mois,\n" +
"    ma.id\n" +
"ORDER BY ma.id;";
            statement = connection.prepareStatement(query);
            statement.setInt(1, idmois);
            statement.setInt(2, annee);
            result = statement.executeQuery();
            if (result.next()) {
                Totalrevenue = result.getDouble("totalrevenue");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return Totalrevenue;
    }

    
    public static ArrayList<Publicite> GraphParMois(int annee) throws Exception {
        ArrayList<Publicite> listeDept = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            FonctionBase connect = new FonctionBase();
            connection = connect.connect();

            // Modifiez la requête en fonction des conditions que vous souhaitez appliquer
            String sql =" SELECT\n" +
"    ma.mois AS nom_mois,\n" +
"    COALESCE(SUM(tb.jourxmontant), 0) AS totalrevenue\n" +
"FROM\n" +
"    moisAnnee ma\n" +
"LEFT JOIN\n" +
"    tableaubord tb\n" +
"ON\n" +
"    ma.annee = tb.annee AND\n" +
"    ma.id = tb.mois\n" +
"WHERE\n" +
"    ma.annee = ?\n" +
"GROUP BY\n" +
"    ma.mois,\n" +
"    ma.id\n" +
"ORDER BY ma.id; ";
            statement = connection.prepareStatement(sql);
            // Paramètres de condition
            statement.setInt(1, annee);
            result = statement.executeQuery();

            while (result.next()) {
                Publicite com = new Publicite();

                com.setNom_mois(result.getString("nom_mois"));
                com.setTotalrevenue(result.getDouble("totalrevenue"));
            
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

    public double somme_totalrevenue(int annee) throws Exception {
        double somme_totalrevenue = 0;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
          FonctionBase connect = new FonctionBase();
            connection = connect.connect();
            String query = "SELECT\n" +
"    SUM(totalrevenue) AS somme_totalrevenue\n" +
"FROM\n" +
"    (SELECT\n" +
"        ma.mois AS nom_mois,\n" +
"        COALESCE(SUM(tb.jourxmontant), 0) AS totalrevenue\n" +
"    FROM\n" +
"        moisAnnee ma\n" +
"    LEFT JOIN\n" +
"        tableaubord tb\n" +
"    ON\n" +
"        ma.annee = tb.annee AND\n" +
"        ma.id = tb.mois\n" +
"    WHERE\n" +
"        ma.annee =?\n" +
"    GROUP BY\n" +
"        ma.mois, ma.id\n" +
"    ORDER BY ma.id) AS revenus;";
            statement = connection.prepareStatement(query);
            statement.setInt(1, annee);
            result = statement.executeQuery();
            if (result.next()) {
                somme_totalrevenue = result.getDouble("somme_totalrevenue");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return somme_totalrevenue;
    }
}
