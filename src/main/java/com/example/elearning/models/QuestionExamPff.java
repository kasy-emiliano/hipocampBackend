package com.example.elearning.models;

public class QuestionExamPff {
    private String idExamen;
    private String idTypeQuestion;
    private String question;
    private  String reponses;

    public String getIdExamen() {
        return idExamen;
    }

    public void setIdExamen(String idExamen) {
        this.idExamen = idExamen;
    }

    
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

 
    public String getReponses() {
        return reponses;
    }

    public void setReponses(String reponses) {
        this.reponses = reponses;
    }
    
    public QuestionExamPff(String idExamen, String reponse) {
        this.idExamen = idExamen;
        this.reponses = reponse;
    }
}
