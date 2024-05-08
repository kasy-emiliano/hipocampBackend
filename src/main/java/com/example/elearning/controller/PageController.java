package com.example.elearning.controller;

import com.example.elearning.models.Apprenant;
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
import com.example.elearning.models.Page;
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
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin
@Controller
public class PageController {

    
    @PostMapping("/InsertConfigurationPage")
    public ResponseEntity<String> InsertConfigurationPage(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String token = request.getParameter("token"); 
        Formateur userDetails = FonctionBase.selectWithTokenFConnecter(token);
        int idFormateur = userDetails.getIdFormateur();
        Page com = new Page();
            boolean informationExist = com.checkIIdPage(idFormateur); //

    if (!informationExist) {
        com.insertConfigPage(idFormateur);
         } else {
        // L'information existe déjà, retourner un message d'erreur ou effectuer une action appropriée
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("L'apprenant a déjà voté pour cette formation.");
    }
        return ResponseEntity.ok("Commentaire ajouté avec succès");

    }
    
     
    

}
