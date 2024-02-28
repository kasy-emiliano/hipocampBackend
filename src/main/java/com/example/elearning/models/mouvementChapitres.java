package com.example.elearning.models;

public class mouvementChapitres {

  private int idmouvementChapitres;
  private int  idApprenant;
  private int  idFormation;
  private int  idSousChapitres;

    public mouvementChapitres() {

    }

    public mouvementChapitres(int idmouvementChapitres, int idApprenant, int idFormation, int idSousChapitres) {
        this.idmouvementChapitres = idmouvementChapitres;
        this.idApprenant = idApprenant;
        this.idFormation = idFormation;
        this.idSousChapitres = idSousChapitres;
    }

    public int getIdmouvementChapitres() {
        return idmouvementChapitres;
    }

    public void setIdmouvementChapitres(int idmouvementChapitres) {
        this.idmouvementChapitres = idmouvementChapitres;
    }

    public int getIdApprenant() {
        return idApprenant;
    }

    public void setIdApprenant(int idApprenant) {
        this.idApprenant = idApprenant;
    }

    public int getIdFormation() {
        return idFormation;
    }

    public void setIdFormation(int idFormation) {
        this.idFormation = idFormation;
    }

    public int getIdSousChapitres() {
        return idSousChapitres;
    }

    public void setIdSousChapitres(int idSousChapitres) {
        this.idSousChapitres = idSousChapitres;
    }

    public void inserer(int idApprenant, int idFormation, int idSousChapitres ) throws Exception {

        String sql="Insert into mouvementChapitres(idApprenant,idFormation,idSousChapitres) values ("+idApprenant+","+idFormation+","+idSousChapitres+")";
        FonctionBase.execute(sql);



    }


}
