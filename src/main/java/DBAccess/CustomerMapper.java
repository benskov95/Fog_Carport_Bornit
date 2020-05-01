package DBAccess;

import FunctionLayer.Customer;
import FunctionLayer.User;

import java.sql.*;
import java.util.ArrayList;

public class CustomerMapper {

    // todo - Vi skal enten have smidt "role" på customer tabellen i databasen,
    // todo - eller lave en employee/admin tabel, så vi har det til adminsiden.
    // todo - Vi mangler derfor også en adminpage, og vi burde måske slette
    // todo - customerpage og employeepage, medmindre nogen har i sinde at bruge
    // todo - dem til noget.

   public static ArrayList<Customer> getAllCustomers () throws SQLException, ClassNotFoundException {

       ArrayList<Customer> customerArrayList = new ArrayList<>();

       String sql = "select * from fog.customer";
       Connection con = Connector.connection();
       try {
           PreparedStatement ps = con.prepareStatement(sql) ;
           ResultSet resultSet = ps.executeQuery();
           while (resultSet.next()){

               int phone = resultSet.getInt("phone");
               String name = resultSet.getString("name");
               String address = resultSet.getString("address");
               String email = resultSet.getString("email");
               String zip_code = resultSet.getString("zip_code");
               customerArrayList.add(new Customer(phone,name,address,email,zip_code));


           }
       } catch (SQLException e) {
           System.out.println("Fejl i connection til database");
           e.printStackTrace();
       }

       return customerArrayList;
   }




    }


