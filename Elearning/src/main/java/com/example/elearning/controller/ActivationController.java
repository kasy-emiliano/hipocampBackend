package com.example.elearning.controller;

import com.example.elearning.models.Apprenant;
import com.example.elearning.models.FonctionBase;
import com.example.elearning.models.Formateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
@CrossOrigin
@Controller
public class ActivationController {



    @GetMapping("/activation")
    public ResponseEntity<String> activateUser(@RequestParam("token") String token) throws Exception {
        Apprenant user = FonctionBase.selectWithToken(token);
        if (user != null) {
            // Activer le compte de l'utilisateur

            user.activation(token);

            // Rediriger vers une page de confirmation
            return  ResponseEntity.ok("votre compte a ete activer");
        } else {
            // Gérer le cas où le token est invalide ou expiré
            return ResponseEntity.badRequest().body("tsy azo");
        }
    }

    @GetMapping("/Activationpassword")
    public ResponseEntity<String> Activationpassword(@RequestParam("token") String token) throws Exception {
        Apprenant user = FonctionBase.selectWithToken(token);
        if (user != null) {
            // Activer le compte de l'utilisateur



            // Rediriger vers une page de confirmation
            return  ResponseEntity.ok("modifier");
        } else {
            // Gérer le cas où le token est invalide ou expiré
            return ResponseEntity.badRequest().body("le token n'existe pas");
        }
    }
    @GetMapping("/activationF")
    public ResponseEntity<String> activateF(@RequestParam("token") String token) throws Exception {
        Formateur user = FonctionBase.selectWithTokenF(token);
        if (user != null) {
            // Activer le compte de l'utilisateur

            user.activation(token);

            // Rediriger vers une page de confirmation
            return  ResponseEntity.ok("votre compte a ete activer");
        } else {
            // Gérer le cas où le token est invalide ou expiré
            return ResponseEntity.badRequest().body("tsy azo");
        }
    }




}
