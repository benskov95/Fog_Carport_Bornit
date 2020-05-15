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

public class OrderMapperTest {

    private static Connection testConnection;
    private static String USER = "fogtest";
    private static String USERPW = "fogtest";
    private static String DBNAME = "fogtest?serverTimezone=CET&useSSL=false&allowPublicKeyRetrieval=true";
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
            stmt.execute( "drop table if exists `order`" );
            stmt.execute( "CREATE TABLE `order` LIKE fog.order" );
            stmt.execute( "drop table if exists `customer`" );
            stmt.execute( "CREATE TABLE `customer` LIKE fog.customer" );
            stmt.execute("INSERT INTO `order` VALUES (1,1,'2020-05-12 15:12:33',600,600,0,0,28261990,10000,1)");
            stmt.execute("INSERT INTO `order` VALUES (2,2,'2020-05-12 15:12:33',720,620,240,540,56480107,15000,2)");
            stmt.execute("INSERT INTO `order` VALUES (3,2,'2020-05-12 15:12:33',720,600,240,240,21429899,17500,3)");
            stmt.execute("INSERT INTO `order` VALUES (4,2,'2020-05-12 15:12:33',620,500,210,200,45604010,19500,3)");
        } catch ( SQLException ex ) {
            System.out.println( "Could not open connection to database: " + ex.getMessage() );
            ex.printStackTrace();
        }
    }
    @Test
    public void testSetUpOK() {
        // Just check that we have a connection.
        assertNotNull( testConnection );
    }

    @Test
    public void testGetAllOrderByStatus() throws SQLException, ClassNotFoundException {
        ArrayList<Order> orders1 = OrderFacade.getAllOrdersByStatusId(1);
        ArrayList<Order> orders2 = OrderFacade.getAllOrdersByStatusId(2);
        ArrayList<Order> orders3 = OrderFacade.getAllOrdersByStatusId(3);

        assert (orders1.size() == 1);
        assert (orders2.size() == 1);
        assert (orders3.size() == 2);
    }

    @Test
    public void testgetCarportType() throws SQLException, ClassNotFoundException {
        String carportTypetype1 = OrderFacade.getCarportType(1);
        String carportTypetype2 = OrderFacade.getCarportType(2);
        String carportTypetype3 = OrderFacade.getCarportType(3);
        String carportTypetype4 = OrderFacade.getCarportType(4);
        assert (carportTypetype1.equals("Fladt tag uden skur"));
        assert (carportTypetype2.equals("Fladt tag med skur"));
        assert (carportTypetype3.equals("Tag med rejsning uden skur"));
        assert (carportTypetype4.equals("Tag med rejsning med skur"));
    }

    @Test
    public void testDeleteOrder() throws SQLException, ClassNotFoundException, LoginSampleException {

        OrderFacade.deleteOrder(1);
        assert (OrderFacade.getMyOrder(1, 28261990) == null);

    }
    @Test
    public void testgetMyOrder() throws LoginSampleException, SQLException, ClassNotFoundException {

        Order myOrder = OrderFacade.getMyOrder(1,28261990);
        assertNotNull(myOrder);

    }
    @Test
    public void testgetOrderForWarehouse() throws LoginSampleException, SQLException, ClassNotFoundException {

        Order myOrder = OrderFacade.getMyOrder(1,28261990);
        assertNotNull(myOrder);

    }
    @Test
    public void testgetOrderStatus () throws SQLException, ClassNotFoundException {

        String orderStatus1 = OrderFacade.getOrderStatus(1);
        String orderStatus2 = OrderFacade.getOrderStatus(2);
        String orderStatus3 = OrderFacade.getOrderStatus(3);
        String orderStatus4 = OrderFacade.getOrderStatus(4);
        assert (orderStatus1.equals("Afventer"));
        assert (orderStatus2.equals("Godkendt"));
        assert (orderStatus3.equals("Pakkes"));
        assert (orderStatus4.equals("Afsendt"));

    }
    @Test
    public void testupdateStatus () throws LoginSampleException, SQLException, ClassNotFoundException {
        OrderFacade.updateStatus(1,2);

        Order order = OrderFacade.getMyOrder(1,28261990);

        assert (order.getStatus_id() == 2);


    }
    @Test
    public void testupdateTotalPrice () throws LoginSampleException, SQLException, ClassNotFoundException {
        OrderFacade.updateTotalPrice(2, 12345);
        Order order = OrderFacade.getMyOrder(2, 56480107);

        assert (order.getTotalPrice() == 12345);

    }
    @Test
    public void testinsertOrder () {
        Customer customer = new Customer(87654321,"Pelle", "Smedeløkken 66", "Pelle@mail.dk", "3770 Allinge");
        Order order = new Order(1,600,720,450,360,0,87654321);

        int newOrder_id = OrderFacade.insertOrder(customer,order);

        assert (newOrder_id == 5);

    }
}
