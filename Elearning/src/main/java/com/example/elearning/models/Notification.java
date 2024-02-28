/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.elearning.models;


import java.util.Date;

import com.example.elearning.generic.Attr;
import com.example.elearning.generic.ClassAnotation;
import com.example.elearning.generic.GenericDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List; 
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;


/**
 *
 * @author mamit
 */
@Entity
@ClassAnotation(table = "notification")
public class Notification {
     @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Attr(isPrimary = true)
    Integer id;
    
    
    Integer reception;
    
       
    String text;

   
    Date dateNotif;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getReception() {
        return reception;
    }

    public void setReception(Integer reception) {
        this.reception = reception;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDateNotif() {
        return dateNotif;
    }

    public void setDateNotif(Date dateNotif) {
        this.dateNotif = dateNotif;
    }

    public Notification(Integer id) {
        this.reception = id;
    }

    public Notification() {
    }

    public Notification(Integer reception, String text, Date dateNotif) {
        this.reception = reception;
        this.text = text;
        this.dateNotif = dateNotif;
    }
    
    
    /* public static ArrayList<Notification> getAllNotificationByReception(Integer reception) throws Exception{
        ArrayList<Notification> notifications=new Notification(reception).get("reception");
        return notifications;
    }


    public static int add(String text,int reception,Date dateNotif){
        try {
            new Notification(reception, text, dateNotif).save();
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }
*/
}
