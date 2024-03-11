package com.example.elearning.models;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FonctionBase {

    public static Connection connect() throws Exception {
        String url = "jdbc:postgresql://viaduct.proxy.rlwy.net:15614/railway?user=postgres&password=eaDeaAbcGb6dFF4F61-A5c6E6Eg4ccCe&charSet=UTF-8";
        //String url = "jdbc:postgresql://localhost:5432/elearning?user=postgres&password=cedric10&charSet=UTF-8";

        Connection connection;
        connection = DriverManager.getConnection(url);
        connection.setAutoCommit(true);
        System.out.println("Connectee");
        return connection;
    }

 public static void closeConnection(Connection connection) throws Exception {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            throw e;
        }
    }


    public  void modifBase(String sql) throws Exception {
        try (Connection connection = connect(); Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        }
    }

    public static void execute(String sql) throws Exception {
        System.out.println(sql);
        try (Connection connection = connect(); Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }

    public static ArrayList<civilite>  Allcivilite() throws Exception {
        String sql="Select * from civilite";
        ArrayList<civilite>  rep=new ArrayList<civilite>();
        Connection connection = connect(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {

            rep.add(new civilite(resultSet.getInt(1),resultSet.getString(2)));
        }

        return rep;
    }


    public static String  nomProfession(int id) throws Exception {
        String sql="Select nom from Profession where idProfession="+id;
        String  rep="";
        Connection connection = connect(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {

            rep=resultSet.getString(1);
        }
System.out.println("hihahiha"+rep);
        return rep;
    }


    public static ArrayList<TypeQuestion> AllTypeQuestion() throws Exception {
        String sql="Select * from TypeQuestion";
        ArrayList<TypeQuestion>  rep=new ArrayList<TypeQuestion>();
        Connection connection = connect(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
System.out.println("huhu");
            rep.add(new TypeQuestion(resultSet.getInt(1),resultSet.getString(2)));
        }

        return rep;

    }


    public static ArrayList<Formation> LesFormationNonValide() throws Exception {
        String sql="select*from formation join categorie on formation.idcategorie=categorie.idcategorie join typesacces on formation.typesacces=typesacces.idTypesAcces join langues on formation.langues=langues.idLangues join unite on formation.unite=unite.idUnite where formation.etat=1 ORDER BY formation.idformation DESC";
        ArrayList<Formation>  rep=new ArrayList<Formation>();
        Connection connection = connect(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql);
        File image = new File("");
        while (resultSet.next()) {
            System.out.println(resultSet.getString(10));
            image=new File(resultSet.getString(10));
            System.out.println(image);
            rep.add(new Formation(resultSet.getInt(1),resultSet.getInt(2), resultSet.getInt(3), resultSet.getString(18), resultSet.getInt(4), resultSet.getString(20), resultSet.getInt(5),resultSet.getString(22), resultSet.getString(6),resultSet.getString(7), resultSet.getInt(8), resultSet.getString(24), resultSet.getString(9), resultSet.getString(10), resultSet.getString(11), resultSet.getInt(12),Files.readAllBytes(image.toPath()),resultSet.getDouble(13),resultSet.getString(14),resultSet.getString(15),resultSet.getString(16)));
        }
connection.close();
        return rep;
    }


    public static ArrayList<Formation> MesFormationSuivies(int parseInt) throws Exception {

        String sql="select*from formation  join categorie on formation.idcategorie=categorie.idcategorie join typesacces on formation.typesacces=typesacces.idTypesAcces join langues on formation.langues=langues.idLangues join unite on formation.unite=unite.idUnite join inscritFormation on formation.idFormation= inscritFormation.idFormation where formation.etat=2 AND inscritFormation.idApprenant="+parseInt+" ORDER BY idinscritFormation DESC";
        System.out.println(sql);
        ArrayList<Formation>  rep=new ArrayList<Formation>();
        Connection connection = connect(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql);
        File image = new File("");
        while (resultSet.next()) {
            System.out.println(resultSet.getString(10));
            image=new File(resultSet.getString(10));
            System.out.println(image);
            rep.add(new Formation(resultSet.getInt(1),resultSet.getInt(2), resultSet.getInt(3), resultSet.getString(18), resultSet.getInt(4), resultSet.getString(20), resultSet.getInt(5),resultSet.getString(22), resultSet.getString(6),resultSet.getString(7), resultSet.getInt(8), resultSet.getString(24), resultSet.getString(9), resultSet.getString(10), resultSet.getString(11), resultSet.getInt(12),Files.readAllBytes(image.toPath()),resultSet.getDouble(13),resultSet.getString(14),resultSet.getString(15),resultSet.getString(16)));
        }
connection.close();

return rep;

    }

    public static ArrayList<mouvementChapitres> mesMouv(int parseInt, int parseInt1) throws Exception {
    ArrayList<mouvementChapitres> rep=new ArrayList<mouvementChapitres>();
    int isanysous=0;
        String sql=" Select count(idsouschapitres) from SousChapitres join chapitres ON chapitres.idChapitres=SousChapitres.idChapitres where idFormation="+parseInt1;


        Connection connection = connect(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            isanysous=resultSet.getInt(1);
        }
 sql="select * from mouvementChapitres where idApprenant="+parseInt+" AND idFormation="+parseInt1+" order by idmouvementChapitres asc";
         connection = connect(); statement = connection.createStatement();  resultSet = statement.executeQuery(sql);
       int i=0;
        while (resultSet.next()) {
     if(i<isanysous) {
         rep.add(new mouvementChapitres(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getInt(4)));
     }
            i++;

        }


        return rep;
    }

    public static mouvementZoom aove(int idApprenant, int idZoom) throws Exception {

        mouvementZoom rep=null;
        String sql="select * from mouvementZoom where idApprenant="+idApprenant+" AND idZoom="+idZoom;
        Connection connection = connect(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            rep=new mouvementZoom(resultSet.getInt(1), resultSet.getInt(2),resultSet.getInt(3),resultSet.getInt(4));

        break;
        }


        return rep;
    }

    public static ReponseQuiz Mareponse(int parseInt) throws Exception {

        ReponseQuiz rep=null;
        String sql="select * from ReponseQuiz where idReponseQuiz="+parseInt;
        Connection connection = connect(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            rep=new ReponseQuiz(resultSet.getInt(1), resultSet.getInt(2),resultSet.getString(3),resultSet.getDouble(4));

            break;
        }


        return rep;

    }


    public  static  int derniereTentative(int idApprenant, int idQuiz) throws Exception {
  int rep=0;

    String sql="select * from mouvementQuiz where idApprenant="+idApprenant+" AND idQuiz="+idQuiz+" order by tentative desc";
    Connection connection = connect(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql);
    while (resultSet.next()) {
        rep=resultSet.getInt(2);

        break;
    }

  return rep;
}


    public static ArrayList<Formation> LesFormationValide() throws Exception {
        String sql="select*from formation join categorie on formation.idcategorie=categorie.idcategorie join typesacces on formation.typesacces=typesacces.idTypesAcces join langues on formation.langues=langues.idLangues join unite on formation.unite=unite.idUnite where formation.etat=2 ORDER BY formation.idformation DESC";
        ArrayList<Formation>  rep=new ArrayList<Formation>();
        Connection connection = connect(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql);
        File image = new File("");
        while (resultSet.next()) {
            System.out.println(resultSet.getString(10));
            image=new File(resultSet.getString(10));
            System.out.println(image);
            rep.add(new Formation(resultSet.getInt(1),resultSet.getInt(2), resultSet.getInt(3), resultSet.getString(18), resultSet.getInt(4), resultSet.getString(20), resultSet.getInt(5),resultSet.getString(22), resultSet.getString(6),resultSet.getString(7), resultSet.getInt(8), resultSet.getString(24), resultSet.getString(9), resultSet.getString(10), resultSet.getString(11), resultSet.getInt(12),Files.readAllBytes(image.toPath()),resultSet.getDouble(13),resultSet.getString(14),resultSet.getString(15),resultSet.getString(16)));      }
connection.close();
        return rep;
    }

















    public static ArrayList<Formation> MesFormation(int idFormateur) throws Exception {
        String sql="select*from formation join categorie on formation.idcategorie=categorie.idcategorie join typesacces on formation.typesacces=typesacces.idTypesAcces join langues on formation.langues=langues.idLangues join unite on formation.unite=unite.idUnite where idFormateur="+idFormateur+" ORDER BY formation.idformation DESC";
        ArrayList<Formation>  rep=new ArrayList<Formation>();
        Connection connection = connect(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql);
        File image = new File("");
        while (resultSet.next()) {
            System.out.println(resultSet.getString(10));
image=new File(resultSet.getString(10));
            System.out.println(image);
            rep.add(new Formation(resultSet.getInt(1),resultSet.getInt(2), resultSet.getInt(3), resultSet.getString(18), resultSet.getInt(4), resultSet.getString(20), resultSet.getInt(5),resultSet.getString(22), resultSet.getString(6),resultSet.getString(7), resultSet.getInt(8), resultSet.getString(24), resultSet.getString(9), resultSet.getString(10), resultSet.getString(11), resultSet.getInt(12),Files.readAllBytes(image.toPath()),resultSet.getDouble(13),resultSet.getString(14),resultSet.getString(15),resultSet.getString(16)));
        }
connection.close();
        return rep;
    }



    public static ArrayList<Formation> RechercheFormation(String sql,Connection connection) throws Exception {
      ArrayList<Formation>  rep=new ArrayList<Formation>();
        Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql);
        File image = new File("");
        while (resultSet.next()) {

            image=new File(resultSet.getString(10));

            rep.add(new Formation(resultSet.getInt(1),resultSet.getInt(2), resultSet.getInt(3), resultSet.getString(18), resultSet.getInt(4), resultSet.getString(20), resultSet.getInt(5),resultSet.getString(22), resultSet.getString(6),resultSet.getString(7), resultSet.getInt(8), resultSet.getString(24), resultSet.getString(9), resultSet.getString(10), resultSet.getString(11), resultSet.getInt(12),Files.readAllBytes(image.toPath()),resultSet.getDouble(13),resultSet.getString(14),resultSet.getString(15),resultSet.getString(16)));    }

        return rep;
    }










    public static Formation MonFormation(int parseInt) throws Exception {

        String sql="select*from formation join categorie on formation.idcategorie=categorie.idcategorie join typesacces on formation.typesacces=typesacces.idTypesAcces join langues on formation.langues=langues.idLangues join unite on formation.unite=unite.idUnite where idFormation="+parseInt;
        Formation  rep=new Formation();
        Connection connection = connect(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql);
        File image = new File("");
        while (resultSet.next()) {

            image=new File(resultSet.getString(10));
            rep=new Formation(resultSet.getInt(1),resultSet.getInt(2), resultSet.getInt(3), resultSet.getString(18), resultSet.getInt(4), resultSet.getString(20), resultSet.getInt(5),resultSet.getString(22), resultSet.getString(6),resultSet.getString(7), resultSet.getInt(8), resultSet.getString(24), resultSet.getString(9), resultSet.getString(10), resultSet.getString(11), resultSet.getInt(12),Files.readAllBytes(image.toPath()),resultSet.getDouble(13),resultSet.getString(14),resultSet.getString(15),resultSet.getString(16));

        }
connection.close();
        return rep;
    }


    public static ArrayList<Profession> AllProfession() throws Exception {
        String sql="Select * from Profession ";
        ArrayList<Profession>  rep=new ArrayList<Profession>();
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                rep.add(new Profession(resultSet.getInt(1), resultSet.getString(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rep;
    }
    public static   ArrayList<modeDexercice>   AllmodeDexercice() throws Exception {
        String sql="Select * from modeDexercice ";
        ArrayList<modeDexercice>  rep=new ArrayList<modeDexercice>();
        Connection connection = connect(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {

            rep.add(new modeDexercice(resultSet.getInt(1),resultSet.getString(2)));
        }

        return rep;
    }

    public static   int   CountAllDemande() throws Exception {
        String sql="Select count(idDemande)  from DemandeAdhesion";
  int rep=0;
        Connection connection = connect(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
rep=resultSet.getInt(1);
 }

        return rep;
    }

    public static   ArrayList<Categorie>   AllCategorie() throws Exception {
        String sql="Select * from Categorie ";
        ArrayList<Categorie>  rep=new ArrayList<Categorie>();
        Connection connection = connect(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql);

        String myString = "";
        byte[] utf8Bytes = myString.getBytes(StandardCharsets.UTF_8);
        String utf8EncodedString ="";

// Utilisez utf8EncodedString dans votre requête SQL ou ailleurs dans votre application


        while (resultSet.next()) {


            myString=resultSet.getString(2);
            utf8Bytes = myString.getBytes(StandardCharsets.UTF_8);
            utf8EncodedString =  new String(utf8Bytes, StandardCharsets.UTF_8);

            System.out.println(utf8EncodedString);
            rep.add(new Categorie(resultSet.getInt(1),utf8EncodedString));
        }

        return rep;
    }

    public static   ArrayList<Langues>   AllLangues() throws Exception {
        String sql="Select * from Langues ";
        ArrayList<Langues>  rep=new ArrayList<Langues>();
        Connection connection = connect(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {

            rep.add(new Langues(resultSet.getInt(1),resultSet.getString(2)));
        }

        return rep;
    }



    public static   ArrayList<TypesAcces>   AllTypesAcces() throws Exception {
        String sql="Select * from modeDexercice ";
        ArrayList<TypesAcces>  rep=new ArrayList<TypesAcces>();
        Connection connection = connect(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {

            rep.add(new TypesAcces(resultSet.getInt(1),resultSet.getString(2)));
        }

        return rep;
    }










public static  ArrayList<Apprenant> ListApprenant() throws Exception {

    String sql="select*from apprenant where etatcompte=1 ";
    System.out.println(sql);
   ArrayList<Apprenant> rep=new ArrayList<Apprenant>();
    Connection connection = connect(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql);
    while (resultSet.next()) {

        rep.add(new Apprenant(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getInt(6),resultSet.getInt(7),resultSet.getInt(8),resultSet.getString(9),resultSet.getDate(10),resultSet.getString(11),resultSet.getInt(12),resultSet.getString(13)));
    }

    return rep;

}

    public static  ArrayList<Apprenant> ListApprenantI(int idFormation) throws Exception {

        String sql="select * from apprenant join inscritformation on inscritformation.idApprenant=apprenant.idapprenant where inscritformation.idformation= "+idFormation;
        System.out.println(sql);
        ArrayList<Apprenant> rep=new ArrayList<Apprenant>();
        Apprenant m=new Apprenant();
        Progression p=new Progression();
        long a=0;
        Connection connection = connect(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
m=new Apprenant(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getInt(6),resultSet.getInt(7),resultSet.getInt(8),resultSet.getString(9),resultSet.getDate(10),resultSet.getString(11),resultSet.getInt(12),resultSet.getDate(17),resultSet.getString(13));
a=p.progresssionA(resultSet.getInt(1),idFormation);
m.setProgression(a);
rep.add(m);
        }

        return rep;

    }






    public static  Apprenant selectWithToken(String token) throws Exception {
        String sql="Select * from Apprenant where token='"+token+"' ";
        System.out.println(sql);
        Apprenant rep=null;
        Connection connection = connect(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {

            rep=new Apprenant(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getInt(6),resultSet.getInt(7),resultSet.getInt(8),resultSet.getString(9),resultSet.getDate(10),resultSet.getString(11),resultSet.getInt(12),resultSet.getString(13));
        }

        return rep;
    }


    public static  Apprenant selectWithTokenConnecter(String token) throws Exception {
        String sql="Select * from Apprenant where token='"+token+"' AND etatcompte=1 ";
        System.out.println(sql);
        Apprenant rep=null;
        Connection connection = connect(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {

            rep=new  Apprenant(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getInt(6),resultSet.getInt(7),resultSet.getInt(8),resultSet.getString(9),resultSet.getDate(10),resultSet.getString(11),resultSet.getInt(12),resultSet.getString(13));
        }
connection.close();
        return rep;
    }








    public static  String  selectPassword(String token) throws Exception {
        String sql="Select mdp from MotDePasseOublier where token='"+token+"' ORDER BY idMotDePasseOublier DESC LIMIT 1";
        String rep="";
        Connection connection = connect(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            rep=resultSet.getString(1);
        }

        return rep;
    }





    public static  DemandeAdhesion selectWithTokend(String token) throws Exception {
        String sql="Select * from DemandeAdhesion where token='"+token+"' AND etat=1";
        DemandeAdhesion rep=null;
        Connection connection = connect(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {

            rep=new DemandeAdhesion(resultSet.getInt(1),resultSet.getTimestamp(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getString(8),resultSet.getString(9),resultSet.getString(10),resultSet.getString(11),resultSet.getInt(12));}

        return rep;
    }

    public static  ArrayList<DemandeAdhesion> AllDemande(int page,int size ) throws Exception {
        String sql="Select * from DemandeAdhesion  ORDER BY idDemande DESC LIMIT "+size+" OFFSET "+page*size;
       ArrayList<DemandeAdhesion>   rep=new ArrayList<DemandeAdhesion>();
        Connection connection = connect(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            Timestamp timestamp = resultSet.getTimestamp(2);

            // Créez un objet SimpleDateFormat pour le format souhaité
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

            // Utilisez SimpleDateFormat pour formater la date en une chaîne de caractères
            String formattedDate = sdf.format(timestamp);

            rep.add(new DemandeAdhesion(resultSet.getInt(1),resultSet.getTimestamp(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getString(8),resultSet.getString(9),resultSet.getString(10),resultSet.getString(11),resultSet.getInt(12),formattedDate));}

        return rep;
    }







    public static  Apprenant LoginApprenant(String email,String mdp) throws Exception {
        String sql="Select * from Apprenant where email='"+email+"' AND mdp='"+mdp+"' AND etatCompte=1";
        Apprenant rep=null;
        Connection connection = connect(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            rep=new Apprenant(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getInt(6),resultSet.getInt(7),resultSet.getInt(8),resultSet.getString(9),resultSet.getDate(10),resultSet.getString(11),resultSet.getInt(12),resultSet.getString(13)) ;  }

        return rep;
    }

    public static Admin LoginAdmin(String email, String password) throws Exception {

        String sql="Select * from Admin where email='"+email+"' AND mdp='"+password+"' ";
        Admin rep=null;
        Connection connection = connect(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            rep=new Admin(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3));
        }

        return rep;
    }

    public static  Formateur LoginFormateur(String email,String mdp) throws Exception {
        String sql="Select * from Formateur where email='"+email+"' AND mdp='"+mdp+"' AND etatCompte=1";
        Formateur rep=null;
        System.out.println(sql);

        Connection connection = connect(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            rep=new Formateur(resultSet.getInt(1),resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getInt(8),  resultSet.getInt(9),  resultSet.getInt(10), resultSet.getString(11), resultSet.getString(12), resultSet.getDate(13), resultSet.getString(14), resultSet.getString(15), resultSet.getString(16), resultSet.getInt(17),resultSet.getString(18),resultSet.getString(19));
        }

        return rep;
    }


    public static  ArrayList<Formateur>  ListFormateur() throws Exception {
        String sql="select*from formateur where etatcompte=1";
        ArrayList<Formateur>  rep=new ArrayList<Formateur>();
        Connection connection = connect(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            rep.add(new Formateur(resultSet.getInt(1),resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getInt(8),  resultSet.getInt(9),  resultSet.getInt(10), resultSet.getString(11), resultSet.getString(12), resultSet.getDate(13), resultSet.getString(14), resultSet.getString(15), resultSet.getString(16), resultSet.getInt(17),resultSet.getString(18),resultSet.getString(19)));

        }
connection.close();
        return rep;


    }








    public static  Formateur  selectWithTokenF(String token) throws Exception {
        String sql="Select * from Formateur where token='"+token+"'";
        Formateur rep=null;
        Connection connection = connect(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            rep = new Formateur(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getInt(8), resultSet.getInt(9), resultSet.getInt(10), resultSet.getString(11), resultSet.getString(12), resultSet.getDate(13), resultSet.getString(14), resultSet.getString(15), resultSet.getString(16), resultSet.getInt(17), resultSet.getString(18), resultSet.getString(19));
        }
        connection.close();
        return rep;


    }

    public static  Formateur  moi(int i) throws Exception {
        String sql="Select * from Formateur where idFormateur="+i;
        Formateur rep=null;
        Connection connection = connect(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            rep=new Formateur(resultSet.getInt(1),resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getInt(8),  resultSet.getInt(9),  resultSet.getInt(10), resultSet.getString(11), resultSet.getString(12), resultSet.getDate(13), resultSet.getString(14), resultSet.getString(15), resultSet.getString(16), resultSet.getInt(17),resultSet.getString(18),resultSet.getString(19));
        }

        return rep;


    }






    public static  Formateur  selectWithTokenFConnecter(String token) throws Exception {
        String sql="Select * from Formateur where token='"+token+"' AND etatcompte=1";
        Formateur rep=null;
        Connection connection = connect(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            rep=new Formateur(resultSet.getInt(1),resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getInt(8),  resultSet.getInt(9),  resultSet.getInt(10), resultSet.getString(11), resultSet.getString(12), resultSet.getDate(13), resultSet.getString(14), resultSet.getString(15), resultSet.getString(16), resultSet.getInt(17),resultSet.getString(18),resultSet.getString(19));
        }

        return rep;


    }




    public static Apprenant findByEmail(String email) throws Exception {

        String sql="Select * from Apprenant where email='"+email+"' AND etatcompte=1";
        Apprenant rep=null;
        Connection connection = connect(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            rep=new Apprenant(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getInt(6),resultSet.getInt(7),resultSet.getInt(8),resultSet.getString(9),resultSet.getDate(10),resultSet.getString(11),resultSet.getInt(12),resultSet.getString(13));     }

        return rep;
    }

    public static Formateur findByEmailF(String email) throws Exception {

        String sql="Select * from Formateur where email='"+email+"' AND etatcompte=1";
        Formateur rep=null;
        Connection connection = connect(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            rep = new Formateur(resultSet.getInt(1),resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getInt(8),  resultSet.getInt(9),  resultSet.getInt(10), resultSet.getString(11), resultSet.getString(12), resultSet.getDate(13), resultSet.getString(14), resultSet.getString(15), resultSet.getString(16), resultSet.getInt(17),resultSet.getString(18),resultSet.getString(19));   }
            return rep;
    }



    public static  Formateur findByEmailA(String email) throws Exception {

        String sql="Select * from Formateur where email='"+email+"' AND etatcompte=1";
        Formateur rep=null;
        Connection connection = connect(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            rep=new Formateur(resultSet.getInt(1),resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getInt(8),  resultSet.getInt(9),  resultSet.getInt(10), resultSet.getString(11), resultSet.getString(12), resultSet.getDate(13), resultSet.getString(14), resultSet.getString(15), resultSet.getString(16), resultSet.getInt(17),resultSet.getString(18),resultSet.getString(19));

        }
        connection.close();
        return rep;
    }







    public static DemandeAdhesion findByIdA(int id) throws Exception {

        String sql="Select * from DemandeAdhesion where idDemande="+id;
        DemandeAdhesion rep=null;
        Connection connection = connect(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {

            rep=new DemandeAdhesion(resultSet.getInt(1),resultSet.getTimestamp(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getString(8),resultSet.getString(9),resultSet.getString(10),resultSet.getString(11),resultSet.getInt(12));
        }
        connection.close();
        return rep;
    }



    public static ArrayList<Categorie> allCategorie() throws Exception {

        String sql="Select * from Categorie";
        ArrayList<Categorie> rep= new ArrayList<Categorie>();
        Connection connection = connect(); PreparedStatement statement = connection.prepareStatement(sql); ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {

            rep.add(new Categorie(resultSet.getInt(1),resultSet.getString(2)));
        }
        connection.close();
        return rep;

    }


    public static ArrayList<Unite> allUnite() throws Exception {

        String sql="Select * from Unite";
        ArrayList<Unite> rep= new ArrayList<Unite>();
        Connection connection = connect(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {

            rep.add(new Unite(resultSet.getInt(1),resultSet.getString(2)));
        }
        connection.close();
        return rep;
    }







    public static ArrayList<TypesAcces> allTypesAcces() throws Exception {

        String sql="Select * from TypesAcces";
        ArrayList<TypesAcces> rep= new ArrayList<TypesAcces>();
        Connection connection = connect(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {

            rep.add(new TypesAcces(resultSet.getInt(1),resultSet.getString(2)));
        }

        return rep;
    }





    public static ArrayList<Langues> allLangues() throws Exception {

        String sql="Select * from Langues";
        ArrayList<Langues> rep= new ArrayList<Langues>();
        Connection connection = connect(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {

            rep.add(new Langues(resultSet.getInt(1),resultSet.getString(2)));
        }

        return rep;
    }


    public static ArrayList<Chapitres> allChapitres(int idFormation) throws Exception {

        String sql="Select * from Chapitres where idFormation="+idFormation;
        ArrayList<Chapitres> rep= new ArrayList<Chapitres>();
        Connection connection = connect(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {

            rep.add(new Chapitres(resultSet.getInt(1),resultSet.getInt(2),resultSet.getString(3)));
        }
        connection.close();
        return rep;
    }


    public static ArrayList<QuestionQuiz> mesQ(int idQuiz) throws Exception {

        String sql="Select * from QuestionQuiz where idQuiz ="+idQuiz;
        ArrayList<QuestionQuiz> rep= new ArrayList<QuestionQuiz>();
        Connection connection = connect(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {

            rep.add(new QuestionQuiz(resultSet.getInt(1),resultSet.getInt(2),resultSet.getInt(3),resultSet.getString(4)));
        }
        connection.close();
        return rep;
    }

    public static int maderniereq(int idQuiz) throws Exception {

        String sql="Select * from QuestionQuiz where idQuiz ="+idQuiz+" order by idQuestionQuiz";
        int rep= 0;
        Connection connection = connect(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {

            rep=resultSet.getInt(1);
        }
        connection.close();
        return rep;

    }



    public static QuestionQuiz monQuestion(int idQuestion) throws Exception {

        String sql="Select * from QuestionQuiz where idQuestionQuiz ="+idQuestion;
        QuestionQuiz rep= new QuestionQuiz();
        Connection connection = connect(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {

            rep=new QuestionQuiz(resultSet.getInt(1),resultSet.getInt(2),resultSet.getInt(3),resultSet.getString(4));
        }
        connection.close();
        return rep;
    }























    public static ArrayList<SousChapitres> allSousChapitres(int idchapitre) throws Exception {

        String sql="Select * from SousChapitres where idChapitres="+idchapitre;
        ArrayList<SousChapitres> rep= new ArrayList<SousChapitres>();
        Connection connection = connect(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {

            rep.add(new SousChapitres(resultSet.getInt(1),resultSet.getInt(2),resultSet.getString(3)));
        }
        connection.close();
        return rep;
    }

    public static int voalohany(int parseInt) throws Exception {

        String sql=" Select * from SousChapitres join chapitres on chapitres.idChapitres=souschapitres.idchapitres where idformation="+parseInt+" order by idsouschapitres asc";
        int rep= 0;
        Connection connection = connect(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql);


        while (resultSet.next()) {

            rep=resultSet.getInt(1);
            break;
        }
        connection.close();
        return rep;

    }



    public static inscritFormation Suivie(int idApprenant,int idFormation) throws Exception {

        String sql="Select * from inscritFormation where idApprenant="+idApprenant+" And idFormation="+idFormation;
        inscritFormation rep= null;
        Connection connection = connect(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {

            rep=new inscritFormation(resultSet.getInt(1),resultSet.getInt(2),resultSet.getInt(3),resultSet.getString(4));
        }
        connection.close();
        return rep;
    }


    public static ArrayList<ContenuSousChapitres> allContenuSousChapitres(int idSouschapitre) throws Exception {

        String sql="Select * from ContenuSousChapitres where idSousChapitres="+idSouschapitre+" order by idContenuSousChapitres ASC";
        ArrayList<ContenuSousChapitres> rep= new ArrayList<ContenuSousChapitres>();
        Connection connection = connect(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql);
        File c = new File("");
        while (resultSet.next()) {

            if(resultSet.getString(5).equals("image")||resultSet.getString(5).equals("video")||resultSet.getString(5).equals("pdf")){
                System.out.println("ihi");
                c=new File(resultSet.getString(3));
                rep.add(new ContenuSousChapitres(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5),Files.readAllBytes(c.toPath())));


            }
            else {
                rep.add(new ContenuSousChapitres(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5)));
            }
        }
        connection.close();
        return rep;
    }


    public static ArrayList<Zoom> allZooms(int idFormation) throws Exception {

        String sql="Select * from zoom where idFormation="+idFormation;
        ArrayList<Zoom> rep= new ArrayList<Zoom>();
        Connection connection = connect(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            System.out.println("jj");

                rep.add(new Zoom(resultSet.getInt(1),resultSet.getInt(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getString(8),resultSet.getString(9),resultSet.getString(10)));
        }
        connection.close();
        return rep;
    }

    public static Zoom MonZoom(int idZoom) throws Exception {

        String sql="Select * from zoom where idZoom="+idZoom;
        Zoom rep= new Zoom();
        Connection connection = connect(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            System.out.println("jj");

            rep=new Zoom(resultSet.getInt(1),resultSet.getInt(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getString(8),resultSet.getString(9),resultSet.getString(10));
        }
        connection.close();
        return rep;
    }








    public static ArrayList<Quiz> allQuiz(int idFormation) throws Exception {

        String sql="Select * from quiz where idFormation="+idFormation;
        ArrayList<Quiz> rep= new ArrayList<Quiz>();
        Connection connection = connect(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {

            rep.add(new Quiz(resultSet.getInt(1),resultSet.getInt(2),resultSet.getString(3)));
        }
        connection.close();
        return rep;
    }


    public static Quiz MonQuiz(int idQuiz) throws Exception {

        String sql="Select * from quiz where idQuiz="+idQuiz;
        Quiz rep= new Quiz();
        Connection connection = connect(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {

            rep=new Quiz(resultSet.getInt(1),resultSet.getInt(2),resultSet.getString(3));
        }
        connection.close();
        return rep;
    }

    public static ArrayList<ReponseQuiz> allrep(int idQuestionQuiz) throws Exception {

        String sql="Select * from ReponseQuiz where idQuestion="+idQuestionQuiz;
        ArrayList<ReponseQuiz> rep= new ArrayList<ReponseQuiz>();
        Connection connection = connect(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {

            rep.add(new ReponseQuiz(resultSet.getInt(1),resultSet.getInt(2),resultSet.getString(3),resultSet.getDouble(4)));
        }
        connection.close();
        return rep;
    }


    public static double totalQuestion(int idQuiz) throws Exception {

    double rep=0.0;



            String sql="select Sum(note) from reponsequiz join QuestionQuiz on QuestionQuiz.idQuestionQuiz=reponsequiz.idquestion where questionquiz.idquiz="+idQuiz;
            Connection connection = connect(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                rep=resultSet.getDouble(1);

                break;
            }


    return rep;
    }

public static ArrayList<NoteQuiz> mesNotes(int idapprenant,int idquiz) throws Exception {
  ArrayList<NoteQuiz> rep=new ArrayList<NoteQuiz>();

  String sql="select  tentative,idapprenant,mouvementquiz.idquiz,Sum(reponsequiz.note) from  mouvementquiz join reponsequiz on reponsequiz.idReponseQuiz=mouvementquiz.idreponsequiz where idapprenant="+idapprenant+" and idquiz="+idquiz+" group by tentative,idapprenant,mouvementquiz.idquiz order by tentative desc";

    Connection connection = connect(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql);
    while (resultSet.next()) {
   rep.add(new NoteQuiz(resultSet.getInt(1),resultSet.getInt(2),resultSet.getInt(3),resultSet.getDouble(4),totalQuestion(idquiz)));
    }


      return rep;
}

public static double cent(int idformation) throws Exception {

double rep=0.0;
String sql=" Select count(idsouschapitres) from SousChapitres join chapitres ON chapitres.idChapitres=SousChapitres.idChapitres where idFormation="+idformation;


    Connection connection = connect(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql);
    while (resultSet.next()) {
        rep=resultSet.getDouble(1);
    }


     sql=" Select count(idZoom) from zoom  where idFormation="+idformation;


 connection = connect();  statement = connection.createStatement();  resultSet = statement.executeQuery(sql);
    while (resultSet.next()) {
        rep=rep+resultSet.getDouble(1);
    }



    sql=" Select count(idQuiz) from Quiz where idFormation="+idformation;


     connection = connect();  statement = connection.createStatement(); resultSet = statement.executeQuery(sql);
    while (resultSet.next()) {
        rep=rep+resultSet.getDouble(1);
    }
    connection.close();
return rep;
    }

    public static double nyvitany(int idApprenant,int idformation,Connection connection) throws Exception {

        double rep=0.0;
        double isanysous=0.0;
        String sql=" Select count(idsouschapitres) from SousChapitres join chapitres ON chapitres.idChapitres=SousChapitres.idChapitres where idFormation="+idformation;


        Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            isanysous=resultSet.getDouble(1);
        }

         sql=" select count(idmouvementchapitres) from mouvementchapitres where idformation="+idformation +"and idApprenant="+idApprenant;

System.out.println(sql);
      resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            rep=resultSet.getDouble(1);
        }


if(rep==1){

    rep=0.0;
}

System.out.println("isanysous"+isanysous);
        System.out.println("rep"+rep);
if(rep>isanysous){

    rep=isanysous;
    System.out.println("lll");
}

else if(rep>1&&rep<=isanysous){
    System.out.println("lllkiiii");
    rep=rep-1;
}

        sql="select count (idmouvementzoom) from mouvementzoom where idapprenant="+idApprenant+" and idformation ="+idformation;


           resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            rep=rep+resultSet.getDouble(1);
        }


        sql=" select mouvementquiz.idquiz from mouvementquiz join quiz on quiz.idquiz=mouvementquiz.idquiz where idapprenant="+idApprenant+" and idformation ="+idformation+" group by mouvementquiz.idquiz";


        resultSet = statement.executeQuery(sql);
       while (resultSet.next()) {
            rep=rep+1.0;
        }





        return rep;
    }

    public static ArrayList<Statistique> StatA(Connection connection) throws SQLException {
        ArrayList<Statistique> rep=new ArrayList<Statistique>();
        String sql="SELECT EXTRACT(YEAR FROM dateDajout) AS annee, COUNT(*) AS nombre_personnes\n" +
                "FROM Apprenant where etatCompte=1\n" +
                " GROUP BY annee\n" +
                " ORDER BY annee desc";
        Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            rep.add(new Statistique(resultSet.getString(1),resultSet.getInt(2)));
        }

       return rep;
    }

    public static Annee StatAm(Connection connection,int parseInt) throws SQLException {
Annee rep=new Annee();
        String sql="SELECT EXTRACT(MONTH FROM dateDajout) AS mois, COUNT(*) AS nombre_personnes\n" +
                "FROM Apprenant where etatCompte=1 and\n" +
                " EXTRACT(YEAR FROM dateDajout) ="+ parseInt+"GROUP BY mois\n" +
                "ORDER BY mois desc";
System.out.println(sql);
        Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql);
int i=0;
        while (resultSet.next()) {

for (int j=0;j<rep.getMesMois().size();j++){

    if(rep.getMesMois().get(j).getChiffre()==resultSet.getInt(1)) {

        rep.getMesMois().get(j).setNombre(resultSet.getInt(2));

    }

}

            i++;
            }

        int cent=0;

        for(int j=0;j<rep.getMesMois().size();j++){
cent=cent+rep.getMesMois().get(j).getNombre();

        }

        for(int j=0;j<rep.getMesMois().size();j++){
            if(rep.getMesMois().get(j).getNombre()!=0) {
                rep.getMesMois().get(j).setPourcent((rep.getMesMois().get(j).getNombre() * 100) / cent);
            }
        }



        return rep;
    }

    public static ArrayList<Details> StatAmd(Connection connection, int parseInt, int parseInt1) throws SQLException {


        ArrayList<Details> rep=new ArrayList<Details>();
        String sql="SELECT \n" +
                "    Profession.nom, -- Replace with the actual column name from Profession table\n" +
                "    COALESCE(COUNT(Apprenant.dateDajout), 0) AS nombre_personnes\n" +
                "FROM \n" +
                "    Profession\n" +
                "LEFT JOIN \n" +
                "    Apprenant ON Apprenant.Profession = Profession.idprofession \n" +
                "               AND etatCompte = 1 \n" +
                "               AND EXTRACT(YEAR FROM Apprenant.dateDajout) ="+ parseInt +
                "               AND EXTRACT(MONTH FROM Apprenant.dateDajout) ="+ parseInt1 +
                "GROUP BY \n" +
                "    Profession.idprofession, Profession.nom;\n" +
                "\n" +
                "\n";
        System.out.println(sql);
        Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            rep.add(new Details(resultSet.getString(1),resultSet.getInt(2)));
        }

        return rep;




    }



    public static ArrayList<Statistique> StatF(Connection connection) throws SQLException {

        ArrayList<Statistique> rep=new ArrayList<Statistique>();
        String sql="SELECT EXTRACT(YEAR FROM dateDajout) AS annee, COUNT(*) AS nombre_personnes\n" +
                "FROM formateur where etatCompte=1\n" +
                " GROUP BY annee\n" +
                " ORDER BY annee desc\n";
        Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            rep.add(new Statistique(resultSet.getString(1),resultSet.getInt(2)));
        }

        return rep;
    }

    public static Annee StatFm(Connection connection,int parseInt) throws SQLException {
        Annee rep=new Annee();
        String sql="SELECT EXTRACT(MONTH FROM dateDajout) AS mois, COUNT(*) AS nombre_personnes\n" +
                "FROM Formateur where etatCompte=1 and\n" +
                " EXTRACT(YEAR FROM dateDajout) ="+ parseInt+"GROUP BY mois\n" +
                "ORDER BY mois desc";

        Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql);
        int i=0;
        while (resultSet.next()) {

            for (int j=0;j<rep.getMesMois().size();j++){

                if(rep.getMesMois().get(j).getChiffre()==resultSet.getInt(1)) {

                    rep.getMesMois().get(j).setNombre(resultSet.getInt(2));

                }

            }

            i++;
        }

        int cent=0;

        for(int j=0;j<rep.getMesMois().size();j++){
            cent=cent+rep.getMesMois().get(j).getNombre();

        }

        for(int j=0;j<rep.getMesMois().size();j++){
            if(rep.getMesMois().get(j).getNombre()!=0) {
                rep.getMesMois().get(j).setPourcent((rep.getMesMois().get(j).getNombre() * 100) / cent);
            }
        }


        return rep;
    }

    public static ArrayList<Details> StatFmd(Connection connection, int parseInt, int parseInt1) throws SQLException {


        ArrayList<Details> rep=new ArrayList<Details>();
        String sql="SELECT \n" +
                "    Profession.nom, -- Replace with the actual column name from Profession table\n" +
                "    COALESCE(COUNT(Formateur.dateDajout), 0) AS nombre_personnes\n" +
                "FROM \n" +
                "    Profession\n" +
                "LEFT JOIN \n" +
                "    Formateur ON Formateur.Profession = Profession.idprofession \n" +
                "               AND etatCompte = 1 \n" +
                "               AND EXTRACT(YEAR FROM Formateur.dateDajout) ="+ parseInt +
                "               AND EXTRACT(MONTH FROM Formateur.dateDajout) ="+ parseInt1 +
                "GROUP BY \n" +
                "    Profession.idprofession, Profession.nom;\n" +
                "\n" +
                "\n";
        Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            rep.add(new Details(resultSet.getString(1),resultSet.getInt(2)));
        }

        return rep;




    }





    public static ArrayList<Statistique> StatFo(Connection connection) throws SQLException {
        ArrayList<Statistique> rep=new ArrayList<Statistique>();
        String sql="SELECT EXTRACT(YEAR FROM devalidation) AS annee, COUNT(*) AS nombre_personnes\n" +
                "FROM Formation where etat=2\n" +
                " GROUP BY annee\n" +
                " ORDER BY annee desc";
        Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            rep.add(new Statistique(resultSet.getString(1),resultSet.getInt(2)));
        }

        return rep;
    }





    public static Annee StatFom(Connection connection,int parseInt) throws SQLException {
        Annee rep=new Annee();
        String sql="SELECT EXTRACT(MONTH FROM devalidation) AS mois, COUNT(*) AS nombre_personnes\n" +
                "FROM Formation where etat=2 and\n" +
                " EXTRACT(YEAR FROM devalidation) ="+ parseInt+"GROUP BY mois\n" +
                "ORDER BY mois desc";

        Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql);
        int i=0;
        while (resultSet.next()) {

            for (int j=0;j<rep.getMesMois().size();j++){

                if(rep.getMesMois().get(j).getChiffre()==resultSet.getInt(1)) {

                    rep.getMesMois().get(j).setNombre(resultSet.getInt(2));

                }

            }

            i++;
        }

        int cent=0;

        for(int j=0;j<rep.getMesMois().size();j++){
            cent=cent+rep.getMesMois().get(j).getNombre();

        }

        for(int j=0;j<rep.getMesMois().size();j++){
            if(rep.getMesMois().get(j).getNombre()!=0) {
                rep.getMesMois().get(j).setPourcent((rep.getMesMois().get(j).getNombre() * 100) / cent);
            }
        }


        return rep;
    }

    public static ArrayList<Details> StatFomd(Connection connection, int parseInt, int parseInt1) throws SQLException {



        ArrayList<Details> rep=new ArrayList<Details>();
        String sql="SELECT \n" +
                "    Categorie.nom, -- Replace with the actual column name from Profession table\n" +
                "    COALESCE(COUNT(Formation.devalidation), 0) AS nombre_personnes\n" +
                "FROM \n" +
                "    Categorie\n" +
                "LEFT JOIN \n" +
                "    Formation ON Formation.idCategorie =Categorie.idCategorie \n" +
                "               AND etat = 2 \n" +
                "               AND EXTRACT(YEAR FROM Formation.devalidation) ="+ parseInt +
                "               AND EXTRACT(MONTH FROM Formation.devalidation) ="+ parseInt1 +
                "GROUP BY \n" +
                "    Categorie.idCategorie, Categorie.nom;\n" +
                "\n" +
                "\n";
        Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            rep.add(new Details(resultSet.getString(1),resultSet.getInt(2)));
        }

        return rep;




    }












}


