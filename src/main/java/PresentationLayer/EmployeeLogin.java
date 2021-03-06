package PresentationLayer;

import FunctionLayer.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;

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
            ArrayList<Order> orders = OrderFacade.getAllOrdersByStatusId(3);
            for (Order order : orders) {
                BomFacade.getBillOfMaterials(order.getOrder_id());
            }
            session.setAttribute("orders", orders);
        }
        session.setAttribute("employee", employee);
        return employee.getRole() + "page";
    }
}
