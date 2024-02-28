package com.example.elearning.models;

import java.util.ArrayList;

public class QuestionQuiz {

   private int idQuestionQuiz;
   private int idQuiz;
   private int idTypeQuestion;
   private String Question;


    private ArrayList<ReponseQuiz> mesReponses;

    private int marina;

    public int getMarina() {
        return marina;
    }

    public void setMarina(int marina) {
        this.marina = marina;
    }

    public QuestionQuiz() {

    }

    public int getIdTypeQuestion() {
        return idTypeQuestion;
    }

    public void setIdTypeQuestion(int idTypeQuestion) {
        this.idTypeQuestion = idTypeQuestion;
    }




    public ArrayList<ReponseQuiz> getMesReponses() {
        return mesReponses;
    }

    public void setMesReponses(ArrayList<ReponseQuiz> mesReponses) {
        this.mesReponses = mesReponses;
    }

    public QuestionQuiz(int idQuestionQuiz, int idQuiz,int idTypeQuestion, String question) throws Exception {
        this.idQuestionQuiz = idQuestionQuiz;
        this.idQuiz = idQuiz;
        Question = question;

        mesReponses=FonctionBase.allrep(idQuestionQuiz);

        marina=0;

        for (int i=0;i<mesReponses.size();i++){


            if(mesReponses.get(i).getNote()!=0.0){

                marina++;

            }
        }


    }


    public void inserer( int idQuiz,int idTypeQuestion, String Question) throws Exception {

        String sql="Insert into QuestionQuiz(idQuiz,idTypeQuestion,Question) values ("+idQuiz+","+idTypeQuestion+",'"+Question+"')";
        FonctionBase.execute(sql);
    }

    public void modifier(int idQuestionQuiz, String titre){


    }

    public void supprimer(int idQuestionQuiz){


    }








    public int getIdQuestionQuiz() {
        return idQuestionQuiz;
    }

    public void setIdQuestionQuiz(int idQuestionQuiz) {
        this.idQuestionQuiz = idQuestionQuiz;
    }

    public int getIdQuiz() {
        return idQuiz;
    }

    public void setIdQuiz(int idQuiz) {
        this.idQuiz = idQuiz;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }
}
