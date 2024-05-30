package com.example.elearning.controller;

import com.example.elearning.generic.GenericDAO;
import com.example.elearning.models.*;
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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@CrossOrigin
@Controller
public class PubliciteController {

    @PostMapping("/AjoutPublicite")
    public ResponseEntity<String> AjoutPublicite(HttpServletRequest request,
            HttpServletResponse response, @RequestParam("image") MultipartFile file) throws Exception {

        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        String dateDebutString = request.getParameter("datedebut");
        String dateFinString = request.getParameter("datefin");
        
        LocalDateTime dateDebut = LocalDateTime.parse(dateDebutString, inputFormatter);
        LocalDateTime dateFin = LocalDateTime.parse(dateFinString, inputFormatter);

        String nomOrganisme = request.getParameter("nomorganisme");
        String email = request.getParameter("email");
        String contact = request.getParameter("contact");
        Timestamp datedebut = Timestamp.valueOf(dateDebut);
        Timestamp datefin = Timestamp.valueOf(dateFin);
        String dureeS = request.getParameter("duree");
        String titre = request.getParameter("titre");
        String resumer = request.getParameter("resumer");
        String lien = request.getParameter("lien");
        int Duree = Integer.parseInt(dureeS);
        double montantParJours = Double.parseDouble(request.getParameter("montantParJours"));

        String UPLOAD_DIR = "uploads/";
        String sary="";
        
        try {
            // Vérifiez si le répertoire de stockage existe, sinon, créez-le
            File uploadDir = new File(UPLOAD_DIR);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            // Récupérez le nom du fichier téléchargé
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

            // Construisez le chemin complet de stockage
             sary = UPLOAD_DIR + fileName;

            // Copiez le fichier dans le répertoire de stockage
            Path targetLocation = Paths.get(sary);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors du téléchargement de l'image : " + e.getMessage());
        }
        
        Publicite com = new Publicite();
        
        if (dateFin.isBefore(dateDebut)) {
 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("date fin doit etre apres la date debut");
} else {

        com.insertPublicite2(nomOrganisme, sary, lien, email, contact, datedebut, datefin, Duree, titre, montantParJours, resumer);
       
}

        
        return ResponseEntity.ok("ok");
    }

    @GetMapping ("/LesPublicite")
    public ResponseEntity<ArrayList<Publicite>> LesPublicite() throws Exception {
 ArrayList<Publicite> com = new Publicite().listePublicitePrensent();
        
        return  ResponseEntity.ok(com);
    }  
}
