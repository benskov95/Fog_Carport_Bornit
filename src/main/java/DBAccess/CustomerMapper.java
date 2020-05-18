package DBAccess;

import FunctionLayer.Customer;
import FunctionLayer.LoginSampleException;

import java.sql.*;
import java.util.ArrayList;

/**
 * /**
 *  * The purpose of the CustomerMapper class is
 *  * to communicate with the Database with SQL statements.
 *  * @author Pelle Rasmussen
 *  */

public class CustomerMapper {

    /**
     * Gets all customers from the DB
     * @return Arraylist of all Customers
     * @throws SQLException
     * @throws ClassNotFoundException
     */

    // todo - Vi skal enten have smidt "role" på customer tabellen i databasen,
    // todo - eller lave en employee/admin tabel, så vi har det til adminsiden.
    // todo - Vi mangler derfor også en adminpage, og vi burde måske slette
    // todo - customerpage og employeepage, medmindre nogen har i sinde at bruge
    // todo - dem til noget.

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
     *Gets a customer by using there phonenumber
     * @param phone Phone number of the customer
     * @return a customer
     * @throws LoginSampleException
     * @throws SQLException
     * @throws ClassNotFoundException
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
                Customer customer = new Customer(phoneNumber, name, address, email, zipCode);
                return customer;
            }
        } catch ( SQLException ex ) {
            ex.printStackTrace();
            throw new LoginSampleException(ex.getMessage());
        }
        return null;
    }

    /**
     * Deletes a customer
     * @param phone phone number of the customer getting deleted
     * @throws SQLException
     * @throws ClassNotFoundException
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


