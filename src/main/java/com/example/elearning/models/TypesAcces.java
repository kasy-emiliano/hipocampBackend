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
@ClassAnotation(table = "TypesAcces")
public class TypesAcces {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Attr(isPrimary = true)
    private int  idTypesAcces;
    @Attr
    private String nom;

    public int getIdTypesAcces() {
        return idTypesAcces;
    }

    public void setIdTypesAcces(int idTypesAcces) {
        this.idTypesAcces = idTypesAcces;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public TypesAcces(int idTypesAcces, String nom) {
        this.idTypesAcces = idTypesAcces;
        this.nom = nom;
    }

    public TypesAcces() {
    }

 public ArrayList<TypesAcces> ListeTypesAcces() throws Exception {
        ArrayList<TypesAcces> listeEmp = GenericDAO.findBySql(new TypesAcces(), "select * from TypesAcces ", new FonctionBase().connect());
        return listeEmp;
    }

}
