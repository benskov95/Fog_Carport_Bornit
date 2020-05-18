package DBAccess;

import FunctionLayer.Employee;
import FunctionLayer.LoginSampleException;
import FunctionLayer.Material;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The purpose of the EmployeeMapper class is
 * to communicate with the Database with SQL
 * statements to retrieve employees and set
 * their roles.
 * @author Pelle Rasmussen
 */

public class EmployeeMapper {
    /**
     * Login for the employees, makes
     * sure the password and ID is
     * correct.
     * @param employee_id
     *  ID provided by user, has to
     *  match an ID in the employee
     *  table in the database.
     * @param password
     * Password provided by user,
     * has to match an ID in the
     * employee table in the database.
     * @return Employee
     * @throws LoginSampleException
     * Thrown if login credentials do not match with
     * any of the registered employees in the database.
     * @author Pelle Rasmussen
     */
    public static Employee login(int employee_id, String password ) throws LoginSampleException {
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT * FROM fog.employee "
                    + "WHERE employee_id=? AND password=?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, employee_id);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int roleId = rs.getInt("role_id");
                int id = rs.getInt("employee_id");
                Employee employee = new Employee(id, password, roleId);
                employee.setEmployee_id(id);
                return employee;
            } else {
                throw new LoginSampleException("Forkert ID eller Password");
            }
        } catch (SQLException | ClassNotFoundException ex) {
            throw new LoginSampleException(ex.getMessage());
        }

    }

    /**
     * Sets the role of an employee.
     * @param employee
     * The Employee object whose
     * role has not yet been set.
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

    public static void setEmployeeRole(Employee employee) throws SQLException, ClassNotFoundException {

        Connection con = Connector.connection();

        String SQL = "SELECT * from fog.role";

            try {
                PreparedStatement ps = con.prepareStatement(SQL);
                ResultSet resultSet = ps.executeQuery();

                while (resultSet.next()) {
                  String role = resultSet.getString("role_name");
                  int roleId = resultSet.getInt("role_id");
                  if (employee.getRoleId() == roleId) {
                      employee.setRole(role);
                    }
                }

            } catch (SQLException e) {
                System.out.println("Fejl i connection til database");
                e.printStackTrace();
            }
        }
    }