package com.example.elearning.controller;

import com.example.elearning.models.Apprenant;
import com.example.elearning.models.FonctionBase;
import com.example.elearning.models.Formateur;
import com.example.elearning.models.MessagePrive;
import com.example.elearning.models.Messages;
import com.example.elearning.models.PrivateMessages;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class MessageController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/message")
    @SendTo("/chatroom/public")
    public Messages receiveMessage(@Payload Messages message) {
        return message;
    }

    @MessageMapping("/private-message")
    public Messages recMessage(@Payload Messages message, @RequestParam("idFormateur") int idFormateur,
            @RequestParam("idApprenant") int idApprenant) throws Exception {

        // Récupérer la liste de messages
        ArrayList<MessagePrive> listeMessages = MessagePrive.ListMessagePri(idFormateur, idApprenant);

        // Envoyer la liste de messages au client via WebSocket
        simpMessagingTemplate.convertAndSendToUser(String.valueOf(message.getIdFormateur()), "/private", listeMessages);

        // Afficher les messages dans la console du serveur
        System.out.println("Messages envoyés au client : " + listeMessages);

        // Retourner le message reçu (optionnel)
        return message;
    }

    @PostMapping("/insertMessage")
    public Messages recMessage(@Payload Messages message,
            @RequestParam("idFormateur") int idFormateur,
            @RequestParam("idApprenant") int idApprenant,
            @RequestParam("message") String messages,
            @RequestParam("type") int type
    ) throws Exception {

        Messages pv = new Messages();

        pv.insertMessage(idFormateur, idApprenant, messages, type);

        simpMessagingTemplate.convertAndSendToUser(String.valueOf(message.getIdFormateur()), "/private", message);
        return message;
    }

    @GetMapping("/MessagePrive")
    public ResponseEntity<ArrayList<MessagePrive>> ListePage(@RequestParam("token") String token) throws Exception {

        Formateur userDetails = FonctionBase.selectWithTokenFConnecter(token);
        int idFormateur = userDetails.getIdFormateur();

        ArrayList<MessagePrive> com = new MessagePrive().MessagePri(idFormateur);
        return ResponseEntity.ok(com);
    }

    @GetMapping("/ListMessagePri")
    public ResponseEntity<ArrayList<MessagePrive>> ListeMessagePrive(@RequestParam("idApprenant") int idApprenant, @RequestParam("token") String token) throws Exception {
        Formateur userDetails = FonctionBase.selectWithTokenFConnecter(token);
        int idFormateur = userDetails.getIdFormateur();

        ArrayList<MessagePrive> com = new MessagePrive().ListMessagePri(idFormateur, idApprenant);
        return ResponseEntity.ok(com);
    }

    @GetMapping("/countVue")
    public ResponseEntity<Integer> countUnreadMessages(@RequestParam("token") String token) throws Exception {
        Formateur userDetails = FonctionBase.selectWithTokenFConnecter(token);
        int idFormateur = userDetails.getIdFormateur();

        int count = new MessagePrive().countVue(idFormateur);
        return ResponseEntity.ok(count);
    }

    @PostMapping("/updateVue")
    public ResponseEntity<String> updateVue(@RequestParam("idApprenant") int idApprenant) throws Exception {

        Messages com = new Messages();
        com.updateVue(idApprenant);

        return ResponseEntity.ok("Phrase modifié avec succès");

    }

}


