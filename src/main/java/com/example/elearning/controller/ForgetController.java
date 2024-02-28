package com.example.elearning.controller;

import com.example.elearning.models.ActivationTokenGenerator;
import com.example.elearning.models.Apprenant;
import com.example.elearning.models.FonctionBase;
import com.example.elearning.models.Formateur;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
@CrossOrigin
@Controller
public class ForgetController {

    @PostMapping("/forgotpassword")
    public ResponseEntity<String> processForgotPassword(@RequestParam("email") String email) throws Exception {
        // Vérifiez si l'utilisateur existe
System.out.println(email);
        Apprenant user = FonctionBase.findByEmail(email);
        if (user == null) {
            System.out.println("tsai");
            return ResponseEntity.badRequest().body("tsy azo");
        }


        Apprenant A=new Apprenant();
        A.insererForget(user.getToken());
        return ResponseEntity.ok("super");
    }



    @PostMapping("/resetpassword")
    public ResponseEntity<String> processResetPassword(@RequestParam("password") String password, @RequestParam("token") String token, Model model) throws Exception {
        // Vérifiez si le jeton est valide

        Apprenant user = FonctionBase.selectWithToken(token);
        if (user == null) {
            System.out.println("tsai");
            return ResponseEntity.badRequest().body("le token n'existe pas");
        }

Apprenant A=new Apprenant();
    A.updateMdp(token,password);
        return ResponseEntity.ok("super");

    }



    @PostMapping("/forgotpasswordF")
    public ResponseEntity<String> processForgotPasswordF(@RequestParam("email") String email) throws Exception {
        // Vérifiez si l'utilisateur existe
        System.out.println(email);
        Formateur user = FonctionBase.findByEmailA(email);
        if (user == null) {
            System.out.println("tsai");
            return ResponseEntity.badRequest().body("tsy azo");
        }


        Formateur A=new Formateur();


        A.insererForget(user.getToken());
        return ResponseEntity.ok("super");
    }



    @PostMapping("/resetpasswordF")
    public ResponseEntity<String> processResetPasswordF(@RequestParam("password") String password, @RequestParam("token") String token, Model model) throws Exception {
        // Vérifiez si le jeton est valide

        Formateur user = FonctionBase.selectWithTokenF(token);
        if (user == null) {
            System.out.println("tsai");
            return ResponseEntity.badRequest().body("le token n'existe pas");
        }

        Formateur A=new Formateur();
        A.updateMdp(token,password);
        return ResponseEntity.ok("super");

    }













}
