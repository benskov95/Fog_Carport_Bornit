package PresentationLayer;

import FunctionLayer.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

/**
 * The MyOrder class is used when a customer
 * decides to cancel their order. Through the
 * execute() method inherited from the Command
 * class, the order (which is put on the session
 * scope in the Login class) is used to delete
 * the order in the database with a matching
 * ID through methods in the OrderMapper and
 * BomMapper.
 *
 * The deletion must happen in a specific
 * sequence due to foreign key constraints -
 * first, any bills of materials must be deleted
 * from the database since the order id is part
 * of each bill of materials. Second, the order
 * itself must be deleted because it has the
 * customer's phone number as one of its
 * foreign keys.
 *
 * Once completed, the customer is sent to
 * the declinedorder.jsp page which
 * provides a brief message explaining
 * that the order has been deleted. From
 * here, the customer can return to the
 * index page.
 * @author Benjamin/benskov95
 */
public class MyOrder extends Command {
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException, SQLException, ClassNotFoundException {

        HttpSession session = request.getSession();
        Order order = (Order) session.getAttribute("order");

        int orderId = order.getOrder_id();
        request.setAttribute("deletedOrderId", orderId);

        BomFacade.deleteBom(orderId);
        OrderFacade.deleteOrder(orderId);

        return "declinedorder";
    }
}
