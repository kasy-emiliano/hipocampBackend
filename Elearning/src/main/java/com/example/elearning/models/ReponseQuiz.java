package com.example.elearning.models;

public class ReponseQuiz {

   private int idReponseQuiz;
   private int idQuestion;
   private String Reponse;
   private double note;

    public ReponseQuiz() {

    }

    public void inserer( int idQuestion, String Reponse,double note) throws Exception {

        String sql="Insert into ReponseQuiz(idQuestion,Reponse,note) values ("+idQuestion+",'"+Reponse+"',"+note+")";
        FonctionBase.execute(sql);
    }

    public void modifier(int idReponseQuiz, String Reponse,double note){


    }

    public void supprimer(int idReponseQuiz){


    }


    public ReponseQuiz(int idReponseQuiz, int idQuestion, String reponse, double note) {
        this.idReponseQuiz = idReponseQuiz;
        this.idQuestion = idQuestion;
        Reponse = reponse;
        this.note = note;
    }

    public int getIdReponseQuiz() {
        return idReponseQuiz;
    }

    public void setIdReponseQuiz(int idReponseQuiz) {
        this.idReponseQuiz = idReponseQuiz;
    }

    public int getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(int idQuestion) {
        this.idQuestion = idQuestion;
    }

    public String getReponse() {
        return Reponse;
    }

    public void setReponse(String reponse) {
        Reponse = reponse;
    }

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }
}
