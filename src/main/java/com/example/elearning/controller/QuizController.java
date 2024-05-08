package com.example.elearning.controller;

import com.example.elearning.models.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@Controller
public class QuizController {

    @PostMapping("/newQuiz")
    public ResponseEntity<String> newQuiz(@RequestParam("idFormation") String idFormation, @RequestParam("titre") String titre) throws Exception {
        // Supposons que vous ayez une classe UserDetails qui représente les détails de l'utilisateur
        Quiz c=new Quiz();
        c.inserer(Integer.parseInt(idFormation),titre);
        return ResponseEntity.ok("ok");
    }
    @GetMapping("/MonQuiz")
    public ResponseEntity<Quiz> MonQuiz(@RequestParam("idQuiz") String idQuiz) throws Exception {
        System.out.println("ito");
        // Supposons que vous ayez une classe UserDetails qui représente les détails de l'utilisateur
        Quiz c=new Quiz();
        c= FonctionBase.MonQuiz(Integer.parseInt(idQuiz));
        return ResponseEntity.ok(c);
    }

    @GetMapping("/TypeQuestion")
    public ResponseEntity<InsererQuestion> TypeQuestion(@RequestParam("idQuiz") String idQuiz) throws Exception {
        // Supposons que vous ayez une classe UserDetails qui représente les détails de l'utilisateur

       InsererQuestion I=new InsererQuestion();

System.out.println("ouigiuafisfdui");


      I.setMonQuiz(FonctionBase.MonQuiz(Integer.parseInt(idQuiz)));
      I.setTypeQuestion(FonctionBase.AllTypeQuestion());


        return ResponseEntity.ok(I);
    }

    @GetMapping("/TypeQuestionA")
    public ResponseEntity<InsererQuestion> TypeQuestionA(@RequestParam("idQuiz") String idQuiz,@RequestParam("token") String token) throws Exception {
        // Supposons que vous ayez une classe UserDetails qui représente les détails de l'utilisateur
        Apprenant A=FonctionBase.selectWithTokenConnecter(token);
        InsererQuestion I=new InsererQuestion();

        System.out.println("ouigiuafisfdui");

        Quiz qu=FonctionBase.MonQuiz(Integer.parseInt(idQuiz));

        qu.setNoteQuizs(FonctionBase.mesNotes(A.getIdApprenant(),Integer.parseInt(idQuiz)));
        I.setMonQuiz(qu);
        I.setTypeQuestion(FonctionBase.AllTypeQuestion());


        return ResponseEntity.ok(I);
    }










    @GetMapping("/PasserQuiz")

    public ResponseEntity<String> PasserQuiz(@RequestParam("idQuiz") String idQuiz,@RequestParam("idrep") String []idrep,@RequestParam("token") String token) throws Exception {

        Apprenant A=FonctionBase.selectWithTokenConnecter(token);

mouvementQuiz Qu=new mouvementQuiz();
int tentative=FonctionBase.derniereTentative(A.getIdApprenant(),Integer.parseInt(idQuiz));

System.out.println("taille"+idrep.length);
tentative=tentative+1;

ArrayList<ReponseQuiz> rep=new ArrayList<ReponseQuiz>();

for (int i=0;i<idrep.length;i++){

    rep.add(FonctionBase.Mareponse(Integer.parseInt(idrep[i])));
}


ArrayList<QuestionQuiz> Q=FonctionBase.mesQ(Integer.parseInt(idQuiz));


ArrayList<ReponseQuiz> reto=new ArrayList<ReponseQuiz>();

for (int i=0;i<Q.size();i++){
reto=new ArrayList<ReponseQuiz>();
    for (int j=0;j< rep.size();j++){

if(Q.get(i).getIdQuestionQuiz()==rep.get(j).getIdQuestion()){

    reto.add(rep.get(j));
}


    }


    if(Q.get(i).getMarina()>=reto.size()){

for (int y=0;y<reto.size();y++){


   Qu.inserer(tentative,A.getIdApprenant(),Integer.parseInt(idQuiz),rep.get(y).getIdReponseQuiz());

}

    }



}










    return ResponseEntity.ok("ok");
    }

    @PostMapping(value = "/newQuestion", consumes = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<String> newQuestion(@RequestBody pff ii    ) throws Exception {

        System.out.println("iio"+ii.getIdQuiz());
        System.out.println(ii.getReponses());
         String reponse=ii.getReponses();
      QuestionQuiz Q=new QuestionQuiz();


Q.inserer(Integer.parseInt(ii.getIdQuiz()),Integer.parseInt(ii.getIdTypeQuestion()),ii.getQuestion());


int d=FonctionBase.maderniereq(Integer.parseInt(ii.getIdQuiz()));
   ReponseQuiz R=new ReponseQuiz();
            // Traitez les réponses ici


        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<ReponseRecu> reponses = objectMapper.readValue(reponse, new TypeReference<List<ReponseRecu>>() {});

            // Faites quelque chose avec la liste de réponses
            // ...

for(int i=0; i<reponses.size();i++){


   System.out.println(reponses.get(i).getText());

 R.inserer(d,reponses.get(i).getText(),Double.valueOf(reponses.get(i).getNote()));

}
            return ResponseEntity.ok("Réponse réussie !");
        } catch (Exception e) {
            e.printStackTrace(); // Log ou gérer l'exception selon vos besoins
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la désérialisation JSON");
        }



    }



    @PostMapping("/newRep")
    public ResponseEntity<String> newRep(@RequestParam("idQuestion") String idQuestion,@RequestParam("reponse") String reponse) throws Exception {
        // Supposons que vous ayez une classe UserDetails qui représente les détails de l'utilisateur


        return ResponseEntity.ok("cool");
    }










}
