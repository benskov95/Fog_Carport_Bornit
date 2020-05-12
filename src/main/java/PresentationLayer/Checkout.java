package PresentationLayer;

import FunctionLayer.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class Checkout extends Command {
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException, SQLException, ClassNotFoundException, OrderException, ServletException, IOException {

        HttpSession session = request.getSession();
        Order order = (Order) session.getAttribute("order");
        Customer customer = (Customer) session.getAttribute("customer");

        OrderFacade.updateStatus(order.getOrder_id(),3);
        order = OrderFacade.getMyOrder(order.getOrder_id(), customer.getPhone());

        session.setAttribute("order", order);

        return "checkoutpage";
    }
}
