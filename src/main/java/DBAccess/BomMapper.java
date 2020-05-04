package DBAccess;

import FunctionLayer.BillOfMaterials;
import FunctionLayer.Customer;
import FunctionLayer.Material;
import FunctionLayer.Order;

import java.sql.*;
import java.util.ArrayList;

public class BomMapper {

    public static void insertBillOfMaterials(BillOfMaterials bom) throws SQLException, ClassNotFoundException {

        String sqlCustomer = "INSERT INTO fog.bill_of_materials (order_id, material_id, material_size_id, quantity, sum) VALUES (?,?,?,?,?)";

        Connection con = Connector.connection();

        for (Material material : bom.getMaterials()) {

            try (PreparedStatement ps = con.prepareStatement(sqlCustomer, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, bom.getOrderId());
                ps.setInt(2, material.getMaterialId());
                ps.setInt(3, material.getMaterialSizeId());
                ps.setInt(4, material.getQuantity());
                ps.setInt(5, material.getSum());
                ps.executeUpdate();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
