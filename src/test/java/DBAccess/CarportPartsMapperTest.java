package DBAccess;

import FunctionLayer.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;

public class CarportPartsMapperTest {

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
            stmt.execute( "drop table if exists carport_parts" );
            stmt.execute( "CREATE TABLE `carport_parts` LIKE fog.carport_parts;" );
            stmt.execute("INSERT INTO carport_parts (material_id, description, carport_id) VALUES (1,'understernbrædder til for & bag ende',2)");
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
    public void testGetCarportParts() throws SQLException, ClassNotFoundException {
        ArrayList<CarPortPart> carportParts = CarportPartsFacade.getCarportParts();
        assert (carportParts.size() == 1);
    }

    @Test
    public void testgetCarportPartIds() throws LoginSampleException, SQLException, ClassNotFoundException {
        ArrayList<Material> materials = MaterialMapper.getAllMaterials();
        CarportPartsFacade.getCarportPartIds(materials, 2);

        assert (materials.get(0).getCarportPartId()==1);
    }


}
