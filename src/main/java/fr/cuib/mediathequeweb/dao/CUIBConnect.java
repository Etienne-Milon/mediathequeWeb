package fr.cuib.mediathequeweb.dao;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

import java.sql.Connection;

public class CUIBConnect {
    private static Connection connexion;

    private CUIBConnect() {
    }

    public static Connection getInstance() {
        if (connexion == null) {
            try {
                SQLServerDataSource ds = new SQLServerDataSource();
                ds.setServerName("127.0.0.1");
                ds.setPortNumber(1404);
                ds.setDatabaseName("CUIB");
                ds.setIntegratedSecurity(false);
                ds.setEncrypt(false);
                ds.setUser("sa");
                ds.setPassword("azerty@private123");
                connexion = ds.getConnection();
            }

            // Handle any errors that may have occurred.
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return connexion;
    }
}
