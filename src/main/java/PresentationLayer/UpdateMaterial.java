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

public class UpdateMaterial extends Command {
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws  SQLException, ClassNotFoundException, OrderException {

        HttpSession session = request.getSession();
        String materialName = request.getParameter("materialName");
        int materialId = Integer.parseInt(request.getParameter("materialId"));
        int materialPrice;

        try {
            materialPrice = Integer.parseInt(request.getParameter("materialPrice"));
        } catch (Exception e) {
            throw new OrderException("Du skal skrive et tal");
        }

        MaterialFacade.updateMaterial(materialId, materialName, materialPrice);

        ArrayList<Material> allMaterials = MaterialFacade.getAllMaterials();
        MaterialFacade.setUnitTypes(allMaterials);

        session.setAttribute("allMaterials", allMaterials);
        request.setAttribute("updated", "Materialet med ID " + materialId + " er blevet opdateret.");

        return "materialpage";
    }
}
