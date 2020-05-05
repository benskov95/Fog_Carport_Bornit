package PresentationLayer;

import FunctionLayer.Employee;
import FunctionLayer.LogicFacade;
import FunctionLayer.LoginSampleException;
import FunctionLayer.OrderFacade;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class EmployeeLogin extends Command {
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException, SQLException, ClassNotFoundException {

        HttpSession session = request.getSession();

        int employee_id = Integer.parseInt(request.getParameter( "employeeId" )) ;
        String password = request.getParameter( "password" );
        Employee employee = LogicFacade.employeeLogin(employee_id, password);

        if (employee.getRoleId() == 1) {
            LogicFacade.setEmployeeRole(employee);
            session.setAttribute("orderlist", OrderFacade.getAllOrdersByStatusId(1));

        }
        if (employee.getRoleId() == 2) {
            LogicFacade.setEmployeeRole(employee);
            session.setAttribute("orderlist" , OrderFacade.getAllOrdersByStatusId(3));
        }

        return employee.getRole() + "page";
    }
}
