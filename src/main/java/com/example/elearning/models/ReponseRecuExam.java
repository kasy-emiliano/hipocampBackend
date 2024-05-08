package com.example.elearning.models;

public class ReponseRecuExam {

    private Long id;
    private String text;
    private boolean checked;
    private String note;


    public ReponseRecuExam() {
    }

    public ReponseRecuExam(Long id, String texte, String note, boolean isChecked) {
        this.id = id;
        this.text = texte;
        this.note = note;
        this.checked = isChecked;
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
