package com.example.elearning.models;

public class mouvementZoom {

  private int idmouvementZoom;
  private int idApprenant;
  private int idFormation;
  private int  idZoom;

    public mouvementZoom() {

    }

    public int getIdmouvementZoom() {
        return idmouvementZoom;
    }

    public void setIdmouvementZoom(int idmouvementZoom) {
        this.idmouvementZoom = idmouvementZoom;
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

    public int getIdZoom() {
        return idZoom;
    }

    public void setIdZoom(int idZoom) {
        this.idZoom = idZoom;
    }

    public mouvementZoom(int idmouvementZoom, int idApprenant, int idFormation, int idZoom) {
        this.idmouvementZoom = idmouvementZoom;
        this.idApprenant = idApprenant;
        this.idFormation = idFormation;
        this.idZoom = idZoom;
    }


    public void insert( int idApprenant, int idFormation, int idZoom ) throws Exception {

        String sql="Insert into mouvementZoom(idApprenant,idFormation,idZoom) values ("+idApprenant+","+idFormation+","+idZoom+")";
        FonctionBase.execute(sql);



    }






}
