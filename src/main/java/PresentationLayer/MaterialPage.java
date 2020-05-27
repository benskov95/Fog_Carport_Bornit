package PresentationLayer;

import FunctionLayer.LoginSampleException;
import FunctionLayer.Material;
import FunctionLayer.MaterialFacade;
import FunctionLayer.OrderException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class MaterialPage extends Command {
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException, SQLException, ClassNotFoundException, OrderException, ServletException, IOException {
        HttpSession session = request.getSession();
        ArrayList<Material> allMaterials = MaterialFacade.getAllMaterials();

        MaterialFacade.setUnitTypes(allMaterials);

        session.setAttribute("allMaterials", allMaterials);

        return "materialpage";
    }
}
