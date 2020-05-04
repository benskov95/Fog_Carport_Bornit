package PresentationLayer;

import FunctionLayer.LoginSampleException;
import FunctionLayer.OrderFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class UpdateOrder extends Command{
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException, SQLException, ClassNotFoundException {


        HttpSession session = request.getSession();

        int order_id = Integer.parseInt(request.getParameter("accept"));

        OrderFacade.updateStatus(order_id,2);

        session.setAttribute("orderlist", OrderFacade.getAllOrdersByStatusId(1));


        return "adminpage";
    }
}
