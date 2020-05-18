package PresentationLayer;

import DBAccess.CustomerMapper;
import FunctionLayer.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

/**
 * The purpose of the Login class is to handle customer logins.
 * It inherits the execute() method from the Command class.
 * This overriden method handles user logins. If the login is
 * succesful, the order information is retrieved from the database
 * and the customer will be redirected to myorder.jsp.
 *
 * The customer's phone number and an order ID are used as
 * credentials for the login process. If a customer has
 * multiple orders, they will have to log in and look at
 * each order individually with each order ID. This is
 * because the functionality code for the myorder.jsp
 * page uses information from a single order (to see the
 * drawing and look at the bill of materials).
 * @author Matt Thomsen
 */

public class Login extends Command {

    /**
     * Inherits the execute() method from the Command interface. This overriden method handles user logins. If the login is succesful,
     * the order information is retrieved from the database and the customer will be redirected to myorder.jsp.
     *
     * @param request
     * @param response
     * @return destination - a jsp page that differs depending on if the login was succesful or not.
     * @throws LoginSampleException if phoneNumber or orderId is incorrect or non-existent.
     * @throws SQLException if any problems with reading from the database occurs.
     * @throws ClassNotFoundException
     */

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException, SQLException, ClassNotFoundException {

        HttpSession session = request.getSession();
        int phoneNumber;
        int orderId;
        Order order;
        Customer customer;
        BillOfMaterials bom;
        String destination = "index";

        if (session.getAttribute("order") != null) {
            session.setAttribute("order", null);
            session.setAttribute("employee", null);
        }

        try {
            phoneNumber = Integer.parseInt(request.getParameter("phoneNumber"));
            orderId = Integer.parseInt(request.getParameter("orderId"));
        } catch (Exception e) {
            throw new LoginSampleException("Der gik noget galt. Sørg for, at begge felter er udfyldt og at der ikke bruges bogstaver.");

        }

        if (phoneNumber != 0 && orderId != 0) {
            order = OrderFacade.getMyOrder(orderId, phoneNumber);
            customer = CustomerMapper.getCustomer(phoneNumber);
            bom = BomFacade.getBillOfMaterials(orderId);

            if (order == null || customer == null) {
                throw new LoginSampleException("FEJL: Der blev ikke fundet nogen ordre med dette telefonnummer og ordrenummer i databasen. Prøv igen.");
            } else {
                session.setAttribute("bom", bom);
                session.setAttribute("customer", customer);
                session.setAttribute("order", order);

                String carportType = OrderFacade.getCarportType(order.getCarport_id());
                session.setAttribute("carportType", carportType);
                session.setAttribute("statusId", order.getStatus_id());

                destination = "myorder";
            }
        }

        return destination;
    }
}
