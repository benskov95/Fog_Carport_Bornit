package PresentationLayer;

import FunctionLayer.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class AddMaterialToDatabase extends Command {
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException, SQLException, ClassNotFoundException, OrderException, ServletException, IOException {

        HttpSession session = request.getSession();
        String[] newMaterialSizes = request.getParameterValues("newMaterialSizes");
        String[] newMaterialAmounts = request.getParameterValues("newMaterialAmounts");
        ArrayList<Size> chosenSizes = new ArrayList<>();
        ArrayList<Size> chosenAmounts = new ArrayList<>();

        String materialName = request.getParameter("newMaterialName");
        int unitId = Integer.parseInt(request.getParameter("newMaterialUnit"));
        int price;
        int materialId;

        if (unitId == 5) {
            request.setAttribute("addMaterialError", "Du skal vælge enhedstype.");
            return "addmaterialpage";
        }

        if (materialName.length() < 2) {
            request.setAttribute("addMaterialError", "Du skal skrive et navn");
            return "addmaterialpage";
        }

        try {
            price = Integer.parseInt(request.getParameter("newMaterialPrice"));
        } catch (Exception e) {
            request.setAttribute("addMaterialError", "Du skal skrive et tal i prisfeltet.");
            return "addmaterialpage";
        }


        if (newMaterialSizes != null && newMaterialAmounts != null) {

            if (newMaterialSizes[0].equals("0") && newMaterialAmounts[0].equals("0")) {
                request.setAttribute("addMaterialError", "Du skal vælge mindst én længde eller et antal til materialet.");
                return "addmaterialpage";
            }

            if (newMaterialSizes.length >= 1 && newMaterialAmounts.length >= 1 && !newMaterialSizes[0].equals("0") && !newMaterialAmounts[0].equals("0")) {
                request.setAttribute("addMaterialError", "Du skal vælge enten længder eller antal på materialet.");
                return "addmaterialpage";
            }

            if (newMaterialAmounts[0].equals("0") && newMaterialSizes.length >= 1) {
                for (String newMaterialSize : newMaterialSizes) {
                    String[] values = newMaterialSize.split(":");
                    int id = Integer.parseInt(values[0]);
                    int size = Integer.parseInt(values[1]);
                    chosenSizes.add(new Size(id, size));
                }
                Material material = new Material(materialName, unitId, price);
                materialId = MaterialFacade.insertMaterial(material, chosenSizes);
                request.setAttribute("successfullyAdded", "Materialet blev tilføjet til databasen. ID: " + materialId);
            }

            if (newMaterialSizes[0].equals("0") && newMaterialAmounts.length >= 1) {
                for (String newMaterialAmount : newMaterialAmounts) {
                    String[] values = newMaterialAmount.split(":");
                    int id = Integer.parseInt(values[0]);
                    int size = Integer.parseInt(values[1]);
                    chosenAmounts.add(new Size(id, size));
                }
                Material material = new Material(materialName, unitId, price);
                materialId = MaterialFacade.insertMaterial(material, chosenAmounts);
                request.setAttribute("successfullyAdded", "Materialet blev tilføjet til databasen. ID: " + materialId);
            }
        }

        return "addmaterialpage";
    }
}
