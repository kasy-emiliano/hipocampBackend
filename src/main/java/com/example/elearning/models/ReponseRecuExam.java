package com.example.elearning.models;

public class ReponseRecuExam {

    private Long id;
    private String text;
    private boolean checked;
    private String note;
    private double typeReponses;

    public Double getTypeReponses() {
        return typeReponses;
    }

    public void setTypeReponses(Double typeReponses) {
        this.typeReponses = typeReponses;
    }


    public ReponseRecuExam() {
    }

    public ReponseRecuExam(Long id, String text, boolean checked, String note, double typeReponses) {
        this.id = id;
        this.text = text;
        this.checked = checked;
        this.note = note;
        this.typeReponses = typeReponses;
    }

     

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
