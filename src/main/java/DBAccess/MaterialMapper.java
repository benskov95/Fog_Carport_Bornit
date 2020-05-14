package DBAccess;

import FunctionLayer.CarPortPart;
import FunctionLayer.Customer;
import FunctionLayer.Material;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MaterialMapper {

    public static ArrayList<Integer> getMaterialLengths(int materialId) throws SQLException, ClassNotFoundException {

        ArrayList<Integer> lengths = new ArrayList<>();
        String sql = "select m.material_id, m.name, s.size from materials m " +
                     "inner join link_material_size l " +
                     "on m.material_id = l.link_material_id " +
                     "inner join size s " +
                     "on l.link_size_id = s.size_id " +
                     "where m.material_id = ?";

        Connection con = Connector.connection();

        try {
            PreparedStatement ps = con.prepareStatement(sql) ;
            ps.setInt(1, materialId);

            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){

                int size = resultSet.getInt("size");
                lengths.add(size);

            }
        } catch (SQLException e) {
            System.out.println("Fejl i connection til database");
            e.printStackTrace();
        }

        return lengths;
    }

    public static void setMaterialValues(ArrayList<Material> materials) throws SQLException, ClassNotFoundException {

        String sql = "select * from materials where material_id = ?";
        Connection con = Connector.connection();

        for (Material material : materials) {

            try {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, material.getMaterialId());
                ResultSet resultSet = ps.executeQuery();
                if (resultSet.next()) {
                    material.setMaterialName(resultSet.getString("name"));
                    material.setUnitId(resultSet.getInt("unit_id"));
                    material.setPrice(resultSet.getInt("price"));
                }
            } catch (SQLException e) {
                System.out.println("Fejl i connection til database");
                e.printStackTrace();
            }
        }
    }

    public static void setMaterialSizeIds(ArrayList<Material> materials) throws SQLException, ClassNotFoundException {

        Connection con = Connector.connection();
        String sql;
        boolean hasLength = false;

        for (Material material : materials) {

            if (material.getSize() == 0) {
                sql = "select m.material_id, m.name, s.size, l.link_size_id, s.size_id from materials m " +
                        "inner join link_material_size l " +
                        "on m.material_id = l.link_material_id " +
                        "inner join size s " +
                        "on l.link_size_id = s.size_id " +
                        "where m.material_id = ?";
            } else {
                sql = "select m.material_id, m.name, s.size, l.link_size_id, s.size_id from materials m " +
                        "inner join link_material_size l " +
                        "on m.material_id = l.link_material_id " +
                        "inner join size s " +
                        "on l.link_size_id = s.size_id " +
                        "where m.material_id = ? and s.size = ?";

                hasLength = true;
            }

            try {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, material.getMaterialId());

                if (hasLength) {
                    ps.setInt(2, material.getSize());
                }

                ResultSet resultSet = ps.executeQuery();
                hasLength = false;
                ps.clearParameters();

                if (resultSet.next()) {
                    int sizeId = resultSet.getInt("size_id");
                    material.setSizeId(sizeId);

                    if (material.getSize() == 0) {
                        int size = resultSet.getInt("size");
                        material.setSize(size);
                    }

                }
            } catch (SQLException e) {
                System.out.println("Fejl i connection til database");
                e.printStackTrace();
            }
        }
    }

    public static void setLinkMaterialSizeIds(ArrayList<Material> materials) throws SQLException, ClassNotFoundException {

        String sql = "select * from link_material_size where link_material_id = ? and link_size_id = ?";
        Connection con = Connector.connection();

        for (Material material : materials) {

            try {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, material.getMaterialId());
                ps.setInt(2, material.getSizeId());
                ResultSet resultSet = ps.executeQuery();
                if (resultSet.next()) {
                    int materialSizeId = resultSet.getInt("pk_link_material_size");
                    material.setMaterialSizeId(materialSizeId);
                }
            } catch (SQLException e) {
                System.out.println("Fejl i connection til database");
                e.printStackTrace();
            }
        }
    }

    public static void setUnitTypes(ArrayList<Material> materials) throws SQLException, ClassNotFoundException {

        Connection con = Connector.connection();

        String SQL = "SELECT * from unit";

        for (Material material : materials) {

            try {
                PreparedStatement ps = con.prepareStatement(SQL);
                ResultSet resultSet = ps.executeQuery();

                while (resultSet.next()) {
                    int dbId = resultSet.getInt("unit_id");
                    String dbUnitType = resultSet.getString("unit_type");

                    if (material.getUnitId() == dbId) {
                        material.setUnit(dbUnitType);
                        break;
                    }
                }

            } catch (SQLException e) {
                System.out.println("Fejl i connection til database");
                e.printStackTrace();
            }
        }
    }

    // Kun til test, derfor ligger den ikke i facaden.
    public static ArrayList<Material> getAllMaterials () throws SQLException, ClassNotFoundException {

        ArrayList<Material> materialArrayList = new ArrayList<>();

        String sql = "select * from materials";
        Connection con = Connector.connection();
        try {
            PreparedStatement ps = con.prepareStatement(sql) ;
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){

                int materialId = resultSet.getInt("material_id");
                String materialName = resultSet.getString("name");
                int unitId = resultSet.getInt("unit_id");
                int price = resultSet.getInt("price");
                materialArrayList.add(new Material(materialId, materialName, unitId, price));


            }
        } catch (SQLException e) {
            System.out.println("Fejl i connection til database");
            e.printStackTrace();
        }

        return materialArrayList;
    }
}
