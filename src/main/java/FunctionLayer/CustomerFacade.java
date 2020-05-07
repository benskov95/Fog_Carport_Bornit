package FunctionLayer;

import DBAccess.CustomerMapper;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerFacade {

    public static ArrayList<Customer> getAllCustomers() throws SQLException, ClassNotFoundException {
        return CustomerMapper.getAllCustomers();
    }

    public static Customer getCustomer(int phone) throws LoginSampleException, SQLException, ClassNotFoundException {
        return CustomerMapper.getCustomer(phone);
    }

    public static void deleteCustomer(int phone) throws LoginSampleException, SQLException, ClassNotFoundException {
        CustomerMapper.deleteCustomer(phone);
    }
}
