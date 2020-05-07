package PresentationLayer;

import DBAccess.CustomerMapper;
import FunctionLayer.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class Login extends Command {

    @Override
    String execute( HttpServletRequest request, HttpServletResponse response ) throws LoginSampleException, SQLException, ClassNotFoundException {

        HttpSession session = request.getSession();
        int phoneNumber;
        int orderId;
        Order order;
        Customer customer;
        BillOfMaterials bom;
        String destination = "index";

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
                session.setAttribute( "customer", customer);
                session.setAttribute("order", order);

                String carportType = OrderFacade.getCarportType(order.getCarport_id());
                String orderStatus = OrderFacade.getOrderStatus(order.getStatus_id());
                session.setAttribute("carportType", carportType);
                session.setAttribute("statusId", order.getStatus_id());
                session.setAttribute("status", orderStatus);

                destination = "myorder";
            }
        }


        return destination;
    }

}
