package com.example.elearning.controller;

import com.example.elearning.Res;
import com.example.elearning.Service;
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
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.GeneralSecurityException;
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
import org.springframework.beans.factory.annotation.Autowired;

@CrossOrigin
@Controller
public class PubliciteController {
@Autowired
    private Service service;

    @PostMapping("/AjoutPublicite")
    public Object handleFileUpload(@RequestParam("image") MultipartFile file,HttpServletRequest request,
            HttpServletResponse response) throws IOException, GeneralSecurityException, Exception {
        if (file.isEmpty()) {
        return "File is empty";
    }
        
        String imageUrl = service.uploadImageToDrive(file);
    System.out.println("Uploaded image URL: " + imageUrl);

    Publicite pub=new Publicite();
    
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
  
        if (dateFin.isBefore(dateDebut)) {
 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("L'apprenant a déjà voté pour cette formation.");
} else {

         pub.insertPublicite2(nomOrganisme,imageUrl, lien, email, contact, datedebut, datefin, Duree, titre, montantParJours, resumer);
       
}


    return "Image successfully uploaded and link inserted into database.";
}

    @GetMapping ("/LesPublicite")
    public ResponseEntity<ArrayList<Publicite>> LesPublicite() throws Exception {
 ArrayList<Publicite> com = new Publicite().listePublicitePrensent();
        
        return  ResponseEntity.ok(com);
    }  
}
