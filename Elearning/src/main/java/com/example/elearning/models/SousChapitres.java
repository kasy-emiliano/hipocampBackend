package com.example.elearning.models;

import java.util.ArrayList;

public class SousChapitres {


    private int idSousChapitres;
    private int idChapitres;
   private String titre;
   private ArrayList<ContenuSousChapitres> contenu;

    public ArrayList<ContenuSousChapitres> getContenu() {
        return contenu;
    }

    public void setContenu(ArrayList<ContenuSousChapitres> contenu) {
        this.contenu = contenu;
    }

    public SousChapitres(int idSousChapitres, int idChapitres, String titre) throws Exception {
        this.idSousChapitres = idSousChapitres;
        this.idChapitres = idChapitres;
        this.titre = titre;

    }

    public SousChapitres() {

    }

    public void inserer(int idChapitres, String titre) throws Exception {
        String sql="Insert into SousChapitres(idChapitres,titre) values ("+idChapitres+",'"+titre+"')";
        FonctionBase.execute(sql);
    }
    public void modif(int idSousChapitres,String titre) throws Exception {
        String sql="UPDATE SousChapitres set titre='"+titre+ "' where idSousChapitres="+idSousChapitres;
        FonctionBase.execute(sql);
    }
    public void suppr(int idSousChapitres) throws Exception {

        String sql="Delete SousChapitres where idSousChapitres="+idSousChapitres;
        FonctionBase.execute(sql);
    }


    public int getIdSousChapitres() {
        return idSousChapitres;
    }

    public void setIdSousChapitres(int idSousChapitres) {
        this.idSousChapitres = idSousChapitres;
    }

    public int getIdChapitres() {
        return idChapitres;
    }

    public void setIdChapitres(int idChapitres) {
        this.idChapitres = idChapitres;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }
}
