package com.example.elearning.models;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class Formateur {

    private int idFormateur;
    private String Nom;
    private String Prenom;
    private String email;
    private String mdp;
    private String NomOrgannisme;
    private String ville;
    private int civilite;
    private int Profession;
    private String nomProfession;
    private int modeDexercice;
    private String bio;
    private String numero;
    private Date datenaissance;
    private String facebook;
    private String linkedin;
    private String token;
    private int etatCompte;
    private String pdp;
    private String pdc;
    private String profession_nom;
    private String phraseCertificat;
    private String nomespace;

    private String logo;
    private String couleurPrincipale;
    private String couleurArrierePlan ;
    private String CouleurTitre ;
    private String couleurText ;
    private String couleurBouton ;
    private String couleurtextBouton ; 

    
    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getCouleurPrincipale() {
        return couleurPrincipale;
    }

    public void setCouleurPrincipale(String couleurPrincipale) {
        this.couleurPrincipale = couleurPrincipale;
    }

    public String getCouleurArrierePlan() {
        return couleurArrierePlan;
    }

    public void setCouleurArrierePlan(String couleurArrierePlan) {
        this.couleurArrierePlan = couleurArrierePlan;
    }

    public String getCouleurTitre() {
        return CouleurTitre;
    }

    public void setCouleurTitre(String CouleurTitre) {
        this.CouleurTitre = CouleurTitre;
    }

    public String getCouleurText() {
        return couleurText;
    }

    public void setCouleurText(String couleurText) {
        this.couleurText = couleurText;
    }

    public String getCouleurBouton() {
        return couleurBouton;
    }

    public void setCouleurBouton(String couleurBouton) {
        this.couleurBouton = couleurBouton;
    }

    public String getCouleurtextBouton() {
        return couleurtextBouton;
    }

    public void setCouleurtextBouton(String couleurtextBouton) {
        this.couleurtextBouton = couleurtextBouton;
    }

    
    public String getNomespace() {
        return nomespace;
    }

    public void setNomespace(String nomespace) {
        this.nomespace = nomespace;
    }
    
    public String getPhraseCertificat() {
        return phraseCertificat;
    }

    public void setPhraseCertificat(String phraseCertificat) {
        this.phraseCertificat = phraseCertificat;
    }

    public String getProfession_nom() {
        return profession_nom;
    }

    public void setProfession_nom(String profession_nom) {
        this.profession_nom = profession_nom;
    }

    public String getCivilite_nom() {
        return civilite_nom;
    }

    public void setCivilite_nom(String civilite_nom) {
        this.civilite_nom = civilite_nom;
    }
    private String civilite_nom;

    public String getPdc() {
        return pdc;
    }

    public void setPdc(String pdc) {
        this.pdc = pdc;
    }
    private String dateDajout;

    public String getNomProfession() {
        return nomProfession;
    }

    public void setNomProfession(String nomProfeaaion) {
        this.nomProfession = nomProfeaaion;
    }

    public String getDateDajout() {
        return dateDajout;
    }

    public void setDateDajout(String dateDajout) {
        this.dateDajout = dateDajout;
    }

    public Formateur(int idFormateur, String nom, String prenom, String email, String mdp, String nomOrgannisme, String ville, int civilite, int profession, int modeDexercice, String bio, String numero, Date datenaissance, String facebook, String linkedin, String token, int etatCompte, String pdp, String d) throws Exception {

        this.idFormateur = idFormateur;
        Nom = nom;
        Prenom = prenom;
        this.email = email;
        this.mdp = mdp;
        NomOrgannisme = nomOrgannisme;
        this.ville = ville;
        this.civilite = civilite;
        Profession = profession;
        this.modeDexercice = modeDexercice;
        this.bio = bio;
        this.numero = numero;
        this.datenaissance = datenaissance;
        this.facebook = facebook;
        this.linkedin = linkedin;
        this.token = token;
        this.etatCompte = etatCompte;
        this.pdp = pdp;
        this.dateDajout = d;

        this.nomProfession = FonctionBase.nomProfession(profession);
    }

    public String getPdp() {
        return pdp;
    }

    public void setPdp(String pdp) {
        this.pdp = pdp;
    }

    public Formateur() {

    }

    public int getIdFormateur() {
        return idFormateur;
    }

    public void setIdFormateur(int idFormateur) {
        this.idFormateur = idFormateur;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String prenom) {
        Prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getNomOrgannisme() {
        return NomOrgannisme;
    }

    public void setNomOrgannisme(String nomOrgannisme) {
        NomOrgannisme = nomOrgannisme;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public int getCivilite() {
        return civilite;
    }

    public void setCivilite(int civilite) {
        this.civilite = civilite;
    }

    public int getProfession() {
        return Profession;
    }

    public void setProfession(int profession) {
        Profession = profession;
    }

    public int getModeDexercice() {
        return modeDexercice;
    }

    public void setModeDexercice(int modeDexercice) {
        this.modeDexercice = modeDexercice;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Date getDatenaissance() {
        return datenaissance;
    }

    public void setDatenaissance(Date datenaissance) {
        this.datenaissance = datenaissance;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getEtatCompte() {
        return etatCompte;
    }

    public void setEtatCompte(int etatCompte) {
        this.etatCompte = etatCompte;
    }

    public Formateur(String nom, String prenom, String email, String mdp, String nomOrgannisme, String ville, int civilite, int profession, int modeDexercice, String bio, String numero, Date datenaissance, String facebook, String linkedin, String token, String photo) throws IOException {
        System.out.println("ouou");
        Nom = nom;
        Prenom = prenom;
        this.email = email;
        this.mdp = mdp;
        NomOrgannisme = nomOrgannisme;
        this.ville = ville;
        this.civilite = civilite;
        Profession = profession;
        this.modeDexercice = modeDexercice;
        this.bio = bio;
        this.numero = numero;
        this.datenaissance = datenaissance;
        this.facebook = facebook;
        this.linkedin = linkedin;
        this.token = token;
        this.pdp = photo;
    }

    public void insertOne(String nom, String prenom, String email, String mdp, String nomOrgannisme, String ville, int civilite, int profession, int modeDexercice, String bio, String numero, Date datenaissance, String facebook, String linkedin, String token, String photo) throws Exception {

        // Créer un utilisateur non activé
        this.inserer(nom, prenom, email, mdp, nomOrgannisme, ville, civilite, profession, modeDexercice, bio, numero, datenaissance, facebook, linkedin, token, photo);

        // Envoyer l'e-mail d'activation
        EmailService.sendActivationEmailF(email, token);

    }

    public void inserer(String nom, String prenom, String email, String mdp, String nomOrgannisme, String ville, int civilite, int profession, int modeDexercice, String bio, String numero, Date datenaissance, String facebook, String linkedin, String token, String photo) throws Exception {
        Date currentDate = datenaissance;

        // Créez un objet SimpleDateFormat avec le format de date souhaité
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Par exemple, "AAAA-MM-JJ HH:mm:ss"

        // Utilisez la méthode format pour convertir la date en une chaîne
        String dateStr = dateFormat.format(currentDate);

        // Affichez la chaîne résultante
        System.out.println("Date en format String : " + dateStr);

        String sql = "insert into Formateur (nom,prenom,email,mdp,nomOrgannisme,ville,civilite,profession,modeDexercice,bio,numero,datenaissance,facebook,linkedin,token,etatCompte,pdp)values('" + nom + "','" + prenom + "','" + email + "','" + mdp + "','" + nomOrgannisme + "','" + ville + "','" + civilite + "','" + profession + "','" + modeDexercice + "','" + bio + "','" + numero + "','" + datenaissance + "','" + facebook + "','" + linkedin + "','" + token + "'," + 0 + ",'" + photo + "')";

        FonctionBase.execute(sql);

    }

    public void insererForget(String token) throws Exception {

        Formateur a = FonctionBase.selectWithTokenF(token);

        EmailService.sendForgetEmailF(a.getEmail(), token);
    }

    public void updateMdp(String token, String mdp) throws Exception {

        String sql = "Update Formateur set mdp='" + mdp + "' Where token='" + token + "'";
        FonctionBase.execute(sql);

    }

    public void publier(int idFormation) throws Exception {

        String sql = "Update Formation set etat=1 Where idFormation=" + idFormation;
        FonctionBase.execute(sql);

    }

    public void activation(String token) throws Exception {
        LocalDate date = LocalDate.now();

        // Formater la date en "année-mois-jour"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = date.format(formatter);

        String sql = "update Formateur set etatCompte=1 , dateDajout='" + formattedDate + "' where token='" + token + "'";

        FonctionBase.execute(sql);

    }

    public void modifphoto(String token, String photo) throws Exception {

        String sql = "Update Formateur set Pdp='" + photo + "' Where token='" + token + "'";
        FonctionBase.execute(sql);

    }

    public void modifphotoCouverture(String token, String photo) throws Exception {

        String sql = "Update Formateur set Pdc='" + photo + "' Where token='" + token + "'";
        FonctionBase.execute(sql);

    }

    public void updateFormateur(String nom, String prenom, String nomOrgannisme, String ville, int civilite, int profession, int modeDexercice, String bio, String numero, Date datenaissance, String facebook, String linkedin, String token) throws Exception {
        Date currentDate = datenaissance;

        // Créez un objet SimpleDateFormat avec le format de date souhaité
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Par exemple, "AAAA-MM-JJ HH:mm:ss"

        // Utilisez la méthode format pour convertir la date en une chaîne
        String dateStr = dateFormat.format(currentDate);

        // Affichez la chaîne résultante
        System.out.println("Date en format String : " + dateStr);

        String sql = "update Formateur set Nom='" + nom + "',Prenom='" + prenom + "',Profession=" + profession + ",civilite=" + civilite + ",modeDexercice=" + modeDexercice + ",numero='" + numero + "',datenaissance='" + dateStr + "',bio='" + bio + "',facebook='" + facebook + "',linkedin='" + linkedin + "',ville='" + ville + "', nomorgannisme='" + nomOrgannisme + "' where token= '" + token + "'";

        FonctionBase.execute(sql);

    }

    public Formateur SelectProfilCouverture(String token) throws Exception {
        Formateur info = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
    
        try {
            FonctionBase connect = new FonctionBase();
            connection = connect.connect();
            String query = "Select * from infoformateur where token=? AND etatcompte=1";
            statement = connection.prepareStatement(query);
            statement.setString(1, token);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                info = new Formateur();
                
                info.setIdFormateur(resultSet.getInt("idformateur"));
                info.setNom(resultSet.getString("nom"));
                info.setPrenom(resultSet.getString("Prenom"));
                info.setEmail(resultSet.getString("email"));
                info.setNomOrgannisme(resultSet.getString("NomOrgannisme"));
                info.setVille(resultSet.getString("Ville"));
                info.setCivilite_nom(resultSet.getString("civilite_nom"));
                info.setProfession_nom(resultSet.getString("profession_nom"));
                info.setBio(resultSet.getString("bio"));
                info.setNumero(resultSet.getString("numero"));
                info.setDatenaissance(resultSet.getDate("datenaissance"));
                info.setFacebook(resultSet.getString("facebook"));
                info.setLinkedin(resultSet.getString("linkedin"));
                info.setToken(resultSet.getString("token"));
                info.setPdp(resultSet.getString("pdp"));
                info.setPdc(resultSet.getString("pdc"));
                info.setDateDajout(resultSet.getString("dateDajout"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Fermer la connexion, le statement et le resultSet dans le bloc finally
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
        return info;
    }
    

    public void updatePhrase(int idFormateur, String phrase) throws Exception {
    Connection connection = null;
    PreparedStatement statement = null;

    try {
        FonctionBase connect = new FonctionBase();
        connection = connect.connect();
        
        // Requête paramétrée avec des ?
        String query = "UPDATE formateur SET phrasecertificat=? WHERE idformateur=?";
        
        // Création du PreparedStatement
        statement = connection.prepareStatement(query);
        
        // Assignation des valeurs aux paramètres
        statement.setString(1, phrase);
        statement.setInt(2, idFormateur);
        
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
    
    
    public void updateNomEspace(int idFormateur, String nomespace) throws Exception {
    Connection connection = null;
    PreparedStatement statement = null;

    try {
        FonctionBase connect = new FonctionBase();
        connection = connect.connect();
        
        // Requête paramétrée avec des ?
        String query = "UPDATE formateur SET nomespace=? WHERE idformateur=?";
        
        // Création du PreparedStatement
        statement = connection.prepareStatement(query);
        
        // Assignation des valeurs aux paramètres
        statement.setString(1, nomespace);
        statement.setInt(2, idFormateur);
        
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

    public ArrayList<Formateur> PhraseFormateur(String token) throws Exception {
        ArrayList<Formateur> listeDept = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
           FonctionBase connect = new FonctionBase();
            connection = connect.connect();

            // Modifiez la requête en fonction des conditions que vous souhaitez appliquer
            String query = "select * from formateur where token=? AND etatcompte=1 ";
            statement = connection.prepareStatement(query);
            // Paramètres de condition
            statement.setString(1, token);
            result = statement.executeQuery();

            while (result.next()) {
                Formateur com = new Formateur();
                com.setIdFormateur(result.getInt("idformateur"));
                com.setPhraseCertificat(result.getString("phrasecertificat")); 
                com.setNomOrgannisme(result.getString("nomorgannisme")); 
                
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
    
     public void updateConfigPage(int idFormateur, String logo, String couleurPrincipale, String couleurArrierePlan, String CouleurTitre, String couleurText, String couleurBouton, String couleurtextBouton) throws Exception {
    Connection connection = null;
    PreparedStatement statement = null;

    try {
        FonctionBase connect = new FonctionBase();
        connection = connect.connect();
        
        // Requête paramétrée avec des ?
        String query = "UPDATE formateur SET logo=?,couleurPrincipale=?,couleurArrierePlan=?,CouleurTitre=?,couleurText=?,couleurBouton=?,couleurtextBouton=? WHERE idformateur=?";
        // Création du PreparedStatement
        statement = connection.prepareStatement(query);
        // Assignation des valeurs aux paramètres
        statement.setString(1, logo);
        statement.setString(2, couleurPrincipale);
        statement.setString(3, couleurArrierePlan);
        statement.setString(4, CouleurTitre);
        statement.setString(5, couleurText);
        statement.setString(6, couleurBouton);
        statement.setString(7, couleurtextBouton);
        statement.setInt(8, idFormateur);

        
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
    
         public ArrayList<Formateur> ListePage(int idFormateur) throws Exception {
        ArrayList<Formateur> listeDept = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
           FonctionBase connect = new FonctionBase();
            connection = connect.connect();

            // Modifiez la requête en fonction des conditions que vous souhaitez appliquer
            String query = "select*from formateur where idformateur=?";
            statement = connection.prepareStatement(query);
            // Paramètres de condition
            statement.setInt(1, idFormateur);
            result = statement.executeQuery();

            while (result.next()) {
                Formateur com = new Formateur();
                com.setIdFormateur(result.getInt("idformateur"));
                com.setCouleurPrincipale(result.getString("couleurprincipale")); 
                com.setCouleurArrierePlan(result.getString("couleurarriereplan")); 
                com.setCouleurTitre(result.getString("couleurtitre")); 
                com.setCouleurText(result.getString("couleurtext")); 
                com.setCouleurBouton(result.getString("couleurbouton")); 
                com.setCouleurtextBouton(result.getString("couleurtextbouton")); 
                com.setLogo(result.getString("logo"));  
                com.setNomespace(result.getString("nomespace"));  
                
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
        
        public ArrayList<Formateur> ListePageNom(String nomespace) throws Exception {
        ArrayList<Formateur> listeDept = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
           FonctionBase connect = new FonctionBase();
            connection = connect.connect();

            // Modifiez la requête en fonction des conditions que vous souhaitez appliquer
            String query = "select*from formateur where nomespace=?";
            statement = connection.prepareStatement(query);
            // Paramètres de condition
            statement.setString(1, nomespace);
            result = statement.executeQuery();

            while (result.next()) {
                Formateur com = new Formateur();
                com.setIdFormateur(result.getInt("idformateur"));
                com.setCouleurPrincipale(result.getString("couleurprincipale")); 
                com.setCouleurArrierePlan(result.getString("couleurarriereplan")); 
                com.setCouleurTitre(result.getString("couleurtitre")); 
                com.setCouleurText(result.getString("couleurtext")); 
                com.setCouleurBouton(result.getString("couleurbouton")); 
                com.setCouleurtextBouton(result.getString("couleurtextbouton")); 
                com.setLogo(result.getString("logo")); 
                com.setNomespace(result.getString("nomespace"));  

                
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
    
     
        public static ArrayList<Formation> MesFormationPlusNotee(int idFormateur) throws Exception {
        ArrayList<Formation> listeDept = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            FonctionBase connect = new FonctionBase();
            connection = connect.connect();

            // Modifiez la requête en fonction des conditions que vous souhaitez appliquer
            String sql ="SELECT formation.*, \n" +
"       COALESCE(moyennes.moyenne_note, 0) AS moyenne_note\n" +
"FROM formation\n" +
" \n" +
"LEFT JOIN (\n" +
"    SELECT idformation, AVG(note) AS moyenne_note\n" +
"    FROM noteformation\n" +
"    GROUP BY idformation\n" +
") moyennes ON formation.idformation = moyennes.idformation\n" +
"WHERE idFormateur = ?\n" +
"ORDER BY moyenne_note DESC;";
            statement = connection.prepareStatement(sql);
            // Paramètres de condition
            statement.setInt(1, idFormateur);
            result = statement.executeQuery();

            while (result.next()) {
                Formation com = new Formation();

                com.setIdFormation(result.getInt("idFormation"));
                com.setIdFormateur(result.getInt("idformateur"));
                com.setIdCategorie(result.getInt("idcategorie"));
                com.setTypesAcces(result.getInt("typesacces"));
                com.setLangues(result.getInt("langues"));
                com.setTitre(result.getString("titre"));
                com.setDuree(result.getString("duree"));
                com.setUnite(result.getInt("unite"));
                com.setResumer(result.getString("resumer"));
                com.setToken(result.getString("token"));
                com.setEtat(result.getInt("etat"));
                com.setPrix(result.getString("prix"));
                com.setDateDajout(result.getString("datedajout"));
                com.setDevalidation(result.getString("devalidation"));
                com.setDedemande(result.getString("dedemande")); 
                com.setEtatPublication(result.getInt("etatPublication"));
                com.setMoyenne_note(result.getDouble("moyenne_note"));
            
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

        
                public static ArrayList<Formation> tauxReussiteParFormation(int idFormateur) throws Exception {
        ArrayList<Formation> listeDept = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            FonctionBase connect = new FonctionBase();
            connection = connect.connect();

            // Modifiez la requête en fonction des conditions que vous souhaitez appliquer
            String sql ="WITH totalInscrits AS (\n" +
"    SELECT \n" +
"        i.idformation,\n" +
"        COUNT(DISTINCT i.idApprenant) AS total\n" +
"    FROM \n" +
"        inscritformation i\n" +
"    GROUP BY \n" +
"        i.idformation\n" +
"),\n" +
" \n" +
"examSeuils AS (\n" +
"    SELECT \n" +
"        tnf.idformation,\n" +
"        tnf.idExamen,\n" +
"        SUM(tnf.note) / 2 AS seuil\n" +
"    FROM \n" +
"        totalNoteExamFormation tnf\n" +
"    GROUP BY \n" +
"        tnf.idformation,\n" +
"        tnf.idExamen\n" +
"),\n" +
" \n" +
"examResults AS (\n" +
"    SELECT \n" +
"        re.idformation,\n" +
"        re.idExamen,\n" +
"        re.idApprenant,\n" +
"        SUM(re.note) AS totalNote\n" +
"    FROM \n" +
"        resultatexamen re\n" +
"    GROUP BY \n" +
"        re.idformation,\n" +
"        re.idExamen,\n" +
"        re.idApprenant\n" +
"),\n" +
" \n" +
"totalAdmis AS (\n" +
"    SELECT \n" +
"        re.idformation,\n" +
"        re.idExamen,\n" +
"        COUNT(DISTINCT re.idApprenant) AS total\n" +
"    FROM \n" +
"        examResults re\n" +
"    JOIN examSeuils es ON re.idformation = es.idformation AND re.idExamen = es.idExamen\n" +
"    WHERE \n" +
"        re.totalNote >= es.seuil\n" +
"    GROUP BY \n" +
"        re.idformation,\n" +
"        re.idExamen\n" +
"),\n" +
" \n" +
"totalFailed AS (\n" +
"    SELECT \n" +
"        re.idformation,\n" +
"        re.idExamen,\n" +
"        COUNT(DISTINCT re.idApprenant) AS total\n" +
"    FROM \n" +
"        examResults re\n" +
"    JOIN examSeuils es ON re.idformation = es.idformation AND re.idExamen = es.idExamen\n" +
"    WHERE \n" +
"        re.totalNote < es.seuil\n" +
"    GROUP BY \n" +
"        re.idformation,\n" +
"        re.idExamen\n" +
"),\n" +
" \n" +
"totalNotPassed AS (\n" +
"    SELECT \n" +
"        i.idformation,\n" +
"        es.idExamen,\n" +
"        COUNT(DISTINCT i.idApprenant) AS total\n" +
"    FROM \n" +
"        inscritformation i\n" +
"    LEFT JOIN examResults er ON i.idApprenant = er.idApprenant AND i.idformation = er.idformation\n" +
"    JOIN examSeuils es ON i.idformation = es.idformation\n" +
"    WHERE \n" +
"        er.idApprenant IS NULL\n" +
"    GROUP BY \n" +
"        i.idformation,\n" +
"        es.idExamen\n" +
")\n" +
" \n" +
"SELECT\n" +
"    f.idformation,\n" +
"    f.titre,\n" +
"    es.idExamen,\n" +
"    ti.total AS totalInscrits,\n" +
"    COALESCE(ta.total, 0) AS Admis,\n" +
"    COALESCE(tf.total, 0) + COALESCE(tnp.total, 0) AS NonAdmis,\n" +
"    ROUND((COALESCE(ta.total, 0)::numeric / NULLIF(ti.total, 0)::numeric) * 100, 2) AS tauxreussite,\n" +
"    ROUND(((COALESCE(tf.total, 0) + COALESCE(tnp.total, 0))::numeric / NULLIF(ti.total, 0)::numeric) * 100, 2) AS pourcentageNonAdmis\n" +
"FROM\n" +
"    totalInscrits ti\n" +
"JOIN\n" +
"    examSeuils es ON ti.idformation = es.idformation\n" +
"JOIN\n" +
"    formation f ON f.idformation = ti.idformation\n" +
"LEFT JOIN\n" +
"    totalAdmis ta ON es.idExamen = ta.idExamen AND es.idformation = ta.idformation\n" +
"LEFT JOIN\n" +
"    totalFailed tf ON es.idExamen = tf.idExamen AND es.idformation = tf.idformation\n" +
"LEFT JOIN\n" +
"    totalNotPassed tnp ON es.idExamen = tnp.idExamen AND es.idformation = tnp.idformation\n" +
"WHERE\n" +
"    f.idformateur = ?\n" +
"ORDER BY\n" +
"    f.idformation, es.idExamen;";
            statement = connection.prepareStatement(sql);
            // Paramètres de condition
            statement.setInt(1, idFormateur);
            result = statement.executeQuery();

            while (result.next()) {
                Formation com = new Formation();

                com.setIdFormation(result.getInt("idFormation"));
                com.setTitre(result.getString("titre"));
                com.setTauxreussite(result.getDouble("tauxreussite"));
            
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

   public static ArrayList<Formation> DroitPaye(int idFormateur) throws Exception {
        ArrayList<Formation> listeDept = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            FonctionBase connect = new FonctionBase();
            connection = connect.connect();

            // Modifiez la requête en fonction des conditions que vous souhaitez appliquer
            String sql ="SELECT\n" +
"    f.idformation,\n" +
"    f.titre,\n" +
"    COALESCE(SUM(ifo.droitpaye), 0) AS sommeDroitPaye,\n" +
"    f.depenseformation,\n" +
"    (COALESCE(SUM(ifo.droitpaye), 0)-f.depenseformation) as benefice\n" +
"\n" +
"FROM\n" +
"    formation f\n" +
"LEFT JOIN inscritFormation ifo ON f.idformation = ifo.idformation\n" +
"where idformateur=?\n" +
"GROUP BY f.idformation, f.titre\n" +
"ORDER BY f.idformation;";
            statement = connection.prepareStatement(sql);
            // Paramètres de condition
            statement.setInt(1, idFormateur);
            result = statement.executeQuery();

            while (result.next()) {
                Formation com = new Formation();

                com.setIdFormation(result.getInt("idFormation"));
                com.setTitre(result.getString("titre"));
                com.setSommeDroitPaye(result.getDouble("sommeDroitPaye"));
                com.setDepenseFormation(result.getDouble("depenseFormation"));
                com.setBenefice(result.getDouble("benefice"));
            
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
   
public static double sommetotaledroitpaye(int idFormateur) throws Exception {
        double somme_totalrevenue = 0;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
          FonctionBase connect = new FonctionBase();
            connection = connect.connect();
            String query = "SELECT\n" +
"    SUM(sub.sommeDroitPaye) AS sommeTotaleDroitPaye\n" +
"FROM\n" +
"    (\n" +
"        SELECT\n" +
"            f.idformation,\n" +
"            f.titre,\n" +
"            COALESCE(SUM(ifo.droitpaye), 0) AS sommeDroitPaye\n" +
"        FROM\n" +
"            formation f\n" +
"        LEFT JOIN inscritFormation ifo ON f.idformation = ifo.idformation\n" +
"        WHERE f.idformateur = ?\n" +
"        GROUP BY f.idformation, f.titre\n" +
"    ) AS sub;";
            statement = connection.prepareStatement(query);
            statement.setInt(1, idFormateur);
            result = statement.executeQuery();
            if (result.next()) {
                somme_totalrevenue = result.getDouble("sommetotaledroitpaye");
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

    
public static double sommetotaleDepense(int idFormateur) throws Exception {
        double somme_totalrevenue = 0;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
          FonctionBase connect = new FonctionBase();
            connection = connect.connect();
            String query = "select sum(depenseformation) as sommedepenses from formation where idformateur=?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, idFormateur);
            result = statement.executeQuery();
            if (result.next()) {
                somme_totalrevenue = result.getDouble("sommedepenses");
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


public static double sommetotaleBenefice(int idFormateur) throws Exception {
        double somme_totalrevenue = 0;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
          FonctionBase connect = new FonctionBase();
            connection = connect.connect();
            String query = "SELECT SUM(benefice) AS sommeTotaleBenefice\n" +
"FROM (\n" +
"    SELECT\n" +
"        f.idformation,\n" +
"        f.titre,\n" +
"        COALESCE(SUM(ifo.droitpaye), 0) AS sommeDroitPaye,\n" +
"        f.depenseformation,\n" +
"        (COALESCE(SUM(ifo.droitpaye), 0) - f.depenseformation) as benefice\n" +
"    FROM\n" +
"        formation f\n" +
"    LEFT JOIN inscritFormation ifo ON f.idformation = ifo.idformation\n" +
"    WHERE idformateur=?\n" +
"    GROUP BY f.idformation, f.titre\n" +
"    ORDER BY f.idformation\n" +
") AS subquery;";
            statement = connection.prepareStatement(query);
            statement.setInt(1, idFormateur);
            result = statement.executeQuery();
            if (result.next()) {
                somme_totalrevenue = result.getDouble("sommetotalebenefice");
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
