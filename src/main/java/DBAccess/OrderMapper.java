package DBAccess;

import FunctionLayer.Customer;
import FunctionLayer.LoginSampleException;
import FunctionLayer.Order;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class OrderMapper {

    public static int insertOrder(Customer customer, Order order) {

        int generatedId = 0;
        String sqlCustomer = "INSERT INTO fog.customer (phone, name, address, email, zip_code) VALUES (?,?,?,?,?)";
        String sqlOrder = "INSERT INTO fog.order (cp_id, carport_width, carport_length, shed_width, shed_length, phone, total_price, status_id ) VALUES (?,?,?,?,?,?,?,?)";

        try {
            Connection con = Connector.connection();
            con.setAutoCommit(false);
            try (PreparedStatement ps = con.prepareStatement(sqlCustomer, Statement.RETURN_GENERATED_KEYS)) {
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

                } catch (Exception e) {
                    e.printStackTrace();
                    con.rollback();
                }
                con.commit();
                con.setAutoCommit(true);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return generatedId;
    }

    public static Order getMyOrder(int orderId, int phone) throws LoginSampleException, SQLException, ClassNotFoundException {

        Connection con = Connector.connection();
        String sqlOrders = "SELECT * from fog.order WHERE order_id = ? AND phone = ?";
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

    public static void deleteOrder(int orderId) throws LoginSampleException, SQLException, ClassNotFoundException {

        String sql = "DELETE FROM fog.order " +
                "WHERE order_id = ?";
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

    public static String getOrderStatus(int statusId) throws SQLException, ClassNotFoundException {

        Connection con = Connector.connection();

        String SQL = "SELECT * from fog.status WHERE status_id = ?";
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

    public static String getCarportType(int typeId) throws SQLException, ClassNotFoundException {

        Connection con = Connector.connection();

        String SQL = "SELECT * from fog.type WHERE type_id = ?";
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

    public static ArrayList<Order> getAllOrderByStatus (int status_id) throws SQLException, ClassNotFoundException {

        ArrayList<Order> orderlist = new ArrayList<>();
        String sqlOrders = "SELECT * FROM fog.order\n" +
                "Where status_id = " + status_id;
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

    public static void updateStatus(int order_id, int status_id) throws LoginSampleException {


        String sql = "update fog.order set status_id = ? where order_id = ?";

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


    public static void updateTotalPrice(int order_id, int totalPrice) {
        String sql = "update fog.order set total_price = ? where order_id = ?";

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
