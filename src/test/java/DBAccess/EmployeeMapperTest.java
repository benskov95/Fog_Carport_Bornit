package DBAccess;

import FunctionLayer.Employee;
import FunctionLayer.LoginSampleException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.assertNotNull;

public class EmployeeMapperTest {


    private static Connection testConnection;
    private static String USER = "fogtest";
    private static String USERPW = "fogtest";
    private static String DBNAME = "fogtest?serverTimezone=CET&useSSL=false";
    private static String HOST = "localhost";

    @BeforeClass
    public static void  setUp() {
        try {
            // avoid making a new connection for each test
            if ( testConnection == null ) {
                String url = String.format( "jdbc:mysql://%s:3306/%s", HOST, DBNAME );
                Class.forName( "com.mysql.cj.jdbc.Driver" );

                testConnection = DriverManager.getConnection( url, USER, USERPW );
                // Make mappers use test
                Connector.setConnection( testConnection );
            } }
        catch ( ClassNotFoundException | SQLException ex ) {
            testConnection = null;
            System.out.println("Could not open connection to database: " + ex.getMessage());
        }
    }

    @Before
    public void beforeEachTest(){
        try ( Statement stmt = testConnection.createStatement()) {
            stmt.execute( "drop table if exists employee" );
            stmt.execute( "CREATE TABLE `employee` LIKE fog.employee;" );
            stmt.execute("INSERT INTO employee VALUES (1,'admin', 1), (2, 'lager', 2)");
        } catch ( SQLException ex ) {
            System.out.println( "Could not open connection to database: " + ex.getMessage() );
        }
    }

    @Test
    public void testSetUpOK() {
        // Just check that we have a connection.
        assertNotNull( testConnection );
    }

    @Test
    public void testLogin() throws LoginSampleException {

        Employee employee = EmployeeMapper.login(1, "admin");
        assertNotNull (employee);

    }

    @Test
    public void testSetEmployeeRole () throws LoginSampleException, SQLException, ClassNotFoundException {

        Employee employee = EmployeeMapper.login(2, "lager");
        EmployeeMapper.setEmployeeRole(employee);
        assert (employee.getRole().equals("warehouse"));

    }

}
