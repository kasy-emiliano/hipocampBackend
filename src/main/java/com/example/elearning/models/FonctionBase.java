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

    public static ArrayList<civilite> Allcivilite() throws Exception {
        String sql = "SELECT * FROM civilite";
        ArrayList<civilite> rep = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        try {
            // Établir une connexion
            connection = connect();
            
            // Créer une déclaration
            statement = connection.createStatement();
            
            // Exécuter la requête
            resultSet = statement.executeQuery(sql);
    
            // Parcourir les résultats
            while (resultSet.next()) {
                rep.add(new civilite(resultSet.getInt(1), resultSet.getString(2)));
            }
        } catch (Exception e) {
            e.printStackTrace(); // Gérer les exceptions de manière appropriée
        } finally {
            // Fermer les ressources dans un bloc finally pour s'assurer qu'elles sont libérées
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    
        return rep;
    }

    public static String nomProfession(int id) throws Exception {
        String sql = "SELECT nom FROM Profession WHERE idProfession=?";
        String rep = "";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try {
            // Établir une connexion
            connection = connect();
            
            // Préparer la requête
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            
            // Exécuter la requête
            resultSet = preparedStatement.executeQuery();
    
            // Traiter les résultats
            while (resultSet.next()) {
                rep = resultSet.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Gérer les exceptions de manière appropriée
        } finally {
            // Fermer les ressources dans un bloc finally pour s'assurer qu'elles sont libérées
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    
        return rep;
    }


   public static ArrayList<TypeQuestion> AllTypeQuestion() throws Exception {
    String sql = "SELECT * FROM TypeQuestion";
    ArrayList<TypeQuestion> rep = new ArrayList<>();
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    
    try {
        // Établir une connexion
        connection = connect();
        
        // Créer une déclaration
        statement = connection.createStatement();
        
        // Exécuter la requête
        resultSet = statement.executeQuery(sql);

        // Parcourir les résultats
        while (resultSet.next()) {
            rep.add(new TypeQuestion(resultSet.getInt(1), resultSet.getString(2)));
        }
    } catch (Exception e) {
        e.printStackTrace(); // Gérer les exceptions de manière appropriée
    } finally {
        // Fermer les ressources dans un bloc finally pour s'assurer qu'elles sont libérées
        if (resultSet != null) {
            resultSet.close();
        }
        if (statement != null) {
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }

    return rep;
}


public static ArrayList<Formation> LesFormationNonValide() throws Exception {
    ArrayList<Formation> listeDept = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            FonctionBase connect = new FonctionBase();
            connection = connect.connect();

            // Modifiez la requête en fonction des conditions que vous souhaitez appliquer
            String sql =" SELECT formation.*,categorie.nom AS categorie_nom,typesacces.nom AS types_acces_nom,langues.nom AS langues_nom,unite.nom AS unite_nom FROM formation JOIN categorie ON formation.idcategorie = categorie.idcategorie JOIN typesacces ON formation.typesacces = typesacces.idTypesAcces JOIN langues ON formation.langues = langues.idLangues JOIN unite ON formation.unite = unite.idUnite WHERE formation.etat = 1 ORDER BY formation.idformation DESC;";
            statement = connection.prepareStatement(sql);
            // Paramètres de condition
            result = statement.executeQuery();

            while (result.next()) {
                Formation com = new Formation();

                com.setIdFormation(result.getInt("idFormation"));
                com.setIdFormateur(result.getInt("idformateur"));
                com.setIdCategorie(result.getInt("idcategorie"));
                com.setTypesAcces(result.getInt("typesacces"));
                com.setLangues(result.getInt("langues"));
                com.setTitre(result.getString("titre"));
                com.setDuree(result.getString("duree"));
                com.setUnite(result.getInt("unite"));
                com.setResumer(result.getString("resumer"));
                com.setToken(result.getString("token"));
                com.setEtat(result.getInt("etat"));
                com.setPrix(result.getString("prix"));
                com.setDateDajout(result.getString("datedajout"));
                com.setDevalidation(result.getString("devalidation"));
                com.setDedemande(result.getString("dedemande"));
                com.setNomCategorie(result.getString("categorie_nom"));
                com.setNomTypesAcces(result.getString("types_acces_nom"));
                com.setNomLangues(result.getString("langues_nom"));
                com.setNomUnite(result.getString("unite_nom"));
                com.setEtatPublication(result.getInt("etatPublication"));
                // Récupérer le chemin de l'image depuis la base de données
                String imagePath = result.getString("pdc");

                // Créer un objet File à partir du chemin de l'image
                File imageFile = new File(imagePath);

                // Lire les octets de l'image
                byte[] imageData = Files.readAllBytes(imageFile.toPath());

                // Définir les octets de l'image dans l'objet Formation
                com.setImage(imageData);
                
 ArrayList<Apprenant> rep = FonctionBase.ListApprenantI(com.getIdFormation());
    // Définissez le nombre total d'élèves pour cette formation
    com.setTotalEleve(rep.size());
                listeDept.add(com);

            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (result != null) {
                result.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return listeDept;
    }
public static ArrayList<Formation> MesFormationSuivies(int parseInt) throws Exception {
    String sql = "SELECT * FROM formation " +
                 "JOIN categorie ON formation.idcategorie = categorie.idcategorie " +
                 "JOIN typesacces ON formation.typesacces = typesacces.idTypesAcces " +
                 "JOIN langues ON formation.langues = langues.idLangues " +
                 "JOIN unite ON formation.unite = unite.idUnite " +
                 "JOIN inscritFormation ON formation.idFormation = inscritFormation.idFormation " +
                 "WHERE formation.etat = 2 AND etatpublication=1 and  inscritFormation.idApprenant = " + parseInt + 
                 "ORDER BY idinscritFormation DESC";
    ArrayList<Formation> rep = new ArrayList<>();
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    try {
        // Établir une connexion
        connection = connect();

        // Créer une déclaration
        statement = connection.createStatement();

        // Exécuter la requête
        resultSet = statement.executeQuery(sql);

        // Parcourir les résultats
        while (resultSet.next()) {
            // Lecture du chemin du fichier image
            String imagePath = resultSet.getString(10);
            File imageFile = new File(imagePath);
            byte[] imageData = Files.readAllBytes(imageFile.toPath());

            // Création de l'objet Formation et ajout à la liste
            rep.add(new Formation(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getString(18), resultSet.getInt(4), resultSet.getString(20), resultSet.getInt(5), resultSet.getString(22), resultSet.getString(6), resultSet.getString(7), resultSet.getInt(8), resultSet.getString(24), resultSet.getString(9), imagePath, resultSet.getString(11), resultSet.getInt(12), imageData, resultSet.getDouble(13), resultSet.getString(14), resultSet.getString(15), resultSet.getString(16)));
        }
    } catch (Exception e) {
        e.printStackTrace(); // Gérer les exceptions de manière appropriée
    } finally {
        // Fermer les ressources dans un bloc finally pour s'assurer qu'elles sont libérées
        if (resultSet != null) {
            resultSet.close();
        }
        if (statement != null) {
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }

    return rep;
}
public static ArrayList<Formation> MesFormationSuiviesDeux(int idApprenant,String nomespace) throws Exception {
        ArrayList<Formation> listeDept = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            FonctionBase connect = new FonctionBase();
            connection = connect.connect();

            // Modifiez la requête en fonction des conditions que vous souhaitez appliquer
            String sql ="SELECT * FROM formation JOIN categorie ON formation.idcategorie = categorie.idcategorie JOIN typesacces ON formation.typesacces = typesacces.idTypesAcces JOIN langues ON formation.langues = langues.idLangues JOIN unite ON formation.unite = unite.idUnite JOIN inscritFormation ON formation.idFormation = inscritFormation.idFormation Join formateur on formation.idformateur=formateur.idformateur WHERE formation.etat = 2 AND etatpublication=2 and formateur.nomespace=? and  inscritFormation.idApprenant = ? ORDER BY idinscritFormation DESC";
            statement = connection.prepareStatement(sql);
            // Paramètres de condition
            statement.setString(1, nomespace);
            statement.setInt(2, idApprenant);
            result = statement.executeQuery();

            while (result.next()) {
                Formation com = new Formation();

                com.setIdFormation(result.getInt("idFormation"));
                com.setIdFormateur(result.getInt("idformateur"));
                com.setIdCategorie(result.getInt("idcategorie"));
                com.setTypesAcces(result.getInt("typesacces"));
                com.setLangues(result.getInt("langues"));
                com.setTitre(result.getString("titre"));
                com.setDuree(result.getString("duree"));
                com.setUnite(result.getInt("unite"));
                com.setResumer(result.getString("resumer"));
                com.setToken(result.getString("token"));
                com.setEtat(result.getInt("etat"));
                com.setPrix(result.getString("prix"));
                com.setDateDajout(result.getString("datedajout"));
                com.setDevalidation(result.getString("devalidation"));
                com.setDedemande(result.getString("dedemande"));
                com.setEtatPublication(result.getInt("etatPublication"));
                // Récupérer le chemin de l'image depuis la base de données
                String imagePath = result.getString("pdc");

                // Créer un objet File à partir du chemin de l'image
                File imageFile = new File(imagePath);

                // Lire les octets de l'image
                byte[] imageData = Files.readAllBytes(imageFile.toPath());

                // Définir les octets de l'image dans l'objet Formation
                com.setImage(imageData);
                 
                listeDept.add(com);

            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (result != null) {
                result.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return listeDept;
    }




public static ArrayList<mouvementChapitres> mesMouv(int parseInt, int parseInt1) throws Exception {
    ArrayList<mouvementChapitres> rep = new ArrayList<>();
    int isanysous = 0;
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    
    try {
        String sql = "SELECT COUNT(idsouschapitres) FROM SousChapitres JOIN chapitres ON chapitres.idChapitres = SousChapitres.idChapitres WHERE idFormation = " + parseInt1;
        
        // Établir une connexion et exécuter la première requête
        connection = connect();
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);
        
        // Récupérer le nombre de sous-chapitres
        while (resultSet.next()) {
            isanysous = resultSet.getInt(1);
        }
        
        // Exécuter la deuxième requête
        sql = "SELECT * FROM mouvementChapitres WHERE idApprenant = " + parseInt + " AND idFormation = " + parseInt1 + " ORDER BY idmouvementChapitres ASC";
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);
        
        // Parcourir les résultats
        int i = 0;
        while (resultSet.next()) {
            if (i < isanysous) {
                rep.add(new mouvementChapitres(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getInt(4)));
            }
            i++;
        }
    } catch (Exception e) {
        e.printStackTrace(); // Gérer les exceptions de manière appropriée
    } finally {
        // Fermer les ressources dans un bloc finally pour s'assurer qu'elles sont libérées
        if (resultSet != null) {
            resultSet.close();
        }
        if (statement != null) {
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }

    return rep;
}


public static mouvementZoom aove(int idApprenant, int idZoom) throws Exception {
    mouvementZoom rep = null;
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    
    try {
        String sql = "SELECT * FROM mouvementZoom WHERE idApprenant = " + idApprenant + " AND idZoom = " + idZoom;
        
        // Établir une connexion et exécuter la requête
        connection = connect();
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);
        
        // Récupérer les données
        if (resultSet.next()) {
            rep = new mouvementZoom(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getInt(4));
        }
    } catch (Exception e) {
        e.printStackTrace(); // Gérer les exceptions de manière appropriée
    } finally {
        // Fermer les ressources dans un bloc finally pour s'assurer qu'elles sont libérées
        if (resultSet != null) {
            resultSet.close();
        }
        if (statement != null) {
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }

    return rep;
}

public static ReponseQuiz Mareponse(int parseInt) throws Exception {
    ReponseQuiz rep = null;
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    try {
        String sql = "SELECT * FROM ReponseQuiz WHERE idReponseQuiz = " + parseInt;

        // Établir une connexion et exécuter la requête
        connection = connect();
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);

        // Récupérer les données
        if (resultSet.next()) {
            rep = new ReponseQuiz(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3), resultSet.getDouble(4));
        }
    } catch (Exception e) {
        e.printStackTrace(); // Gérer les exceptions de manière appropriée
    } finally {
        // Fermer les ressources dans un bloc finally pour s'assurer qu'elles sont libérées
        if (resultSet != null) {
            resultSet.close();
        }
        if (statement != null) {
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }

    return rep;
}



public static int derniereTentative(int idApprenant, int idQuiz) throws Exception {
    int rep = 0;
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    
    try {
        String sql = "SELECT * FROM mouvementQuiz WHERE idApprenant = " + idApprenant + " AND idQuiz = " + idQuiz + " ORDER BY tentative DESC";
        
        // Établir une connexion et exécuter la requête
        connection = connect();
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);
        
        // Récupérer les données
        if (resultSet.next()) {
            rep = resultSet.getInt(2);
        }
    } catch (Exception e) {
        e.printStackTrace(); // Gérer les exceptions de manière appropriée
    } finally {
        // Fermer les ressources dans un bloc finally pour s'assurer qu'elles sont libérées
        if (resultSet != null) {
            resultSet.close();
        }
        if (statement != null) {
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }

    return rep;
}



public static ArrayList<Formation> LesFormationValide() throws Exception {
    String sql = "SELECT * FROM formation " +
                 "JOIN categorie ON formation.idcategorie = categorie.idcategorie " +
                 "JOIN typesacces ON formation.typesacces = typesacces.idTypesAcces " +
                 "JOIN langues ON formation.langues = langues.idLangues " +
                 "JOIN unite ON formation.unite = unite.idUnite " +
                 "WHERE formation.etat = 2 " +
                 "ORDER BY formation.idformation DESC";
    ArrayList<Formation> rep = new ArrayList<>();
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    try {
        // Établir une connexion
        connection = connect();
        
        // Créer une déclaration
        statement = connection.createStatement();
        
        // Exécuter la requête
        resultSet = statement.executeQuery(sql);

        // Parcourir les résultats
        while (resultSet.next()) {
            // Lecture du chemin du fichier image
            String imagePath = resultSet.getString(10);
            File imageFile = new File(imagePath);
            byte[] imageData = Files.readAllBytes(imageFile.toPath());

            // Création de l'objet Formation et ajout à la liste
            rep.add(new Formation(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getString(18), resultSet.getInt(4), resultSet.getString(20), resultSet.getInt(5), resultSet.getString(22), resultSet.getString(6), resultSet.getString(7), resultSet.getInt(8), resultSet.getString(24), resultSet.getString(9), imagePath, resultSet.getString(11), resultSet.getInt(12), imageData, resultSet.getDouble(13), resultSet.getString(14), resultSet.getString(15), resultSet.getString(16)));
        }
    } catch (Exception e) {
        e.printStackTrace(); // Gérer les exceptions de manière appropriée
    } finally {
        // Fermer les ressources dans un bloc finally pour s'assurer qu'elles sont libérées
        if (resultSet != null) {
            resultSet.close();
        }
        if (statement != null) {
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }

    return rep;
}


public static ArrayList<Formation> MesFormation(int idFormateur) throws Exception {
        ArrayList<Formation> listeDept = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            FonctionBase connect = new FonctionBase();
            connection = connect.connect();

            // Modifiez la requête en fonction des conditions que vous souhaitez appliquer
            String sql ="SELECT formation.*,categorie.nom AS categorie_nom,typesacces.nom AS types_acces_nom,langues.nom AS langues_nom,unite.nom AS unite_nom FROM formation JOIN categorie ON formation.idcategorie = categorie.idcategorie JOIN typesacces ON formation.typesacces = typesacces.idTypesAcces JOIN langues ON formation.langues = langues.idLangues JOIN unite ON formation.unite = unite.idUnite WHERE idFormateur =? ORDER BY formation.idformation DESC;";
            statement = connection.prepareStatement(sql);
            // Paramètres de condition
            statement.setInt(1, idFormateur);
            result = statement.executeQuery();

            while (result.next()) {
                Formation com = new Formation();

                com.setIdFormation(result.getInt("idFormation"));
                com.setIdFormateur(result.getInt("idformateur"));
                com.setIdCategorie(result.getInt("idcategorie"));
                com.setTypesAcces(result.getInt("typesacces"));
                com.setLangues(result.getInt("langues"));
                com.setTitre(result.getString("titre"));
                com.setDuree(result.getString("duree"));
                com.setUnite(result.getInt("unite"));
                com.setResumer(result.getString("resumer"));
                com.setToken(result.getString("token"));
                com.setEtat(result.getInt("etat"));
                com.setPrix(result.getString("prix"));
                com.setDateDajout(result.getString("datedajout"));
                com.setDevalidation(result.getString("devalidation"));
                com.setDedemande(result.getString("dedemande"));
                com.setNomCategorie(result.getString("categorie_nom"));
                com.setNomTypesAcces(result.getString("types_acces_nom"));
                com.setNomLangues(result.getString("langues_nom"));
                com.setNomUnite(result.getString("unite_nom"));
                com.setEtatPublication(result.getInt("etatPublication"));
                // Récupérer le chemin de l'image depuis la base de données
                String imagePath = result.getString("pdc");

                // Créer un objet File à partir du chemin de l'image
                File imageFile = new File(imagePath);

                // Lire les octets de l'image
                byte[] imageData = Files.readAllBytes(imageFile.toPath());

                // Définir les octets de l'image dans l'objet Formation
                com.setImage(imageData);
                
 ArrayList<Apprenant> rep = FonctionBase.ListApprenantI(com.getIdFormation());
    // Définissez le nombre total d'élèves pour cette formation
    com.setTotalEleve(rep.size());
                listeDept.add(com);

            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (result != null) {
                result.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return listeDept;
    }

public static ArrayList<Formation> RechercheFormation(String sql, Connection connection) throws Exception {
    ArrayList<Formation> listeDept = new ArrayList<>();

    Statement statement = null;
    ResultSet result = null;
    try {
        statement = connection.createStatement();
        result = statement.executeQuery(sql);

         while (result.next()) {
                Formation com = new Formation();

                com.setIdFormation(result.getInt("idFormation"));
                com.setIdFormateur(result.getInt("idformateur"));
                com.setIdCategorie(result.getInt("idcategorie"));
                com.setTypesAcces(result.getInt("typesacces"));
                com.setLangues(result.getInt("langues"));
                com.setTitre(result.getString("titre"));
                com.setDuree(result.getString("duree"));
                com.setUnite(result.getInt("unite"));
                com.setResumer(result.getString("resumer"));
                com.setToken(result.getString("token"));
                com.setEtat(result.getInt("etat"));
                com.setPrix(result.getString("prix"));
                com.setDateDajout(result.getString("datedajout"));
                com.setDevalidation(result.getString("devalidation"));
                com.setDedemande(result.getString("dedemande"));
                com.setNomCategorie(result.getString("categorie_nom"));
                com.setNomTypesAcces(result.getString("types_acces_nom"));
                com.setNomLangues(result.getString("langues_nom"));
                com.setNomUnite(result.getString("unite_nom"));
                com.setEtatPublication(result.getInt("etatPublication"));
                // Récupérer le chemin de l'image depuis la base de données
                String imagePath = result.getString("pdc");

                // Créer un objet File à partir du chemin de l'image
                File imageFile = new File(imagePath);

                // Lire les octets de l'image
                byte[] imageData = Files.readAllBytes(imageFile.toPath());

                // Définir les octets de l'image dans l'objet Formation
                com.setImage(imageData);
                 
                ArrayList<Apprenant> rep = FonctionBase.ListApprenantI(com.getIdFormation());
    // Définissez le nombre total d'élèves pour cette formation
    com.setTotalEleve(rep.size());
 
                listeDept.add(com);

            }
    } finally {
        // Fermeture du ResultSet
        if (result != null) {
            try {
                result.close();
            } catch (SQLException ex) {
                ex.printStackTrace(); // Gérer l'exception de fermeture
            }
        }

        // Fermeture du Statement
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace(); // Gérer l'exception de fermeture
            }
        }

        // Fermeture de la connexion
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace(); // Gérer l'exception de fermeture
            }
        }
    }

    return listeDept;
}

 

 

/*public static ArrayList<Formation> MesFormation(int idFormateur) throws Exception {
    String sql = "SELECT * FROM formation " +
                 "JOIN categorie ON formation.idcategorie = categorie.idcategorie " +
                 "JOIN typesacces ON formation.typesacces = typesacces.idTypesAcces " +
                 "JOIN langues ON formation.langues = langues.idLangues " +
                 "JOIN unite ON formation.unite = unite.idUnite " +
                 "WHERE idFormateur = " + idFormateur + " " +
                 "ORDER BY formation.idformation DESC";
    ArrayList<Formation> rep = new ArrayList<>();
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    try {
        // Établir une connexion
        connection = connect();
        
        // Créer une déclaration
        statement = connection.createStatement();
        
        // Exécuter la requête
        resultSet = statement.executeQuery(sql);

        // Parcourir les résultats
        while (resultSet.next()) {
            // Lecture du chemin du fichier image
            String imagePath = resultSet.getString(10);
            File imageFile = new File(imagePath);
            byte[] imageData = Files.readAllBytes(imageFile.toPath());

            // Création de l'objet Formation et ajout à la liste
            rep.add(new Formation(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getString(19), resultSet.getInt(4), resultSet.getString(21), resultSet.getInt(5), resultSet.getString(23), resultSet.getString(6), resultSet.getString(7), resultSet.getInt(8), resultSet.getString(25), resultSet.getString(9), imagePath, resultSet.getString(12), resultSet.getInt(13), imageData, resultSet.getDouble(14), resultSet.getString(15), resultSet.getString(16), resultSet.getString(17),resultSet.getInt(18)));
        }
    } catch (Exception e) {
        e.printStackTrace(); // Gérer les exceptions de manière appropriée
    } finally {
        // Fermer les ressources dans un bloc finally pour s'assurer qu'elles sont libérées
        if (resultSet != null) {
            resultSet.close();
        }
        if (statement != null) {
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }

    return rep;
}*/


/*public static ArrayList<Formation> RechercheFormation(String sql, Connection connection) throws Exception {
    ArrayList<Formation> rep = new ArrayList<>();

    Statement statement = null;
    ResultSet resultSet = null;
    try {
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);

        File image = new File("");
        while (resultSet.next()) {
            image = new File(resultSet.getString(10));
            rep.add(new Formation(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getString(18), resultSet.getInt(4), resultSet.getString(20), resultSet.getInt(5), resultSet.getString(22), resultSet.getString(6), resultSet.getString(7), resultSet.getInt(8), resultSet.getString(24), resultSet.getString(9), resultSet.getString(10), resultSet.getString(11), resultSet.getInt(12), Files.readAllBytes(image.toPath()), resultSet.getDouble(13), resultSet.getString(14), resultSet.getString(15), resultSet.getString(16)));
        }
    } finally {
        // Fermeture du ResultSet
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException ex) {
                ex.printStackTrace(); // Gérer l'exception de fermeture
            }
        }

        // Fermeture du Statement
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace(); // Gérer l'exception de fermeture
            }
        }

        // Fermeture de la connexion
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace(); // Gérer l'exception de fermeture
            }
        }
    }

    return rep;
}
*/

public static Formation MonFormation(int parseInt) throws Exception {
    String sql = "SELECT formation.*, categorie.nom AS categorie_nom, typesacces.nom AS types_acces_nom, langues.nom AS langues_nom, unite.nom AS unite_nom FROM formation JOIN categorie ON formation.idcategorie = categorie.idcategorie JOIN typesacces ON formation.typesacces = typesacces.idTypesAcces JOIN langues ON formation.langues = langues.idLangues JOIN unite ON formation.unite = unite.idUnite WHERE idformation = " + parseInt;
    Formation rep = null; // Utilisation de null pour indiquer qu'aucune formation n'a encore été créée

    Connection connection = connect();
    Statement statement = null;
    ResultSet result = null;
    try {
        statement = connection.createStatement();
        result = statement.executeQuery(sql);

        // On crée l'objet Formation s'il y a des résultats
        if (result.next()) {
            rep = new Formation(); // Création de l'objet Formation
            rep.setIdFormation(result.getInt("idFormation"));
            rep.setIdFormateur(result.getInt("idformateur"));
            rep.setIdCategorie(result.getInt("idcategorie"));
            rep.setTypesAcces(result.getInt("typesacces"));
            rep.setLangues(result.getInt("langues"));
            rep.setTitre(result.getString("titre"));
            rep.setDuree(result.getString("duree"));
            rep.setUnite(result.getInt("unite"));
            rep.setResumer(result.getString("resumer"));
            rep.setToken(result.getString("token"));
            rep.setEtat(result.getInt("etat"));
            rep.setPrix(result.getString("prix"));
            rep.setDateDajout(result.getString("datedajout"));
            rep.setDevalidation(result.getString("devalidation"));
            rep.setDedemande(result.getString("dedemande"));
            rep.setNomCategorie(result.getString("categorie_nom"));
            rep.setNomTypesAcces(result.getString("types_acces_nom"));
            rep.setNomLangues(result.getString("langues_nom"));
            rep.setNomUnite(result.getString("unite_nom"));
            rep.setEtatPublication(result.getInt("etatPublication"));
            // Récupérer le chemin de l'image depuis la base de données
            String imagePath = result.getString("pdc");

            // Créer un objet File à partir du chemin de l'image
            File imageFile = new File(imagePath);

            // Lire les octets de l'image
            byte[] imageData = Files.readAllBytes(imageFile.toPath());

            // Définir les octets de l'image dans l'objet Formation
            rep.setImage(imageData);
            Formateur formateur = FonctionBase.moi(result.getInt("idFormateur"));

             ArrayList<Chapitres> listC = FonctionBase.allChapitres(rep.getIdFormation());
             ArrayList<Quiz> listQ= FonctionBase.allQuiz(rep.getIdFormation());
             ArrayList<Zoom> listZ = FonctionBase.allZooms(rep.getIdFormation());
    // Définissez le nombre total d'élèves pour cette formation
              
 
            // Définir le formateur dans l'objet Formation
            rep.setMonFormateur(formateur);
            rep.setMeschapitres(listC);
            rep.setMesQuizs(listQ);
            rep.setMeszooms(listZ);
        }
    } finally {
        // Fermeture du ResultSet
        if (result != null) {
            try {
                result.close();
            } catch (SQLException ex) {
                ex.printStackTrace(); // Gérer l'exception de fermeture
            }
        }

        // Fermeture du Statement
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace(); // Gérer l'exception de fermeture
            }
        }

        // Fermeture de la connexion
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace(); // Gérer l'exception de fermeture
            }
        }
    }

    return rep;
}



public static ArrayList<Profession> AllProfession() throws Exception {
    String sql = "SELECT * FROM Profession";
    ArrayList<Profession> rep = new ArrayList<>();
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

public static ArrayList<modeDexercice> AllmodeDexercice() throws Exception {
    String sql = "SELECT * FROM modeDexercice";
    ArrayList<modeDexercice> rep = new ArrayList<>();

    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    try {
        connection = connect();
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            rep.add(new modeDexercice(resultSet.getInt(1), resultSet.getString(2)));
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        // Fermeture de la connexion, du statement et du resultSet dans le bloc finally
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    return rep;
}

public static int CountAllDemande() throws Exception {
    String sql = "SELECT COUNT(idDemande) FROM DemandeAdhesion";
    int rep = 0;
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    try {
        connection = connect();
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            rep = resultSet.getInt(1);
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        // Fermeture de la connexion, du statement et du resultSet dans le bloc finally
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    return rep;
}

public static ArrayList<Categorie> AllCategorie() throws Exception {
    String sql = "Select * from Categorie where idcategorie  not in (select idcategorie from deletecategorie )order by idcategorie asc";
    ArrayList<Categorie> rep = new ArrayList<>();

    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    try {
        connection = connect();
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            String myString = resultSet.getString(2);
            byte[] utf8Bytes = myString.getBytes(StandardCharsets.UTF_8);
            String utf8EncodedString = new String(utf8Bytes, StandardCharsets.UTF_8);
            System.out.println(utf8EncodedString);
            rep.add(new Categorie(resultSet.getInt(1), utf8EncodedString));
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        // Fermeture de la connexion, du statement et du resultSet dans le bloc finally
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    return rep;
}


public static ArrayList<Langues> AllLangues() throws Exception {
    String sql = "SELECT * FROM Langues";
    ArrayList<Langues> rep = new ArrayList<>();

    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    try {
        connection = connect();
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            rep.add(new Langues(resultSet.getInt(1), resultSet.getString(2)));
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        // Fermeture de la connexion, du statement et du resultSet dans le bloc finally
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    return rep;
}




public static ArrayList<TypesAcces> AllTypesAcces() throws Exception {
    String sql = "SELECT * FROM modeDexercice";
    ArrayList<TypesAcces> rep = new ArrayList<>();

    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    try {
        connection = connect();
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            rep.add(new TypesAcces(resultSet.getInt(1), resultSet.getString(2)));
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        // Fermeture de la connexion, du statement et du resultSet dans le bloc finally
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    return rep;
}


public static ArrayList<Apprenant> ListApprenant() throws Exception {
    String sql = "SELECT * FROM apprenant WHERE etatcompte=1";
    System.out.println(sql);
    ArrayList<Apprenant> rep = new ArrayList<>();
    
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    try {
        connection = connect();
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);
        
        while (resultSet.next()) {
            rep.add(new Apprenant(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getInt(6), resultSet.getInt(7), resultSet.getInt(8), resultSet.getString(9), resultSet.getDate(10), resultSet.getString(11), resultSet.getInt(12), resultSet.getString(13)));
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        // Fermeture de la connexion, du statement et du resultSet dans le bloc finally
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    return rep;
}

public static ArrayList<Apprenant> ListApprenantI(int idFormation) throws Exception {
    String sql = "SELECT * FROM apprenant JOIN inscritformation ON inscritformation.idApprenant=apprenant.idapprenant WHERE inscritformation.idformation=" + idFormation;
    System.out.println(sql);
    ArrayList<Apprenant> rep = new ArrayList<>();
    Progression p = new Progression();
    long a = 0;
    
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    try {
        connection = connect();
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);
        
        while (resultSet.next()) {
            Apprenant m = new Apprenant(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getInt(6), resultSet.getInt(7), resultSet.getInt(8), resultSet.getString(9), resultSet.getDate(10), resultSet.getString(11), resultSet.getInt(12), resultSet.getDate(17), resultSet.getString(13));
            a = p.progresssionA(resultSet.getInt(1), idFormation);
            m.setProgression(a);
            rep.add(m);
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        // Fermeture de la connexion, du statement et du resultSet dans le bloc finally
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    return rep;
}


public static Apprenant selectWithToken(String token) throws Exception {
    String sql = "SELECT * FROM Apprenant WHERE token='" + token + "'";
    System.out.println(sql);
    Apprenant rep = null;
    
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    try {
        connection = connect();
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);
        
        while (resultSet.next()) {
            rep = new Apprenant(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getInt(6), resultSet.getInt(7), resultSet.getInt(8), resultSet.getString(9), resultSet.getDate(10), resultSet.getString(11), resultSet.getInt(12), resultSet.getString(13));
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        // Fermeture de la connexion, du statement et du resultSet dans le bloc finally
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    return rep;
}


public static Apprenant selectWithTokenConnecter(String token) throws Exception {
    String sql = "SELECT * FROM Apprenant WHERE token='" + token + "' AND etatcompte=1";
    System.out.println(sql);
    Apprenant rep = null;
    
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    try {
        connection = connect();
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);
        
        while (resultSet.next()) {
            rep = new Apprenant(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getInt(6), resultSet.getInt(7), resultSet.getInt(8), resultSet.getString(9), resultSet.getDate(10), resultSet.getString(11), resultSet.getInt(12), resultSet.getString(13));
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        // Fermeture de la connexion, du statement et du resultSet dans le bloc finally
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    return rep;
}









public static String selectPassword(String token) throws Exception {
    String sql = "SELECT mdp FROM MotDePasseOublier WHERE token='" + token + "' ORDER BY idMotDePasseOublier DESC LIMIT 1";
    String rep = "";
    
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    try {
        connection = connect();
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);
        
        while (resultSet.next()) {
            rep = resultSet.getString(1);
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        // Fermeture de la connexion, du statement et du resultSet dans le bloc finally
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    return rep;
}





public static DemandeAdhesion selectWithTokend(String token) throws Exception {
    String sql = "SELECT * FROM DemandeAdhesion WHERE token='" + token + "' AND etat=1";
    DemandeAdhesion rep = null;
    
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    try {
        connection = connect();
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);
        
        while (resultSet.next()) {
            rep = new DemandeAdhesion(resultSet.getInt(1), resultSet.getTimestamp(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8), resultSet.getString(9), resultSet.getString(10), resultSet.getString(11), resultSet.getInt(12));
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        // Fermeture de la connexion, du statement et du resultSet dans le bloc finally
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    return rep;
}

public static ArrayList<DemandeAdhesion> AllDemande(int page, int size) throws Exception {
    String sql = "SELECT * FROM DemandeAdhesion ORDER BY idDemande DESC LIMIT " + size + " OFFSET " + page * size;
    ArrayList<DemandeAdhesion> rep = new ArrayList<DemandeAdhesion>();
    
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    try {
        connection = connect();
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);
        
        while (resultSet.next()) {
            Timestamp timestamp = resultSet.getTimestamp(2);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String formattedDate = sdf.format(timestamp);
            rep.add(new DemandeAdhesion(resultSet.getInt(1), resultSet.getTimestamp(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8), resultSet.getString(9), resultSet.getString(10), resultSet.getString(11), resultSet.getInt(12), formattedDate));
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        // Fermeture de la connexion, du statement et du resultSet dans le bloc finally
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    return rep;
}


public static Apprenant LoginApprenant(String email, String mdp) throws Exception {
    String sql = "SELECT * FROM Apprenant WHERE email = ? AND mdp = ? AND etatCompte = 1";
    Apprenant rep = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    try {
        connection = connect();
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, email);
        preparedStatement.setString(2, mdp);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            rep = new Apprenant(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getInt(6), resultSet.getInt(7), resultSet.getInt(8), resultSet.getString(9), resultSet.getDate(10), resultSet.getString(11), resultSet.getInt(12), resultSet.getString(13));
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        // Fermeture de la connexion, du preparedStatement et du resultSet dans le bloc finally
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    return rep;
}
public static Admin LoginAdmin(String email, String password) throws Exception {
    String sql = "SELECT * FROM Admin WHERE email = ? AND mdp = ?";
    Admin rep = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    try {
        connection = connect();
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, email);
        preparedStatement.setString(2, password);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            rep = new Admin(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        // Fermeture de la connexion, du preparedStatement et du resultSet dans le bloc finally
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    return rep;
}


public static Formateur LoginFormateur(String email, String mdp) throws Exception {
    String sql = "SELECT * FROM Formateur WHERE email = ? AND mdp = ? AND etatCompte = 1";
    Formateur rep = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    try {
        connection = connect();
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, email);
        preparedStatement.setString(2, mdp);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            rep = new Formateur(
                resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                resultSet.getString(4), resultSet.getString(5), resultSet.getString(6),
                resultSet.getString(7), resultSet.getInt(8), resultSet.getInt(9),
                resultSet.getInt(10), resultSet.getString(11), resultSet.getString(12),
                resultSet.getDate(13), resultSet.getString(14), resultSet.getString(15),
                resultSet.getString(16), resultSet.getInt(17), resultSet.getString(18),
                resultSet.getString(19)
            );
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        // Fermeture de la connexion, du preparedStatement et du resultSet dans le bloc finally
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    return rep;
}


public static ArrayList<Formateur> ListFormateur() throws Exception {
    String sql = "SELECT * FROM formateur WHERE etatcompte = 1";
    ArrayList<Formateur> rep = new ArrayList<Formateur>();
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    try {
        connection = connect();
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            rep.add(new Formateur(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getInt(8), resultSet.getInt(9), resultSet.getInt(10), resultSet.getString(11), resultSet.getString(12), resultSet.getDate(13), resultSet.getString(14), resultSet.getString(15), resultSet.getString(16), resultSet.getInt(17), resultSet.getString(18), resultSet.getString(19)));
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        // Fermeture de la connexion, du statement et du resultSet dans le bloc finally
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    return rep;
}









public static Formateur selectWithTokenF(String token) throws Exception {
    String sql = "SELECT * FROM Formateur WHERE token='" + token + "'";
    Formateur rep = null;
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    try {
        connection = connect();
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            rep = new Formateur(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getInt(8), resultSet.getInt(9), resultSet.getInt(10), resultSet.getString(11), resultSet.getString(12), resultSet.getDate(13), resultSet.getString(14), resultSet.getString(15), resultSet.getString(16), resultSet.getInt(17), resultSet.getString(18), resultSet.getString(19));
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        // Fermeture de la connexion, du statement et du resultSet dans le bloc finally
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    return rep;
}

public static Formateur moi(int i) throws Exception {
    String sql = "SELECT * FROM Formateur WHERE idFormateur=" + i;
    Formateur rep = null;
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    try {
        connection = connect();
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            rep = new Formateur(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getInt(8), resultSet.getInt(9), resultSet.getInt(10), resultSet.getString(11), resultSet.getString(12), resultSet.getDate(13), resultSet.getString(14), resultSet.getString(15), resultSet.getString(16), resultSet.getInt(17), resultSet.getString(18), resultSet.getString(19));
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        // Fermeture de la connexion, du statement et du resultSet dans le bloc finally
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    return rep;
}





public static Formateur selectWithTokenFConnecter(String token) throws Exception {
    String sql = "SELECT * FROM Formateur WHERE token='" + token + "' AND etatcompte=1";
    Formateur rep = null;
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    try {
        connection = connect();
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            rep = new Formateur(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getInt(8), resultSet.getInt(9), resultSet.getInt(10), resultSet.getString(11), resultSet.getString(12), resultSet.getDate(13), resultSet.getString(14), resultSet.getString(15), resultSet.getString(16), resultSet.getInt(17), resultSet.getString(18), resultSet.getString(19));
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        // Fermeture de la connexion, du statement et du resultSet dans le bloc finally
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    return rep;
}




public static Apprenant findByEmail(String email) throws Exception {
    String sql = "Select * from Apprenant where email='" + email + "' AND etatcompte=1";
    Apprenant rep = null;
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    try {
        connection = connect();
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            rep = new Apprenant(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getInt(6), resultSet.getInt(7), resultSet.getInt(8), resultSet.getString(9), resultSet.getDate(10), resultSet.getString(11), resultSet.getInt(12), resultSet.getString(13));
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        // Fermeture de la connexion, du statement et du resultSet dans le bloc finally
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    return rep;
}


public static Formateur findByEmailF(String email) throws Exception {
    String sql = "Select * from Formateur where email='" + email + "' AND etatcompte=1";
    Formateur rep = null;
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    try {
        connection = connect();
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            rep = new Formateur(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getInt(8), resultSet.getInt(9), resultSet.getInt(10), resultSet.getString(11), resultSet.getString(12), resultSet.getDate(13), resultSet.getString(14), resultSet.getString(15), resultSet.getString(16), resultSet.getInt(17), resultSet.getString(18), resultSet.getString(19));
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        // Fermeture de la connexion, du statement et du resultSet dans le bloc finally
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    return rep;
}



public static Formateur findByEmailA(String email) throws Exception {
    String sql = "Select * from Formateur where email='" + email + "' AND etatcompte=1";
    Formateur rep = null;
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    try {
        connection = connect();
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            rep = new Formateur(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getInt(8), resultSet.getInt(9), resultSet.getInt(10), resultSet.getString(11), resultSet.getString(12), resultSet.getDate(13), resultSet.getString(14), resultSet.getString(15), resultSet.getString(16), resultSet.getInt(17), resultSet.getString(18), resultSet.getString(19));
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        // Fermeture de la connexion, du statement et du resultSet dans le bloc finally
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    return rep;
}








public static DemandeAdhesion findByIdA(int id) throws Exception {
    String sql = "Select * from DemandeAdhesion where idDemande=" + id;
    DemandeAdhesion rep = null;
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    try {
        connection = connect();
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            rep = new DemandeAdhesion(resultSet.getInt(1), resultSet.getTimestamp(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8), resultSet.getString(9), resultSet.getString(10), resultSet.getString(11), resultSet.getInt(12));
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        // Fermeture de la connexion, du statement et du resultSet dans le bloc finally
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    return rep;
}




public static ArrayList<Categorie> allCategorie() throws Exception {
    String sql = "Select * from Categorie";
    ArrayList<Categorie> rep = new ArrayList<Categorie>();
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    try {
        connection = connect();
        statement = connection.prepareStatement(sql);
        resultSet = statement.executeQuery();

        while (resultSet.next()) {
            rep.add(new Categorie(resultSet.getInt(1), resultSet.getString(2)));
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        // Fermeture de la connexion, du statement et du resultSet dans le bloc finally
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    return rep;
}


public static ArrayList<Unite> allUnite() throws Exception {
    String sql = "Select * from Unite";
    ArrayList<Unite> rep = new ArrayList<Unite>();
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    try {
        connection = connect();
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            rep.add(new Unite(resultSet.getInt(1), resultSet.getString(2)));
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        // Fermeture de la connexion, du statement et du resultSet dans le bloc finally
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    return rep;
}








public static ArrayList<TypesAcces> allTypesAcces() throws Exception {
    String sql = "Select * from TypesAcces";
    ArrayList<TypesAcces> rep = new ArrayList<TypesAcces>();
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    try {
        connection = connect();
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            rep.add(new TypesAcces(resultSet.getInt(1), resultSet.getString(2)));
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        // Fermeture de la connexion, du statement et du resultSet dans le bloc finally
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    return rep;
}





public static ArrayList<Langues> allLangues() throws Exception {
    String sql = "Select * from Langues";
    ArrayList<Langues> rep = new ArrayList<Langues>();
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    try {
        connection = connect();
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            rep.add(new Langues(resultSet.getInt(1), resultSet.getString(2)));
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        // Fermeture de la connexion, du statement et du resultSet dans le bloc finally
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    return rep;
}


public static ArrayList<Chapitres> allChapitres(int idFormation) throws Exception {
    String sql = "Select * from Chapitres where idFormation=" + idFormation;
    ArrayList<Chapitres> rep = new ArrayList<Chapitres>();
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    try {
        connection = connect();
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            rep.add(new Chapitres(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3)));
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        // Fermeture de la connexion, du statement et du resultSet dans le bloc finally
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    return rep;
}


public static ArrayList<QuestionQuiz> mesQ(int idQuiz) throws Exception {
    String sql = "Select * from QuestionQuiz where idQuiz =" + idQuiz;
    ArrayList<QuestionQuiz> rep = new ArrayList<QuestionQuiz>();
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    try {
        connection = connect();
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            rep.add(new QuestionQuiz(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getString(4)));
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        // Fermeture de la connexion, du statement et du resultSet dans le bloc finally
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    return rep;
}


public static int maderniereq(int idQuiz) throws Exception {
    String sql = "Select * from QuestionQuiz where idQuiz =" + idQuiz + " order by idQuestionQuiz";
    int rep = 0;
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    try {
        connection = connect();
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            rep = resultSet.getInt(1);
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        // Fermeture de la connexion, du statement et du resultSet dans le bloc finally
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    return rep;
}




public static QuestionQuiz monQuestion(int idQuestion) throws Exception {
    String sql = "Select * from QuestionQuiz where idQuestionQuiz =" + idQuestion;
    QuestionQuiz rep = null;
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    try {
        connection = connect();
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            rep = new QuestionQuiz(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getString(4));
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        // Fermeture de la connexion, du statement et du resultSet dans le bloc finally
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    return rep;
}























public static ArrayList<SousChapitres> allSousChapitres(int idchapitre) throws Exception {
    String sql = "Select * from SousChapitres where idChapitres=" + idchapitre;
    ArrayList<SousChapitres> rep = new ArrayList<SousChapitres>();
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    try {
        connection = connect();
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            rep.add(new SousChapitres(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3)));
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        // Fermeture de la connexion, du statement et du resultSet dans le bloc finally
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    return rep;
}


public static int voalohany(int parseInt) throws Exception {
    String sql = "Select * from SousChapitres join chapitres on chapitres.idChapitres=souschapitres.idchapitres where idformation=" + parseInt + " order by idsouschapitres asc";
    int rep = 0;
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    try {
        connection = connect();
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            rep = resultSet.getInt(1);
            break;
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        // Fermeture de la connexion, du statement et du resultSet dans le bloc finally
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    return rep;
}




public static inscritFormation Suivie(int idApprenant, int idFormation) throws Exception {
    String sql = "Select * from inscritFormation where idApprenant=" + idApprenant + " And idFormation=" + idFormation;
    inscritFormation rep = null;
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    try {
        connection = connect();
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            rep = new inscritFormation(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getString(4));
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        // Fermeture de la connexion, du statement et du resultSet dans le bloc finally
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    return rep;
}

public static ArrayList<ContenuSousChapitres> allContenuSousChapitres(int idSouschapitre) throws Exception {
    String sql = "Select * from ContenuSousChapitres where idSousChapitres=" + idSouschapitre + " order by idContenuSousChapitres ASC";
    ArrayList<ContenuSousChapitres> rep = new ArrayList<ContenuSousChapitres>();
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    try {
        connection = connect();
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);
        File c = new File("");

        while (resultSet.next()) {
            if (resultSet.getString(5).equals("image") || resultSet.getString(5).equals("video") || resultSet.getString(5).equals("pdf")) {
                System.out.println("ihi");
                c = new File(resultSet.getString(3));
                rep.add(new ContenuSousChapitres(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), Files.readAllBytes(c.toPath())));
            } else {
                rep.add(new ContenuSousChapitres(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5)));
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        // Fermeture de la connexion, du statement et du resultSet dans le bloc finally
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    return rep;
}



public static ArrayList<Zoom> allZooms(int idFormation) throws Exception {
    String sql = "Select * from zoom where idFormation=" + idFormation;
    ArrayList<Zoom> rep = new ArrayList<Zoom>();
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    try {
        connection = connect();
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            System.out.println("jj");
            rep.add(new Zoom(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8), resultSet.getString(9), resultSet.getString(10)));
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        // Fermeture de la connexion, du statement et du resultSet dans le bloc finally
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    return rep;
}

public static Zoom MonZoom(int idZoom) throws Exception {
    String sql = "Select * from zoom where idZoom=" + idZoom;
    Zoom rep = new Zoom();
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    try {
        connection = connect();
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            System.out.println("jj");
            rep = new Zoom(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8), resultSet.getString(9), resultSet.getString(10));
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        // Fermeture de la connexion, du statement et du resultSet dans le bloc finally
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    return rep;
}








public static ArrayList<Quiz> allQuiz(int idFormation) throws Exception {
    String sql = "Select * from quiz where idFormation=" + idFormation;
    ArrayList<Quiz> rep = new ArrayList<Quiz>();
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    try {
        connection = connect();
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            rep.add(new Quiz(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3)));
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        // Fermeture de la connexion, du statement et du resultSet dans le bloc finally
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    return rep;
}



public static Quiz MonQuiz(int idQuiz) throws Exception {
    String sql = "Select * from quiz where idQuiz=" + idQuiz;
    Quiz rep = new Quiz();
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    try {
        connection = connect();
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            rep = new Quiz(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3));
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        // Fermeture de la connexion, du statement et du resultSet dans le bloc finally
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    return rep;
}
public static ArrayList<ReponseQuiz> allrep(int idQuestionQuiz) throws Exception {
    String sql = "Select * from ReponseQuiz where idQuestion=" + idQuestionQuiz;
    ArrayList<ReponseQuiz> rep = new ArrayList<ReponseQuiz>();
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    try {
        connection = connect();
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            rep.add(new ReponseQuiz(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3), resultSet.getDouble(4)));
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        // Fermeture de la connexion, du statement et du resultSet dans le bloc finally
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    return rep;
}


public static double totalQuestion(int idQuiz) throws Exception {
    double rep = 0.0;
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    try {
        String sql = "select Sum(note) from reponsequiz join QuestionQuiz on QuestionQuiz.idQuestionQuiz=reponsequiz.idquestion where questionquiz.idquiz=" + idQuiz;
        connection = connect();
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            rep = resultSet.getDouble(1);
            break;
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        // Fermeture de la connexion, du statement et du resultSet dans le bloc finally
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    return rep;
}


public static ArrayList<NoteQuiz> mesNotes(int idapprenant, int idquiz) throws Exception {
    ArrayList<NoteQuiz> rep = new ArrayList<NoteQuiz>();
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    try {
        String sql = "select tentative, idapprenant, mouvementquiz.idquiz, Sum(reponsequiz.note) from mouvementquiz join reponsequiz on reponsequiz.idReponseQuiz=mouvementquiz.idreponsequiz where idapprenant=" + idapprenant + " and idquiz=" + idquiz + " group by tentative, idapprenant, mouvementquiz.idquiz order by tentative desc";
        connection = connect();
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            rep.add(new NoteQuiz(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getDouble(4), totalQuestion(idquiz)));
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        // Fermeture de la connexion, du statement et du resultSet dans le bloc finally
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    return rep;
}


public static double cent(int idformation) throws Exception {
    double rep = 0.0;
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    try {
        String sql = "Select count(idsouschapitres) from SousChapitres join chapitres ON chapitres.idChapitres=SousChapitres.idChapitres where idFormation=" + idformation;
        connection = connect();
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            rep = resultSet.getDouble(1);
        }

        sql = "Select count(idZoom) from zoom where idFormation=" + idformation;
        resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            rep += resultSet.getDouble(1);
        }

        sql = "Select count(idQuiz) from Quiz where idFormation=" + idformation;
        resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            rep += resultSet.getDouble(1);
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        // Fermeture de la connexion, du statement et du resultSet dans le bloc finally
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    return rep;
}


    public static double nyvitany(int idApprenant, int idformation, Connection connection) throws Exception {
        double rep = 0.0;
        double isanysous = 0.0;
        Statement statement = null;
        ResultSet resultSet = null;
    
        try {
            String sql = "Select count(idsouschapitres) from SousChapitres join chapitres ON chapitres.idChapitres=SousChapitres.idChapitres where idFormation=" + idformation;
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                isanysous = resultSet.getDouble(1);
            }
    
            sql = "select count(idmouvementchapitres) from mouvementchapitres where idformation=" + idformation + " and idApprenant=" + idApprenant;
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                rep = resultSet.getDouble(1);
            }
    
            if (rep == 1) {
                rep = 0.0;
            }
    
            if (rep > isanysous) {
                rep = isanysous;
            } else if (rep > 1 && rep <= isanysous) {
                rep = rep - 1;
            }
    
            sql = "select count(idmouvementzoom) from mouvementzoom where idapprenant=" + idApprenant + " and idformation =" + idformation;
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                rep += resultSet.getDouble(1);
            }
    
            sql = "select mouvementquiz.idquiz from mouvementquiz join quiz on quiz.idquiz=mouvementquiz.idquiz where idapprenant=" + idApprenant + " and idformation =" + idformation + " group by mouvementquiz.idquiz";
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                rep += 1.0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Fermeture du statement et du resultSet dans le bloc finally
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    
        return rep;
    }
    

    public static ArrayList<Statistique> StatA(Connection connection) throws SQLException {
        ArrayList<Statistique> rep = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
    
        try {
            String sql = "SELECT EXTRACT(YEAR FROM dateDajout) AS annee, COUNT(*) AS nombre_personnes " +
                         "FROM Apprenant WHERE etatCompte = 1 " +
                         "GROUP BY annee " +
                         "ORDER BY annee DESC";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
    
            while (resultSet.next()) {
                rep.add(new Statistique(resultSet.getString(1), resultSet.getInt(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fermeture du statement et du resultSet dans le bloc finally
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    
        return rep;
    }
    

    public static Annee StatAm(Connection connection, int parseInt) throws SQLException {
        Annee rep = new Annee();
        String sql = "SELECT EXTRACT(MONTH FROM dateDajout) AS mois, COUNT(*) AS nombre_personnes " +
                     "FROM Apprenant WHERE etatCompte = 1 AND EXTRACT(YEAR FROM dateDajout) = " + parseInt +
                     " GROUP BY mois " +
                     "ORDER BY mois DESC";
        System.out.println(sql);
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                for (int j = 0; j < rep.getMesMois().size(); j++) {
                    if (rep.getMesMois().get(j).getChiffre() == resultSet.getInt(1)) {
                        rep.getMesMois().get(j).setNombre(resultSet.getInt(2));
                    }
                }
            }
    
            int cent = 0;
            for (int j = 0; j < rep.getMesMois().size(); j++) {
                cent = cent + rep.getMesMois().get(j).getNombre();
            }
    
            for (int j = 0; j < rep.getMesMois().size(); j++) {
                if (rep.getMesMois().get(j).getNombre() != 0) {
                    rep.getMesMois().get(j).setPourcent((rep.getMesMois().get(j).getNombre() * 100) / cent);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fermeture du statement et du resultSet dans le bloc finally
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    
        return rep;
    }
    public static ArrayList<Details> StatAmd(Connection connection, int parseInt, int parseInt1) throws SQLException {
        ArrayList<Details> rep = new ArrayList<>();
        String sql = "SELECT " +
                     "    Profession.nom, " +
                     "    COALESCE(COUNT(Apprenant.dateDajout), 0) AS nombre_personnes " +
                     "FROM " +
                     "    Profession " +
                     "LEFT JOIN " +
                     "    Apprenant ON Apprenant.Profession = Profession.idprofession " +
                     "               AND etatCompte = 1 " +
                     "               AND EXTRACT(YEAR FROM Apprenant.dateDajout) =" + parseInt +
                     "               AND EXTRACT(MONTH FROM Apprenant.dateDajout) =" + parseInt1 +
                     "GROUP BY " +
                     "    Profession.idprofession, Profession.nom";
        System.out.println(sql);
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                rep.add(new Details(resultSet.getString(1), resultSet.getInt(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fermeture du statement et du resultSet dans le bloc finally
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return rep;
    }
    


    public static ArrayList<Statistique> StatF(Connection connection) throws SQLException {
        ArrayList<Statistique> rep = new ArrayList<>();
        String sql = "SELECT EXTRACT(YEAR FROM dateDajout) AS annee, COUNT(*) AS nombre_personnes " +
                     "FROM formateur where etatCompte=1 " +
                     "GROUP BY annee " +
                     "ORDER BY annee desc";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                rep.add(new Statistique(resultSet.getString(1), resultSet.getInt(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fermeture du statement et du resultSet dans le bloc finally
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return rep;
    }
    

    public static Annee StatFm(Connection connection, int parseInt) throws SQLException {
        Annee rep = new Annee();
        String sql = "SELECT EXTRACT(MONTH FROM dateDajout) AS mois, COUNT(*) AS nombre_personnes " +
                     "FROM Formateur where etatCompte=1 and " +
                     "EXTRACT(YEAR FROM dateDajout) =" + parseInt + " GROUP BY mois " +
                     "ORDER BY mois desc";
    
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                for (int j = 0; j < rep.getMesMois().size(); j++) {
                    if (rep.getMesMois().get(j).getChiffre() == resultSet.getInt(1)) {
                        rep.getMesMois().get(j).setNombre(resultSet.getInt(2));
                    }
                }
            }
    
            int cent = 0;
            for (int j = 0; j < rep.getMesMois().size(); j++) {
                cent += rep.getMesMois().get(j).getNombre();
            }
    
            for (int j = 0; j < rep.getMesMois().size(); j++) {
                if (rep.getMesMois().get(j).getNombre() != 0) {
                    rep.getMesMois().get(j).setPourcent((rep.getMesMois().get(j).getNombre() * 100) / cent);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fermeture du statement et du resultSet dans le bloc finally
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    
        return rep;
    }
    
    public static ArrayList<Details> StatFmd(Connection connection, int parseInt, int parseInt1) throws SQLException {

        ArrayList<Details> rep = new ArrayList<Details>();
        String sql = "SELECT " +
                     "    Profession.nom, " +
                     "    COALESCE(COUNT(Formateur.dateDajout), 0) AS nombre_personnes " +
                     "FROM " +
                     "    Profession " +
                     "LEFT JOIN " +
                     "    Formateur ON Formateur.Profession = Profession.idprofession " +
                     "               AND etatCompte = 1 " +
                     "               AND EXTRACT(YEAR FROM Formateur.dateDajout) =" + parseInt +
                     "               AND EXTRACT(MONTH FROM Formateur.dateDajout) =" + parseInt1 +
                     "GROUP BY " +
                     "    Profession.idprofession, Profession.nom";
    
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                rep.add(new Details(resultSet.getString(1), resultSet.getInt(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fermeture du statement et du resultSet dans le bloc finally
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    
        return rep;
    }
    





    public static ArrayList<Statistique> StatFo(Connection connection) throws SQLException {
        ArrayList<Statistique> rep = new ArrayList<Statistique>();
        String sql = "SELECT EXTRACT(YEAR FROM devalidation) AS annee, COUNT(*) AS nombre_personnes " +
                     "FROM Formation where etat = 2 " +
                     "GROUP BY annee " +
                     "ORDER BY annee desc";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                rep.add(new Statistique(resultSet.getString(1), resultSet.getInt(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fermeture du statement et du resultSet dans le bloc finally
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return rep;
    }
    




    public static Annee StatFom(Connection connection, int parseInt) throws SQLException {
        Annee rep = new Annee();
        String sql = "SELECT EXTRACT(MONTH FROM devalidation) AS mois, COUNT(*) AS nombre_personnes " +
                     "FROM Formation where etat = 2 and " +
                     "EXTRACT(YEAR FROM devalidation) =" + parseInt + " GROUP BY mois " +
                     "ORDER BY mois desc";
    
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                for (int j = 0; j < rep.getMesMois().size(); j++) {
                    if (rep.getMesMois().get(j).getChiffre() == resultSet.getInt(1)) {
                        rep.getMesMois().get(j).setNombre(resultSet.getInt(2));
                    }
                }
            }
    
            int cent = 0;
            for (int j = 0; j < rep.getMesMois().size(); j++) {
                cent = cent + rep.getMesMois().get(j).getNombre();
            }
    
            for (int j = 0; j < rep.getMesMois().size(); j++) {
                if (rep.getMesMois().get(j).getNombre() != 0) {
                    rep.getMesMois().get(j).setPourcent((rep.getMesMois().get(j).getNombre() * 100) / cent);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fermeture du statement et du resultSet dans le bloc finally
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    
        return rep;
    }
    

    public static ArrayList<Details> StatFomd(Connection connection, int parseInt, int parseInt1) throws SQLException {
        ArrayList<Details> rep = new ArrayList<Details>();
        String sql = "SELECT " +
                     "    Categorie.nom, " +
                     "    COALESCE(COUNT(Formation.devalidation), 0) AS nombre_personnes " +
                     "FROM " +
                     "    Categorie " +
                     "LEFT JOIN " +
                     "    Formation ON Formation.idCategorie = Categorie.idCategorie " +
                     "               AND etat = 2 " +
                     "               AND EXTRACT(YEAR FROM Formation.devalidation) = " + parseInt + " " +
                     "               AND EXTRACT(MONTH FROM Formation.devalidation) = " + parseInt1 + " " +
                     "GROUP BY " +
                     "    Categorie.idCategorie, Categorie.nom";
    
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                rep.add(new Details(resultSet.getString(1), resultSet.getInt(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fermeture du statement et du resultSet dans le bloc finally
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    
        return rep;
    }
    












}


