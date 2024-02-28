package com.example.elearning.models;

public class mouvementQuiz {
   private int  idmouvementQuiz;
   private int tentative;
   private int idApprenant ;
   private int idQuiz ;
   private int  idReponseQuiz;

    public mouvementQuiz() {

    }

    public int getTentative() {
        return tentative;
    }

    public void setTentative(int tentative) {
        this.tentative = tentative;
    }

    public mouvementQuiz(int idmouvementQuiz, int tentative, int idApprenant, int idQuiz, int idReponseQuiz) {
        this.idmouvementQuiz = idmouvementQuiz;
        this.tentative = tentative;
        this.idApprenant = idApprenant;
        this.idQuiz = idQuiz;
        this.idReponseQuiz = idReponseQuiz;
    }

    public void inserer(int tentative,int idApprenant, int idQuiz, int idReponseQuiz) throws Exception {
        String sql="Insert into mouvementQuiz(tentative,idApprenant,idQuiz,idReponseQuiz) values ("+tentative+","+idApprenant+","+idQuiz+","+idReponseQuiz+")";
        FonctionBase.execute(sql);


    }


    public int getIdmouvementQuiz() {
        return idmouvementQuiz;
    }

    public void setIdmouvementQuiz(int idmouvementQuiz) {
        this.idmouvementQuiz = idmouvementQuiz;
    }

    public int getIdApprenant() {
        return idApprenant;
    }

    public void setIdApprenant(int idApprenant) {
        this.idApprenant = idApprenant;
    }

    public int getIdQuiz() {
        return idQuiz;
    }

    public void setIdQuiz(int idQuiz) {
        this.idQuiz = idQuiz;
    }

    public int getIdReponseQuiz() {
        return idReponseQuiz;
    }

    public void setIdReponseQuiz(int idReponseQuiz) {
        this.idReponseQuiz = idReponseQuiz;
    }
}
