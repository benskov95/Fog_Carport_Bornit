package DBAccess;

import FunctionLayer.CarPortPart;
import FunctionLayer.Customer;
import FunctionLayer.Material;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CarportPartsMapper {

    public static ArrayList<CarPortPart> getCarportParts() throws SQLException, ClassNotFoundException {

        ArrayList<CarPortPart> carportPartsArrayList = new ArrayList<>();

        String sql = "select * from fog.carport_parts";
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

    public static void getCarportPartIds(ArrayList<Material> materials, int carportTypeId) throws SQLException, ClassNotFoundException {

        String sql = "select * from fog.carport_parts where carport_id = ?";
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
                        break;
                }
            }
        }
        } catch (SQLException e) {
            System.out.println("Fejl i connection til database");
            e.printStackTrace();
        }
    }

    public static void getCarportPartDescriptions(ArrayList<Material> materials) throws SQLException, ClassNotFoundException {

        String sql = "select * from fog.carport_parts where pk_carport_part_id = ?";
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
            }
        }


    }

}