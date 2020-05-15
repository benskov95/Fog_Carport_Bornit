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
    public void testInsertBillOfMaterials() throws SQLException, ClassNotFoundException {
        ArrayList<Material> materials = MaterialMapper.getAllMaterials();
        BillOfMaterials bom = new BillOfMaterials(materials);

        bom.setOrderId(4);
        BomFacade.insertBillOfMaterials(bom);

        BillOfMaterials newBom = BomMapper.getBillOfMaterialsForTest(4);
        assert (newBom.getMaterials().size() == 3);
    }

    @Test
    public void testgetBillOfMaterials() throws SQLException, ClassNotFoundException {
        BillOfMaterials bom = BomMapper.getBillOfMaterialsForTest(10);
        assertNotNull (bom);
    }

    @Test
    public void testDeleteBom() throws SQLException, ClassNotFoundException {
        int amountBeforeDeletion = BomMapper.getNumberOfBillOfMaterials();
        BomFacade.deleteBom(10);
        assert (amountBeforeDeletion > BomMapper.getNumberOfBillOfMaterials());
    }
}
