package com.example.elearning.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class Apprenant {
private int idApprenant;
private String Nom;
private String Prenom ;
private String email;
private String mdp ;
private int Profession;
private String nomProfession;
private int civilite;
private String nomCivilite;
private int modeDexercice;
private String nomModeDexercice;
private String numero;
private Date datenaissance;
private String token;
private int etatCompte;
private Date ajoutCours;
private String dateDajout;
private  long progression=0;

    public long getProgression() {
        return progression;
    }

    public void setProgression(long progression) {
        this.progression = progression;
    }

    public String getDateDajout() {
        return dateDajout;
    }

    public void setDateDajout(String dateDajout) {
        this.dateDajout = dateDajout;
    }

    public Date getAjoutCours() {
        return ajoutCours;
    }

    public void setAjoutCours(Date ajoutCours) {
        this.ajoutCours = ajoutCours;
    }

    public Apprenant(int idApprenant, String nom, String prenom, String email, String mdp, int profession, String nomProfession, int civilite, String nomCivilite, int modeDexercice, String nomModeDexercice, String numero, Date datenaissance, String token, int etatCompte, Date ajoutCours,String d) {
        this.idApprenant = idApprenant;
        Nom = nom;
        Prenom = prenom;
        this.email = email;
        this.mdp = mdp;
        Profession = profession;
        this.nomProfession = nomProfession;
        this.civilite = civilite;
        this.nomCivilite = nomCivilite;
        this.modeDexercice = modeDexercice;
        this.nomModeDexercice = nomModeDexercice;
        this.numero = numero;
        this.datenaissance = datenaissance;
        this.token = token;
        this.etatCompte = etatCompte;
        this.ajoutCours = ajoutCours;
        this.dateDajout=d;
    }

    public Apprenant(int idApprenant, String nom, String prenom, String email, String mdp, int profession, int civilite, int modeDexercice, String numero, Date datenaissance, String token, int etatCompte,String d) throws Exception {
        this.idApprenant = idApprenant;
        Nom = nom;
        Prenom = prenom;
        this.email = email;
        this.mdp = mdp;
        Profession = profession;
        this.civilite = civilite;
        this.modeDexercice = modeDexercice;
        this.numero = numero;
        this.datenaissance = datenaissance;
        this.token = token;
        this.etatCompte = etatCompte;
        this.dateDajout=d;
        this.nomProfession=FonctionBase.nomProfession(profession);
    }


    public Apprenant(int idApprenant, String nom, String prenom, String email, String mdp, int profession, int civilite, int modeDexercice, String numero, Date datenaissance, String token, int etatCompte,Date ajoutCours,String d) throws Exception {
        this.idApprenant = idApprenant;
        Nom = nom;
        Prenom = prenom;
        this.email = email;
        this.mdp = mdp;
        Profession = profession;
        this.civilite = civilite;
        this.modeDexercice = modeDexercice;
        this.numero = numero;
        this.datenaissance = datenaissance;
        this.token = token;
        this.etatCompte = etatCompte;
        this.ajoutCours = ajoutCours;
    this.dateDajout=d;
    this.nomProfession=FonctionBase.nomProfession(profession);
    }

    public int getProfession() {
        return Profession;
    }

    public void setProfession(int profession) {
        Profession = profession;
    }

    public int getCivilite() {
        return civilite;
    }

    public void setCivilite(int civilite) {
        this.civilite = civilite;
    }

    public int getModeDexercice() {
        return modeDexercice;
    }

    public void setModeDexercice(int modeDexercice) {
        this.modeDexercice = modeDexercice;
    }

    public Apprenant(){

}

    public String getNomProfession() {
        return nomProfession;
    }

    public void setNomProfession(String nomProfession) {
        this.nomProfession = nomProfession;
    }

    public String getNomCivilite() {
        return nomCivilite;
    }

    public void setNomCivilite(String nomCivilite) {
        this.nomCivilite = nomCivilite;
    }

    public String getNomModeDexercice() {
        return nomModeDexercice;
    }

    public void setNomModeDexercice(String nomModeDexercice) {
        this.nomModeDexercice = nomModeDexercice;
    }

    public int getIdApprenant() {
        return idApprenant;
    }

    public void setIdApprenant(int idApprenant) {
        this.idApprenant = idApprenant;
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

    public void  insertOne(String nom, String prenom, String email, String mdp,int profession, int civilite, int modeDexercice, String numero, Date datenaissance) throws Exception {

    String activationToken = ActivationTokenGenerator.generateToken();

    // Créer un utilisateur non activé


    this.inserer(nom,prenom,email, mdp,profession,civilite,modeDexercice,numero,datenaissance, activationToken);

    // Envoyer l'e-mail d'activation
    EmailService.sendActivationEmail(email, activationToken);


}

    public void inserer(String nom, String prenom, String email, String mdp,int profession, int civilite, int modeDexercice, String numero, Date datenaissance,String token) throws Exception {
        Date currentDate = datenaissance;

        // Créez un objet SimpleDateFormat avec le format de date souhaité
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Par exemple, "AAAA-MM-JJ HH:mm:ss"

        // Utilisez la méthode format pour convertir la date en une chaîne
        String dateStr = dateFormat.format(currentDate);

        // Affichez la chaîne résultante
        System.out.println("Date en format String : " + dateStr);

        String sql="insert into Apprenant (nom,prenom,email,mdp,profession,civilite,modeDexercice,numero,datenaissance,token,etatCompte)values('"+nom+"','"+prenom+"','"+email+"','"+mdp+"',"+profession+","+civilite+","+modeDexercice+",'"+numero+"','"+dateStr+"','"+token+"',"+0+")";
        FonctionBase.execute(sql);

    }

    public void insererForget(String token) throws Exception {

        Apprenant a=FonctionBase.selectWithToken(token);

        EmailService.sendForgetEmail(a.getEmail(), token);
    }

    public void updateMdp(String token,String mdp) throws Exception {


        String sql="Update Apprenant set mdp='"+mdp+"' Where token='"+token+"'";
        FonctionBase.execute(sql);

    }


public void activation(String token) throws Exception {
    LocalDate date = LocalDate.now();

    // Formater la date en "année-mois-jour"
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    String formattedDate = date.format(formatter);
  String sql="update Apprenant set etatCompte=1 , dateDajout='"+formattedDate+"' where token='"+token+"'";

  FonctionBase.execute(sql);


}
public void updateApprenant(String token,String Nom,String Prenom,int Profession,int civilite,int modeDexercice,String numero,Date datenaissance) throws Exception {
    Date currentDate = datenaissance;

    // Créez un objet SimpleDateFormat avec le format de date souhaité
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Par exemple, "AAAA-MM-JJ HH:mm:ss"

    // Utilisez la méthode format pour convertir la date en une chaîne
    String dateStr = dateFormat.format(currentDate);

    // Affichez la chaîne résultante
    System.out.println("Date en format String : " + dateStr);

String sql="update Apprenant set Nom='"+Nom+"',Prenom='"+Prenom+"',Profession="+Profession+",civilite="+civilite+",modeDexercice="+modeDexercice+",numero='"+numero+"',datenaissance='"+dateStr+"' where token= '"+token+"'";

FonctionBase.execute(sql);

}


  public ArrayList<Apprenant> idApprenant(String token) throws Exception {
    ArrayList<Apprenant> listeDept = new ArrayList<>();
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet result = null;
    
    try {
       FonctionBase connect = new FonctionBase();
            connection = connect.connect();
        
        // Modifiez la requête en fonction des conditions que vous souhaitez appliquer
        String query = "select * from Apprenant where token=? LIMIT 1";
        statement = connection.prepareStatement(query);
        // Paramètres de condition
        statement.setString(1, token);
        result = statement.executeQuery();
        
        while (result.next()) {
             Apprenant com = new Apprenant();            
            com.setIdApprenant(result.getInt("idapprenant"));
            com.setNom(result.getString("nom"));
            com.setPrenom(result.getString("prenom"));
            com.setEmail(result.getString("email"));
            com.setNumero(result.getString("numero"));
            com.setDatenaissance(result.getDate("datenaissance"));
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
