package DBAccess;

import FunctionLayer.Customer;
import FunctionLayer.LoginSampleException;
import FunctionLayer.Order;

import java.sql.*;
import java.time.LocalDate;

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
                ps.setString(4,customer.getEmail());
                ps.setString(5,customer.getZip_code());
                ps.executeUpdate();


                    try (PreparedStatement ps1 = con.prepareStatement(sqlOrder, Statement.RETURN_GENERATED_KEYS)) {
                        ps1.setInt(1, order.getCarport_id());
                        ps1.setInt(2, order.getCarport_width());
                        ps1.setInt(3, order.getCarport_length());
                        ps1.setInt(4, order.getShed_width());
                        ps1.setInt(5, order.getShed_length());
                        ps1.setInt(6,order.getPhone());
                        ps1.setInt(7,1);
                        ps1.executeUpdate();
                    } catch (Exception e) {
                        con.rollback();
                    }

                con.commit();
                con.setAutoCommit(true);

            }


        } catch (Exception e) {


        }

    }
}
