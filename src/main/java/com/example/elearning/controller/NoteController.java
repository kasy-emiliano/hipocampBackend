package com.example.elearning.controller;

import com.example.elearning.models.Apprenant;
import com.example.elearning.models.ApprenantEtoiles;
import com.example.elearning.models.Commentaire;
import com.example.elearning.models.FonctionBase;
import com.example.elearning.models.Formateur;
import com.example.elearning.models.Formation;
import com.example.elearning.models.FormationCommentaire;
import com.example.elearning.models.NoteFormation;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@CrossOrigin
@Controller
public class NoteController {


@PostMapping("/AjoutNote")
public ResponseEntity<String> ajoutNote(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) throws Exception {
    String idFormationParam = request.getParameter("idFormation");
 String noteString = request.getParameter("note");
    // Vérifier si idFormationParam n'est pas null
    if (idFormationParam != null && noteString !=null ) {
        try {
            int idFormation = Integer.parseInt(idFormationParam);

            int note = Integer.parseInt(noteString);
            //int note=noteNormal*2;
            String token = request.getParameter("token");
            Apprenant userDetails = FonctionBase.selectWithTokenConnecter(token);

            NoteFormation com = new NoteFormation();
           
          
            if (userDetails != null) {
    // Récupérer l'idApprenant
    System.out.println("Apprenant ID: " + userDetails.getIdApprenant());
    System.out.println("Apprenant Nom: " + userDetails.getNom());
    int idApprenant = userDetails.getIdApprenant();
                
    boolean informationExist = com.checkIfDejaNoter(idApprenant,idFormation); //

    if (!informationExist) {
        // L'information n'existe pas encore, ajouter la note
        com.insertnoteformation(idFormation, idApprenant, note);
        
        return ResponseEntity.ok("Note ajouté avec succès");
    } else {
        // L'information existe déjà, retourner un message d'erreur ou effectuer une action appropriée
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("L'apprenant a déjà voté pour cette formation.");
    }
} else {
    // L'Apprenant n'est pas authentifié, retourner une réponse d'erreur
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Non autorisé");
}
        } catch (NumberFormatException e) {
            // Gérer le cas où la conversion en nombre échoue
            return ResponseEntity.badRequest().body("Format de nombre invalide pour idFormation");
        }
    } else {
        // Gérer le cas où idFormationParam est null
        return ResponseEntity.badRequest().body("Paramètre idFormation manquant");
    }
    
}

@GetMapping ("/ApprenantNote")
    public ResponseEntity<ArrayList<NoteFormation>> ApprenantNote(HttpServletRequest request) throws Exception {
  String idFormationParam = request.getParameter("idFormation");
   int idFormation = Integer.parseInt(idFormationParam);
  String token = request.getParameter("token");
           Apprenant userDetails = FonctionBase.selectWithTokenConnecter(token);
                // Récupérer l'idApprenant
                System.out.println("Apprenant ID: " + userDetails.getIdApprenant());
                System.out.println("Apprenant Nom: " + userDetails.getNom());
                
                int idApprenant = userDetails.getIdApprenant();
                
            ArrayList<NoteFormation> com = new NoteFormation().ApprenantNote(idApprenant,idFormation);
            return  ResponseEntity.ok(com);
}
    @GetMapping ("/moyenne")
public ResponseEntity<Double> moyenne(@RequestParam("idFormation") int idFormation) {
    try {
        NoteFormation note = new NoteFormation();
        double moyenne = note.Moyenne(idFormation);
        return ResponseEntity.ok(moyenne);
    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
}

@GetMapping ("/ApprenantEtoiles")
    public ResponseEntity<ArrayList<ApprenantEtoiles>> ApprenantEtoiles(HttpServletRequest request) throws Exception {
  String idFormationParam = request.getParameter("idFormation");
   int idFormation = Integer.parseInt(idFormationParam);
                
            ArrayList<ApprenantEtoiles> com = new ApprenantEtoiles().ApprenantEtoile(idFormation);
            return  ResponseEntity.ok(com);
}
    
}
