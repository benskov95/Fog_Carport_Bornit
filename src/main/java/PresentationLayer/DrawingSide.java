package PresentationLayer;

import FunctionLayer.LoginSampleException;
import FunctionLayer.Svg;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DrawingSide extends Command {
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {

        //Tegning ytre del med piler og mål
        Svg svgOuterDrawing = new Svg(900,400,"0,0,900,400",0,0);
        //pilehodedefinisjon
        svgOuterDrawing.addDefs();
        //pile
        svgOuterDrawing.addLine(40, 10, 40, 220);
        svgOuterDrawing.addLine(75, 260, 855, 260);
        //txt
        svgOuterDrawing.addTextRotated("30,105", "210 cm");
        svgOuterDrawing.addText(502, 670, "780 cm");

        //Tegning indre del, selve Carport
        Svg svg = new Svg(800, 210, "0,0,780,210",75,10);

        //ramme
        svg.addRamme(0,0,210,780);
        //understærn
        svg.addRect(0,0,20,780);
        //overstærn
        svg.addRect(0,0,12.5,780);
        //stolper
        svg.addRect(55,0,210,9.7);
        svg.addRect(420,0,210,9.7);
        svg.addRect(730,0,210,9.7);

        request.setAttribute("svgdrawing", svg.toString());
        return "drawing";
    }
}
