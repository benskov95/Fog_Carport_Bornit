package FunctionLayer;

import DBAccess.CustomerMapper;
import DBAccess.InitializeMapper;
import DBAccess.OrderMapper;
import DBAccess.UserMapper;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The purpose of LogicFacade is to...
 * @author kasper
 */
public class LogicFacade {

    public static User login( String email, String password ) throws LoginSampleException {
        return UserMapper.login( email, password );
    } 

    public static User createUser( String email, String password ) throws LoginSampleException {
        User user = new User(email, password, "customer");
        UserMapper.createUser( user );
        return user;
    }

    public static Order insertOrder (int carport_id, int carport_width, int carport_length, int shed_width, int shed_length, int phone, int status_id, String name, String address, String email, String zip_code){
        Customer customer = new Customer(phone,name,address,email,zip_code );
        Order order = new Order(carport_id,carport_width,carport_length,shed_width,shed_length,phone,status_id);
        OrderMapper.insertOrder(customer,order);
        return order;
    }

    public static ArrayList<Integer> getCWList() throws LoginSampleException, SQLException, ClassNotFoundException {
        return InitializeMapper.getCarportWidth();
    }

    public static ArrayList<Integer> getCLList() throws LoginSampleException, SQLException, ClassNotFoundException {
        return InitializeMapper.getCarportLength();
    }

    public static ArrayList<Integer> getSWList() throws LoginSampleException, SQLException, ClassNotFoundException {
        return InitializeMapper.getShedWidth();
    }

    public static ArrayList<Integer> getSLList() throws LoginSampleException, SQLException, ClassNotFoundException {
        return InitializeMapper.getShedLength();
    }

}
