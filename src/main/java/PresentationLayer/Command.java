package PresentationLayer;

import FunctionLayer.LoginSampleException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

abstract class Command {

    private static HashMap<String, Command> commands;

    private static void initCommands() {
        commands = new HashMap<>();
        commands.put("login", new Login());
        commands.put("logout", new Logout());
        commands.put("flatorder", new FlatOrder());
        commands.put("redirect", new Redirect());
        commands.put("myorder", new MyOrder());
        commands.put("bompage", new BomPage());
        commands.put("allOrders", new AllOrders());
        commands.put("updateorder", new UpdateOrder());
        commands.put("deleteorder", new DeleteOrder());
        commands.put("employeeLogin", new EmployeeLogin());
        commands.put("shiporder", new ShipOrder());

    }

    static Command from(HttpServletRequest request) {
        String targetName = request.getParameter("target");
        if (commands == null) {
            initCommands();
        }
        return commands.getOrDefault(targetName, new UnknownCommand());   // unknowncommand er default.
    }

    abstract String execute(HttpServletRequest request, HttpServletResponse response)
            throws LoginSampleException, SQLException, ClassNotFoundException, FunctionLayer.OrderException, ServletException, IOException;

}
