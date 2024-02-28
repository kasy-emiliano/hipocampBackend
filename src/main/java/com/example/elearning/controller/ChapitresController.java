package com.example.elearning.controller;

import com.example.elearning.models.*;
import org.apache.tika.Tika;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Objects;

@CrossOrigin
@Controller
public class ChapitresController {



    @PostMapping("/newChapitres")
    public ResponseEntity<String> newChapitres(@RequestParam("idFormation") String idFormation, @RequestParam("chapitre") String titre) throws Exception {
        // Supposons que vous ayez une classe UserDetails qui représente les détails de l'utilisateur
      Chapitres c=new Chapitres();
      c.inserer(Integer.parseInt(idFormation),titre);
        return ResponseEntity.ok("ok");
    }



    @PostMapping("/newSousChapitres")
    public ResponseEntity<String> newSousChapitres(@RequestParam("idChapitre") String idChapitre, @RequestParam("titre") String titre) throws Exception {
        // Supposons que vous ayez une classe UserDetails qui représente les détails de l'utilisateur
        SousChapitres c=new SousChapitres();
        c.inserer(Integer.parseInt(idChapitre),titre);
        return ResponseEntity.ok("ok");

    }

    @PostMapping("/addSousChapitreT")
    public ResponseEntity<String> addSousChapitreT(@RequestParam("idSousChapitres") String idSousChapitres, @RequestParam("text") String text) throws Exception {
        // Supposons que vous ayez une classe UserDetails qui représente les détails de l'utilisateur

        ContenuSousChapitres C=new ContenuSousChapitres();
        C.inserer(Integer.parseInt(idSousChapitres),text,"","text");
        return ResponseEntity.ok("ok");

    }


    @PostMapping("/addSousChapitreL")
    public ResponseEntity<String> addSousChapitreL(@RequestParam("idSousChapitres") String idSousChapitres, @RequestParam("text") String text,@RequestParam("legende") String legende) throws Exception {
        // Supposons que vous ayez une classe UserDetails qui représente les détails de l'utilisateur

        ContenuSousChapitres C=new ContenuSousChapitres();
        C.inserer(Integer.parseInt(idSousChapitres),text,legende,"lien");
        return ResponseEntity.ok("ok");

    }





    @PostMapping("/addSousChapitre")
    public ResponseEntity<String> addSousChapitre(@RequestParam("idSousChapitres") String idSousChapitres ,@RequestParam("files") MultipartFile file, @RequestParam("legende") String texts) throws Exception {
        ContenuSousChapitres C=new ContenuSousChapitres();
      int i=0;
String UPLOAD_DIR="";
            try {
                String fileType = new Tika().detect(file.getInputStream());
                String filePath = "";
                if (fileType.startsWith("image")) {
                    // Upload to image folder

UPLOAD_DIR="image/";



                    try {
                        // Vérifiez si le répertoire de stockage existe, sinon, créez-le
                        File uploadDir = new File(UPLOAD_DIR);
                        if (!uploadDir.exists()) {
                            uploadDir.mkdirs();
                        }

                        // Récupérez le nom du fichier téléchargé
                        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

                        // Construisez le chemin complet de stockage
                        filePath = UPLOAD_DIR + fileName;

                        // Copiez le fichier dans le répertoire de stockage
                        Path targetLocation = Paths.get(filePath);
                        Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
                    } catch (Exception e) {
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors du téléchargement de l'image : " + e.getMessage());
                    }










                    C.inserer(Integer.parseInt(idSousChapitres),filePath,texts,"image");
                    i++;

                } else if (fileType.startsWith("video")) {
                    // Upload to video folder
                    UPLOAD_DIR="video/";



                    try {
                        // Vérifiez si le répertoire de stockage existe, sinon, créez-le
                        File uploadDir = new File(UPLOAD_DIR);
                        if (!uploadDir.exists()) {
                            uploadDir.mkdirs();
                        }

                        // Récupérez le nom du fichier téléchargé
                        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

                        // Construisez le chemin complet de stockage
                        filePath = UPLOAD_DIR + fileName;

                        // Copiez le fichier dans le répertoire de stockage
                        Path targetLocation = Paths.get(filePath);
                        Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
                    } catch (Exception e) {
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors du téléchargement de l'image : " + e.getMessage());
                    }







                    C.inserer(Integer.parseInt(idSousChapitres),filePath,texts,"video");
                    i++;
                } else if (fileType.equals("application/pdf")) {
                    // Upload to pdf folder
                    UPLOAD_DIR="pdf/";



                    try {
                        // Vérifiez si le répertoire de stockage existe, sinon, créez-le
                        File uploadDir = new File(UPLOAD_DIR);
                        if (!uploadDir.exists()) {
                            uploadDir.mkdirs();
                        }

                        // Récupérez le nom du fichier téléchargé
                        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

                        // Construisez le chemin complet de stockage
                        filePath = UPLOAD_DIR + fileName;

                        // Copiez le fichier dans le répertoire de stockage
                        Path targetLocation = Paths.get(filePath);
                        Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
                    } catch (Exception e) {
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors du téléchargement de l'image : " + e.getMessage());
                    }







                    C.inserer(Integer.parseInt(idSousChapitres),filePath,texts,"pdf");
                    i++;
                } else {
                    // Handle other types of files
                    // Your logic here
                }

            } catch (IOException e) {
                e.printStackTrace();
                // Handle the exception
            }


        // Process other form data
        // Your logic here

        return ResponseEntity.ok("Course created successfully.");
    }

    @GetMapping("/ContenuSousChapitre")
    public ResponseEntity<ArrayList<ContenuSousChapitres>> ContenuSousChapitre(@RequestParam("idSousChapitre") String idSousChapitre) throws Exception {
        // Supposons que vous ayez une classe UserDetails qui représente les détails de l'utilisateur
        ArrayList<ContenuSousChapitres> c=FonctionBase.allContenuSousChapitres(Integer.parseInt(idSousChapitre));
        return ResponseEntity.ok(c);
    }




}
