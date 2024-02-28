package com.example.elearning.models;

public class ContenuSousChapitres {

  private int  idContenuSousChapitres;
  private int  idSousChapitres ;
  private String  Contenu;
  private String legende;
  private String  typa ;
    private byte[] content;

    public byte[] getContent() {
        return content;
    }

    public ContenuSousChapitres(int idContenuSousChapitres, int idSousChapitres, String contenu, String legende, String typa, byte[] content) {
        this.idContenuSousChapitres = idContenuSousChapitres;
        this.idSousChapitres = idSousChapitres;
        Contenu = contenu;
        this.legende = legende;
        this.typa = typa;
        this.content = content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public ContenuSousChapitres() {

    }

    public void inserer(int idSousChapitres,String contenu,String legende,String typa) throws Exception {

      String sql="insert into ContenuSousChapitres (idSousChapitres,contenu,legende,typa) values("+idSousChapitres+",'"+contenu+"','"+legende+"','"+typa+"')";

      FonctionBase.execute(sql);

    }


    public int getIdContenuSousChapitres() {
        return idContenuSousChapitres;
    }

    public void setIdContenuSousChapitres(int idContenuSousChapitres) {
        this.idContenuSousChapitres = idContenuSousChapitres;
    }

    public int getIdSousChapitres() {
        return idSousChapitres;
    }

    public void setIdSousChapitres(int idSousChapitres) {
        this.idSousChapitres = idSousChapitres;
    }

    public String getContenu() {
        return Contenu;
    }

    public void setContenu(String contenu) {
        Contenu = contenu;
    }

    public String getTypa() {
        return typa;
    }

    public void setTypa(String typa) {
        this.typa = typa;
    }

    public String getLegende() {
        return legende;
    }

    public void setLegende(String legende) {
        this.legende = legende;
    }


    public ContenuSousChapitres(int idContenuSousChapitres, int idSousChapitres, String contenu, String legende, String typa) {
        this.idContenuSousChapitres = idContenuSousChapitres;
        this.idSousChapitres = idSousChapitres;
        Contenu = contenu;
        this.legende = legende;
        this.typa = typa;
    }




}
