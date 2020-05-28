package FunctionLayer;

import DBAccess.EmployeeMapper;
import DBAccess.InitializeMapper;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The purpose of LogicFacade is to...
 * @author kasper
 */
public class LogicFacade {

//    public static User createUser( String email, String password ) throws LoginSampleException {
//        User user = new User(email, password, "customer");
//        UserMapper.createUser( user );
//        return user;
//    }

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
    public static ArrayList<Size> getSList(boolean isLengths) throws SQLException, ClassNotFoundException {
        return InitializeMapper.getSizes(isLengths);
    }
    public static ArrayList<Unit> getUList() throws SQLException, ClassNotFoundException {
        return InitializeMapper.getUnits();
    }
    public static Employee employeeLogin (int employee_id, String password) throws LoginSampleException {
        return EmployeeMapper.login(employee_id, password);
    }

    public static void setEmployeeRole(Employee employee) throws SQLException, ClassNotFoundException {
        EmployeeMapper.setEmployeeRole(employee);
    }

}
