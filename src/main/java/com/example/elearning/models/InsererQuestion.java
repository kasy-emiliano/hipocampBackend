package com.example.elearning.models;

import java.util.ArrayList;

public class InsererQuestion {

    private Quiz monQuiz;
    private ArrayList<TypeQuestion> TypeQuestion;
    private Examens monExam;

    public Examens getMonExam() {
        return monExam;
    }

    public void setMonExam(Examens monExam) {
        this.monExam = monExam;
    }


    public Quiz getMonQuiz() {
        return monQuiz;
    }

    public void setMonQuiz(Quiz monQuiz) {
        this.monQuiz = monQuiz;
    }

    public ArrayList<com.example.elearning.models.TypeQuestion> getTypeQuestion() {
        return TypeQuestion;
    }

    public void setTypeQuestion(ArrayList<com.example.elearning.models.TypeQuestion> typeQuestion) {
        TypeQuestion = typeQuestion;
    }
}
