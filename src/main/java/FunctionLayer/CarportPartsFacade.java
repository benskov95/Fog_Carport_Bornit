package FunctionLayer;

import DBAccess.CarportPartsMapper;
import DBAccess.CustomerMapper;

import java.sql.SQLException;
import java.util.ArrayList;

public class CarportPartsFacade {

    public static ArrayList<CarPortPart> getCarportParts() throws SQLException, ClassNotFoundException {
        return CarportPartsMapper.getCarportParts();
    }

    public static void getCarportPartIds(ArrayList<Material> materials, int carportTypeId) throws SQLException, ClassNotFoundException {
        CarportPartsMapper.getCarportPartIds(materials, carportTypeId);
    }

    public static void getCarportPartDescriptions(ArrayList<Material> materials) throws SQLException, ClassNotFoundException {
        CarportPartsMapper.getCarportPartDescriptions(materials);
    }

}
