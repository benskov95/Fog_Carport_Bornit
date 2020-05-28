package DBAccess;
import FunctionLayer.LoginSampleException;
import FunctionLayer.Size;
import FunctionLayer.Unit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The purpose of the InitializeMapper class is
 * to communicate with the Database with SQL
 * statements to retrieve carport and shed
 * measurements from the database.
 * @author Pelle Rasmussen
 */

public class InitializeMapper {

    /**
     * Gets the carport widths used on the
     * dropdown menus on the website.
     * @return Arraylist of carport widths
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
     * Gets the carport lengths used on the
     * dropdown menus on the website.
     * @return Arraylist of carport lengths
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
     * Gets the shed widths used on the
     * dropdown menus on the website.
     * @return Arraylist of shed widths
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
     * Gets the shed lengths used on the
     * dropdown menus on the website.
     * @return Arraylist of shed lengths
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

    public static ArrayList<Size> getSizes(boolean isLengths) throws  SQLException, ClassNotFoundException {

        ArrayList<Size> sizeList = new ArrayList<>();
        String sql;
        int numberOfActualLengths = 20; // Der er 20 egentlige længder til træ og tagplader

        if (isLengths) {
        sql = "SELECT * FROM fog.size where size_id <= " + numberOfActualLengths;
        } else {
            sql = "select * from fog.size where size_id > " + numberOfActualLengths; // Alle størrelser i databasen med ID over 20 er ikke længder.
        }

        Connection con = Connector.connection();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
                while (resultSet.next()) {
                    int sizeId = resultSet.getInt("size_id");
                    int size = resultSet.getInt("size");
                    if (sizeId > 20 && size == 1) {
                        sizeList.add(new Size(sizeId, size));
                        break;
                    } else {
                        sizeList.add(new Size(sizeId, size));
                    }
                }

            sizeList.removeIf(size -> size.getSize() == 0); // Bliver brugt ifb. beregner, ikke nødvendig at have med her.
            sizeList.removeIf(size -> size.getSize() == 100); // Kommer kun med hvis det er til antal, og vi vil kun have, at man kan vælge 1.

            } catch(SQLException e){
                System.out.println("Fejl i connection til database");
                e.printStackTrace();
            }
            return sizeList;
        }

    public static ArrayList<Unit> getUnits() throws  SQLException, ClassNotFoundException {

        ArrayList<Unit> uList = new ArrayList<>();

        String sql = "SELECT * FROM fog.unit";
        Connection con = Connector.connection();
        try  (PreparedStatement ps = con.prepareStatement(sql);
              ResultSet resultSet = ps.executeQuery() )
        {
            while (resultSet.next()) {
                int unitId = resultSet.getInt("unit_id");
                String unitName = resultSet.getString("unit_type");
                uList.add(new Unit(unitId, unitName));
            }
        } catch (SQLException e) {
            System.out.println("Fejl i connection til database");
            e.printStackTrace();
        }
        return uList;
    }
}
