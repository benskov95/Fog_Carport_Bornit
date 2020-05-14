package DBAccess;

import FunctionLayer.Material;
import FunctionLayer.MaterialFacade;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;

public class MaterialMapperTest {

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

            stmt.execute( "drop table if exists materials" );
            stmt.execute( "CREATE TABLE `materials` LIKE fog.materials;" );
            stmt.execute("INSERT INTO materials VALUES (1, 'testmateriale 1', 1, 1), (2, 'testmateriale 2', 2, 2), (3, 'testmateriale 1', 3, 3)");

            stmt.execute( "drop table if exists `size`" );
            stmt.execute( "CREATE TABLE `size` LIKE fog.size;" );
            stmt.execute("INSERT INTO `size` VALUES (1, 240), (2, 300), (3, 360)");

            stmt.execute( "drop table if exists link_material_size" );
            stmt.execute( "CREATE TABLE link_material_size LIKE fog.link_material_size;" );
            stmt.execute("INSERT INTO link_material_size VALUES (1, 1, 1), (2, 2, 2), (3, 3, 3)");

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
    public void testGetMaterialLengths() throws SQLException, ClassNotFoundException {
        ArrayList<Integer> lengths = MaterialFacade.getMaterialLengths(1);
        assert (lengths.get(0) == 240);
    }

    @Test
    public void testSetMaterialValues() throws SQLException, ClassNotFoundException {
        ArrayList<Material> materials = new ArrayList<>();
        materials.add(new Material(1));

        MaterialFacade.setMaterialValues(materials);
        assert (materials.get(0).getMaterialName().equals("testmateriale 1"));
    }

    @Test
    public void testSetMaterialSizeIds() throws SQLException, ClassNotFoundException {
        ArrayList<Material> materials = new ArrayList<>();
        materials.add(new Material(1));
        materials.add(new Material(2));
        materials.add(new Material(3));

        MaterialFacade.setMaterialSizeIds(materials);
        assert (materials.get(2).getSizeId() == 3);

    }

    @Test
    public void testSetLinkMaterialSizeIds() {
        ArrayList<Material> materials = new ArrayList<>();
        materials.add(new Material(1));
        materials.add(new Material(2));
        materials.add(new Material(3));

        assert (materials.get(0).)
    }
}
