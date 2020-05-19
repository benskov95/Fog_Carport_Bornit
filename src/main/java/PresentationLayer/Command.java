package PresentationLayer;

import FunctionLayer.LoginSampleException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * The purpose of Command is to create a link
 * between the jsp pages and their respective
 * classes and functions. Each string and
 * object added in the HashMap in initCommands
 * represent an individual jsp page with actions
 * and functions related to those jsp pages.
 *
 * This information is relayed to the FrontController
 * and used to ensure that the jsp pages always
 * have their matching class (and therefore
 * functionality).
 *
 * All classes listed in the initCommands method
 * below extend Command, and all actions and
 * calculations happen through Command's
 * execute method, whose content is different
 * for each class that extends Command.
 *
 * @author Benjamin/benskov95
 */
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
        commands.put("drawing", new Drawing());
        commands.put("shiporder", new ShipOrder());
        commands.put("checkout", new Checkout());
        commands.put("carportplanside", new DrawingSide());


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
