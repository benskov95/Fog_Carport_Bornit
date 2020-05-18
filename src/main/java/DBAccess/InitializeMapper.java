package DBAccess;
import FunctionLayer.LoginSampleException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The purpose of the InitializeMapper class is
 * to communicate with the Database with SQL statements.
 * @author Pelle Rasmussen
 */

public class InitializeMapper {

    /**
     * Gets the carport widths used on the dropdown menus on the website
     * @return Arraylist of carport widths
     * @throws SQLException
     * @throws ClassNotFoundException
     */

    public static ArrayList<Integer> getCarportWidth() throws SQLException, ClassNotFoundException {

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
    /**
     * Gets the carport lengths used on the dropdown menus on the website
     * @return Arraylist of carport lengths
     * @throws SQLException
     * @throws ClassNotFoundException
     */

    public static ArrayList<Integer> getCarportLength() throws SQLException, ClassNotFoundException {

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
    /**
     * Gets the shed widths used on the dropdown menus on the website
     * @return Arraylist of shed widths
     * @throws SQLException
     * @throws ClassNotFoundException
     */

    public static ArrayList<Integer> getShedWidth() throws  SQLException, ClassNotFoundException {

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
    /**
     * Gets the shed lengths used on the dropdown menus on the website
     * @return Arraylist of shed lengths
     * @throws SQLException
     * @throws ClassNotFoundException
     */

    public static ArrayList<Integer> getShedLength() throws  SQLException, ClassNotFoundException {

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
