package FunctionLayer;

import DBAccess.Connector;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.Assert.assertNotNull;

public class CalculatorTest {

    private static Connection testConnection;
    private static String USER = "fog";
    private static String USERPW = "fog";
    private static String DBNAME = "fog?serverTimezone=CET&useSSL=false";
    private static String HOST = "localhost";

    private int carportLength = 780;
    private int carportWidth = 600;
    private int shedLength = 210;
    private int shedWidth = 540;
    Calculator calculator = new Calculator();


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

    @Test
    public void testSetUpOK() {
        // Just check that we have a connection.
        assertNotNull(testConnection);
    }

    @Test
    public void testType1Calc() throws SQLException, ClassNotFoundException {
        BillOfMaterials bom = calculator.type1Calc(carportLength, carportWidth);
        assert (bom.getMaterials().size() == 19);
    }

    @Test
    public void testType2Calc() throws SQLException, ClassNotFoundException {
        BillOfMaterials bom = calculator.type2Calc(carportLength, carportWidth, shedLength, shedWidth);
        assert (bom.getMaterials().size() == 29);
    }

    @Test
    public void testCalcOptimalLengthOfMaterial() throws SQLException, ClassNotFoundException {
        int res = calculator.calcOptimalLengthOfMaterial(shedWidth, 54);
        assert (res == 270);
    }

    @Test
    public void testCalcNumberOfCladdingPlanks() {
        int res = calculator.calcNumberOfCladdingPlanks(shedWidth, shedLength);
        assert (res == 200);
    }

    @Test
    public void testCalcLengthOfTrapezPlates() throws SQLException, ClassNotFoundException {
        int res = calculator.calcLengthAndAmountOfTrapezPlates(carportLength, carportWidth, MaterialFacade.getMaterialLengths(69));
        assert (res == 8 && calculator.getPrimaryRoofPlateLength() == 600 && calculator.getSecondaryRoofPlateLength() == 240);
    }

    @Test
    public void testCalcSpaceBetweenRafters() {
        int res = calculator.calcSpaceBetweenRafters(carportLength);
        assert (res == 52);
    }

    @Test
    public void testCalcNumberOfPosts() {
        int res = calculator.calcNumberOfPosts(carportLength, carportWidth, shedLength, shedWidth);
        assert (res == 10);
    }
}
