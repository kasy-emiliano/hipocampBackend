package com.example.elearning.controller;

import com.example.elearning.models.Apprenant;
import com.example.elearning.models.Certificat;
import com.example.elearning.models.CheckExamen;
import com.example.elearning.models.Commentaire;
import com.example.elearning.models.CommentairePayload;
import com.example.elearning.models.Examens;
import com.example.elearning.models.FonctionBase;
import com.example.elearning.models.Formateur;
import com.example.elearning.models.Formation;
import com.example.elearning.models.FormationCommentaire;
import com.example.elearning.models.InsererQuestion;
import com.example.elearning.models.NoteFormation;
import com.example.elearning.models.QuestionExamPff;
import com.example.elearning.models.QuestionExamen;
import com.example.elearning.models.QuestionQuiz;
import com.example.elearning.models.QuestionReponse;
import com.example.elearning.models.Quiz;
import com.example.elearning.models.ReponseQuiz;
import com.example.elearning.models.ReponseRecu;
import com.example.elearning.models.ReponseRecuExam;
import com.example.elearning.models.ReponsesApprenant;
import com.example.elearning.models.ReponsesExamen;
import com.example.elearning.models.mouvementQuiz;
import com.example.elearning.models.pff;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
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
import org.springframework.http.MediaType;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin
@Controller
public class CertificatCotroller {

      
      @GetMapping ("/CetificatAdmis")
    public ResponseEntity<ArrayList<Certificat>> CetificatAdmis(@RequestParam("idExamen") int idExamen,HttpServletRequest request, HttpServletResponse response  ) throws Exception {
        
        String token = request.getParameter("token");
            Apprenant userDetails = FonctionBase.selectWithTokenConnecter(token);
           
    // Récupérer l'idApprenant 
    int idApprenant = userDetails.getIdApprenant();
                 
 
      ArrayList<Certificat> com = new Certificat().CertificatAdmis(idExamen,idApprenant);
 
        return  ResponseEntity.ok(com);         
} 
 
        @GetMapping ("/ListAdmis")
    public ResponseEntity<ArrayList<Certificat>> ListAdmis(@RequestParam("idExamen") int idExamen,@RequestParam("idFormation") int idFormation) throws Exception {
        
      ArrayList<Certificat> com = new Certificat().ListAdmis(idFormation,idExamen);
 
        return  ResponseEntity.ok(com);         
} 

        @GetMapping("/noteTotal")
    public ResponseEntity<Integer> noteTotal(@RequestParam("idExamen") int idExamen) throws Exception {
        

        int count = new Certificat().noteTotal(idExamen);
        return ResponseEntity.ok(count);
    }
        @GetMapping("/pourcentage")
    public ResponseEntity<Double> pourcentage(@RequestParam("idFormation") int idFormation,@RequestParam("idExamen") int idExamen) throws Exception {
        

        double pourcentage = new Certificat().pourcentage(idFormation,idExamen);
        return ResponseEntity.ok(pourcentage);
    }

    
}
