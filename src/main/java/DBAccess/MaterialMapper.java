package DBAccess;

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
        String sql = "select m.material_id, m.name, s.size from fog.materials m\n" +
                     "inner join link_material_size l \n" +
                     "on m.material_id = l.link_material_id\n" +
                     "inner join size s \n" +
                     "on l.link_size_id = s.size_id" +
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
}
