package DBAccess;

import FunctionLayer.Customer;
import FunctionLayer.LoginSampleException;
import FunctionLayer.Order;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.sql.*;


public class OrderMapper {

    public static void insertOrder(Customer customer, Order order) {

        String sqlCustomer = "INSERT INTO fog.customer (phone, name, address, email, zip_code) VALUES (?,?,?,?,?)";
        String sqlOrder = "INSERT INTO fog.order (cp_id, carport_width, carport_length, shed_width, shed_length, phone, status_id ) VALUES (?,?,?,?,?,?,?)";

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
                    ps1.setInt(7, 1);
                    ps1.executeUpdate();
                } catch (Exception e) {
                    e.printStackTrace();
                    con.rollback();
                }

                con.commit();
                con.setAutoCommit(true);

            }


        } catch (Exception e) {


        }

    }
    public static Order getMyOrder (int phone) throws LoginSampleException, SQLException, ClassNotFoundException {


        String sqlOrders = "SELECT * fog.order WHERE phone = ?";
        Connection con = Connector.connection();
        try  (  PreparedStatement ps = con.prepareStatement(sqlOrders);

                ResultSet resultSet = ps.executeQuery() )
        {
            while (resultSet.next()) {
                ps.setInt(1,phone);
                int order_id = resultSet.getInt("order_id");
                Date date = resultSet.getDate("date");
                int carport_width = resultSet.getInt("carport_width");
                int carport_length = resultSet.getInt("carport_length");
                int shed_width = resultSet.getInt("shed_width");
                int shed_length = resultSet.getInt("shed_length");
                int phone1 = resultSet.getInt("phone");
                int status_id = resultSet.getInt("status_id");


                Order myOrder  = new Order(order_id,date,carport_width,carport_length,shed_width,shed_length,phone1,status_id);

                return myOrder;
            }
        } catch (SQLException e) {
            System.out.println("Fejl i connection til database");
            e.printStackTrace();
        }
        return null;

    }

    public static void deleteOrder (int order_id) throws LoginSampleException, SQLException, ClassNotFoundException {
        String sql = "DELETE FROM fog.order\n" +
                "WHERE order_id = ?";
        Connection con = Connector.connection();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, order_id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Fejl i connection til database");
            e.printStackTrace();
        }
    }
}
