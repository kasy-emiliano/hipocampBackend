package com.example.elearning.models;

import java.util.ArrayList;

public class Quiz {

    private int idQuiz;
    private int idFormation ;
    private String titre ;
private ArrayList<QuestionQuiz> mesQuestion;
private ArrayList<NoteQuiz> noteQuizs;
    public ArrayList<QuestionQuiz> getMesQuestion() {
        return mesQuestion;
    }

    public ArrayList<NoteQuiz> getNoteQuizs() {
        return noteQuizs;
    }

    public void setNoteQuizs(ArrayList<NoteQuiz> noteQuizs) {
        this.noteQuizs = noteQuizs;
    }

    public void setMesQuestion(ArrayList<QuestionQuiz> mesQuestion) {
        this.mesQuestion = mesQuestion;
    }

    public Quiz() {

    }

    public int getIdQuiz() {
        return idQuiz;
    }

    public void setIdQuiz(int idQuiz) {
        this.idQuiz = idQuiz;
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

    public Quiz(int idQuiz, int idFormation, String titre) throws Exception {
        this.idQuiz = idQuiz;
        this.idFormation = idFormation;
        this.titre = titre;
        this.mesQuestion=FonctionBase.mesQ(idQuiz);
    }

    public void inserer( int idFormation, String titre) throws Exception {

        String sql="Insert into Quiz(idFormation,titre) values ("+idFormation+",'"+titre+"')";
        FonctionBase.execute(sql);
    }

    public void modifier(int idQuiz,  String titre) throws Exception {
        String sql="UPDATE Quiz set titre='"+titre+"' where idQuiz="+idQuiz;

        FonctionBase.execute(sql);

    }

    public void supprimer(int idQuiz) throws Exception {

        String sql="delete zoom where idQuiz="+idQuiz;

        FonctionBase.execute(sql);
    }
}
