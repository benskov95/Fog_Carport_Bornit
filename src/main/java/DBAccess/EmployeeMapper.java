package DBAccess;

import FunctionLayer.Employee;
import FunctionLayer.LoginSampleException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeMapper {

    public static Employee login(int employee_id, String password ) throws LoginSampleException {
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT employee_id, role FROM fog.employee "
                    + "WHERE employee_id=? AND password=?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, employee_id);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String role = rs.getString("role");
                int id = rs.getInt("employee_id");
                Employee employee = new Employee(id, password, role);
                employee.setEmployee_id(id);
                return employee;
            } else {
                throw new LoginSampleException("Forkert ID eller Password");
            }
        } catch (SQLException | ClassNotFoundException ex) {
            throw new LoginSampleException(ex.getMessage());
        }

    }
}
