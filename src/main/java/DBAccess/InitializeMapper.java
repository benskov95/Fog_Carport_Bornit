package DBAccess;
import FunctionLayer.LoginSampleException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InitializeMapper {

    public static ArrayList<Integer> getCarportWidth() throws LoginSampleException, SQLException, ClassNotFoundException {

        ArrayList<Integer> CWList = new ArrayList<>();

        String sql = "SELECT size FROM fog.carport_width";
        Connection con = Connector.connection();
        try  (PreparedStatement ps = con.prepareStatement(sql);
              ResultSet resultSet = ps.executeQuery() )
        {
            while (resultSet.next()) {
                int size = resultSet.getInt("size");
                CWList.add(size);
            }
        } catch (SQLException e) {
            System.out.println("Fejl i connection til database");
            e.printStackTrace();
        }
        return CWList;
    }

    public static ArrayList<Integer> getCarportLength() throws LoginSampleException, SQLException, ClassNotFoundException {

        ArrayList<Integer> CLList = new ArrayList<>();

        String sql = "SELECT size FROM fog.carport_length";
        Connection con = Connector.connection();
        try  (PreparedStatement ps = con.prepareStatement(sql);
              ResultSet resultSet = ps.executeQuery() )
        {
            while (resultSet.next()) {
                int size = resultSet.getInt("size");
                CLList.add(size);
            }
        } catch (SQLException e) {
            System.out.println("Fejl i connection til database");
            e.printStackTrace();
        }
        return CLList;
    }

    public static ArrayList<Integer> getShedWidth() throws LoginSampleException, SQLException, ClassNotFoundException {

        ArrayList<Integer> SWList = new ArrayList<>();

        String sql = "SELECT size FROM fog.shed_width";
        Connection con = Connector.connection();
        try  (PreparedStatement ps = con.prepareStatement(sql);
              ResultSet resultSet = ps.executeQuery() )
        {
            while (resultSet.next()) {
                int size = resultSet.getInt("size");
                SWList.add(size);
            }
        } catch (SQLException e) {
            System.out.println("Fejl i connection til database");
            e.printStackTrace();
        }
        return SWList;
    }

    public static ArrayList<Integer> getShedLength() throws LoginSampleException, SQLException, ClassNotFoundException {

        ArrayList<Integer> SLList = new ArrayList<>();

        String sql = "SELECT size FROM fog.shed_length";
        Connection con = Connector.connection();
        try  (PreparedStatement ps = con.prepareStatement(sql);
              ResultSet resultSet = ps.executeQuery() )
        {
            while (resultSet.next()) {
                int size = resultSet.getInt("size");
                SLList.add(size);
            }
        } catch (SQLException e) {
            System.out.println("Fejl i connection til database");
            e.printStackTrace();
        }
        return SLList;
    }



}
