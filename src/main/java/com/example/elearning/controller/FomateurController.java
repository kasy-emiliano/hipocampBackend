package com.example.elearning.controller;

import com.example.elearning.models.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@CrossOrigin
@Controller
public class FomateurController {

    @GetMapping("/create")
    public  ResponseEntity<String> create(@RequestParam("token") String token) throws Exception {
        DemandeAdhesion user = FonctionBase.selectWithTokend(token);
        Formateur F=null;
        if (user == null) {
            // Activer le compte de l'utilisateur



            // Rediriger vers une page de confirmation
           return ResponseEntity.badRequest().body("tsy azo");

        } else {
            F=FonctionBase.selectWithTokenF(token);

            if(F!=null){


                return ResponseEntity.badRequest().body("tsy azo");
            }

            else {

                return  ResponseEntity.ok("go");
            }
            // Gérer le cas où le token est invalide ou expiré

        }
    }

    @PostMapping  ("/createFormateur")
    public ResponseEntity<String> createFormateur(@RequestParam("nom") String nom, @RequestParam("prenom") String prenom, @RequestParam("email") String email, @RequestParam("password") String mdp, @RequestParam("organisme") String nomOrgannisme, @RequestParam("ville") String ville, @RequestParam("civilite") String civilite, @RequestParam("profession") String profession, @RequestParam("modeDexercice") String modeDexercice, @RequestParam("bio") String bio, @RequestParam("phone") String numero, @RequestParam("datenaissance") String datenaissance, @RequestParam("facebook") String facebook, @RequestParam("linkedin") String linkedin, @RequestParam("token") String token, Model model) throws Exception {
        // Générer un token d'activation
System.out.println(datenaissance);
        DemandeAdhesion user = FonctionBase.selectWithTokend(token);
        Formateur F = null;
        Formateur fo = FonctionBase.findByEmailF(email);

        Formateur f = new Formateur();
        String dateStr = datenaissance; // Par exemple, "AAAA-MM-JJ"

        // Créez un objet SimpleDateFormat avec le format de date correspondant à la chaîne
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Correspond au format de la chaîne
        Date date = dateFormat.parse(dateStr);

    //upload image

        String UPLOAD_DIR = "uploads/";
        String filePath="";
        /* try {
            // Vérifiez si le répertoire de stockage existe, sinon, créez-le
            File uploadDir = new File(UPLOAD_DIR);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            // Récupérez le nom du fichier téléchargé
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

            // Construisez le chemin complet de stockage
             filePath = UPLOAD_DIR + fileName;

            // Copiez le fichier dans le répertoire de stockage
            Path targetLocation = Paths.get(filePath);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors du téléchargement de l'image : " + e.getMessage());
        }


*/







        if ((user == null)||(fo!=null)) {
            // Activer le compte de l'utilisateur

System.out.println("efefwwfe");

            // Rediriger vers une page de confirmation
            return ResponseEntity.badRequest().body("tsy azo");

        } else {
            F=FonctionBase.selectWithTokenF(token);

            if(F!=null){

               return ResponseEntity.badRequest().body("tsy azo");
            }

            else {
                f.insertOne(nom,  prenom,  email,  mdp,  nomOrgannisme,  ville, Integer.parseInt(civilite),  Integer.parseInt(profession),  Integer.parseInt(modeDexercice), bio,  numero,  date,  facebook,  linkedin, token, filePath );;

                return  ResponseEntity.ok("go");
            }
            // Gérer le cas où le token est invalide ou expiré

        }





      // Rediriger vers une page de confirmation
    }

    @PostMapping  ("/ModifPhotoProfil")
    public ResponseEntity<String> ModifPhotoProfil( @RequestParam("token") String token, @RequestParam("photo") MultipartFile file, Model model) throws Exception {
        // Générer un token d'activation


        Formateur F = null;

System.out.println("oayyy ");

        //upload image

        String UPLOAD_DIR = "uploads/";
        String filePath="";
         try {
            // Vérifiez si le répertoire de stockage existe, sinon, créez-le
            File uploadDir = new File(UPLOAD_DIR);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            // Récupérez le nom du fichier téléchargé
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

            // Construisez le chemin complet de stockage
             filePath = UPLOAD_DIR + fileName;

            // Copiez le fichier dans le répertoire de stockage
            Path targetLocation = Paths.get(filePath);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);


        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors du téléchargement de l'image : " + e.getMessage());
        }

            F=FonctionBase.selectWithTokenF(token);

            if(F==null){

                return ResponseEntity.badRequest().body("tsy azo");
            }

            else {
F.modifphoto(F.getToken(),filePath);
                return  ResponseEntity.ok("token");
            }
            // Gérer le cas où le token est invalide ou expiré

        }

        // Rediriger vers une page de confirmation


    @PostMapping  ("/ModifPhotoCouverture")
    public ResponseEntity<String> ModifPhotoCouverture( @RequestParam("token") String token, @RequestParam("photo") MultipartFile file, Model model) throws Exception {
        // Générer un token d'activation
        Formateur F = null;
System.out.println("oayyy ");

        //upload image

        String UPLOAD_DIR = "uploads/";
        String filePath="";
         try {
            // Vérifiez si le répertoire de stockage existe, sinon, créez-le
            File uploadDir = new File(UPLOAD_DIR);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            // Récupérez le nom du fichier téléchargé
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

            // Construisez le chemin complet de stockage
             filePath = UPLOAD_DIR + fileName;

            // Copiez le fichier dans le répertoire de stockage
            Path targetLocation = Paths.get(filePath);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);


        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors du téléchargement de l'image : " + e.getMessage());
        }

            F=FonctionBase.selectWithTokenF(token);

            if(F==null){

                return ResponseEntity.badRequest().body("tsy azo");
            }

            else {
F.modifphotoCouverture(F.getToken(),filePath);
                return  ResponseEntity.ok("token");
            }
            // Gérer le cas où le token est invalide ou expiré

        }

        // Rediriger vers une page de confirmation

    

    @PostMapping("/DemandeAdhesion")
    public ResponseEntity<String> DemandeAdhesion(@RequestParam("nom") String nom, @RequestParam("prenom") String prenom, @RequestParam("email") String email, @RequestParam("organisme") String organisme, @RequestParam("ville") String ville, @RequestParam("objet") String objet, @RequestParam("message") String message, @RequestParam("numero") String numero, Model model) throws Exception {
        // Générer un token d'activation
        // Générer un token d'activation




        // Créez un objet SimpleDateFormat avec le format de date correspondant à la chaîne
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Correspond au format de la chaîne

        try {

            DemandeAdhesion  d=new DemandeAdhesion();
            d.insererDemande(organisme,nom,prenom,ville,email,numero,objet,message);
            // Affichez la date résultante

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return  ResponseEntity.ok("go");
    }




    @GetMapping("/EnvoyerLien")
    public ResponseEntity<ArrayList<DemandeAdhesion>> getAllDemande(@RequestParam String page, @RequestParam String size) throws Exception {
        // Supposons que vous ayez plusieurs objets à envoyer

        ArrayList<DemandeAdhesion> all=FonctionBase.AllDemande(Integer.parseInt(page),Integer.parseInt(size));
        System.out.println(all.get(0).getEmail());


        return ResponseEntity.ok(all);
    }


    @GetMapping("/SendLink")
    public ResponseEntity<String> SendLink(@RequestParam("idDemande") String idDemande) throws Exception {
        // Supposons que vous ayez plusieurs objets à envoyer

DemandeAdhesion rep=new DemandeAdhesion();

rep.EnvoyerLien(Integer.parseInt(idDemande));

        return ResponseEntity.ok("oee");
    }

    @PostMapping("/LoginFormateur")
    public ResponseEntity<String>LoginFormateur(@RequestParam("email") String email, @RequestParam("password") String password) throws Exception {
        // Supposons que vous ayez une classe UserDetails qui représente les détails de l'utilisateur
        Formateur userDetails = FonctionBase.LoginFormateur(email,password);
        if(userDetails==null) {
            // Ajoutez d'autres détails de l'utilisateur
            System.out.println("kjj");
            return ResponseEntity.badRequest().body("");
        }
        System.out.println("k");
        return ResponseEntity.ok(userDetails.getToken());
    }


    @GetMapping("/InfoFormateur")
    public ResponseEntity<Formateur>InfoFormateur(@RequestParam("token") String token) throws Exception {
        // Supposons que vous ayez une classe UserDetails qui représente les détails de l'utilisateur
        System.out.println("aaaaaaaa");
        Formateur userDetails = FonctionBase.selectWithTokenFConnecter(token);
        if(userDetails==null) {
            // Ajoutez d'autres détails de l'utilisateur
            System.out.println("kjj");
            return ResponseEntity.badRequest().body(userDetails);
        }
        System.out.println("k");
        return ResponseEntity.ok(userDetails);
    }
    
    
    @GetMapping("/InfoFormateurPhoto")
    public ResponseEntity<Formateur>InfoFormateurPhoto(@RequestParam("token") String token) throws Exception {
        // Supposons que vous ayez une classe UserDetails qui représente les détails de l'utilisateur
        
        Formateur userDetails = new Formateur().SelectProfilCouverture(token);
        if(userDetails==null) {
            // Ajoutez d'autres détails de l'utilisateur
            System.out.println("kjj");
            return ResponseEntity.badRequest().body(userDetails);
        }
        System.out.println("k");
        return ResponseEntity.ok(userDetails);
    }

    @GetMapping ("/ListApprenantI")
    public ResponseEntity<ArrayList<Apprenant>> ListApprenantI(@RequestParam("idFormation") String idFormation) throws Exception {

        ArrayList<Apprenant>rep=FonctionBase.ListApprenantI(Integer.parseInt(idFormation));
System.out.println("tss");


        return  ResponseEntity.ok(rep);
    }










    @PostMapping ("/ModifFormateur")
    public ResponseEntity<String> ModifFormateur(@RequestParam("nom") String nom, @RequestParam("prenom") String prenom, @RequestParam("organisme") String nomOrgannisme, @RequestParam("ville") String ville, @RequestParam("civilite") String civilite, @RequestParam("profession") String profession, @RequestParam("modeDexercice") String modeDexercice, @RequestParam("bio") String bio, @RequestParam("numero") String numero, @RequestParam("datenaissance") String datenaissance, @RequestParam("facebook") String facebook, @RequestParam("linkedin") String linkedin, @RequestParam("token") String token, Model model) throws Exception {
        // Générer un token d'activation
        // Générer un token d'activation

     Formateur A= FonctionBase.selectWithTokenFConnecter(token);
        if(A==null){
            System.out.println("tsai");
            return ResponseEntity.badRequest().body("tsy azo");
        }
        String dateStr = datenaissance; // Par exemple, "AAAA-MM-JJ"

        // Créez un objet SimpleDateFormat avec le format de date correspondant à la chaîne
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Correspond au format de la chaîne

        try {
            System.out.println(datenaissance);
            // Utilisez la méthode parse pour convertir la chaîne en un objet Date
            Date date = dateFormat.parse(dateStr);
            Formateur user = new Formateur();
            user.updateFormateur(nom,  prenom, nomOrgannisme,  ville, Integer.parseInt(civilite),  Integer.parseInt(profession),  Integer.parseInt(modeDexercice), bio,  numero,  date,  facebook,  linkedin, token);

            // Affichez la date résultante
            System.out.println("Date : " + date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return  ResponseEntity.ok(token);
    }
    @PostMapping ("/ChangerMdpF")
    public ResponseEntity<String>ChangerMdpf(@RequestParam("token") String token,@RequestParam("ancien") String ancien,@RequestParam("nouveau") String nouveau) throws Exception {
        // Supposons que vous ayez une classe UserDetails qui représente les détails de l'utilisateur
        Formateur userDetails = FonctionBase.selectWithTokenFConnecter(token);
        if(userDetails==null) {
            // Ajoutez d'autres détails de l'utilisateur
            System.out.println("kjj");
            return ResponseEntity.badRequest().body("tsy azo");
        }

        if(!userDetails.getMdp().equals(ancien)){

            return ResponseEntity.badRequest().body("tsy azo");

        }

        if(userDetails.getMdp().equals(ancien)){

            userDetails.updateMdp(token,nouveau);

        }
        return ResponseEntity.ok(token);
    }

    @GetMapping ("/StatF")
    public ResponseEntity<A> StatF() throws Exception {



        ArrayList<Statistique>rep=new ArrayList<Statistique>();

        Connection c=FonctionBase.connect();
        rep=FonctionBase.StatF(c);
        c.close();

        A Af=new A(rep);

        return  ResponseEntity.ok(Af);
    }

    @GetMapping ("/StatFm")
    public ResponseEntity<Annee> StatFm(String annee ) throws Exception {



        Connection c=FonctionBase.connect();
        Annee rep=FonctionBase.StatFm(c,Integer.parseInt(annee));
        c.close();
        return  ResponseEntity.ok(rep);
    }


    @GetMapping ("/StatFmd")
    public ResponseEntity<ArrayList<Details>> StatFmd(String annee,String mois ) throws Exception {
        Connection c=FonctionBase.connect();
        ArrayList<Details>rep=FonctionBase.StatFmd(c,Integer.parseInt(annee),Integer.parseInt(mois));



        c.close();
        return  ResponseEntity.ok(rep);
    }


    
    @PostMapping ("/UpdatePhrase")
    public ResponseEntity<String> UpdatePhrase(@RequestParam("phrase")String phrase,@RequestParam("token") String token) throws Exception {

 
            try { 

            Formateur userDetails = FonctionBase.selectWithTokenFConnecter(token);

            Formateur com = new Formateur();

            if (userDetails != null) {
                // Récupérer l'idApprenant
                System.out.println("Formateur ID: " + userDetails.getIdFormateur());

                int idFormateur = userDetails.getIdFormateur();
                
                com.updatePhrase(idFormateur,phrase);
                
                return ResponseEntity.ok("Phrase modifié avec succès");
                
            } else {
                // L'Apprenant n'est pas authentifié, retourner une réponse d'erreur
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Non autorisé");
            }
        } catch (NumberFormatException e) {
            // Gérer le cas où la conversion en nombre échoue
            return ResponseEntity.badRequest().body("Format de nombre invalide pour idFormation");
        }
    }
    
    @PostMapping ("/UpdateNomEspace")
    public ResponseEntity<String> UpdateNomEspace(@RequestParam("nomespace")String nomespace,@RequestParam("token") String token) throws Exception {

 
            try { 

            Formateur userDetails = FonctionBase.selectWithTokenFConnecter(token);

            Formateur com = new Formateur();

            if (userDetails != null) {
                // Récupérer l'idApprenant
                System.out.println("Formateur ID: " + userDetails.getIdFormateur());

                int idFormateur = userDetails.getIdFormateur();
                
                com.updateNomEspace(idFormateur,nomespace);
                
                return ResponseEntity.ok("Phrase modifié avec succès");
                
            } else {
                // L'Apprenant n'est pas authentifié, retourner une réponse d'erreur
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Non autorisé");
            }
        } catch (NumberFormatException e) {
            // Gérer le cas où la conversion en nombre échoue
            return ResponseEntity.badRequest().body("Format de nombre invalide pour idFormation");
        }
    }
    
     @GetMapping ("/PhraseFormateur")
    public ResponseEntity<ArrayList<Formateur>> PhraseFormateur(@RequestParam("token") String token  ) throws Exception {
                
                 ArrayList<Formateur> com = new Formateur().PhraseFormateur(token);
                 
        return  ResponseEntity.ok(com);
                
 

    }
    
    @PostMapping("/ConfigurationPage")
    public ResponseEntity<String> ConfigurationPage( @RequestParam("logo") MultipartFile file,HttpServletRequest request, HttpServletResponse response) throws Exception {

        String token = request.getParameter("token");
        String couleurPrincipale = request.getParameter("couleurPrincipale");
        String couleurArrierePlan = request.getParameter("couleurArrierePlan");
        String CouleurTitre = request.getParameter("CouleurTitre");
        String couleurText = request.getParameter("couleurText");
        String couleurBouton = request.getParameter("couleurBouton");
        String couleurtextBouton = request.getParameter("couleurtextBouton");

        String UPLOAD_DIR = "uploads/";
        String logo="";
        
        try {
            // Vérifiez si le répertoire de stockage existe, sinon, créez-le
            File uploadDir = new File(UPLOAD_DIR);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            // Récupérez le nom du fichier téléchargé
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

            // Construisez le chemin complet de stockage
             logo = UPLOAD_DIR + fileName;

            // Copiez le fichier dans le répertoire de stockage
            Path targetLocation = Paths.get(logo);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors du téléchargement de l'image : " + e.getMessage());
        }
        
        Formateur userDetails = FonctionBase.selectWithTokenFConnecter(token);

        int idFormateur = userDetails.getIdFormateur();

        Formateur com = new Formateur();

        com.updateConfigPage(idFormateur, logo,couleurPrincipale,couleurArrierePlan,CouleurTitre,couleurText,couleurBouton,couleurtextBouton);

        return ResponseEntity.ok("Commentaire ajouté avec succès");

    }
    
           @GetMapping ("/ListConfigPage")
    public ResponseEntity<ArrayList<Formateur>> ListePage(@RequestParam("token") String token  ) throws Exception {
        
                    Formateur userDetails = FonctionBase.selectWithTokenFConnecter(token);
                int idFormateur = userDetails.getIdFormateur();
        
 ArrayList<Formateur> com = new Formateur().ListePage(idFormateur);
        return  ResponseEntity.ok(com);
    }
    
      @GetMapping ("/ListConfigPageNom")
    public ResponseEntity<ArrayList<Formateur>> ListePageNom(@RequestParam("nomespace") String nomespace  ) throws Exception {
        
        
 ArrayList<Formateur> com = new Formateur().ListePageNom(nomespace);
        return  ResponseEntity.ok(com);
    }
   


    

}
