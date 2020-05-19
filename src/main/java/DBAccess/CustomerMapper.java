package DBAccess;

import FunctionLayer.Customer;
import FunctionLayer.LoginSampleException;
import FunctionLayer.OrderException;

import java.sql.*;
import java.util.ArrayList;

/**
 * The purpose of the CustomerMapper class is
 * to communicate with the Database with SQL
 * statements to insert, delete or retrieve
 * customers.
 * @author Pelle Rasmussen
 */

public class CustomerMapper {

    /**
     * Gets all customers from the database.
     * @return Arraylist of all Customers
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

   public static ArrayList<Customer> getAllCustomers () throws SQLException, ClassNotFoundException {

       ArrayList<Customer> customerArrayList = new ArrayList<>();

       String sql = "select * from customer";
       Connection con = Connector.connection();
       try {
           PreparedStatement ps = con.prepareStatement(sql) ;
           ResultSet resultSet = ps.executeQuery();
           while (resultSet.next()){

               int phone = resultSet.getInt("phone");
               String name = resultSet.getString("name");
               String address = resultSet.getString("address");
               String email = resultSet.getString("email");
               String zipCode = resultSet.getString("zip_code");
               customerArrayList.add(new Customer(phone, name, address, email, zipCode));


           }
       } catch (SQLException e) {
           System.out.println("Fejl i connection til database");
           e.printStackTrace();
       }

       return customerArrayList;
   }

    /**
     * Gets a customer by using their phone
     * number as an identifier.
     * @param phone Phone number of the customer
     * @return a customer
     * @throws LoginSampleException
     * Thrown if the phone number does not match with
     * any of the registered customers in the database.
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

    public static Customer getCustomer (int phone) throws LoginSampleException, SQLException, ClassNotFoundException {

        Connection con = Connector.connection();
        try {
            String SQL = "select * from customer where phone = ?";
            PreparedStatement ps = con.prepareStatement( SQL );
            ps.setInt( 1, phone);
            ResultSet rs = ps.executeQuery();
            if ( rs.next() ) {
                int phoneNumber = rs.getInt("phone");
                String name = rs.getString("name");
                String address= rs.getString("address");
                String email = rs.getString("email");
                String zipCode = rs.getString("zip_code");
                return new Customer(phoneNumber, name, address, email, zipCode);
            }
        } catch ( SQLException ex ) {
            ex.printStackTrace();
            throw new LoginSampleException(ex.getMessage());
        }
       return null;
    }

    /**
     * Deletes a customer.
     * @param phone phone number of the customer getting deleted
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

    public static void deleteCustomer(int phone) throws SQLException, ClassNotFoundException {

        String sql = "DELETE FROM customer " +
                "WHERE phone = ?";
        Connection con = Connector.connection();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, phone);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Fejl i connection til database");
            e.printStackTrace();
        }
    }
}


