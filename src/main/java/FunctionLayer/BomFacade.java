package FunctionLayer;

import DBAccess.BomMapper;

import java.sql.SQLException;

public class BomFacade {

    public static void insertBillOfMaterials(BillOfMaterials bom) throws SQLException, ClassNotFoundException {
        BomMapper.insertBillOfMaterials(bom);
    }
}
