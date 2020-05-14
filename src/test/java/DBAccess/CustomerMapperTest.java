package DBAccess;

import FunctionLayer.Customer;
import FunctionLayer.CustomerFacade;
import FunctionLayer.LoginSampleException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;

public class CustomerMapperTest {

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
            stmt.execute( "drop table if exists customer" );
            stmt.execute( "CREATE TABLE `customer` LIKE fog.customer;" );
            stmt.execute("INSERT INTO customer VALUES (12345678,'John','Johngade 2','John@johnmail.john', '2800 Hvemved')");
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
    public void testGetAllCustomers() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> customers = CustomerFacade.getAllCustomers();
        assert (customers.size() == 1);
    }

    @Test
    public void testGetCustomer() throws LoginSampleException, SQLException, ClassNotFoundException {
        Customer customer = CustomerFacade.getCustomer(12345678);
        assert (customer.getName().equals("John"));
    }

    @Test
    public void testDeleteCustomer() throws SQLException, ClassNotFoundException, LoginSampleException {
        ArrayList<Customer> customers = CustomerFacade.getAllCustomers();
        CustomerFacade.deleteCustomer(customers.get(0).getPhone());
        customers = CustomerFacade.getAllCustomers();
        assert (customers.size() == 0);
    }
}
