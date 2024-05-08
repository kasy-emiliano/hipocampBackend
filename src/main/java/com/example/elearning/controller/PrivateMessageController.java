package com.example.elearning.controller;

import com.example.elearning.models.Apprenant;
import com.example.elearning.models.Commentaire;
import com.example.elearning.models.CommentairePayload;
import com.example.elearning.models.FonctionBase;
import com.example.elearning.models.Formateur;
import com.example.elearning.models.Formation;
import com.example.elearning.models.FormationCommentaire;
import com.example.elearning.models.PrivateMessages;
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
public class PrivateMessageController {
 
@PostMapping("/AjoutMessagePriveApprenant")
public ResponseEntity<String> AjoutMessagePriveApprenant(HttpServletRequest request, HttpServletResponse response) throws Exception {
      


            String token = request.getParameter("tokenAp");
            Apprenant userDetails = FonctionBase.selectWithTokenConnecter(token);

               PrivateMessages pv=new PrivateMessages();

            if (userDetails != null) {
                // Récupérer l'idApprenant
                System.out.println("Formateur ID: " + userDetails.getIdApprenant());

                int idApprenant = userDetails.getIdApprenant();

               
               // pv.insertMessagePriveApprenant(idApprenant);
                
                return ResponseEntity.ok("personne ajouté avec succès");
                
            } else {
                // L'Apprenant n'est pas authentifié, retourner une réponse d'erreur
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Non autorisé");
            }
         
     
}

@PostMapping("/AjoutMessagePrive")
public ResponseEntity<String> AjoutMessagePrive(HttpServletRequest request, HttpServletResponse response) throws Exception {

 

            String tokenAp = request.getParameter("tokenAp");
            Apprenant userDetail = FonctionBase.selectWithTokenConnecter(tokenAp);
            String token = request.getParameter("token");
            Formateur userDetails = FonctionBase.selectWithTokenFConnecter(token);

               PrivateMessages pv=new PrivateMessages();
            if (userDetails != null) {
                // Récupérer l'idApprenant
                System.out.println("Formateur ID: " + userDetails.getIdFormateur());

                 System.out.println("Formateur ID: " + userDetail.getIdApprenant());

                int idApprenant = userDetail.getIdApprenant();
                int idFormateur = userDetails.getIdFormateur();

               
                pv.insertMessagePrive(idFormateur,idApprenant);
                
                return ResponseEntity.ok("personne ajouté avec succès");
                
            } else {
                // L'Apprenant n'est pas authentifié, retourner une réponse d'erreur
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Non autorisé");
            }
   
    
}
}
