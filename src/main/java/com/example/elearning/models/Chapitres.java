package com.example.elearning.models;

import java.util.ArrayList;

public class Chapitres {

  private int  idChapitres;
  private int  idFormation;
  private String  titre;
    private ArrayList<SousChapitres> mesSouschapitres;
  public Chapitres(){


  }

    public ArrayList<SousChapitres> getMesSouschapitres() {
        return mesSouschapitres;
    }

    public void setMesSouschapitres(ArrayList<SousChapitres> mesSouschapitres) {
        this.mesSouschapitres = mesSouschapitres;
    }

    public Chapitres(int idChapitres, int idFormation, String titre) throws Exception {
        this.idChapitres = idChapitres;
        this.idFormation = idFormation;
        this.titre = titre;
        this.mesSouschapitres=FonctionBase.allSousChapitres(idChapitres);
    }
    public void inserer(int idFormation, String titre) throws Exception {
        String sql="Insert into Chapitres(idFormation,titre) values ("+idFormation+",'"+titre+"')";
        FonctionBase.execute(sql);
    }
    public void modif(int idChapitres,String titre) throws Exception {
        String sql="UPDATE Chapitres set titre='"+titre+ "' where idChapitres="+idChapitres;
        FonctionBase.execute(sql);
    }
    public void suppr(int idChapitres) throws Exception {

        String sql="Delete Chapitres where idChapitres="+idChapitres;
        FonctionBase.execute(sql);
    }
    public Chapitres(int idFormation, String titre) {
        this.idFormation = idFormation;
        this.titre = titre;
    }

    public int getIdChapitres() {
        return idChapitres;
    }

    public void setIdChapitres(int idChapitres) {
        this.idChapitres = idChapitres;
    }

    public int getIdFormation() {
        return idFormation;
    }

    public void setIdFormation(int idFormation) {
        this.idFormation = idFormation;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }



}
