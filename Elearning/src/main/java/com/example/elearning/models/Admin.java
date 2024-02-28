package com.example.elearning.models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Admin {

private int idAmin;
private String email;
private String mdp;

    public Admin(int idAmin, String email, String mdp) {
        this.idAmin = idAmin;
        this.email = email;
        this.mdp = mdp;
    }

    public Admin() {

    }

    public void ValiderPublication(int idFormation) throws Exception {
        LocalDate date = LocalDate.now();

        // Formater la date en "année-mois-jour"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = date.format(formatter);

        String sql="Update Formation set etat=2 ,  devalidation ='"+formattedDate+"' Where idFormation="+idFormation;
        FonctionBase.execute(sql);

    }


    public int getIdAmin() {
        return idAmin;
    }

    public void setIdAmin(int idAmin) {
        this.idAmin = idAmin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public void ValidationFormation(int parseInt) throws Exception {

        LocalDate date = LocalDate.now();

        // Formater la date en "année-mois-jour"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = date.format(formatter);

            String sql="UPDATE Formation set etat=2,  devalidation ='"+formattedDate+"' where idFormation="+parseInt;
            FonctionBase.execute(sql);




    }
}
