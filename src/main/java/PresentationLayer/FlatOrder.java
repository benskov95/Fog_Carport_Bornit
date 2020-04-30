package PresentationLayer;

import FunctionLayer.LogicFacade;
import FunctionLayer.LoginSampleException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class FlatOrder extends Command {
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {

        HttpSession session = request.getSession();

        int carportWidth = Integer.parseInt(request.getParameter("carportwidth"));
        int carportLength = Integer.parseInt(request.getParameter("carportlength"));
        int shedWidth = Integer.parseInt(request.getParameter("shedwidth"));
        int shedLength = Integer.parseInt(request.getParameter("shedlength"));

        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String postalCodeCity = request.getParameter("postalcode");
        int telephone = Integer.parseInt(request.getParameter("telephone"));
        String email = request.getParameter("email");

        LogicFacade.insertOrder(1,carportWidth,carportLength,shedWidth,shedLength,telephone,1, name,address,email,postalCodeCity);

        return "receipt";
    }


}
