package DBAccess;

import FunctionLayer.Customer;
import FunctionLayer.Log;
import FunctionLayer.Order;
import FunctionLayer.OrderException;

import java.sql.*;
import java.util.ArrayList;


/**
 * The purpose of the OrderMapper class is
 * to communicate with the Database with SQL
 * statements to insert, update, delete or
 * retrieve orders from the database.
 * @author Pelle Rasmussen
 */

public class OrderMapper {

    /**
     * Insert an order linked with the customer
     * making the order.
     * @param customer
     * A Customer object created from
     * values provided by the user of
     * the website.
     * @param order
     * An Order object created in the
     * FlatOrder class whenever an
     * order is made.
     * @return The new unique generated order id.
     * @author Pelle Rasmussen
     */

    public static int insertOrder(Customer customer, Order order) throws OrderException, SQLException, ClassNotFoundException {

        int generatedId = 0;
        String sqlCustomer = "INSERT INTO customer (phone, name, address, email, zip_code) VALUES (?,?,?,?,?)";
        String sqlOrder = "INSERT INTO `order` (cp_id, carport_width, carport_length, shed_width, shed_length, phone, total_price, status_id ) VALUES (?,?,?,?,?,?,?,?)";
        Connection con = Connector.connection();
        try {
            try (PreparedStatement ps = con.prepareStatement(sqlCustomer, Statement.RETURN_GENERATED_KEYS)) {
                con.setAutoCommit(false);
                ps.setInt(1, customer.getPhone());
                ps.setString(2, customer.getName());
                ps.setString(3, customer.getAddress());
                ps.setString(4, customer.getEmail());
                ps.setString(5, customer.getZip_code());
                ps.executeUpdate();
                try (PreparedStatement ps1 = con.prepareStatement(sqlOrder, Statement.RETURN_GENERATED_KEYS)) {
                    ps1.setInt(1, order.getCarport_id());
                    ps1.setInt(2, order.getCarport_width());
                    ps1.setInt(3, order.getCarport_length());
                    ps1.setInt(4, order.getShed_width());
                    ps1.setInt(5, order.getShed_length());
                    ps1.setInt(6, order.getPhone());
                    ps1.setInt(7, order.getTotalPrice());
                    ps1.setInt(8, order.getStatus_id());
                    ps1.executeUpdate();

                    ResultSet idResultset = ps1.getGeneratedKeys();
                    if (idResultset.next()){
                        generatedId = idResultset.getInt(1);
                    }
                } catch (SQLException e) {
                        Log.severe("insertOrder - rollback");
                        e.printStackTrace();
                        con.rollback();
                }
                con.commit();
                con.setAutoCommit(true);
            }
        } catch (Exception e) {
            Log.severe( "insertOrder "+ e.getMessage());
            throw new OrderException("Dette telfonnummer er allerede registreret i databasen.");
        }
        return generatedId;
    }

    public static int insertOrderForExistingCustomer(Order order) throws SQLException, ClassNotFoundException {

        int generatedId = 0;
        String sqlOrder = "INSERT INTO `order` (cp_id, carport_width, carport_length, shed_width, shed_length, phone, total_price, status_id ) VALUES (?,?,?,?,?,?,?,?)";
        Connection con = Connector.connection();

        try {
            con.setAutoCommit(false);

                try (PreparedStatement ps1 = con.prepareStatement(sqlOrder, Statement.RETURN_GENERATED_KEYS)) {
                    ps1.setInt(1, order.getCarport_id());
                    ps1.setInt(2, order.getCarport_width());
                    ps1.setInt(3, order.getCarport_length());
                    ps1.setInt(4, order.getShed_width());
                    ps1.setInt(5, order.getShed_length());
                    ps1.setInt(6, order.getPhone());
                    ps1.setInt(7, order.getTotalPrice());
                    ps1.setInt(8, order.getStatus_id());
                    ps1.executeUpdate();

                    ResultSet idResultset = ps1.getGeneratedKeys();
                    if (idResultset.next()){
                        generatedId = idResultset.getInt(1);
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                    con.rollback();
                }
                con.commit();
                con.setAutoCommit(true);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return generatedId;
    }

    /**
     * Retrieves an order by using the order ID
     * and the customers phone number.
     * @param orderId
     * Provided order ID.
     * @param phone
     * Provided phone number.
     * @return An Order object.
     * @throws SQLException
     *  Thrown if the provided SQL string in each method
     *  has incorrect syntax, unknown keywords etc. or
     *  if the connection to the database cannot be
     *  established.
     * @throws ClassNotFoundException
     * Thrown from Connector if the "Class.forName" method
     * doesn't find the specified class
     * (JDBC driver in this case).
     * @author Pelle Rasmussen
     */

    public static Order getMyOrder(int orderId, int phone) throws  SQLException, ClassNotFoundException {

        Connection con = Connector.connection();
        String sqlOrders = "SELECT * from `order` WHERE order_id = ? AND phone = ?";
        try  (  PreparedStatement ps = con.prepareStatement(sqlOrders)) {

            ps.setInt(1,orderId);
            ps.setInt(2, phone);

            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                int order_id = resultSet.getInt("order_id");
                int cp_id = resultSet.getInt("cp_id");
                Date date = resultSet.getDate("date");
                int carport_width = resultSet.getInt("carport_width");
                int carport_length = resultSet.getInt("carport_length");
                int shed_width = resultSet.getInt("shed_width");
                int shed_length = resultSet.getInt("shed_length");
                int phoneNumber = resultSet.getInt("phone");
                int totalPrice = resultSet.getInt("total_price");
                int status_id = resultSet.getInt("status_id");

                if (status_id == 1) {
                    totalPrice = 0;
                }

                return new Order(order_id, cp_id, date, carport_width, carport_length, shed_width, shed_length, phoneNumber, totalPrice, status_id);
            }
        } catch (SQLException e) {
            System.out.println("Fejl i connection til database");
            e.printStackTrace();
        }
        return null;

    }

    /**
     * Used to get an order for
     * the warehouse which is
     * used to retrieve and
     * display bill of materials
     * in the BomPage class.
     * @param orderId
     * Provided order ID.
     * @return An Order object.
     * @throws SQLException
     *  Thrown if the provided SQL string in each method
     *  has incorrect syntax, unknown keywords etc. or
     *  if the connection to the database cannot be
     *  established.
     * @throws ClassNotFoundException
     * Thrown from Connector if the "Class.forName" method
     * doesn't find the specified class
     * (JDBC driver in this case).
     * @author Pelle Rasmussen
     */

    public static Order getOrderForWarehouse(int orderId) throws SQLException, ClassNotFoundException {

        Connection con = Connector.connection();
        String sqlOrders = "SELECT * from `order` WHERE order_id = ?";
        try  (  PreparedStatement ps = con.prepareStatement(sqlOrders)) {

            ps.setInt(1,orderId);

            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                int order_id = resultSet.getInt("order_id");
                int cp_id = resultSet.getInt("cp_id");
                Date date = resultSet.getDate("date");
                int carport_width = resultSet.getInt("carport_width");
                int carport_length = resultSet.getInt("carport_length");
                int shed_width = resultSet.getInt("shed_width");
                int shed_length = resultSet.getInt("shed_length");
                int phoneNumber = resultSet.getInt("phone");
                int totalPrice = resultSet.getInt("total_price");
                int status_id = resultSet.getInt("status_id");

                if (status_id == 1) {
                    totalPrice = 0;
                }

                return new Order(order_id, cp_id, date, carport_width, carport_length, shed_width, shed_length, phoneNumber, totalPrice, status_id);
            }
        } catch (SQLException e) {
            System.out.println("Fejl i connection til database");
            e.printStackTrace();
        }
        return null;

    }

    /**
     * Deletes a order using its Order id.
     * @param orderId
     * Provided order ID.
     * @throws SQLException
     *  Thrown if the provided SQL string in each method
     *  has incorrect syntax, unknown keywords etc. or
     *  if the connection to the database cannot be
     *  established.
     * @throws ClassNotFoundException
     * Thrown from Connector if the "Class.forName" method
     * doesn't find the specified class
     * (JDBC driver in this case).
     * @author Pelle Rasmussen
     */

    public static void deleteOrder(int orderId) throws  SQLException, ClassNotFoundException {

        String sql = "DELETE FROM `order` WHERE order_id = ?";
        Connection con = Connector.connection();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, orderId);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Fejl i connection til database");
            e.printStackTrace();
        }
    }

    /**
     * Used to find and set the correct
     * status name for each order based
     * on its status ID.
     * @param statusId
     * Provided status id.
     * @return The name matching the status ID.
     * @throws SQLException
     *  Thrown if the provided SQL string in each method
     *  has incorrect syntax, unknown keywords etc. or
     *  if the connection to the database cannot be
     *  established.
     * @throws ClassNotFoundException
     * Thrown from Connector if the "Class.forName" method
     * doesn't find the specified class
     * (JDBC driver in this case).
     * @author Pelle Rasmussen
     */

    public static String getOrderStatus(int statusId) throws SQLException, ClassNotFoundException {

        Connection con = Connector.connection();

        String SQL = "SELECT * from status WHERE status_id = ?";
        try  (  PreparedStatement ps = con.prepareStatement(SQL)) {

            ps.setInt(1, statusId);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("status");
            }

        } catch (SQLException e) {
            System.out.println("Fejl i connection til database");
            e.printStackTrace();
        }
        return null;

    }

    /**
     * Gets the type of the Carport.
     * @param typeId
     * Used to find the matching
     * carport type name.
     * @return A carport type name.
     * @throws SQLException
     *  Thrown if the provided SQL string in each method
     *  has incorrect syntax, unknown keywords etc. or
     *  if the connection to the database cannot be
     *  established.
     * @throws ClassNotFoundException
     * Thrown from Connector if the "Class.forName" method
     * doesn't find the specified class
     * (JDBC driver in this case).
     * @author Pelle Rasmussen
     */

    public static String getCarportType(int typeId) throws SQLException, ClassNotFoundException {

        Connection con = Connector.connection();

        String SQL = "SELECT * from `type` WHERE type_id = ?";
        try  (  PreparedStatement ps = con.prepareStatement(SQL)) {

            ps.setInt(1, typeId);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("type");
            }

        } catch (SQLException e) {
            System.out.println("Fejl i connection til database");
            e.printStackTrace();
        }
        return null;

    }

    /**
     * Used to get orders based on the status ID.
     * Example: status id 1 = "afventer". Mainly
     * used for the admin and warehouse employees
     * to be able to view all orders that are
     * relevant to them (admin has to approve
     * orders so he only needs to see orders
     * with status ID 1, for example).
     * @param status_id
     * @return
     * Arraylist of orders with status names.
     * @throws SQLException
     *  Thrown if the provided SQL string in each method
     *  has incorrect syntax, unknown keywords etc. or
     *  if the connection to the database cannot be
     *  established.
     * @throws ClassNotFoundException
     * Thrown from Connector if the "Class.forName" method
     * doesn't find the specified class
     * (JDBC driver in this case).
     * @author Pelle Rasmussen
     */

    public static ArrayList<Order> getAllOrdersByStatus(int status_id) throws SQLException, ClassNotFoundException {

        ArrayList<Order> orderlist = new ArrayList<>();
        String sqlOrders = "SELECT * FROM `order` Where status_id = " + status_id;
        Connection con = Connector.connection();
        try  (  PreparedStatement ps = con.prepareStatement(sqlOrders);

                ResultSet resultSet = ps.executeQuery()
                )

        {
            while (resultSet.next()) {
                int order_id = resultSet.getInt("order_id");
                int cp_id = resultSet.getInt("cp_id");
                Date date = resultSet.getDate("date");
                int carport_width = resultSet.getInt("carport_width");
                int carport_length = resultSet.getInt("carport_length");
                int shed_width = resultSet.getInt("shed_width");
                int shed_length = resultSet.getInt("shed_length");
                int phone = resultSet.getInt("phone");
                int totalPrice = resultSet.getInt("total_price");
                int statusid = resultSet.getInt("status_id");


                Order order = new Order(order_id,cp_id,date,carport_width,carport_length,shed_width,shed_length,phone,totalPrice,statusid);
                orderlist.add(order);
            }
        } catch (SQLException e) {
            System.out.println("Fejl i connection til database");
            e.printStackTrace();
        }
        return orderlist;
    }

    /**
     * Updates the status of a Order.
     * @param order_id
     * Order whose status ID is being
     * updated.
     * @param status_id
     * The new status ID for the order.
     * @author Pelle Rasmussen
     */

    public static void updateStatus(int order_id, int status_id) {


        String sql = "update `order` set status_id = ? where order_id = ?";

        try{
            Connection con = Connector.connection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1,status_id);
            ps.setInt(2,order_id);
            ps.executeUpdate();
        }

        catch (SQLException | ClassNotFoundException e) {
            System.out.println("Fejl i connection til database");
            e.printStackTrace();

        }

    }

    /**
     * Updates the orders total price.
     * @param order_id
     * Order whose total price is being
     * updated.
     * @param totalPrice
     * The new total price for the order.
     * @author Pelle Rasmussen
     */

    public static void updateTotalPrice(int order_id, int totalPrice) {
        String sql = "update `order`set total_price = ? where order_id = ?";

        try{
            Connection con = Connector.connection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1,totalPrice);
            ps.setInt(2,order_id);
            ps.executeUpdate();
        }

        catch (SQLException | ClassNotFoundException e) {
            System.out.println("Fejl i connection til database");
            e.printStackTrace();

        }

    }

}

