package PresentationLayer;

import DBAccess.BomMapper;
import FunctionLayer.BomFacade;
import FunctionLayer.LoginSampleException;
import FunctionLayer.OrderFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class RejectOrder extends Command {
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException, SQLException, ClassNotFoundException {

        HttpSession session = request.getSession();

        int order_id = Integer.parseInt(request.getParameter("reject"));
        BomFacade.deleteBom(order_id);
        OrderFacade.updateStatus(order_id, 5);

        session.setAttribute("orderlist", OrderFacade.getAllOrdersByStatusId(1));

        return "adminpage";
    }
}
