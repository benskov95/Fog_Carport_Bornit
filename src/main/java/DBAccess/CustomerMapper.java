package DBAccess;

import FunctionLayer.Customer;

import java.sql.*;

public class CustomerMapper {

    public static void insertCustomer (Customer customer){



        String sqlCustomer ="INSERT INTO fog.customer (phone, name, address, email, zip_code) VALUES (?,?,?,?,?)";


        try  {
            Connection con = Connector.connection();
            try (PreparedStatement ps = con.prepareStatement(sqlCustomer, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, customer.getPhone());
                ps.setString(2, customer.getName());
                ps.setString(3, customer.getAddress());
                ps.setString(4, customer.getEmail());
                ps.setString(5, customer.getZip_code());
                ps.executeUpdate();

                ResultSet idResultSet = ps.getGeneratedKeys();

            }
        }catch (Exception e){
            e.printStackTrace();

        }


    }

}
