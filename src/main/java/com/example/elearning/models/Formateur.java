package com.example.elearning.models;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Formateur {

    private  int idFormateur ;
    private String Nom ;
    private String Prenom;
    private String email;
    private String mdp ;
    private String NomOrgannisme;
    private String ville ;
    private int civilite ;
    private int Profession;
    private String nomProfession;
    private int modeDexercice ;
    private String bio ;
    private String numero ;
    private Date datenaissance ;
    private String facebook;
    private String linkedin ;
    private String token;
    private int etatCompte ;
    private String pdp;
    private byte[] image;
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Formateur(int idFormateur, String nom, String prenom, String email, String mdp, String nomOrgannisme, String ville, int civilite, int profession, int modeDexercice, String bio, String numero, Date datenaissance, String facebook, String linkedin, String token, int etatCompte, String pdp,String d) throws Exception {

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
this.dateDajout=d;
        File images = new File("");

        if(!this.pdp.equals("")){
        images=new File(this.pdp);

        setImage(Files.readAllBytes(images.toPath()));
        System.out.println(this.image);
        }
        this.nomProfession=FonctionBase.nomProfession(profession);
    }


    public String getPdp() {
        return pdp;
    }

    public void setPdp(String pdp)
    {
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


    public Formateur(String nom, String prenom, String email, String mdp, String nomOrgannisme, String ville, int civilite, int profession, int modeDexercice, String bio, String numero, Date datenaissance, String facebook, String linkedin, String token,String photo) throws IOException {
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
        this.pdp=photo;

        File image = new File("");
        image=new File(this.pdp);

        setImage(Files.readAllBytes(image.toPath()));
        System.out.println("ouou");
    }


    public void  insertOne(String nom, String prenom, String email, String mdp, String nomOrgannisme, String ville, int civilite, int profession, int modeDexercice, String bio, String numero, Date datenaissance, String facebook, String linkedin,String token,String photo) throws Exception {



        // Créer un utilisateur non activé


        this.inserer(nom,  prenom,  email,  mdp,  nomOrgannisme,  ville,  civilite,  profession,  modeDexercice, bio,  numero,  datenaissance,  facebook,  linkedin, token, photo);

        // Envoyer l'e-mail d'activation
        EmailService.sendActivationEmailF(email, token);


    }

    public void inserer(String nom, String prenom, String email, String mdp, String nomOrgannisme, String ville, int civilite, int profession, int modeDexercice, String bio, String numero, Date datenaissance, String facebook, String linkedin,String token,String photo) throws Exception {
        Date currentDate = datenaissance;

        // Créez un objet SimpleDateFormat avec le format de date souhaité
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Par exemple, "AAAA-MM-JJ HH:mm:ss"

        // Utilisez la méthode format pour convertir la date en une chaîne
        String dateStr = dateFormat.format(currentDate);

        // Affichez la chaîne résultante
        System.out.println("Date en format String : " + dateStr);

        String sql="insert into Formateur (nom,prenom,email,mdp,nomOrgannisme,ville,civilite,profession,modeDexercice,bio,numero,datenaissance,facebook,linkedin,token,etatCompte,pdp)values('"+nom+"','"+prenom+"','"+email+"','"+mdp+"','"+nomOrgannisme+"','"+ville+"','"+civilite+"','"+profession+"','"+modeDexercice+"','"+bio+"','"+numero+"','"+datenaissance+"','"+facebook+"','"+linkedin+"','"+token+"',"+0+",'"+photo+"')";

        FonctionBase.execute(sql);

    }

    public void insererForget(String token) throws Exception {

        Formateur a=FonctionBase.selectWithTokenF(token);

        EmailService.sendForgetEmailF(a.getEmail(), token);
    }

    public void updateMdp(String token,String mdp) throws Exception {


        String sql="Update Formateur set mdp='"+mdp+"' Where token='"+token+"'";
        FonctionBase.execute(sql);

    }

    public void publier(int idFormation) throws Exception {


        String sql="Update Formation set etat=1 Where idFormation="+idFormation;
        FonctionBase.execute(sql);

    }





    public void activation(String token) throws Exception {
        LocalDate date = LocalDate.now();

        // Formater la date en "année-mois-jour"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = date.format(formatter);


        String sql="update Formateur set etatCompte=1 , dateDajout='"+formattedDate+"' where token='"+token+"'";

        FonctionBase.execute(sql);


    }

    public  void modifphoto(String token,String photo) throws Exception {

        String sql="Update Formateur set Pdp='"+photo+"' Where token='"+token+"'";
        FonctionBase.execute(sql);

    }

    public void updateFormateur(String nom, String prenom,  String nomOrgannisme, String ville, int civilite, int profession, int modeDexercice, String bio, String numero, Date datenaissance, String facebook, String linkedin,String token) throws Exception {
        Date currentDate = datenaissance;

        // Créez un objet SimpleDateFormat avec le format de date souhaité
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Par exemple, "AAAA-MM-JJ HH:mm:ss"

        // Utilisez la méthode format pour convertir la date en une chaîne
        String dateStr = dateFormat.format(currentDate);

        // Affichez la chaîne résultante
        System.out.println("Date en format String : " + dateStr);

        String sql="update Formateur set Nom='"+nom+"',Prenom='"+prenom+"',Profession="+profession+",civilite="+civilite+",modeDexercice="+modeDexercice+",numero='"+numero+"',datenaissance='"+dateStr+"',bio='"+bio+"',facebook='"+facebook+"',linkedin='"+linkedin+"',ville='"+ville+"', nomorgannisme='"+nomOrgannisme+"' where token= '"+token+"'";

        FonctionBase.execute(sql);

    }






}
