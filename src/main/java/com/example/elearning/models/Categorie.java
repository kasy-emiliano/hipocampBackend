package com.example.elearning.models;

import com.example.elearning.generic.Attr;
import com.example.elearning.generic.ClassAnotation;
import com.example.elearning.generic.GenericDAO;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
@ClassAnotation(table = "categorie")
public class Categorie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Attr(isPrimary = true)
    private int  idCategorie;
    @Attr
    private String nom;

    public Categorie(){


    }

    public Categorie(int idCategorie, String nom) {
        this.idCategorie = idCategorie;
        this.nom = nom;
    }

    public void inserer(String n) throws Exception {

        String sql="Insert into Categorie(nom)values('"+n+"')";
        FonctionBase.execute(sql);
    }
    public void modifier(int i,String n) throws Exception {

        String sql="update Categorie set nom='"+n+"' where idcategorie="+i;
        FonctionBase.execute(sql);
    }

    public void supprimer(int i) throws Exception {

        String sql="Insert into DeleteCategorie(idcategorie)values("+i+")";
        FonctionBase.execute(sql);
    }


    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public ArrayList<Categorie> ListeCategorie() throws Exception {
        ArrayList<Categorie> listeEmp = GenericDAO.findBySql(new Categorie(), "select * from Categorie ", new FonctionBase().connect());
        return listeEmp;
    }
}
