package com.example.elearning.controller;

import com.example.elearning.models.Apprenant;
import com.example.elearning.models.FonctionBase;
import com.example.elearning.models.Formateur;
import com.example.elearning.models.Messages;
import com.example.elearning.models.PrivateMessages;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MessageController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/message")
    @SendTo("/chatroom/public")
    public Messages receiveMessage(@Payload Messages message){
        return message;
    }

    @MessageMapping("/private-message")
    public Messages recMessage(@Payload Messages message){
        
        simpMessagingTemplate.convertAndSendToUser(message.getReceiverName(),"/private",message);
        System.out.println(message.toString());
        return message;
    }
    /*@PostMapping("/private-message")
public Messages recMessage(@Payload Messages message, 
                           @RequestParam("idFormateur") int idFormateur,
                           @RequestParam("idApprenant") int idApprenant,
                           @RequestParam("message") String messages
                          ) throws Exception {
        
        
        System.out.println("aonaaaaaa lesy");
        
               Messages pv=new Messages();
              
                pv.insertMessage(idFormateur,idApprenant,messages);
             
        
        simpMessagingTemplate.convertAndSendToUser(String.valueOf(message.getIdFormateur()), "/private", message);
        return message;
    }**/
 
}
