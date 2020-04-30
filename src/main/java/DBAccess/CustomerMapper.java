package DBAccess;

import FunctionLayer.Customer;
import FunctionLayer.User;

import java.sql.*;
import java.util.ArrayList;

public class CustomerMapper {

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


