package com.example.elearning.controller;


import com.example.elearning.models.Categorie;
import com.example.elearning.models.FonctionBase;
import com.example.elearning.models.FormationCommentaire;
import com.example.elearning.models.TypesAcces;
import java.util.ArrayList;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin
@Controller


public class TypesAccesController {
    
       @GetMapping ("/LesTypesAcces")
    public ResponseEntity<ArrayList<TypesAcces>> LesTypesAcces() throws Exception {
 ArrayList<TypesAcces> com = new FonctionBase().allTypesAcces();
        return  ResponseEntity.ok(com);
    } 
   

}
