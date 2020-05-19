package PresentationLayer;

import FunctionLayer.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 * The purpose of the BomPage class is to retrieve the
 * correct bill of materials for a specific order ID
 * and make sure it gets displayed correctly on the
 * bompage.jsp page.
 *
 * Inherits the execute() method from the Command
 * class. The method is used to retrieve the
 * bill of materials and sort its materials
 * into two categories - all for the purpose
 * of displaying it in an easy-to-understand
 * fashion.
 * @author Matt Thomsen
 */

public class BomPage extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, LoginSampleException {

        HttpSession session = request.getSession();
        Order order = (Order) session.getAttribute("order");
        int orderId = 0;
        BillOfMaterials bom;

        try {
            orderId = Integer.parseInt(request.getParameter("billofmaterials"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (orderId != 0) {
            bom = BomFacade.getBillOfMaterials(orderId);
            session.setAttribute("order", OrderFacade.getOrderForWarehouse(orderId));
        } else {
            bom = BomFacade.getBillOfMaterials(order.getOrder_id());

            for (Material material : bom.getMaterials()) {
                if (material.getMaterialId() == 38 && material.getQuantity() <= 2 && material.getSize() <= order.getShed_length()) {
                    String current = material.getCarportPartDescription();
                    material.setCarportPartDescription(current + " (skur del)");
                }
            }
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