package DBAccess;

import FunctionLayer.CarPortPart;
import FunctionLayer.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CarportPartsMapper {

    public static ArrayList<CarPortPart> getCarportParts() throws SQLException, ClassNotFoundException {

        ArrayList<CarPortPart> carportPartsArrayList = new ArrayList<>();

        String sql = "SELECT * fog.carport_parts";
        Connection con = Connector.connection();

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {

                int carPortPartId = resultSet.getInt("pk_carport_part_idgi");
                int materialId = resultSet.getInt("material_id");
                String beskrivelse = resultSet.getString("beskrivelse");
                int carPortId = resultSet.getInt("carport_id");
                carportPartsArrayList.add(new CarPortPart(carPortPartId, materialId, beskrivelse, carPortId));

            }
        } catch (SQLException e) {
            System.out.println("Fejl i connection til database");
            e.printStackTrace();
        }

        return carportPartsArrayList;
    }
}