package com.example.elearning.controller;

import java.util.ArrayList;

import org.springframework.messaging.handler.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

/*import com.google.gson.Gson;
import com.project.model.Notification;
import com.project.service.NotificationService;

@CrossOrigin
@Controller
public class SocketController {
    
    @MessageMapping("/notification/{id}")
    @SendTo("/topic/notif/{id}")
    public String notification(@DestinationVariable("id") Integer id)throws Exception{
        Gson gson=new Gson();
        ArrayList<Notification> notifications=NotificationService.getAllNotificationByReception(id);
        return gson.toJson(notifications);
    }

}
*/
