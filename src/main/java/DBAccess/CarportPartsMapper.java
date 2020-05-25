package DBAccess;

import FunctionLayer.CarPortPart;
import FunctionLayer.Customer;
import FunctionLayer.Log;
import FunctionLayer.Material;
import PresentationLayer.UnknownCommand;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 * The purpose of the CarportPartsMapper class is
 * to communicate with the Database with SQL
 * statements to retrieve carport parts.
 *
 * Mainly used to assign specific values
 * to Material objects.
 * @author Pelle Rasmussen
 */

public class CarportPartsMapper {
    /**
     * Gets all Carport parts.
     * @return Arraylist of Carport Parts.
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
    public static ArrayList<CarPortPart> getCarportParts() throws SQLException, ClassNotFoundException {

        ArrayList<CarPortPart> carportPartsArrayList = new ArrayList<>();

        String sql = "select * from carport_parts";
        Connection con = Connector.connection();

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {

                int carPortPartId = resultSet.getInt("pk_carport_part_id");
                int materialId = resultSet.getInt("material_id");
                String description = resultSet.getString("description");
                int carPortId = resultSet.getInt("carport_id");
                carportPartsArrayList.add(new CarPortPart(carPortPartId, materialId, description, carPortId));

            }
        } catch (SQLException e) {
            System.out.println("Fejl i connection til database");
            e.printStackTrace();
        }

        return carportPartsArrayList;
    }

    /**
     * The getCarportPartIds() method is used
     * to assign the correct carport part IDs
     * to each material in an arraylist.
     *
     * This is necessary to later assign the
     * correct descriptions to each material
     * but also important for when the
     * BillOfMaterials (containing this
     * arraylist of materials) has to be
     * inserted into the database.
     *
     * @param materials
     * Arraylist of materials that is or
     * will be part of a BillOfMaterials
     * object.
     * @param carportTypeId
     * Carport parts in the database
     * are split into categories based
     * on whether the carport has a shed
     * or not. The method has to know the
     * type of the carport to retrieve and
     * assign the correct carport part IDs
     * to each material.
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
    public static void getCarportPartIds(ArrayList<Material> materials, int carportTypeId) throws SQLException, ClassNotFoundException {

        String sql = "select * from carport_parts where carport_id = ?";
        Connection con = Connector.connection();
        int partId;

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, carportTypeId);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {

                int dbMaterialId = resultSet.getInt("material_id");

                for (Material material : materials) {

                    if (dbMaterialId == material.getMaterialId() && material.getCarportPartId() == 0) {
                        partId = resultSet.getInt("pk_carport_part_id");
                        material.setCarportPartId(partId);
                        if (dbMaterialId == 69) {
                            material.roofCarportPartIdHelper(partId, materials);
                        }
                        if (dbMaterialId == 38) {
                            material.beamCarportPartIdHelper(partId, materials);
                        }
                        break;
                }
            }
        }
        } catch (SQLException e) {
            System.out.println("Fejl i connection til database");
            e.printStackTrace();
        }
    }

    /**
     * Retrieves all the descriptions of the
     * carport parts and assigns them to the
     * materials in the provided arraylist of
     * Material objects based on their carport
     * part ID.
     * @param materials Arraylist of Materials
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

    public static void getCarportPartDescriptions(ArrayList<Material> materials) throws SQLException, ClassNotFoundException {

        String sql = "select * from carport_parts where pk_carport_part_id = ?";
        Connection con = Connector.connection();

        for (Material material : materials) {

            try {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, material.getCarportPartId());
                ResultSet resultSet = ps.executeQuery();
                if (resultSet.next()) {
                    material.setCarportPartDescription(resultSet.getString("description"));

                }
            } catch (SQLException e) {
                System.out.println("Fejl i connection til database");
                e.printStackTrace();
                throw new SQLException(e.getMessage());
            }
        }


    }

}