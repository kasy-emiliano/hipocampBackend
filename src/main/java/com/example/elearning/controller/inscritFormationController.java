package com.example.elearning.controller;

import com.example.elearning.models.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@CrossOrigin
@Controller
public class inscritFormationController {


    @PostMapping("/inscrireWithId")
    public ResponseEntity<String> inscrireWithId( @RequestParam("idApprenant") String idApprenant,@RequestParam("idFormation") String idFormation) throws Exception {
        // Supposons que vous ayez une classe UserDetails qui représente les détails de l'utilisateur
        inscritFormation ins=new inscritFormation();

        ins.insererWithid(Integer.parseInt(idApprenant),Integer.parseInt(idFormation));
        int S= FonctionBase.voalohany(Integer.parseInt(idFormation));
        mouvementChapitres m=new mouvementChapitres();
        m.inserer(Integer.parseInt(idApprenant),Integer.parseInt(idFormation),S);
        return ResponseEntity.ok("ok");
    }

    @PostMapping("/inscrireWithemail")
    public ResponseEntity<String> inscrireWithemail(@RequestParam("email") String email,@RequestParam("idFormation") String idFormation) throws Exception {
        // Supposons que vous ayez une classe UserDetails qui représente les détails de l'utilisateur
        inscritFormation ins=new inscritFormation();
        inscritFormation inss=new inscritFormation();
        Apprenant A=new Apprenant();
        int idApprenant=0;
        int S= 0;
        mouvementChapitres m=new mouvementChapitres();

        A=FonctionBase.findByEmail(email);


        idApprenant=A.getIdApprenant();
        ins=FonctionBase.Suivie(idApprenant,Integer.parseInt(idFormation));

        Formation form=new Formation();
        double prix=form.prixFormation(Integer.parseInt(idFormation));
        if(ins!=null){

            System.out.println("hihi");
        }
        else {
            inss.insererWithemail(email, Integer.parseInt(idFormation),prix);
            S = FonctionBase.voalohany(Integer.parseInt(idFormation));
            m = new mouvementChapitres();
            m.inserer(A.getIdApprenant(), Integer.parseInt(idFormation), S);


        }






        return ResponseEntity.ok("ok");
    }


    @PostMapping("/inscrireWithcsv")
    public ResponseEntity<String> inscrireWithcsv(@RequestParam("file") MultipartFile file, @RequestParam("idFormation") String idFormation) throws Exception {
        // Supposons que vous ayez une classe UserDetails qui représente les détails de l'utilisateur
        inscritFormation ins=new inscritFormation();
        inscritFormation inss=new inscritFormation();
        Apprenant A=new Apprenant();
        int idApprenant=0;
        int S= 0;
        mouvementChapitres m=new mouvementChapitres();
        if (file.isEmpty()) {
            // Le fichier est vide, gérer l'erreur si nécessaire
            return ResponseEntity.ok("tsisy");
        }

        System.out.println("tsitsi");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            // Lire le fichier CSV ligne par ligne et traiter les données
            String line;
            while ((line = reader.readLine()) != null) {
                // Ici, vous pouvez traiter chaque ligne du fichier CSV
                // par exemple, en utilisant un parseur CSV ou en le stockant dans une base de données
                System.out.println("Ligne CSV : " + line);


                 A=FonctionBase.findByEmail(line);


                 idApprenant=A.getIdApprenant();
                 ins=FonctionBase.Suivie(idApprenant,Integer.parseInt(idFormation));
                 
        Formation form=new Formation();
        double prix=form.prixFormation(Integer.parseInt(idFormation));

                if(ins!=null){

System.out.println("hihi");
                }
                else {
                    inss.insererWithemail(line, Integer.parseInt(idFormation),prix);
                    S = FonctionBase.voalohany(Integer.parseInt(idFormation));
                    m = new mouvementChapitres();
                    m.inserer(A.getIdApprenant(), Integer.parseInt(idFormation), S);


                }






            }
        } catch (IOException e) {
            e.printStackTrace(); // Gérer l'exception si nécessaire
        }
        return ResponseEntity.ok("ok");
    }







}
