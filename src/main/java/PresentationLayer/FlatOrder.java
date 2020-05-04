package PresentationLayer;

import FunctionLayer.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;

public class FlatOrder extends Command {
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException, SQLException, ClassNotFoundException {

        Calculator calculator = new Calculator();
        int carportWidth = Integer.parseInt(request.getParameter("carportwidth"));
        int carportLength = Integer.parseInt(request.getParameter("carportlength"));
        int shedWidth = Integer.parseInt(request.getParameter("shedwidth"));
        int shedLength = Integer.parseInt(request.getParameter("shedlength"));

        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String postalCodeCity = request.getParameter("postalcode");
        int telephone = Integer.parseInt(request.getParameter("telephone"));
        String email = request.getParameter("email");

        Order order = new Order(1, carportWidth, carportLength, shedWidth, shedLength, telephone);
        Customer customer = new Customer(telephone, name, address, email, postalCodeCity);
        int orderId = OrderFacade.insertOrder(customer, order);

        BillOfMaterials bom = calculator.type1Calc(carportLength, carportWidth, orderId);
        BomFacade.insertBillOfMaterials(bom);

        request.setAttribute("orderId", orderId);

        return "receipt";
    }


}
