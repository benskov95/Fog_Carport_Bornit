package PresentationLayer;

import FunctionLayer.LoginSampleException;
import FunctionLayer.OrderException;
import FunctionLayer.OrderFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class ShipOrder extends Command {
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException, SQLException, ClassNotFoundException, OrderException {

        HttpSession session = request.getSession();

        int order_id = Integer.parseInt(request.getParameter("shipped"));

        OrderFacade.updateStatus(order_id, 4);

        session.setAttribute("orderlist", OrderFacade.getAllOrdersByStatusId(3));

        return "warehousepage";
    }
}
