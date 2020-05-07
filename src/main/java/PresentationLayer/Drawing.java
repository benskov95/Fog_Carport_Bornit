
package PresentationLayer;

import FunctionLayer.LoginSampleException;
import FunctionLayer.Svg;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Drawing extends Command {
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {
        //Tegning ytre del med piler og mål
        Svg svgOuterDrawing = new Svg(900,800,"0,0,900,800",0,0);
        //pilehodedefinisjon
        svgOuterDrawing.addDefs();
        //pile
        svgOuterDrawing.addLine(40, 10, 40, 610);
        svgOuterDrawing.addLine(75, 650, 855, 650);
        //txt
        svgOuterDrawing.addTextRotated("30,300", "600 cm");
        svgOuterDrawing.addText(502, 670, "780 cm");

       //Tegning indre del, selve Carport
        Svg svg = new Svg(800, 600, "0,0,800,600",75,10);

        //ramme
        svg.addRamme(0,0,600,780);
        //remme
        svg.addRect(0,35,4.5,780);
        svg.addRect(0,565,4.5,780);
        //spær --Loope igjennom?
        svg.addRect(0,0,600,4.5);
        svg.addRect(775.5,0,600,4.5);
        //hulbånd
        svg.addPerfiratedBand(55, 35, 600, 569.5);
        svg.addPerfiratedBand(55, 569.5, 600, 35);
        //stolper
        svg.addRect(110,32,9.7,9.7);
        svg.addRect(420,32,9.7,9.7);
        svg.addRect(730,32,9.7,9.7);
        svg.addRect(110,562,9.7,9.7);
        svg.addRect(420,562,9.7,9.7);
        svg.addRect(730,562,9.7,9.7);

        HttpSession session = request.getSession();
        String svgFinal = svg.toString().replace(",", ".");
        session.setAttribute("svgdrawing", svgFinal);
        return "carportplan";
    }
}