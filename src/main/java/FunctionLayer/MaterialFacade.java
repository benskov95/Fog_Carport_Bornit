package FunctionLayer;

import DBAccess.MaterialMapper;

import java.sql.SQLException;
import java.util.ArrayList;

public class MaterialFacade {

    public static ArrayList<Integer> getMaterialLengths(int materialId) throws SQLException, ClassNotFoundException {
        return MaterialMapper.getMaterialLengths(materialId);
    }
}
