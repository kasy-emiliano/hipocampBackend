package com.example.elearning.controller;

import com.example.elearning.models.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ZeroCopyHttpOutputMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@CrossOrigin
@Controller
public class ZoomController {

    @PostMapping("/newZoom")
    public ResponseEntity<String> newZoom(@RequestParam("idFormation") String idFormation,@RequestParam("titre") String titre,@RequestParam("daty") String daty,@RequestParam("heureDeb") String heureDeb,@RequestParam("minuteDeb") String minuteDeb,@RequestParam("heureFin") String heureFin,@RequestParam("minuteFin") String minuteFin,@RequestParam("FuseauxHoraire") String FuseauxHoraire,@RequestParam("lien") String lien) throws Exception {
        // Supposons que vous ayez une classe UserDetails qui représente les détails de l'utilisateur
Zoom c=new Zoom();
        c.inserer(Integer.parseInt( idFormation),  titre,  daty, heureDeb,minuteDeb,heureFin,minuteFin ,FuseauxHoraire,  lien);
        return ResponseEntity.ok("ok");
    }

    @PostMapping("/ModifZoom")
    public ResponseEntity<String> ModifZoom(@RequestParam("idZoom") String idZoom,@RequestParam("idFormation") String idFormation,@RequestParam("titre") String titre,@RequestParam("daty") String daty,@RequestParam("heureDeb") String heureDeb,@RequestParam("minuteDeb") String minuteDeb,@RequestParam("heureFin") String heureFin,@RequestParam("minuteFin") String minuteFin,@RequestParam("FuseauxHoraire") String FuseauxHoraire,@RequestParam("lien") String lien) throws Exception {
        // Supposons que vous ayez une classe UserDetails qui représente les détails de l'utilisateur
        Zoom c=new Zoom();
        c.modifier(Integer.parseInt(idZoom),Integer.parseInt( idFormation),  titre,  daty, heureDeb,minuteDeb,minuteFin, heureFin,FuseauxHoraire,  lien);
        return ResponseEntity.ok("ok");
    }

    @GetMapping ("/SupprimerZoom")
    public ResponseEntity<String> SupprimerZoom(@RequestParam("idZoom") String idZoom) throws Exception {
        // Supposons que vous ayez une classe UserDetails qui représente les détails de l'utilisateur
        Zoom c=new Zoom();
        System.out.println(idZoom);
        c.supprimer(Integer.parseInt(idZoom));


        System.out.println(c.getLien());
        return ResponseEntity.ok("tsss");
    }




    @GetMapping ("/MonZoom")
    public ResponseEntity<Zoom> MonZoom(@RequestParam("idZoom") String idZoom) throws Exception {
        // Supposons que vous ayez une classe UserDetails qui représente les détails de l'utilisateur
        Zoom c=new Zoom();
        System.out.println(idZoom);
        c= FonctionBase.MonZoom(Integer.parseInt(idZoom));


        System.out.println(c.getLien());
        return ResponseEntity.ok(c);
    }

    @GetMapping ("/VoirZoom")
    public ResponseEntity<String> VoirZoom(@RequestParam("token") String token,@RequestParam("idFormation") String idFormation,@RequestParam("idZoom") String idZoom) throws Exception {
        // Supposons que vous ayez une classe UserDetails qui représente les détails de l'utilisateur
        Zoom c=new Zoom();
        System.out.println(idZoom);
        Apprenant A=FonctionBase.selectWithTokenConnecter(token);
        c= FonctionBase.MonZoom(Integer.parseInt(idZoom));
mouvementZoom Z=new mouvementZoom();



        mouvementZoom ze=FonctionBase.aove(A.getIdApprenant(),c.getIdZoom());
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/elearning", "postgres", "0000")) {

            // Obtenez la date et l'heure actuelles
            LocalDateTime now = LocalDateTime.now();

            // Requête SQL pour récupérer les enregistrements de la table Zoom


            // Requête SQL pour récupérer les enregistrements de la table Zoom
            String sql = "SELECT * FROM Zoom WHERE daty = ? " +
                    "AND (CAST(HeureDeb AS TIME) < ? OR (CAST(HeureDeb AS TIME) = ? AND CAST(minuteDeb AS INTEGER) <= ?)) " +
                    "AND (CAST(HeureFin AS TIME) > ? OR (CAST(HeureFin AS TIME) = ? AND CAST(minuteFin AS INTEGER) >= ?))";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setDate(1, java.sql.Date.valueOf(now.toLocalDate()));
                preparedStatement.setInt(2, now.getHour());
                preparedStatement.setInt(3, now.getHour());
                preparedStatement.setInt(4, now.getMinute());
                preparedStatement.setInt(5, now.getHour());
                preparedStatement.setInt(6, now.getHour());
                preparedStatement.setInt(7, now.getMinute());

                // Exécutez la requête
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        System.out.println("La date et l'heure actuelles sont entre " );
if(ze==null) {
    Z.insert(A.getIdApprenant(), Integer.parseInt(idFormation), c.getIdZoom());

}
                    } else {
                        System.out.println("La date et l'heure actuelles ne sont pas entre les heures spécifiées pour la date spécifiée.");
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(c.getLien());
        return ResponseEntity.ok("hihi");
    }













}
