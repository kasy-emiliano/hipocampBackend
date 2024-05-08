package com.example.elearning.models;

import java.io.File;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class Formation {

    private int idFormation;
    private int idFormateur;
    private int idCategorie;
    private String nomCategorie;
    private int TypesAcces;
    private String nomTypesAcces;
    private int Langues;
    private String nomLangues;
    private String Titre;
    private String duree;
    private int unite;
    private String nomUnite;
    private String resumer;
    private String pdc;
    private String token;
    private byte[] image;
    private String prix;
    private int etat;
    private ArrayList<Chapitres> meschapitres;
    private ArrayList<Zoom> meszooms;
    private ArrayList<Quiz> mesQuizs;
    private String dateDajout;
    private Formateur monFormateur;
    private long progres;
    private String devalidation;
    private String dedemande;
    private int totalEleve;
    private String nomespace;
    private int etatPublication;

    public int getEtatPublication() {
        return etatPublication;
    }

    public void setEtatPublication(int etatPublication) {
        this.etatPublication = etatPublication;
    }

    public String getNomespace() {
        return nomespace;
    }

    public void setNomespace(String nomespace) {
        this.nomespace = nomespace;
    }

    public String getDedemande() {
        return dedemande;
    }

    public void setDedemande(String dedemande) {
        this.dedemande = dedemande;
    }

    public long getProgres() {

        return progres;
    }

    public void setProgres(long progres) {
        this.progres = progres;
    }

    public Formateur getMonFormateur() {
        return monFormateur;
    }

    public void setMonFormateur(Formateur monFormateur) {
        this.monFormateur = monFormateur;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public String getDevalidation() {
        return devalidation;
    }

    public void setDevalidation(String devalidation) {
        this.devalidation = devalidation;
    }

    public int getUnite() {
        return unite;
    }

    public void setUnite(int unite) {
        this.unite = unite;
    }

    public Formation() {

    }

    public String getDateDajout() {
        return dateDajout;
    }

    public void setDateDajout(String dateDajout) {
        this.dateDajout = dateDajout;
    }

    public ArrayList<Chapitres> getMeschapitres() {
        return meschapitres;
    }

    public void setMeschapitres(ArrayList<Chapitres> meschapitres) {
        this.meschapitres = meschapitres;
    }

    public Formation(int idFormation, int idFormateur, int idCategorie, String nomCategorie, int typesAcces, String nomTypesAcces, int langues, String nomLangues, String titre, String duree, int unite, String nomUnite, String resumer, String pdc, String token, int etat, byte[] b, double prix, String d, String c, String e) throws Exception {
        this.idFormation = idFormation;
        this.idFormateur = idFormateur;
        this.idCategorie = idCategorie;
        this.nomCategorie = nomCategorie;
        TypesAcces = typesAcces;
        this.nomTypesAcces = nomTypesAcces;
        Langues = langues;
        this.nomLangues = nomLangues;
        Titre = titre;
        this.duree = duree;
        this.unite = unite;
        this.nomUnite = nomUnite;
        this.resumer = resumer;
        this.pdc = pdc;
        this.token = token;
        this.etat = etat;
        this.image = b;
        this.prix = String.valueOf(prix);
        this.meschapitres = FonctionBase.allChapitres(idFormation);
        this.meszooms = FonctionBase.allZooms(idFormation);
        this.mesQuizs = FonctionBase.allQuiz(idFormation);
        this.devalidation = c;
        this.dateDajout = d;
        this.dedemande = e;
        //System.out.println("iooooo:"+this.dateDajout);
        this.monFormateur = FonctionBase.moi(idFormateur);

        ArrayList<Apprenant> rep = FonctionBase.ListApprenantI(idFormation);
        this.totalEleve = rep.size();
        //System.out.println("tsssss:"+this.totalEleve);
    }

    public ArrayList<Quiz> getMesQuizs() {
        return mesQuizs;
    }

    public void setMesQuizs(ArrayList<Quiz> mesQuizs) {
        this.mesQuizs = mesQuizs;
    }

    public ArrayList<Zoom> getMeszooms() {
        return meszooms;
    }

    public void setMeszooms(ArrayList<Zoom> meszooms) {
        this.meszooms = meszooms;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getNomCategorie() {
        return nomCategorie;
    }

    public void setNomCategorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }

    public String getNomTypesAcces() {
        return nomTypesAcces;
    }

    public void setNomTypesAcces(String nomTypesAcces) {
        this.nomTypesAcces = nomTypesAcces;
    }

    public String getNomLangues() {
        return nomLangues;
    }

    public void setNomLangues(String nomLangues) {
        this.nomLangues = nomLangues;
    }

    public String getNomUnite() {
        return nomUnite;
    }

    public void setNomUnite(String nomUnite) {
        this.nomUnite = nomUnite;
    }

    public Formation(int idFormation, int idFormateur, int idCategorie, int typesAcces, int langues, String titre, String duree, int u, String resumer, String pdc, String token, int etat) {
        this.idFormation = idFormation;
        this.idFormateur = idFormateur;
        this.idCategorie = idCategorie;
        TypesAcces = typesAcces;
        Langues = langues;
        Titre = titre;
        this.duree = duree;
        this.unite = u;
        this.resumer = resumer;
        this.pdc = pdc;
        this.token = token;
        this.etat = etat;
    }

    public Formation(int idFormateur, int idCategorie, int typesAcces, int langues, String titre, String duree, int u, String resumer, String pdc, String token, int etat) {
        this.idFormateur = idFormateur;
        this.idCategorie = idCategorie;
        TypesAcces = typesAcces;
        Langues = langues;
        Titre = titre;
        this.duree = duree;
        this.unite = u;
        this.resumer = resumer;
        this.pdc = pdc;
        this.token = token;
        this.etat = etat;
    }

    public void inserer(int idFormateur, int idCategorie, int typesAcces, int langues, String titre, String duree, int u, String resumer, String pdc, double prix,int etatPublication) throws Exception {
        String Token = ActivationTokenGenerator.generateToken();
        LocalDate date = LocalDate.now();

        // Formater la date en "année-mois-jour"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = date.format(formatter);
        String sql = "Insert into Formation(idFormateur,idCategorie,typesAcces,langues,titre,duree,unite,resumer,pdc,token,etat,prix,dateDajout,etatPublication)values(" + idFormateur + "," + idCategorie + "," + typesAcces + "," + langues + ",'" + titre + "','" + duree + "'," + u + ",'" + resumer + "','" + pdc + "','" + Token + "'," + 0 + "," + prix + ",'" + formattedDate + "','" + etatPublication + "')";
        System.out.println(sql);
        FonctionBase.execute(sql);
    }

    public void modif(int idCategorie, int typesAcces, int langues, String titre, String duree, int u, String resumer, String pdc, String token, double prix) throws Exception {
        String sql = "UPDATE Formation set idCategorie=" + idCategorie + ", typesAcces=" + typesAcces + ", langues=" + langues + ", titre='" + titre + "', duree='" + duree + "', unite=" + u + ", resumer='" + resumer + "',pdc='" + pdc + "',prix=" + prix + " where token='" + token + "'";

        FonctionBase.execute(sql);
    }

    public void demandevalidation(int id) throws Exception {

        LocalDate date = LocalDate.now();

        // Formater la date en "année-mois-jour"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = date.format(formatter);

        String sql = "UPDATE Formation set etat=1 ,dedemande='" + formattedDate + "' where idFormation=" + id;
        FonctionBase.execute(sql);
    }

    public void suppr(String token) throws Exception {
        String sql = "delete formation where token='" + token + "'";

        FonctionBase.execute(sql);

    }

    public int getIdFormation() {
        return idFormation;
    }

    public void setIdFormation(int idFormation) {
        this.idFormation = idFormation;
    }

    public int getIdFormateur() {
        return idFormateur;
    }

    public void setIdFormateur(int idFormateur) {
        this.idFormateur = idFormateur;
    }

    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    public int getTypesAcces() {
        return TypesAcces;
    }

    public void setTypesAcces(int typesAcces) {
        TypesAcces = typesAcces;
    }

    public int getLangues() {
        return Langues;
    }

    public void setLangues(int langues) {
        Langues = langues;
    }

    public String getTitre() {
        return Titre;
    }

    public void setTitre(String titre) {
        Titre = titre;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public String getResumer() {
        return resumer;
    }

    public void setResumer(String resumer) {
        this.resumer = resumer;
    }

    public void setTotalEleve(int totalEleve) {
        this.totalEleve = totalEleve;
    }

    public int getTotalEleve() {
        return totalEleve;
    }

    public String getPdc() {
        return pdc;
    }

    public void setPdc(String pdc) {
        this.pdc = pdc;
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

    public ArrayList<Formation> mesFormationEspace(String nomespace) throws Exception {
        ArrayList<Formation> listeDept = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            FonctionBase connect = new FonctionBase();
            connection = connect.connect();

            // Modifiez la requête en fonction des conditions que vous souhaitez appliquer
            String query = "SELECT formation.idformation, formation.idformateur, formation.idcategorie, formation.typesacces, formation.langues, formation.titre, formation.duree, formation.unite, formation.resumer, formation.pdc, formation.token, formation.etat, formation.prix, formation.datedajout, formation.devalidation, formation.dedemande, categorie.nom AS nomCategorie,typesacces.nom AS nomTypesAcces,langues.nom AS nomLangues,unite.nom AS nomUnite,Formateur.nomespace FROM formation JOIN categorie ON formation.idcategorie = categorie.idcategorie JOIN typesacces ON formation.typesacces = typesacces.idTypesAcces JOIN langues ON formation.langues = langues.idLangues JOIN unite ON formation.unite = unite.idUnite JOIN Formateur ON formation.idformateur = Formateur.idFormateur WHERE nomespace= ? ORDER BY formation.idformation DESC ";
            statement = connection.prepareStatement(query);
            // Paramètres de condition
            statement.setString(1, nomespace);
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
                com.setNomCategorie(result.getString("nomCategorie"));
                com.setNomTypesAcces(result.getString("nomTypesAcces"));
                com.setNomLangues(result.getString("nomLangues"));
                com.setNomUnite(result.getString("nomUnite"));
                com.setNomespace(result.getString("nomespace"));
                // Récupérer le chemin de l'image depuis la base de données
                String imagePath = result.getString("pdc");

                // Créer un objet File à partir du chemin de l'image
                File imageFile = new File(imagePath);

                // Lire les octets de l'image
                byte[] imageData = Files.readAllBytes(imageFile.toPath());

                // Définir les octets de l'image dans l'objet Formation
                com.setImage(imageData);

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
