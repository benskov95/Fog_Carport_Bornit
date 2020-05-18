package DBAccess;

/**
 * The purpose of the BomMapper class is
 * to communicate with the Database with SQL statements.
 * @author Pelle Rasmussen
 */

import FunctionLayer.*;
import com.mysql.cj.protocol.Resultset;

import java.sql.*;
import java.util.ArrayList;

public class BomMapper {
    /**
     * Inserts a Arraylist of Materials to the DB.
     * @param bom / Arraylist of Materials
     * @throws SQLException
     * @throws ClassNotFoundException
     */

    public static void insertBillOfMaterials(BillOfMaterials bom) throws SQLException, ClassNotFoundException {

        String sql = "INSERT INTO bill_of_materials (order_id, material_id, material_size_id, bom_carport_part_id, quantity, sum) VALUES (?,?,?,?,?,?)";

        Connection con = Connector.connection();

        for (Material material : bom.getMaterials()) {

            try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, bom.getOrderId());
                ps.setInt(2, material.getMaterialId());
                ps.setInt(3, material.getMaterialSizeId());
                ps.setInt(4, material.getCarportPartId());
                ps.setInt(5, material.getQuantity());
                ps.setInt(6, material.getSum());
                ps.executeUpdate();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *
     * @param orderId / The customers Order ID
     * @return  Returns a Bill of Materials
     * @throws SQLException
     * @throws ClassNotFoundException
     */

    public static BillOfMaterials getBillOfMaterials(int orderId) throws SQLException, ClassNotFoundException {

        String sql = "select b.bom_id, b.bom_carport_part_id, b.order_id, b.material_id, " +
                    "m.name, s.size, b.quantity, m.unit_id, c.description " +
                    "from bill_of_materials b " +
                    "inner join materials m " +
                    "on b.material_id = m.material_id " +
                    "inner join link_material_size l " +
                    "on b.material_size_id = l.pk_link_material_size " +
                    "inner join carport_parts c " +
                    "on b.bom_carport_part_id = c.pk_carport_part_id " +
                    "inner join size s " +
                    "on l.link_size_id = s.size_id " +
                    "where b.order_id = ? " +
                    "group by b.bom_id " +
                    "order by b.bom_id";

        BillOfMaterials bom = new BillOfMaterials();
        bom.setOrderId(orderId);
        Connection con = Connector.connection();

            try {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, orderId);
                ResultSet resultSet = ps.executeQuery();

                while (resultSet.next()) {
                    int materialId = resultSet.getInt("material_id");
                    String name = resultSet.getString("name");
                    int size = resultSet.getInt("size");
                    int quantity = resultSet.getInt("quantity");
                    int unitId = resultSet.getInt("unit_id");
                    String description = resultSet.getString("description");
                    int carportpart_id = resultSet.getInt("bom_carport_part_id");

                    bom.addMaterial(new Material(materialId, name,unitId, quantity, size , carportpart_id,description ));
                }

            } catch (Exception e) {
                e.printStackTrace();
        }
            return bom;
    }

    /**
     * Deletes a Bill of Materials
     * @param orderId
     * @throws SQLException
     * @throws ClassNotFoundException
     */

    public static void deleteBom(int orderId) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM bill_of_materials " +
                "WHERE order_id = ?";
        Connection con = Connector.connection();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, orderId);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Fejl i connection til database");
            e.printStackTrace();
        }
    }

    // Kun til test

    /**
     * Only used for test, returns all material IDs from the Bom list.
     * @param orderId
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static BillOfMaterials getBillOfMaterialsForTest(int orderId) throws SQLException, ClassNotFoundException {

        String sql = "select * from bill_of_materials where order_id = ?";

        BillOfMaterials bom = new BillOfMaterials();
        bom.setOrderId(orderId);
        Connection con = Connector.connection();

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, orderId);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                int materialId = resultSet.getInt("material_id");
                bom.addMaterial(new Material(materialId));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return bom;
    }

    /**
     * Only for test.
     * @return The number of BOM's.
     * @throws SQLException
     * @throws ClassNotFoundException
     */

    public static int getNumberOfBillOfMaterials() throws SQLException, ClassNotFoundException {

        String sql = "select * from bill_of_materials";
        Connection con = Connector.connection();
        int count = 0;

        try {
            PreparedStatement ps = con.prepareStatement(sql) ;
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                count++;
            }
        } catch (SQLException e) {
            System.out.println("Fejl i connection til database");
            e.printStackTrace();
        }
        return  count;
    }
}

