package com.example.elearning.controller;


import com.example.elearning.models.Categorie;
import com.example.elearning.models.FonctionBase;
import java.util.ArrayList;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin
@Controller


public class CategorieController {
    
       @GetMapping ("/LesCategorie")
    public ResponseEntity<ArrayList<Categorie>> LesCategorie() throws Exception {
 ArrayList<Categorie> com = new FonctionBase().AllCategorie();

        return  ResponseEntity.ok(com);
    } 
   
    
    @PostMapping("/newCategorie")
    public ResponseEntity<String> newCategorie(@RequestParam("titre") String titre) throws Exception {
        // Supposons que vous ayez une classe UserDetails qui représente les détails de l'utilisateur
       Categorie c=new Categorie();
        c.inserer(titre);
        return ResponseEntity.ok("ok");

    }
    
    @PostMapping("/modifCategorie")
    public ResponseEntity<String> modifCategorie(@RequestParam("idCategorie") String idChapitre, @RequestParam("titre") String titre) throws Exception {
        // Supposons que vous ayez une classe UserDetails qui représente les détails de l'utilisateur
   Categorie c=new Categorie();
        c.modifier(Integer.parseInt(idChapitre),titre);
        return ResponseEntity.ok("ok");

    }
    
    @PostMapping("/supprimerCategorie")
    public ResponseEntity<String> supprimerCategorie(@RequestParam("idCategorie") String idCategorie) throws Exception {
        // Supposons que vous ayez une classe UserDetails qui représente les détails de l'utilisateur
     Categorie c=new Categorie();
        c.supprimer(Integer.parseInt(idCategorie));
        return ResponseEntity.ok("ok");

    }

}
