package PresentationLayer;

import FunctionLayer.Employee;
import FunctionLayer.LoginSampleException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Redirecter til anden jsp side.
 */
public class Redirect extends Command {
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        String destination = request.getParameter("destination");

        Employee employee = (Employee) session.getAttribute("employee");

        if (destination.equals("orderpage")) {
            if (employee != null && employee.getRoleId() == 2) {
                destination = "warehousepage";
            } else {
                destination = "myorder";
            }
        }
        return destination;
    }
}

