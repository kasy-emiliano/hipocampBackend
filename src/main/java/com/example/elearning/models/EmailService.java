package com.example.elearning.models;

import org.springframework.web.multipart.MultipartFile;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;


public class EmailService {

    public static void sendActivationEmail(String toEmail, String activationToken) {
        try {
            // Obtenez l'adresse IP de l'ordinateur local
            InetAddress localhost = InetAddress.getLocalHost();

            System.out.println("Adresse IP de l'ordinateur local : " + localhost.getHostAddress());
        } catch (  UnknownHostException e) {
            e.printStackTrace();
        }
        final String username = "mireillamireilla9@gmail.com";
        final String password = "fbcrposiiroetqoc";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "*");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Activation de compte");
            String activationLink = "http://localhost:8080/activation?token=" + activationToken;
            message.setText("Cliquez sur ce lien pour activer votre compte : " + activationLink);
            Transport.send(message);

            System.out.println("E-mail envoyé avec succès!");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    public static void sendActivationEmailF(String toEmail, String activationToken) {
        try {
            // Obtenez l'adresse IP de l'ordinateur local
            InetAddress localhost = InetAddress.getLocalHost();

            System.out.println("Adresse IP de l'ordinateur local : " + localhost.getHostAddress());
        } catch (  UnknownHostException e) {
            e.printStackTrace();
        }
        final String username = "mireillamireilla9@gmail.com";
        final String password = "fbcrposiiroetqoc";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "*");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Activation de compte");
            String activationLink = "http://localhost:8080/activationF?token=" + activationToken;
            message.setText("Cliquez sur ce lien pour activer votre compte : " + activationLink);
            Transport.send(message);

            System.out.println("E-mail envoyé avec succès!");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }



    public static void sendForgetEmail(String toEmail, String activationToken) {
        try {
            // Obtenez l'adresse IP de l'ordinateur local
            InetAddress localhost = InetAddress.getLocalHost();

            System.out.println("Adresse IP de l'ordinateur local : " + localhost.getHostAddress());
        } catch (  UnknownHostException e) {
            e.printStackTrace();
        }
        final String username = "mireillamireilla9@gmail.com";
        final String password = "fbcrposiiroetqoc";
        

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "*");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Modifier mot de passe");
            String activationLink = "http://localhost:8080/PasswordReset?token=" + activationToken;
            message.setText("Cliquez sur ce lien pour changer le mot de passe votre compte : " + activationLink);
            Transport.send(message);

            System.out.println("E-mail envoyé avec succès!");

        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    public static void sendForgetEmailF(String toEmail, String activationToken) {
        try {
            // Obtenez l'adresse IP de l'ordinateur local
            InetAddress localhost = InetAddress.getLocalHost();

            System.out.println("Adresse IP de l'ordinateur local : " + localhost.getHostAddress());
        } catch (  UnknownHostException e) {
            e.printStackTrace();
        }
        final String username = "mireillamireilla9@gmail.com";
        final String password = "fbcrposiiroetqoc";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "*");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Modifier mot de passe");
            String activationLink = "http://localhost:3000/PasswordResetForm?token=" + activationToken;
            message.setText("Cliquez sur ce lien pour changer le mot de passe votre compte : " + activationLink);
            Transport.send(message);

            System.out.println("E-mail envoyé avec succès!");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }









    public static  void sendDemande(DemandeAdhesion d){
        final String Admin="jeanbade118@gmail.com";
        final String username = "mireillamireilla9@gmail.com";
        final String password = "fbcrposiiroetqoc";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "*");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(Admin));
            message.setSubject(d.getObjet());

            message.setText(d.getMessage()+"\nNom:"+d.getNom()+ "\nPrenom:"+d.getPrenom()+"\nOrganisme:"+d.getOrganisme()+ "\nEmail:"+d.getEmail()+"\nNumero:"+d.getNumero()+"\nVille:"+d.getVille());
            Transport.send(message);

            System.out.println("E-mail envoyé avec succès!");

        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }


    public static void sendlink(DemandeAdhesion d) {

        final String username = "mireillamireilla9@gmail.com";
        final String password = "fbcrposiiroetqoc";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "*");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(d.getEmail()));
            message.setSubject("Lien de creation du compte");
            String activationLink = "http://localhost:3000/InfoFormateur?token=" + d.getToken();
            message.setText("Cliquez sur ce lien pour creer votre compte : " + activationLink);
            Transport.send(message);

            System.out.println("E-mail envoyé avec succès!");

        } catch (MessagingException e) {
            e.printStackTrace();

        }
    }










}
