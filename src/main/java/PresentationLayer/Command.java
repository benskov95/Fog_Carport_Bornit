package PresentationLayer;

import FunctionLayer.LoginSampleException;

import java.sql.SQLException;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

abstract class Command {

    private static HashMap<String, Command> commands;

    private static void initCommands() {
        commands = new HashMap<>();
        commands.put("login", new Login());
        commands.put("flatorder", new FlatOrder());
        commands.put("redirect", new Redirect());
        commands.put("myorder", new MyOrder());
        commands.put("bompage", new BomPage());
        commands.put("allOrders", new AllOrders());
        commands.put("updateorder", new UpdateOrder());
        commands.put("deleteorder", new DeleteOrder());
    }

    static Command from(HttpServletRequest request) {
        String targetName = request.getParameter("target");
        if (commands == null) {
            initCommands();
        }
        return commands.getOrDefault(targetName, new UnknownCommand());   // unknowncommand er default.
    }

    abstract String execute(HttpServletRequest request, HttpServletResponse response)
            throws LoginSampleException, SQLException, ClassNotFoundException;

}
