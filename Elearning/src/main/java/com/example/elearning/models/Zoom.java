package com.example.elearning.models;

public class Zoom {

   private int idZoom;
   private int idFormation ;
   private String titre;
   private String Daty;
   private String HeureDeb;
    private String MinuteDeb;
   private String HeureFin;
    private String MinuteFin;
   private String FuseauxHoraire;
   private String lien;

    public Zoom() {

    }

    public String getMinuteDeb() {
        return MinuteDeb;
    }

    public void setMinuteDeb(String minuteDeb) {
        MinuteDeb = minuteDeb;
    }

    public String getMinuteFin() {
        return MinuteFin;
    }

    public void setMinuteFin(String minuteFin) {
        MinuteFin = minuteFin;
    }

    public int getIdZoom() {
        return idZoom;
    }

    public void setIdZoom(int idZoom) {
        this.idZoom = idZoom;
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

    public String getDaty() {
        return Daty;
    }

    public void setDaty(String daty) {
        Daty = daty;
    }

    public String getHeureDeb() {
        return HeureDeb;
    }

    public void setHeureDeb(String heureDeb) {
        HeureDeb = heureDeb;
    }

    public String getHeureFin() {
        return HeureFin;
    }

    public void setHeureFin(String heureFin) {
        HeureFin = heureFin;
    }

    public String getLien() {
        return lien;
    }

    public void setLien(String lien) {
        this.lien = lien;
    }

    public Zoom(int idZoom, int idFormation, String titre, String daty, String heureDeb, String minuteDeb, String heureFin, String minuteFin, String fuseauxHoraire, String lien) {
        this.idZoom = idZoom;
        this.idFormation = idFormation;
        this.titre = titre;
        Daty = daty;
        HeureDeb = heureDeb;
        MinuteDeb = minuteDeb;
        HeureFin = heureFin;
        MinuteFin = minuteFin;
        FuseauxHoraire = fuseauxHoraire;
        this.lien = lien;
    }

    public String getFuseauxHoraire() {
        return FuseauxHoraire;
    }

    public void setFuseauxHoraire(String fuseauxHoraire) {
        FuseauxHoraire = fuseauxHoraire;
    }

    public void inserer(int idFormation, String titre, String daty, String heureDeb,String minuteDeb, String heureFin,String minuteFin, String FuseauxHoraire, String lien) throws Exception {

        String sql="Insert into Zoom(idFormation,titre,daty,heureDeb,minuteDeb,heureFin,minuteFin,FuseauxHoraire,lien)values("+idFormation+",'"+titre+"','"+daty+"','"+heureDeb+"','"+minuteDeb+"','"+heureFin+"','"+minuteFin+"','"+FuseauxHoraire+"','"+lien+"')";
        System.out.println(sql);
        FonctionBase.execute(sql);

    }

    public void modifier(int idZoom,int idFormation, String titre, String daty, String heureDeb,String minuteDeb, String heureFin,String minuteFin, String FuseauxHoraire, String lien) throws Exception {
        String sql="UPDATE Zoom set titre='"+titre+"', daty='"+daty+"', heureDeb='"+heureDeb+"',minuteDeb='"+minuteDeb+"', heureFin='"+heureFin+"',minuteFin='"+minuteFin+"',FuseauxHoraire='"+FuseauxHoraire+"', lien='"+lien+"' where idZoom="+idZoom;

        FonctionBase.execute(sql);
    }

    public void supprimer(int idZoom) throws Exception {

        String sql="delete zoom where idzoom="+idZoom;

        FonctionBase.execute(sql);
    }

}
