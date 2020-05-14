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

public class BomMapperTest {

    private static Connection testConnection;
    private static String USER = "fogtest";
    private static String USERPW = "fogtest";
    private static String DBNAME = "fogtest?serverTimezone=CET&useSSL=false";
    private static String HOST = "localhost";

    @BeforeClass
    public static void setUp() {
        try {
            // avoid making a new connection for each test
            if (testConnection == null) {
                String url = String.format("jdbc:mysql://%s:3306/%s", HOST, DBNAME);
                Class.forName("com.mysql.cj.jdbc.Driver");

                testConnection = DriverManager.getConnection(url, USER, USERPW);
                // Make mappers use test
                Connector.setConnection(testConnection);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            testConnection = null;
            System.out.println("Could not open connection to database: " + ex.getMessage());
        }
    }

    @Before
    public void beforeEachTest() {
        try (Statement stmt = testConnection.createStatement()) {
            stmt.execute("drop table if exists bill_of_materials");
            stmt.execute("CREATE TABLE `bill_of_materials` LIKE fog.bill_of_materials;");
            stmt.execute("INSERT INTO bill_of_materials VALUES (1, 10, 38, 114, 34, 2, 78)");
            stmt.execute("INSERT INTO bill_of_materials VALUES (2, 11, 92, 188, 42, 2, 418)");
            stmt.execute("INSERT INTO bill_of_materials VALUES (3, 12, 95, 191, 45, 1, 159)");
        } catch (SQLException ex) {
            System.out.println("Could not open connection to database: " + ex.getMessage());
        }
    }

    @Test
    public void testSetUpOK() {
        // Just check that we have a connection.
        assertNotNull(testConnection);
    }

    @Test
    public void testInsertBillOfMaterials() {

    }

    @Test
    public void testgetBillOfMaterials() throws SQLException, ClassNotFoundException {
        BillOfMaterials bom = BomFacade.getBillOfMaterials(10);
        assert (bom.getMaterials().size() == 1);
    }

    @Test
    public void testDeleteBom() throws SQLException, ClassNotFoundException {
        ArrayList<BillOfMaterials> bom = new ArrayList<>();
        bom.add(BomFacade.getBillOfMaterials(10));
        bom.add(BomFacade.getBillOfMaterials(11));
        bom.add(BomFacade.getBillOfMaterials(12));
        BomFacade.deleteBom(bom.get(0).getOrderId());
        assert (bom.size() == 2);
        // Evt bare lav getAllBillOfMaterials...
    }
}
