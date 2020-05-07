
package PresentationLayer;

import FunctionLayer.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Drawing extends Command {
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {

        HttpSession session = request.getSession();

        Calculator calc = new Calculator();
        Order order = (Order) session.getAttribute("order");
        BillOfMaterials bom = (BillOfMaterials) session.getAttribute("bom");

        int width = order.getCarport_width();
        int length = order.getCarport_length();
        int amountOfRafters = 0;
        int temp = 0;
        //Tegning ytre del med piler og mål
        Svg svgOuterDrawing = new Svg(900,800,"0,0,900,800",0,0);
        //pilehodedefinisjon
        svgOuterDrawing.addDefs();
        //pile
        svgOuterDrawing.addLine(40, 10, 40, 610);
        svgOuterDrawing.addLine(75, 650, 855, 650);
        //txt
        svgOuterDrawing.addTextRotated("30,300", width + " cm");
        svgOuterDrawing.addText(502, 670, length + " cm");

       //Tegning indre del, selve Carport




        Svg svg = new Svg(800, 600, "0,0,800,600",75,10);

        //ramme
        svg.addRamme(0,0,width,length);
        //remme
        svg.addRect(0,35,4.5,length);
        svg.addRect(0,565,4.5,length);
        //spær --Loope igjennom?

        int sbr = calc.calcSpaceBetweenRafters(length);
        for (Material material : bom.getMaterials()) {
            if (material.getCarportPartId() == 59 || material.getCarportPartId() == 35){
                amountOfRafters = material.getQuantity();
            }

        }
        svg.addRect(0,0,width,4.5);
        while (amountOfRafters > 1){
            temp += sbr;
            svg.addRect(temp,0,width,4.5);
            amountOfRafters--;
        }
//        svg.addRect(length-4.5,0,width,4.5);


        //hulbånd
        svg.addPerfiratedBand(sbr, length-sbr, 210, 569.5);
        svg.addPerfiratedBand(sbr, 569.5, 210, length-sbr);
        //stolper
        svg.addRect(110,32,9.7,9.7);
        svg.addRect(420,32,9.7,9.7);
        svg.addRect(730,32,9.7,9.7);
        svg.addRect(110,562,9.7,9.7);
        svg.addRect(420,562,9.7,9.7);
        svg.addRect(730,562,9.7,9.7);




        String svgFinal = svg.toString().replace(",", ".");
        session.setAttribute("svgdrawing", svgFinal);
        return "carportplan";
    }
}