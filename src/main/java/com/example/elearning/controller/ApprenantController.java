package com.example.elearning.controller;

import com.example.elearning.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
@CrossOrigin
@Controller
public class ApprenantController {




    @PostMapping ("/register")
    public ResponseEntity<String> register(@RequestParam("nom") String nom,@RequestParam("prenom") String prenom,@RequestParam("email") String email, @RequestParam("password") String password,@RequestParam("profession") String profession,@RequestParam("civilite") String civilite,@RequestParam("modeDexercice") String modeDexercice,@RequestParam("numero") String numero,@RequestParam("datenaissance") String datenaissance, Model model) throws Exception {
        // Générer un token d'activation
        // Générer un token d'activation

Apprenant A= FonctionBase.findByEmail(email);
if(A!=null){
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
            Apprenant user = new Apprenant();
            user.insertOne(nom,prenom,email,password,Integer.parseInt(profession),Integer.parseInt(civilite),Integer.parseInt(modeDexercice),numero,date);

            // Affichez la date résultante
            System.out.println("Date : " + date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return  ResponseEntity.ok("go");
    }





    @PostMapping("/LoginApprenant")
    public ResponseEntity<String>LoginApprenant(@RequestParam("email") String email, @RequestParam("password") String password) throws Exception {
        // Supposons que vous ayez une classe UserDetails qui représente les détails de l'utilisateur
        Apprenant userDetails = FonctionBase.LoginApprenant(email,password);
if(userDetails==null) {
    // Ajoutez d'autres détails de l'utilisateur
    System.out.println("kjj");
return ResponseEntity.badRequest().body("pff");
}
        System.out.println("k");
        return ResponseEntity.ok(userDetails.getToken());
    }

@GetMapping("/InfoApprenant")
public ResponseEntity<Apprenant>InfoApprenant(@RequestParam("token") String token) throws Exception {
    // Supposons que vous ayez une classe UserDetails qui représente les détails de l'utilisateur
    Apprenant userDetails = FonctionBase.selectWithTokenConnecter(token);
    if(userDetails==null) {
        // Ajoutez d'autres détails de l'utilisateur
        System.out.println("kjjA");
        return ResponseEntity.badRequest().body(userDetails);
    }
    System.out.println("kA");
    return ResponseEntity.ok(userDetails);
}


    @PostMapping ("/ModifApprenant")
    public ResponseEntity<String> ModifApprenant(@RequestParam("token") String token,@RequestParam("nom") String nom,@RequestParam("prenom") String prenom,@RequestParam("profession") String profession,@RequestParam("civilite") String civilite,@RequestParam("modeDexercice") String modeDexercice,@RequestParam("numero") String numero,@RequestParam("datenaissance") String datenaissance, Model model) throws Exception {
        // Générer un token d'activation
        // Générer un token d'activation

        Apprenant A= FonctionBase.selectWithTokenConnecter(token);
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
            Apprenant user = new Apprenant();
            user.updateApprenant(token,nom,prenom,Integer.parseInt(profession),Integer.parseInt(civilite),Integer.parseInt(modeDexercice),numero,date);

            // Affichez la date résultante
            System.out.println("Date : " + date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return  ResponseEntity.ok(token);
    }
@PostMapping ("/ChangerMdp")
public ResponseEntity<String>ChangerMdp(@RequestParam("token") String token,@RequestParam("ancien") String ancien,@RequestParam("nouveau") String nouveau) throws Exception {
    // Supposons que vous ayez une classe UserDetails qui représente les détails de l'utilisateur
    Apprenant userDetails = FonctionBase.selectWithTokenConnecter(token);
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

    @GetMapping ("/MesFormationSuivies")
    public ResponseEntity<ArrayList<Formation>> MesFormationSuivies(@RequestParam("token") String token  ) throws Exception {

        Apprenant A=FonctionBase.selectWithTokenConnecter(token);
        ArrayList<Formation>rep=FonctionBase.MesFormationSuivies(A.getIdApprenant());

        Progression p=new Progression();

        for (int i=0;i<rep.size();i++){


            rep.get(i).setProgres(p.progresssionA(A.getIdApprenant(),rep.get(i).getIdFormation()));

        }

        return  ResponseEntity.ok(rep);
    }




    @GetMapping ("/Suivant")
    public ResponseEntity<Cours> Suivant(@RequestParam("token") String token,@RequestParam("idFormation") String idFormation,@RequestParam("idSousChapitres") String idSousChapitres  ) throws Exception {
String misy="Suivant";
        int iza=0;

        Apprenant A=FonctionBase.selectWithTokenConnecter(token);
        mouvementChapitres m=new mouvementChapitres();
       ArrayList<mouvementChapitres>M=FonctionBase.mesMouv(A.getIdApprenant(),Integer.parseInt(idFormation));
       Formation F=FonctionBase.MonFormation(Integer.parseInt(idFormation));

for (int i=0;i<M.size()-1;i++){

  if(M.get(i).getIdSousChapitres()==Integer.parseInt(idSousChapitres)){

      misy="tsisy";
  }
}



        ArrayList<ContenuSousChapitres> c=FonctionBase.allContenuSousChapitres(Integer.parseInt(idSousChapitres));
System.out.println(misy);
Cours C=new Cours(misy,c);
        return  ResponseEntity.ok(C);
    }



    @GetMapping ("/SuivantB")
    public ResponseEntity<Cours> SuivantB(@RequestParam("token") String token,@RequestParam("idFormation") String idFormation,@RequestParam("idSousChapitres") String idSousChapitres  ) throws Exception {
        String misy="Suivant";
        int iza=0;

        Apprenant A=FonctionBase.selectWithTokenConnecter(token);
        mouvementChapitres m=new mouvementChapitres();
        ArrayList<mouvementChapitres>M=FonctionBase.mesMouv(A.getIdApprenant(),Integer.parseInt(idFormation));
        Formation F=FonctionBase.MonFormation(Integer.parseInt(idFormation));

        for (int i=0;i<M.size()-1;i++){

            if(M.get(i).getIdSousChapitres()==Integer.parseInt(idSousChapitres)){

                misy="tsisy";
            }
        }

        if(F.getMeschapitres().get(F.getMeschapitres().size()-1).getMesSouschapitres().get(F.getMeschapitres().get(F.getMeschapitres().size()-1).getMesSouschapitres().size()-1).getIdSousChapitres()==Integer.parseInt(idSousChapitres)){

            misy="tsisy";
            m.inserer(A.getIdApprenant(), Integer.parseInt(idFormation), Integer.parseInt(idSousChapitres));
        }



        if(misy.equals("Suivant")) {
            for (int i = 0; i < F.getMeschapitres().size(); i++) {

                for (int j = 0; j < F.getMeschapitres().get(i).getMesSouschapitres().size(); j++) {

                    for (int v = 0; v < M.size(); v++) {
                        if (F.getMeschapitres().get(i).getMesSouschapitres().get(j).getIdSousChapitres() == M.get(v).getIdSousChapitres()) {
                            if (j < F.getMeschapitres().get(i).getMesSouschapitres().size()-1) {
                                iza = F.getMeschapitres().get(i).getMesSouschapitres().get(j + 1).getIdSousChapitres();
                                break;
                            } else {

                                iza = F.getMeschapitres().get(i + 1).getMesSouschapitres().get(0).getIdSousChapitres();
                                break;
                            }

                        }
                    }
                }

            }

            m.inserer(A.getIdApprenant(), Integer.parseInt(idFormation), iza);
        }

        ArrayList<ContenuSousChapitres> c=FonctionBase.allContenuSousChapitres(Integer.parseInt(idSousChapitres));
        System.out.println(misy);
        Cours C=new Cours(misy,c);
        return  ResponseEntity.ok(C);
    }


    @GetMapping ("/StatA")
    public ResponseEntity<A> StatA( ) throws Exception {

        ArrayList<Statistique>rep=new ArrayList<Statistique>();

Connection c=FonctionBase.connect();
rep=FonctionBase.StatA(c);
c.close();

A Af=new A(rep);
        return  ResponseEntity.ok(Af);
    }

    @GetMapping ("/StatAm")
    public ResponseEntity<Annee> StatAm(String annee ) throws Exception {


        System.out.println("uhuiigudgiusgdfciasgduifc");

        Connection c=FonctionBase.connect();
        Annee rep=FonctionBase.StatAm(c,Integer.parseInt(annee));
        c.close();

        return  ResponseEntity.ok(rep);
    }


    @GetMapping ("/StatAmd")
    public ResponseEntity<ArrayList<Details>> StatAmd(String annee,String mois ) throws Exception {
        Connection c=FonctionBase.connect();
ArrayList<Details>rep=FonctionBase.StatAmd(c,Integer.parseInt(annee),Integer.parseInt(mois));



        c.close();
        return  ResponseEntity.ok(rep);
    }

}