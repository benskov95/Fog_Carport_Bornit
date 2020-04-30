package DBAccess;

import FunctionLayer.Order;

import java.sql.*;

public class StatusMapper {


    public static String getStatusByID(int status_id) throws SQLException, ClassNotFoundException {



        String sqlOrders = "SELECT * fog.status WHERE status = ?";
        Connection con = Connector.connection();
        try  (PreparedStatement ps = con.prepareStatement(sqlOrders);

              ResultSet resultSet = ps.executeQuery() )
        {
            while (resultSet.next()) {
                ps.setInt(1,status_id);
                String status = resultSet.getString("status");

                return status;


            }
        } catch (SQLException e) {
            System.out.println("Fejl i connection til database");
            e.printStackTrace();
        }
        return null;

    }
}


