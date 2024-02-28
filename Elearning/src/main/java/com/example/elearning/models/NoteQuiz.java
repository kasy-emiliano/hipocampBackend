package com.example.elearning.models;

public class NoteQuiz {
    private int idQuiz;
    private int idApprenant;
    private int tentative;
    private double totalRep;
    private double totalQuestion;

    public NoteQuiz(int tentative,int idApprenant,int idQuiz,   double totalRep, double totalQuestion) {
        this.idQuiz = idQuiz;
        this.idApprenant = idApprenant;
        this.tentative = tentative;
        this.totalRep = totalRep;
        this.totalQuestion = totalQuestion;
    }

    public int getIdQuiz() {
        return idQuiz;
    }

    public void setIdQuiz(int idQuiz) {
        this.idQuiz = idQuiz;
    }

    public int getIdApprenant() {
        return idApprenant;
    }

    public void setIdApprenant(int idApprenant) {
        this.idApprenant = idApprenant;
    }

    public int getTentative() {
        return tentative;
    }

    public void setTentative(int tentative) {
        this.tentative = tentative;
    }

    public double getTotalRep() {
        return totalRep;
    }

    public void setTotalRep(double totalRep) {
        this.totalRep = totalRep;
    }

    public double getTotalQuestion() {
        return totalQuestion;
    }

    public void setTotalQuestion(double totalQuestion) {
        this.totalQuestion = totalQuestion;
    }
}
