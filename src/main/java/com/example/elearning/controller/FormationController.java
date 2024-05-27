package com.example.elearning.controller;

import com.example.elearning.models.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
import java.util.ArrayList;
import java.util.Objects;

@CrossOrigin
@Controller
public class FormationController {

    @GetMapping ("/DetailsFormation")
    public ResponseEntity<DetailsFormation> DetailsFormation( ) throws Exception {


DetailsFormation D=new DetailsFormation();


D.setAllCategorie(FonctionBase.AllCategorie());
D.setAllUnite(FonctionBase.allUnite());
D.setAllTypesAcces(FonctionBase.allTypesAcces());
D.setAllLangues(FonctionBase.allLangues());
        return  ResponseEntity.ok(D);
    }

    @PostMapping ("/AjoutFormation")
    public ResponseEntity<String> AjoutFormation(@RequestParam("token")String token, @RequestParam("idCategorie")String idCategorie, @RequestParam("typeAcces")String  typesAcces, @RequestParam("langues")String  langues, @RequestParam("titre")String  titre, @RequestParam("duree")String  duree,@RequestParam("unite")String  u, @RequestParam("resumer")String  resumer, @RequestParam("photo") MultipartFile file,@RequestParam("prix") String prix,@RequestParam("etatPublication")String etatPublication) throws Exception {
System.out.println("jojj");
        System.out.println("idCategorie:"+idCategorie);
        System.out.println("typesAcces:"+typesAcces);
        System.out.println("langues:"+langues);
        System.out.println("unite:"+u);
        Formation f=new Formation();
        Formateur fo=FonctionBase.selectWithTokenF(token);
        String UPLOAD_DIR = "uploads/";
        String filePath="";


       double p=0.0;

       try {
           p=Double.valueOf(prix);
       }
        catch (Exception e){
            return  ResponseEntity.badRequest().body("pff");
        }

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



        f.inserer(fo.getIdFormateur(),Integer.parseInt(idCategorie),Integer.parseInt(typesAcces),Integer.parseInt(langues),titre,duree,Integer.parseInt(u),resumer,filePath,p,Integer.parseInt(etatPublication));



        return  ResponseEntity.ok("ok");
    }

    @GetMapping ("/RechercheFormation")
    public ResponseEntity<Recherche> RechercheFormation(@RequestParam("categorie") String categorie,@RequestParam("TypesAcces") String TypesAcces ,@RequestParam("mot") String mot) throws Exception {

int misyCondition=0;

String sql="SELECT formation.*,categorie.nom AS categorie_nom,typesacces.nom AS types_acces_nom,langues.nom AS langues_nom,unite.nom AS unite_nom FROM formation JOIN categorie ON formation.idcategorie = categorie.idcategorie JOIN typesacces ON formation.typesacces = typesacces.idTypesAcces JOIN langues ON formation.langues = langues.idLangues JOIN unite ON formation.unite = unite.idUnite WHERE formation.etat = 2 ";


        if(!categorie.equals("")){

          misyCondition=1;
        }


        if(misyCondition==1) {

            sql = sql + " AND Formation. idCategorie=" + Integer.parseInt(categorie);

        }
if(!TypesAcces.equals("")){

    if(misyCondition==1){

        sql=sql+" AND  Formation. TypesAcces=" + Integer.parseInt(TypesAcces);
    }
    else {

        sql=sql+" AND  Formation. TypesAcces=" + Integer.parseInt(TypesAcces);

    }


}






if(!mot.equals("")){
    if((misyCondition==1) || (!TypesAcces.equals(""))){

        sql=sql+" AND ( Formation.titre LIKE '%"+mot+"%' OR Formation.resumer LIKE '%"+mot+"%')";
    }
    else {

        sql=sql+" AND Formation.titre LIKE '%"+mot+"%' OR Formation.resumer LIKE '%"+mot+"%'";

    }

}

sql=sql+ " ORDER BY Formation.idFormation DESC  ";
            System.out.println(sql);

            Recherche rep=new Recherche();
        Connection c=FonctionBase.connect();
        rep.setRecherche(FonctionBase.RechercheFormation(sql,c));
        DetailsFormation D=new DetailsFormation();
c.close();

        D.setAllCategorie(FonctionBase.AllCategorie());
        D.setAllUnite(FonctionBase.allUnite());
        D.setAllTypesAcces(FonctionBase.allTypesAcces());
        D.setAllLangues(FonctionBase.allLangues());
        rep.setF(D);

        System.out.println("le");
        return  ResponseEntity.ok(rep);
    }
    
        @GetMapping ("/RechercheFormationDeux")
    public ResponseEntity<Recherche> RechercheFormationDeux(@RequestParam("categorie") String categorie,@RequestParam("TypesAcces") String TypesAcces ,@RequestParam("mot") String mot) throws Exception {

int misyCondition=0;

String sql="SELECT formation.*,categorie.nom AS categorie_nom,typesacces.nom AS types_acces_nom,langues.nom AS langues_nom,unite.nom AS unite_nom FROM formation JOIN categorie ON formation.idcategorie = categorie.idcategorie JOIN typesacces ON formation.typesacces = typesacces.idTypesAcces JOIN langues ON formation.langues = langues.idLangues JOIN unite ON formation.unite = unite.idUnite WHERE formation.etat = 2 and formation.etatpublication= 1 ";


        if(!categorie.equals("")){

          misyCondition=1;
        }


        if(misyCondition==1) {

            sql = sql + " AND Formation. idCategorie=" + Integer.parseInt(categorie);

        }
if(!TypesAcces.equals("")){

    if(misyCondition==1){

        sql=sql+" AND  Formation. TypesAcces=" + Integer.parseInt(TypesAcces);
    }
    else {

        sql=sql+" AND  Formation. TypesAcces=" + Integer.parseInt(TypesAcces);

    }


}






if(!mot.equals("")){
    if((misyCondition==1) || (!TypesAcces.equals(""))){

        sql=sql+" AND ( Formation.titre LIKE '%"+mot+"%' OR Formation.resumer LIKE '%"+mot+"%')";
    }
    else {

        sql=sql+" AND Formation.titre LIKE '%"+mot+"%' OR Formation.resumer LIKE '%"+mot+"%'";

    }

}

sql=sql+ " ORDER BY Formation.idFormation DESC  ";
            System.out.println(sql);

            Recherche rep=new Recherche();
        Connection c=FonctionBase.connect();
        rep.setRecherche(FonctionBase.RechercheFormation(sql,c));
        DetailsFormation D=new DetailsFormation();
c.close();

        D.setAllCategorie(FonctionBase.AllCategorie());
        D.setAllUnite(FonctionBase.allUnite());
        D.setAllTypesAcces(FonctionBase.allTypesAcces());
        D.setAllLangues(FonctionBase.allLangues());
        rep.setF(D);

        System.out.println("le");
        return  ResponseEntity.ok(rep);
    }

    @GetMapping ("/MesFormation")
    public ResponseEntity<ArrayList<Formation>> MesFormation(@RequestParam("token") String token  ) throws Exception {
System.out.println(token);
       Formateur f=FonctionBase.selectWithTokenF(token);

    ArrayList<Formation>rep=FonctionBase.MesFormation(f.getIdFormateur());

        return  ResponseEntity.ok(rep);
    }
    @GetMapping ("/MesFormationNomEspace")
    public ResponseEntity<ArrayList<Formation>> MesFormationNomEspace(@RequestParam("nomespace") String nomespace) throws Exception {

 ArrayList<Formation>rep=new Formation().mesFormationEspace(nomespace);

        return  ResponseEntity.ok(rep);
    }

     
    @GetMapping ("/MonFormation")
    public ResponseEntity<Formation> MonFormation(@RequestParam("idFormation") String idFormation  ) throws Exception {



        Formation rep=FonctionBase.MonFormation(Integer.parseInt(idFormation));

        return  ResponseEntity.ok(rep);
    }


    @GetMapping ("/MonFormationC")
    public ResponseEntity<Moi> MonFormationC(@RequestParam("idFormation") String idFormation ,@RequestParam("token") String token ) throws Exception {


        Apprenant A=FonctionBase.selectWithTokenConnecter(token);
        mouvementChapitres m=new mouvementChapitres();
        ArrayList<mouvementChapitres>M=FonctionBase.mesMouv(A.getIdApprenant(),Integer.parseInt(idFormation));
        Formation F=FonctionBase.MonFormation(Integer.parseInt(idFormation));
        Progression p=new Progression();
        F.setProgres(p.progresssionA(A.getIdApprenant(),F.getIdFormation()));
Moi rep=new Moi(M,F);
        return  ResponseEntity.ok(rep);
    }


    @GetMapping ("/demandevalidation")
    public ResponseEntity<String> demandevalidation(@RequestParam("idFormation") String idFormation  ) throws Exception {



        Formation rep=new Formation();


        rep.demandevalidation(Integer.parseInt(idFormation));
        return  ResponseEntity.ok("cool");
    }








    @GetMapping ("/SuivreFormation")
    public ResponseEntity<String> SuivreFormation(@RequestParam("idFormation") String idFormation,@RequestParam("token") String token  ) throws Exception {

        Apprenant A=FonctionBase.selectWithTokenConnecter(token);
        Formation rep=FonctionBase.MonFormation(Integer.parseInt(idFormation));
        int S= 0;
        mouvementChapitres m=new mouvementChapitres();
System.out.println("lelee");
int idApprenant=A.getIdApprenant();
inscritFormation ins=FonctionBase.Suivie(idApprenant,Integer.parseInt(idFormation));

 inscritFormation  inss=new inscritFormation();
if(ins!=null){
    return  ResponseEntity.ok("ao");

}


else {

    if(rep.getTypesAcces()==1){
inss.insererWithid(idApprenant,Integer.parseInt(idFormation));
        S = FonctionBase.voalohany(Integer.parseInt(idFormation));
        m = new mouvementChapitres();
        m.inserer(A.getIdApprenant(), Integer.parseInt(idFormation), S);
System.out.println("gratos");
        return  ResponseEntity.ok("ao");
    }

    else {
        System.out.println("lafo");
        return  ResponseEntity.badRequest().body("tsia");
    }

}

    }


    @GetMapping ("/StatFo")
    public ResponseEntity<A> StatF( ) throws Exception {
        ArrayList<Statistique>rep=new ArrayList<Statistique>();

        Connection c=FonctionBase.connect();
        rep=FonctionBase.StatFo(c);
        c.close();

        A Af=new A(rep);
        return  ResponseEntity.ok(Af);
    }

    @GetMapping ("/StatFom")
    public ResponseEntity<Annee> StatFom(String annee ) throws Exception {



        Connection c=FonctionBase.connect();
        Annee rep=FonctionBase.StatFom(c,Integer.parseInt(annee));
        c.close();
        return  ResponseEntity.ok(rep);
    }


    @GetMapping ("/StatFomd")
    public ResponseEntity<ArrayList<Details>> StatFmd(String annee,String mois ) throws Exception {
        Connection c=FonctionBase.connect();
        ArrayList<Details>rep=FonctionBase.StatFomd(c,Integer.parseInt(annee),Integer.parseInt(mois));



        c.close();
        return  ResponseEntity.ok(rep);
    }


       @GetMapping ("/Nomformation")
    public ResponseEntity<ArrayList<Formation>> Nomformation(@RequestParam("idFormation") int idFormation) throws Exception {
        
      ArrayList<Formation> com = new Formation().Nomformation(idFormation);
 
        return  ResponseEntity.ok(com);         
} 
 

    @GetMapping ("/MesFormationPlusNotee")
    public ResponseEntity<ArrayList<Formation>> MesFormationPlusNotee(@RequestParam("token") String token  ) throws Exception {
System.out.println(token);
       Formateur f=FonctionBase.selectWithTokenF(token);

    ArrayList<Formation>rep=Formateur.MesFormationPlusNotee(f.getIdFormateur());

        return  ResponseEntity.ok(rep);
    }
    
    @GetMapping ("/tauxreussite")
    public ResponseEntity<ArrayList<Formation>> tauxreussite(@RequestParam("token") String token  ) throws Exception {
System.out.println(token);
       Formateur f=FonctionBase.selectWithTokenF(token);

    ArrayList<Formation>rep=Formateur.tauxReussiteParFormation(f.getIdFormateur());

        return  ResponseEntity.ok(rep);
    }
    
}
