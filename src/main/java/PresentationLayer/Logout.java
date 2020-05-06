package PresentationLayer;

import FunctionLayer.LoginSampleException;
import FunctionLayer.OrderException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class Logout extends Command {
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException, SQLException, ClassNotFoundException, OrderException, ServletException, IOException {

        HttpSession session = request.getSession();
        session.invalidate();

        request.getRequestDispatcher("/index.jsp").forward(request, response);

        return null;
    }
}
