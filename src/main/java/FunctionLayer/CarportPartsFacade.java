package FunctionLayer;

import DBAccess.CarportPartsMapper;
import DBAccess.CustomerMapper;

import java.sql.SQLException;
import java.util.ArrayList;

public class CarportPartsFacade {

    public static ArrayList<CarPortPart> getCarportParts() throws SQLException, ClassNotFoundException {
        return CarportPartsMapper.getCarportParts();
    }

}
