package com.example.elearning.controller;

import com.example.elearning.models.Apprenant;
import com.example.elearning.models.Commentaire;
import com.example.elearning.models.FonctionBase;
import com.example.elearning.models.Formateur;
import com.example.elearning.models.Formation;
import com.example.elearning.models.FormationCommentaire;
import com.example.elearning.models.FormationReponseCommentaire;
import com.example.elearning.models.ReponseCommentaire;
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
public class ReponseCommentaireController {

    @PostMapping("/AjoutReponseCommentaireApprenant")
    public ResponseEntity<String> commentaire(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) throws Exception {
        String idCommentaireParam = request.getParameter("idCommentaire");

        // Vérifier si idFormationParam n'est pas null
        if (idCommentaireParam != null) {
            try {
                int idCommentaire = Integer.parseInt(idCommentaireParam);

                String reponsecommentaire = request.getParameter("reponsecommentaire");
                String token = request.getParameter("token");
                Apprenant userDetails = FonctionBase.selectWithTokenConnecter(token);

                ReponseCommentaire com = new ReponseCommentaire();

                if (userDetails != null) {
                    // Récupérer l'idApprenant
                    System.out.println("Apprenant ID: " + userDetails.getIdApprenant());
                    System.out.println("Apprenant Nom: " + userDetails.getNom());
                    System.out.println("Apprenant Nom: " + userDetails.getPrenom());
                    int idApprenant = userDetails.getIdApprenant();

                    
                    
                    com.insertReponseCommentaireApprenant(idCommentaire, idApprenant, reponsecommentaire);

                    return ResponseEntity.ok("ReponseCommentaire ajouté avec succès");

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

    @PostMapping("/AjoutReponseCommentaireFormateur")
    public ResponseEntity<String> commentaireFormateur(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) throws Exception {
        String idCommentaireParam = request.getParameter("idCommentaire");

        // Vérifier si idFormationParam n'est pas null
        if (idCommentaireParam != null) {
            try {
                int idCommentaire = Integer.parseInt(idCommentaireParam);

                String reponsecommentaire = request.getParameter("reponsecommentaire");
                String token = request.getParameter("token");
                Formateur userDetails = FonctionBase.selectWithTokenFConnecter(token);

                ReponseCommentaire com = new ReponseCommentaire();

                if (userDetails != null) {
                    // Récupérer l'idApprenant
                    System.out.println("Formateur ID: " + userDetails.getIdFormateur());
                    System.out.println("Formateur Nom: " + userDetails.getNom());
                    System.out.println("Formateur Nom: " + userDetails.getPrenom());

                    int idFormateur = userDetails.getIdFormateur();

                    com.insertReponsesCommentaireFormateur(idCommentaire, idFormateur, reponsecommentaire);

                    return ResponseEntity.ok("Reponse Commentaire ajouté avec succès");

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

    @GetMapping("/LesReponsesCommentaires")
    public ResponseEntity<ArrayList<FormationReponseCommentaire>> LesCommentaires(@RequestParam("idCommentaire") int idCommentaire) throws Exception {
        ArrayList<FormationReponseCommentaire> com = new FormationReponseCommentaire().FormationReponseCommentaire(idCommentaire);
        return ResponseEntity.ok(com);
    }
}
