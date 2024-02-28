package com.example.elearning.models;

import java.sql.Timestamp;

public class DemandeAdhesion {

  private  int    idDemande;
  private String  Organisme ;
  private String  Nom ;
  private String  Prenom;
  private String ville ;
  private String email;
  private String numero;
  private String  objet;
  private String  message;
  private String  token;
  private Timestamp Ajout;
  private int  etat;
private  String ovina;

  public String getOvina() {
    return ovina;
  }

  public void setOvina(String ovina) {
    this.ovina = ovina;
  }

  public Timestamp getAjout() {
    return Ajout;
  }

  public void setAjout(Timestamp ajout) {
    Ajout = ajout;
  }

  public int getIdDemande() {
    return idDemande;
  }

  public void setIdDemande(int idDemande) {
    this.idDemande = idDemande;
  }

  public String getOrganisme() {
    return Organisme;
  }

  public void setOrganisme(String organisme) {
    Organisme = organisme;
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

  public String getVille() {
    return ville;
  }

  public void setVille(String ville) {
    this.ville = ville;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getNumero() {
    return numero;
  }

  public void setNumero(String numero) {
    this.numero = numero;
  }

  public String getObjet() {
    return objet;
  }

  public void setObjet(String objet) {
    this.objet = objet;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public int getEtat() {
    return etat;
  }

  public void setEtat(int etat) {
    this.etat = etat;
  }

  public DemandeAdhesion(String organisme, String nom, String prenom, String ville, String email, String numero, String objet, String message) {
    Organisme = organisme;
    Nom = nom;
    Prenom = prenom;
    this.ville = ville;
    this.email = email;
    this.numero = numero;
    this.objet = objet;
    this.message = message;
  }

  public DemandeAdhesion(int idDemande,Timestamp t, String organisme, String nom, String prenom, String ville, String email, String numero, String objet, String message, String token, int etat) {

    this.idDemande = idDemande;
    this.Ajout=t;
    Organisme = organisme;
    Nom = nom;
    Prenom = prenom;
    this.ville = ville;
    this.email = email;
    this.numero = numero;
    this.objet = objet;
    this.message = message;
    this.token = token;
    this.etat = etat;
  }
  public DemandeAdhesion(int idDemande,Timestamp t, String organisme, String nom, String prenom, String ville, String email, String numero, String objet, String message, String token, int etat,String o) {

    this.idDemande = idDemande;
    this.Ajout=t;
    Organisme = organisme;
    Nom = nom;
    Prenom = prenom;
    this.ville = ville;
    this.email = email;
    this.numero = numero;
    this.objet = objet;
    this.message = message;
    this.token = token;
    this.etat = etat;
    this.ovina=o;
  }


  public DemandeAdhesion( ){}

  public void insererDemande(String organisme,String nom, String prenom, String ville, String email, String numero, String objet, String message) throws Exception {

DemandeAdhesion d=new DemandeAdhesion(organisme,nom,prenom,ville,email,numero,objet,message);
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    organisme=organisme.replace("'","''");
    nom=nom.replace("'","''");
    prenom=prenom.replace("'","''");
    ville=ville.replace("'","''");
    objet=objet.replace("'","''");
    message=message.replace("'","''");
    String sql="insert into DemandeAdhesion (Ajout,organisme,nom,prenom,ville,email,numero,objet,message,etat)values('"+timestamp+"','"+organisme+"','"+nom+"','"+prenom+"','"+ville+"','"+email+"','"+numero+"','"+objet+"','"+message+"',"+0+")";
    FonctionBase.execute(sql);
    EmailService.sendDemande(d);
  }


public DemandeAdhesion findById(int id) throws Exception {
    DemandeAdhesion rep=null;
rep=FonctionBase.findByIdA(id);
    return rep;
}
public  boolean efa(int idDemande){
  boolean rep=false;


  return rep;
}

public void EnvoyerLien(int idDemande ) throws Exception {
  String activationToken = ActivationTokenGenerator.generateToken();
  DemandeAdhesion rep=null;

  String sql="update  DemandeAdhesion set token='"+activationToken+"',etat=1  where idDemande="+idDemande;
  FonctionBase.execute(sql);
  rep=FonctionBase.findByIdA(idDemande);
  EmailService.sendlink(rep);

}

}
