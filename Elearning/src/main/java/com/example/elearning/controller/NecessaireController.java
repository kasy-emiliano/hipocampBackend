package com.example.elearning.controller;

import com.example.elearning.models.ActivationTokenGenerator;
import com.example.elearning.models.Apprenant;
import com.example.elearning.models.FonctionBase;
import com.example.elearning.models.inscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
@CrossOrigin
@Controller
public class NecessaireController {

    @GetMapping("/details")
    public ResponseEntity<inscription> getUserDetails() throws Exception {
        // Supposons que vous ayez plusieurs objets à envoyer

        inscription userDetails = new inscription();
        userDetails.setAllcivilite(FonctionBase.Allcivilite());

        userDetails.setAllprofession(FonctionBase.AllProfession());

        System.out.println(userDetails.getAllprofession().get(0).getNom());
        userDetails.setAllmodeDexercice(FonctionBase.AllmodeDexercice());


        return ResponseEntity.ok(userDetails);
    }



















    @GetMapping("/total-pages")
    public ResponseEntity<Long> getTotalPages() throws Exception {

        int totalPages = FonctionBase.CountAllDemande();
        int totalRecords = totalPages; // Nombre total d'enregistrements dans votre base de données
        int pageSize = 10; // Nombre d'éléments à afficher par page
        int rep= (int) Math.ceil((double) totalRecords / pageSize);
        return ResponseEntity.ok((long)rep);
    }











}
