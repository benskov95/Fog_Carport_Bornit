package PresentationLayer;


import FunctionLayer.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 * The purpose of the FlatOrder class is to insert order related
 * data into the database and creating a bill of materials that
 * matches the type of carport ordered.
 *
 * Inherits the execute() method from the Command class.
 * Whenever a new order is placed, this method is called.
 * With help from the Calculator class, a bill of materials
 * is created after which the order itself, the customer
 * and the bill of materials are inserted into the database.
 * If the customer already exists, all registration fields
 * are skipped.
 *
 * @author Matt Thomsen
 */

public class FlatOrder extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, OrderException, LoginSampleException {

        Calculator calculator = new Calculator();

        int carportWidth;
        int carportLength;
        int shedWidth;
        int shedLength;
        String name;
        String address;
        String postalCodeCity;
        int telephone;
        int registeredTelephone;
        String email;
        Customer customer;
        BillOfMaterials bom;
        Order order;
        int orderId;

        try {
            carportWidth = Integer.parseInt(request.getParameter("carportwidth"));
            carportLength = Integer.parseInt(request.getParameter("carportlength"));
        } catch (Exception e) {
            throw new OrderException("Du mangler Længde eller Bredde på carport.");
        }

        shedWidth = Integer.parseInt(request.getParameter("shedwidth"));
        shedLength = Integer.parseInt(request.getParameter("shedlength"));

        if (shedLength != 0 && shedWidth == 0 || shedLength == 0 && shedWidth != 0) {
            throw new OrderException("Du mangler bredde eller længde på skur.");
        }

        if (shedLength >= carportLength || shedWidth >= carportWidth) {
            throw new OrderException("Skuret må ikke være længere eller bredere end carporten.");
        }

        try {
            telephone = Integer.parseInt(request.getParameter("telephone"));
        } catch (Exception e) {
            throw new OrderException("Telefonnummeret må kun bestå af tal.");
        }

        String s = String.valueOf(telephone);
        if (s.length() != 8) {
            throw new OrderException("Du skal bruge et 8-cifret telefonnummer.");
        }

        if (shedLength > 0 && shedWidth > 0) {
            bom = calculator.type2Calc(carportLength, carportWidth, shedLength, shedWidth);
            order = new Order(2, carportWidth, carportLength, shedWidth, shedLength, bom.getTotalPrice(), telephone);
        } else {
            bom = calculator.type1Calc(carportLength, carportWidth);
            order = new Order(1, carportWidth, carportLength, shedWidth, shedLength, bom.getTotalPrice(), telephone);
        }

        try {
            registeredTelephone = Integer.parseInt(request.getParameter("registeredTelephone"));
        } catch (Exception e) {
            throw new OrderException("Telefonnummeret må kun bestå af tal.");
        }

        customer = CustomerFacade.getCustomer(registeredTelephone);

        if (customer == null && telephone != 12345678) {
            name = request.getParameter("name");
            address = request.getParameter("address");
            postalCodeCity = request.getParameter("postalcode");
            email = request.getParameter("email");

            if (name.length() == 0 || address.length() == 0 || postalCodeCity.length() == 0 || email.length() == 0) {
                throw new OrderException("Du mangler at udfylde et felt.");
            }

            customer = new Customer(telephone, name, address, email, postalCodeCity);
            orderId = OrderFacade.insertOrder(customer, order);

        } else if (customer != null) {
            order.setPhone(customer.getPhone());
            orderId = OrderFacade.insertOrderForExistingCustomer(order);
        } else {
            throw new OrderException("Du er ikke registreret, udfyld venligst alle felter for at bestille");
        }


        bom.setOrderId(orderId);
        BomFacade.insertBillOfMaterials(bom);

        request.setAttribute("orderId", orderId);

        return "receipt";
    }
}
