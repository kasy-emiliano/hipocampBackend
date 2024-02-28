package com.example.elearning.controller;

import com.example.elearning.models.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@CrossOrigin
@Controller
public class AdminController {


    @PostMapping("/LoginAdmin")
    public ResponseEntity<String>LoginAdmin(@RequestParam("email") String email, @RequestParam("password") String password) throws Exception {
        // Supposons que vous ayez une classe UserDetails qui représente les détails de l'utilisateur
        Admin userDetails = FonctionBase.LoginAdmin(email,password);
        if(userDetails==null) {
            // Ajoutez d'autres détails de l'utilisateur
            System.out.println("kjj");
            return ResponseEntity.badRequest().body("pff");
        }
        System.out.println("k");
        return ResponseEntity.ok("suraa");
    }










    @PostMapping("/InsererCategorie")
    public ResponseEntity<String> InsererCategorie(@RequestParam("categorie") String nom) throws Exception {

        Categorie c=new Categorie();

        c.inserer(nom);

        return  ResponseEntity.ok("ok");
    }


    @PostMapping("/InsererPublicite")
    public ResponseEntity<String> InsererPublicite(@RequestParam("categorie") String nom) throws Exception {

        Categorie c=new Categorie();

        c.inserer(nom);

        return  ResponseEntity.ok("ok");
    }



    @PostMapping("/ListePublicite")
    public ResponseEntity<String> ListePublicite(@RequestParam("categorie") String nom) throws Exception {

        Categorie c=new Categorie();

        c.inserer(nom);

        return  ResponseEntity.ok("ok");
    }





    @GetMapping("/ListFormationNonValide")
    public ResponseEntity<ArrayList<Formation>> ListFormationNonValide() throws Exception {

        Categorie c=new Categorie();


ArrayList<Formation> rep= FonctionBase.LesFormationNonValide();
        return  ResponseEntity.ok(rep);
    }






    @GetMapping ("/ValidationFormation")
    public ResponseEntity<String> ValidationFormation(@RequestParam("idFormation") String idFormation  ) throws Exception {



        Admin rep=new Admin();


        rep.ValidationFormation(Integer.parseInt(idFormation));
        return  ResponseEntity.ok("cool");
    }



    @GetMapping ("/ListApprenant")
    public ResponseEntity<ArrayList<Apprenant>> ListApprenant() throws Exception {

ArrayList<Apprenant>rep=FonctionBase.ListApprenant();



        return  ResponseEntity.ok(rep);
    }

    @GetMapping ("/ListFormateur")
    public ResponseEntity<ArrayList<Formateur>> ListFormation() throws Exception {

        ArrayList<Formateur>rep=FonctionBase.ListFormateur();



        return  ResponseEntity.ok(rep);
    }





}
