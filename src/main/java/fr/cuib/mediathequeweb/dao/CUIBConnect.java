package fr.cuib.mediathequeweb.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class CUIBConnect {

    private static Connection connexion;

    private CUIBConnect() {
    }

    public static Connection getInstance() {
        if (connexion == null) {
            try {
                String dbURL = "jdbc:sqlserver://127.0.0.1:1404;databaseName=CUIB;encrypt=false";
                String user = "sa";
                String pass = "azerty@private123";
                connexion = DriverManager.getConnection(dbURL, user, pass);
            }

            // Handle any errors that may have occurred.
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return connexion;
    }
}
