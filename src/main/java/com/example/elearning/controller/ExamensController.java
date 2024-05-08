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
public class ExamensController {

    @PostMapping("/AjoutExam")
    public ResponseEntity<String> AjoutExam(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String idFormationParam = request.getParameter("idFormation");
        String timerparam = request.getParameter("timer");

        // Vérifier si idFormationParam n'est pas null
        if (idFormationParam != null) {
            try {
                int idFormation = Integer.parseInt(idFormationParam);
                int timer = Integer.parseInt(timerparam);

                String TitreExamen = request.getParameter("TitreExamen");

                Examens com = new Examens();

                com.insertExamens(idFormation, TitreExamen,timer);

                return ResponseEntity.ok("Commentaire ajouté avec succès");

            } catch (NumberFormatException e) {
                // Gérer le cas où la conversion en nombre échoue
                return ResponseEntity.badRequest().body("Format de nombre invalide pour idFormation");
            }
        } else {
            // Gérer le cas où idFormationParam est null
            return ResponseEntity.badRequest().body("Paramètre idFormation manquant");
        }

    }
    
    @GetMapping("/TypeQuestionExam")
    public ResponseEntity<InsererQuestion> TypeQuestion(@RequestParam("idExamen") String idExamen) throws Exception {
        // Supposons que vous ayez une classe UserDetails qui représente les détails de l'utilisateur

       InsererQuestion I=new InsererQuestion();

System.out.println("ouigiuafisfdui");

 
      I.setTypeQuestion(FonctionBase.AllTypeQuestion());


        return ResponseEntity.ok(I);
    }

    @PostMapping(value = "/newQuestionExamen", consumes = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<String> newQuestionExamen(@RequestBody QuestionExamPff ii) throws Exception {

        System.out.println("iio" + ii.getIdExamen());
        System.out.println(ii.getReponses());
        String reponse = ii.getReponses();
        QuestionExamen Q = new QuestionExamen();

        Q.insertQuestionExamen(Integer.parseInt(ii.getIdExamen()), Integer.parseInt(ii.getIdTypeQuestion()), ii.getQuestion());

        int d = QuestionExamen.maderniereq(Integer.parseInt(ii.getIdExamen()));
        ReponsesExamen R = new ReponsesExamen();
        // Traitez les réponses ici

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<ReponseRecuExam> reponses = objectMapper.readValue(reponse, new TypeReference<List<ReponseRecuExam>>() {
            });

            // Faites quelque chose avec la liste de réponses
            // ...
            for (int i = 0; i < reponses.size(); i++) {

                System.out.println(reponses.get(i).getText());

                R.insertReponsesExamen(d, reponses.get(i).getText(), Double.valueOf(reponses.get(i).getNote()));

            }
            return ResponseEntity.ok("Réponse réussie !");
        } catch (Exception e) {
            e.printStackTrace(); // Log ou gérer l'exception selon vos besoins
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la désérialisation JSON");
        }

    }
    
    
        @GetMapping("/TypeQuestionExamenA")
    public ResponseEntity<InsererQuestion> TypeQuestionExamenA(@RequestParam("examen_id") String idExamen,@RequestParam("token") String token) throws Exception {
        // Supposons que vous ayez une classe UserDetails qui représente les détails de l'utilisateur
        Apprenant A=FonctionBase.selectWithTokenConnecter(token);
        InsererQuestion I=new InsererQuestion();

               Examens qu=Examens.MonExam(Integer.parseInt(idExamen));
                       I.setMonExam(qu);

        I.setTypeQuestion(FonctionBase.AllTypeQuestion());


        return ResponseEntity.ok(I);
    }

    
    
        @GetMapping("/PasserExamen")

    public ResponseEntity<String> PasserExamen(@RequestParam("idExamen") String idExamen, String idQuestion,@RequestParam("idrep") String []idrep,@RequestParam("token") String token) throws Exception {

        Apprenant A=FonctionBase.selectWithTokenConnecter(token);

ReponsesApprenant Qu=new ReponsesApprenant();
 

ArrayList<ReponsesExamen> rep=new ArrayList<ReponsesExamen>();

for (int i=0;i<idrep.length;i++){

    rep.add(ReponsesExamen.Mareponse(Integer.parseInt(idrep[i])));
}


ArrayList<QuestionExamen> Q=QuestionExamen.mesQ(Integer.parseInt(idExamen));


ArrayList<ReponsesExamen> reto=new ArrayList<ReponsesExamen>();

for (int i=0;i<Q.size();i++){
reto=new ArrayList<ReponsesExamen>();
    for (int j=0;j< rep.size();j++){

if(Q.get(i).getIdQuestion()==rep.get(j).getIdQuestion()){

    reto.add(rep.get(j));
}
    }

    if(Q.get(i).getMarina()>=reto.size()){

for (int y=0;y<reto.size();y++){


   Qu.insertReponsesApprenant(Integer.parseInt(idExamen),A.getIdApprenant(),rep.get(y).getIdReponse());

}
    }
}
    return ResponseEntity.ok("ok");
    }
    
    
       @GetMapping ("/LesExamens")
    public ResponseEntity<ArrayList<Examens>> LesExamens(@RequestParam("idFormation") int idFormation  ) throws Exception {
 ArrayList<Examens> com = new Examens().ListeExamens(idFormation);
        return  ResponseEntity.ok(com);
    }
    
      @GetMapping ("/questionReponse")
    public ResponseEntity<ArrayList<QuestionReponse>> questionReponse(@RequestParam("examen_id") int examen_id  ) throws Exception {
 ArrayList<QuestionReponse> com = new QuestionReponse().ListeQuestionReponses(examen_id);
        return  ResponseEntity.ok(com);
    }
   
      @GetMapping ("/Timer")
    public ResponseEntity<ArrayList<Examens>> Timer(@RequestParam("idExamen") int idExamen  ) throws Exception {
 ArrayList<Examens> com = new Examens().Timer(idExamen);
        return  ResponseEntity.ok(com);
    }
    @PostMapping("/AjoutCheckExamen")
public ResponseEntity<String> CheckExamen(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) throws Exception {
 String idExamenparam = request.getParameter("idExamen");
    // Vérifier si idFormationParam n'est pas null
    if (idExamenparam !=null ) {
        try {
            int idExamen = Integer.parseInt(idExamenparam);

            String token = request.getParameter("token");
            Apprenant userDetails = FonctionBase.selectWithTokenConnecter(token);

            CheckExamen com = new CheckExamen();
           
          
            if (userDetails != null) {
    // Récupérer l'idApprenant 
    int idApprenant = userDetails.getIdApprenant();
                
    boolean informationExist = com.checkIfDejaFaitExam(idApprenant,idExamen); //

    if (!informationExist) {
        // L'information n'existe pas encore, ajouter la note
        com.insertCheckExamen(idApprenant,idExamen);
        
        return ResponseEntity.ok(" Apprenant passe l'examen avec succès");
    } else {
        // L'information existe déjà, retourner un message d'erreur ou effectuer une action appropriée
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("L'apprenant a déjà voté pour cette formation.");
    }
} else {
    // L'Apprenant n'est pas authentifié, retourner une réponse d'erreur
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Non autorisé");
}
        } catch (NumberFormatException e) {
            // Gérer le cas où la conversion en nombre échoue
            return ResponseEntity.badRequest().body("Format de nombre invalide pour idFormation");
        }
    } else {
        // Gérer le cas où idFormationParam est null
        return ResponseEntity.badRequest().body("Paramètre idFormation manquant");
    }
    
}

}
