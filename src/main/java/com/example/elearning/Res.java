package com.example.elearning;

import com.example.elearning.models.FonctionBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import lombok.Data;

@Data
public class Res {

    private int status;
    private String message;
    private String url;

    public void insertPublicite2(String url) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            FonctionBase connect = new FonctionBase();
            connection = connect.connect();
            String query = "insert into sary(url) values (?)";

            statement = connection.prepareStatement(query);
            statement.setString(1, url);
            statement.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}
