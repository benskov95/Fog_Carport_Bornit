package PresentationLayer;

import DBAccess.MaterialMapper;
import FunctionLayer.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;

public class BomPage extends Command {
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException, SQLException, ClassNotFoundException {

        HttpSession session = request.getSession();
        Order order = (Order) session.getAttribute("order");
        int orderId = 0;
        BillOfMaterials bom = new BillOfMaterials();
        try {
            orderId = Integer.parseInt(request.getParameter("billofmaterials"));
            session.setAttribute("warehouse_orderId", orderId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (orderId != 0) {
            bom = BomFacade.getBillOfMaterials(orderId);
        } else {
            bom = BomFacade.getBillOfMaterials(order.getOrder_id());
        }

        MaterialFacade.setUnitTypes(bom.getMaterials());
        ArrayList<Material> woodAndRoofPlates = new ArrayList<>();
        ArrayList<Material> bracketsAndScrews = new ArrayList<>();

        for (Material material : bom.getMaterials()) {
            if (material.getSize() <= 1) { // Materialernes længde sættes til 1 hvis de er 0 når de hentes gennem getBillOfMaterials().
                material.setSize(0); // Disse materialer har ingen længde - prøver at følge samme struktur som pdf styklisten.
                bracketsAndScrews.add(material);
            } else {
                woodAndRoofPlates.add(material);
            }
        }

        session.setAttribute("woodAndRoofPlates", woodAndRoofPlates);
        session.setAttribute("bracketsAndScrews", bracketsAndScrews);

        return "bompage";
    }
}