package DBAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 The purpose of Connector is to establish
 a connection to the database so that CRUD
 operations can be carried out through the
 various Mapper classes.

 @author Benjamin/benskov95
 */
public class Connector {

    private static String URL;
    private static String USERNAME;
    private static String PASSWORD;

    private static Connection singleton;

    public static void setConnection( Connection con ) {
        singleton = con;
    }

    public static Connection connection() throws ClassNotFoundException, SQLException {
        if ((singleton == null) || singleton.isClosed()) {
            setDBCredentials();
            Class.forName( "com.mysql.cj.jdbc.Driver" );
            singleton = DriverManager.getConnection( URL, USERNAME, PASSWORD );
        }
        return singleton;
    }

    public static void setDBCredentials() {
        String deployed = System.getenv("DEPLOYED");
        if (deployed != null){
            // Prod: hent variabler fra setenv.sh i Tomcats bin folder
            URL = System.getenv("JDBC_CONNECTION_STRING");
            USERNAME = System.getenv("JDBC_USER");
            PASSWORD = System.getenv("JDBC_PASSWORD");
        } else {
            // Localhost
            URL = "jdbc:mysql://localhost:3306/fog?serverTimezone=CET&useSSL=false&allowPublicKeyRetrieval=true";
            USERNAME = "fog";
            PASSWORD = "fog";
        }
    }

}
