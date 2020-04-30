package FunctionLayer;

import DBAccess.CustomerMapper;
import DBAccess.UserMapper;

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
    public static Customer insertCustomer (int phone, String name, String address, String email, String zip_code){
        Customer customer = new Customer(phone,name,address,email,zip_code);
        CustomerMapper.insertCustomer(customer);
        return  customer;

    }

}
