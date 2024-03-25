package com.example.elearning.controller;

import com.example.elearning.models.Apprenant;
import com.example.elearning.models.Commentaire;
import com.example.elearning.models.CommentairePayload;
import com.example.elearning.models.FonctionBase;
import com.example.elearning.models.Formateur;
import com.example.elearning.models.Formation;
import com.example.elearning.models.FormationCommentaire;
import com.google.gson.Gson;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PathVariable;

@CrossOrigin
@Controller
public class CommentaireController {

 //@Autowired
    //private SimpMessagingTemplate messagingTemplate;
@PostMapping("/AjoutCommentaire")
 //@SendTo("/topic/public")
public ResponseEntity<String> commentaireApprenant(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //String token = message.getToken();
    // Vérifier si idFormationParam n'est pas null
           String idFormationParam = request.getParameter("idFormation");

    // Vérifier si idFormationParam n'est pas null
    if (idFormationParam != null) {
        try {
            int idFormation = Integer.parseInt(idFormationParam);

            String commentaire = request.getParameter("Commentaire");
            String token = request.getParameter("token");
            Apprenant userDetails = FonctionBase.selectWithTokenConnecter(token);

            Commentaire com = new Commentaire();

            if (userDetails != null) {
                // Récupérer l'idApprenant
                System.out.println("Formateur ID: " + userDetails.getIdApprenant());
                System.out.println("Formateur Nom: " + userDetails.getNom());

                int idApprenant = userDetails.getIdApprenant();

               
                com.insertCommentaire1(idFormation,idApprenant,commentaire);
                
                return ResponseEntity.ok("Commentaire ajouté avec succès");
                
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
         
                //com.insertCommentaireFormateur(message.getIdFormation(), idApprenant, message.getCommentaire());

                //messagingTemplate.convertAndSend("/topic/public", com);
      
}

@PostMapping("/AjoutCommentaireFormateur")
public ResponseEntity<String> commentaireFormateur(HttpServletRequest request, HttpServletResponse response) throws Exception {
    String idFormationParam = request.getParameter("idFormation");

    // Vérifier si idFormationParam n'est pas null
    if (idFormationParam != null) {
        try {
            int idFormation = Integer.parseInt(idFormationParam);

            String commentaire = request.getParameter("Commentaire");
            String token = request.getParameter("token");
            Formateur userDetails = FonctionBase.selectWithTokenFConnecter(token);

            Commentaire com = new Commentaire();

            if (userDetails != null) {
                // Récupérer l'idApprenant
                System.out.println("Formateur ID: " + userDetails.getIdFormateur());
                System.out.println("Formateur Nom: " + userDetails.getNom());

                int idFormateur = userDetails.getIdFormateur();

               
                com.insertCommentaireFormateur(idFormation,idFormateur,commentaire);
                
                return ResponseEntity.ok("Commentaire ajouté avec succès");
                
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
   
   /*@GetMapping ("/Commentaires")
    public ResponseEntity<ArrayList<FormationCommentaire>> Commentaires(@RequestParam("idCommentaire") int idCommentaire  ) throws Exception {
 ArrayList<FormationCommentaire> com = new FormationCommentaire().ListeCommentaire(idCommentaire);
        return  ResponseEntity.ok(com);
    }*/

      @GetMapping ("/LesCommentaires")
    public ResponseEntity<ArrayList<FormationCommentaire>> LesCommentaires(@RequestParam("idFormation") int idFormation  ) throws Exception {
 ArrayList<FormationCommentaire> com = new FormationCommentaire().FormationCommentaire(idFormation);
        return  ResponseEntity.ok(com);
    } 
   
    
      
      /*@GetMapping("/commentaires/{idFormation}")
    public ResponseEntity<ArrayList<FormationCommentaire>> getCommentaires(@PathVariable int idFormation) throws Exception {
        // Récupérer les commentaires de la base de données pour la formation spécifiée
        ArrayList<FormationCommentaire> commentaires = new FormationCommentaire().FormationCommentaire(idFormation);// Logique pour récupérer les commentaires
        
        // Envoyer les commentaires via WebSocket
        messagingTemplate.convertAndSend("/topic/commentaires/" + idFormation, commentaires);

        return ResponseEntity.ok(commentaires);
    }*/
}
