package DBAccess;

import FunctionLayer.Material;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The purpose of the MaterialMapper class is
 * to communicate with the Database with SQL
 * statements to retrieve materials from the
 * database and to modify Material objects.
 * @author Pelle Rasmussen
 */

public class MaterialMapper {

    /**
     * Gets the lengths of specific materials
     * @param materialId The id of the material
     * @return  Arraylist of all the lengths.
     * @throws SQLException
     *  Thrown if the provided SQL string in each method
     *  has incorrect syntax, unknown keywords etc. or
     *  if the connection to the database cannot be
     *  established.
     * @throws ClassNotFoundException
     * Thrown from Connector if the "Class.forName" method
     * doesn't find the specified class
     * (JDBC driver in this case).
     * @author Pelle Rasmussen
     */

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

    /**
     * Sets the values of the materials.
     * "name","unit id" and "price"
     * @param materials
     * Arraylist of materials with
     * undefined names, unit IDs and
     * prices.
     * @throws SQLException
     *  Thrown if the provided SQL string in each method
     *  has incorrect syntax, unknown keywords etc. or
     *  if the connection to the database cannot be
     *  established.
     * @throws ClassNotFoundException
     * Thrown from Connector if the "Class.forName" method
     * doesn't find the specified class
     * (JDBC driver in this case).
     * @author Pelle Rasmussen
     */

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

    /**
     * Sets size IDs for each material
     * in the provided arraylist which
     * is necessary for when the
     * arraylist later becomes part of
     * a BillOfMaterials object that is
     * then inserted into the database.
     * @param materials
     * An arraylist of materials
     * with no size IDs.
     * @throws SQLException
     *  Thrown if the provided SQL string in each method
     *  has incorrect syntax, unknown keywords etc. or
     *  if the connection to the database cannot be
     *  established.
     * @throws ClassNotFoundException
     * Thrown from Connector if the "Class.forName" method
     * doesn't find the specified class
     * (JDBC driver in this case).
     * @author Benjamin/benskov95
     */
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

    /**
     * Sets link_material_size IDs for
     * each material in the provided
     * arraylist which is necessary for
     * when the arraylist later becomes
     * part of a BillOfMaterials object
     * that is then inserted into the
     * database.
     *
     * The link_size_material ID is
     * necessary to ensure that each
     * material's size is correct, as
     * many materials have different
     * sizes.
     * @param materials
     * An arraylist of materials
     * with no link_material_size IDs.
     * @throws SQLException
     *  Thrown if the provided SQL string in each method
     *  has incorrect syntax, unknown keywords etc. or
     *  if the connection to the database cannot be
     *  established.
     * @throws ClassNotFoundException
     * Thrown from Connector if the "Class.forName" method
     * doesn't find the specified class
     * (JDBC driver in this case).
     * @author Benjamin/benskov95
     */
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

    /**
     * Only used to as part of the
     * categorization process in the
     * BomPage class, where a bill of
     * materials is retrieved from the
     * database and its materials are
     * split into two different categories
     * when displayed on the bompage.jsp
     * page.
     * @param materials
     * Arraylist of materials with
     * undefined unit names.
     * @throws SQLException
     *  Thrown if the provided SQL string in each method
     *  has incorrect syntax, unknown keywords etc. or
     *  if the connection to the database cannot be
     *  established.
     * @throws ClassNotFoundException
     * Thrown from Connector if the "Class.forName" method
     * doesn't find the specified class
     * (JDBC driver in this case).
     * @author Benjamin/benskov95
     */
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

    public static void updateMaterial (int materialId, String name, int price) {


        String sql = "update materials set `name` = ?, price = ? where material_id = ?";

        try{
            Connection con = Connector.connection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, name);
            ps.setInt(2, price);
            ps.setInt(3, materialId);
            ps.executeUpdate();
        }

        catch (SQLException | ClassNotFoundException e) {
            System.out.println("Fejl i connection til database");
            e.printStackTrace();

        }

    }
}
