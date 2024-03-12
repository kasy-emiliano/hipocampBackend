package com.example.elearning;


import com.example.elearning.models.Publicite;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.InputStreamContent;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.util.Collections;
import org.springframework.web.multipart.MultipartFile;

@org.springframework.stereotype.Service
public class Service {

    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final String SERVICE_ACOUNT_KEY_PATH = getPathToGoodleCredentials();

    private static String getPathToGoodleCredentials() {
        String currentDirectory = System.getProperty("user.dir");
        Path filePath = Paths.get(currentDirectory, "cred.json");
        return filePath.toString();
    }

   public String uploadImageToDrive(MultipartFile file) throws GeneralSecurityException, IOException {
   
    try {
        String folderId = "1hyptoJce-ZuyOVTA6yH8LwwM6htvqE8c";
        Drive drive = createDriveService();
        com.google.api.services.drive.model.File fileMetaData = new com.google.api.services.drive.model.File();
        fileMetaData.setName(file.getOriginalFilename()); // Utilisation du nom de fichier d'origine
        fileMetaData.setParents(Collections.singletonList(folderId));
        InputStreamContent mediaContent = new InputStreamContent("image/jpeg", file.getInputStream());
        com.google.api.services.drive.model.File uploadedFile = drive.files().create(fileMetaData, mediaContent)
                .setFields("id").execute();
        return "https://drive.google.com/uc?export=view&id=" + uploadedFile.getId();        
        

    } catch (Exception e) {
        System.out.println(e.getMessage());
         System.out.println("Error uploading image to Google Drive: " + e.getMessage());
         throw e;
    }
}

    private Drive createDriveService() throws GeneralSecurityException, IOException {

        GoogleCredential credential = GoogleCredential.fromStream(new FileInputStream(SERVICE_ACOUNT_KEY_PATH))
                .createScoped(Collections.singleton(DriveScopes.DRIVE));

        return new Drive.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                JSON_FACTORY,
                credential)
                .build();

    }
}
