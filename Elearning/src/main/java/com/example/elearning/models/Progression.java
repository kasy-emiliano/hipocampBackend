package com.example.elearning.models;

import java.sql.Connection;

public class Progression {

    public long progresssion(double centpourcent,double valeur){
System.out.println("cent:"+centpourcent);
        System.out.println("valeur:"+valeur);
      double  rep=0.0;
rep=(valeur*100)/centpourcent;

        long nombreArrondi = Math.round(rep);
      return nombreArrondi;
    }


    public long progresssionA(int idApprenant,int idFormation) throws Exception {

        Connection c=FonctionBase.connect();

        double cent=FonctionBase.cent(idFormation);
        double valeur=FonctionBase.nyvitany(idApprenant,idFormation,c);
      long  rep=this.progresssion(cent,valeur);
      c.close();
        return rep;
    }



}
