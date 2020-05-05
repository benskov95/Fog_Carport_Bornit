package PresentationLayer;

import FunctionLayer.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class FlatOrder extends Command {
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException, SQLException, ClassNotFoundException, OrderException {

        Calculator calculator = new Calculator();


        int  carportWidth = 0;
        int  carportLength = 0;

        int shedWidth = 0;
        int shedLength = 0;
        String name = null;
        String address = null;
        String postalCodeCity = null;
        int telephone = 0;
        String email = null;








        try {

            carportWidth = Integer.parseInt(request.getParameter("carportwidth"));
            carportLength = Integer.parseInt(request.getParameter("carportlength"));
            } catch (Exception e) {
            throw new OrderException("Du mangler Længde eller Bredde på carport");
        }
        shedWidth = Integer.parseInt(request.getParameter("shedwidth"));
        shedLength = Integer.parseInt(request.getParameter("shedlength"));

        if (shedLength != 0 && shedWidth == 0 || shedLength ==0 && shedWidth != 0){
            throw new OrderException("Du mangler bredde eller længde på skur");
        }

        name = request.getParameter("name");
        address = request.getParameter("address");
        postalCodeCity = request.getParameter("postalcode");
        email = request.getParameter("email");

        if (name.length() == 0 || address.length() == 0 || postalCodeCity.length() == 0 || email.length() == 0){
            throw new OrderException("Du mangler at udfylde et felt");
        }

        try {
            telephone = Integer.parseInt(request.getParameter("telephone"));
            String s = String.valueOf(telephone);
            if (s.length() != 8 ){
                throw new OrderException("Ugyldigt telefon nummer");
            }

        } catch (Exception e){
            throw new OrderException(e.getMessage());
        }






        BillOfMaterials bom = calculator.type1Calc(carportLength, carportWidth);
        Order order = new Order(1, carportWidth, carportLength, shedWidth, shedLength, bom.getTotalPrice(), telephone);
        Customer customer = new Customer(telephone, name, address, email, postalCodeCity);

        int orderId = OrderFacade.insertOrder(customer, order);
        bom.setOrderId(orderId);
        BomFacade.insertBillOfMaterials(bom);

        request.setAttribute("orderId", orderId);

        return "receipt";
    }


}
