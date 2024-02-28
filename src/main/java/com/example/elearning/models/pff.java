package com.example.elearning.models;

public class pff {
    private String idQuiz;
    private String idTypeQuestion;
    private String question;
    private  String reponses;

    public String getIdTypeQuestion() {
        return idTypeQuestion;
    }

    public void setIdTypeQuestion(String idTypeQuestion) {
        this.idTypeQuestion = idTypeQuestion;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public pff(String idQuiz, String reponse) {
        this.idQuiz = idQuiz;
        this.reponses = reponse;
    }

    public String getIdQuiz() {
        return idQuiz;
    }

    public void setIdQuiz(String idQuiz) {
        this.idQuiz = idQuiz;
    }

    public String getReponses() {
        return reponses;
    }

    public void setReponses(String reponses) {
        this.reponses = reponses;
    }
}
