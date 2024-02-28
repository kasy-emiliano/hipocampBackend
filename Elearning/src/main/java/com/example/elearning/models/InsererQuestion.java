package com.example.elearning.models;

import java.util.ArrayList;

public class InsererQuestion {

    private Quiz monQuiz;
    private ArrayList<TypeQuestion> TypeQuestion;


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
