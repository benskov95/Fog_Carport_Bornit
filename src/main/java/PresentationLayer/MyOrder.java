package PresentationLayer;

import FunctionLayer.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class MyOrder extends Command {
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException, SQLException, ClassNotFoundException {

        HttpSession session = request.getSession();
        Order order = (Order) session.getAttribute("order");

        int orderId = order.getOrder_id();
        int phone = order.getPhone();
        request.setAttribute("deletedOrderId", orderId);

        BomFacade.deleteBom(orderId);
        OrderFacade.deleteOrder(orderId);
        CustomerFacade.deleteCustomer(phone);

        return "declinedorder";
    }
}
