package FunctionLayer;

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

}
