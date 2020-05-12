package PresentationLayer;

import FunctionLayer.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DrawingSide extends Command {
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {

        HttpSession session = request.getSession();

        Calculator calc = new Calculator();
        Order order = (Order) session.getAttribute("order");
        BillOfMaterials bom = (BillOfMaterials) session.getAttribute("bom");

        int height = 210;
        int length = order.getCarport_length();
        int arrowTextY = height/2;
        int arrowTextX = (length/2) + 75;

        double rafterWidth = 4.5;
        int underFasciaHeight = 20;
        double overFasciaheight = 12.5;
        double postWidth = 9.7;
        int permanentYValue = 35;
        double widthWithRafterWidth = height + rafterWidth;
        int sbr = calc.calcSpaceBetweenRafters(length);
        int shedWidth = order.getShed_width();
        int shedLength = order.getShed_length();
        double beamHeight = 19.5;


        //Tegning ytre del med piler og mål
        Svg svgOuterDrawing = new Svg(1000,1000,"0,0,1000,1000",0,0);
        //pilehodedefinisjon
        svgOuterDrawing.addDefs();
        //pile
        svgOuterDrawing.addLine(30, 10, 30, height+10);
        svgOuterDrawing.addLine(75, height+60, length+75, height+60);
        //txt
        svgOuterDrawing.addTextRotated("15, " + arrowTextY, height + " cm");
        svgOuterDrawing.addText(arrowTextX, height+85, length + " cm");

        //Tegning indre del, selve Carport
        Svg svg = new Svg(800, 210, "0,0,780,210",75,10);

        //ramme
        svg.addRamme(0,0,height,length);
        //stolper

        int sbrTo = sbr / 2;
        double spaceBetweenStartAndPosts = sbr * 1.5;
        int finalPostsSpace = length - sbrTo;
        double test = finalPostsSpace - spaceBetweenStartAndPosts;
        double testTo = test / 2;
        double check = spaceBetweenStartAndPosts + testTo;


        svg.addRect(spaceBetweenStartAndPosts+5,0,height,postWidth);
        svg.addRect(finalPostsSpace+5,0,height,postWidth);

        if (length > 300) {
            svg.addRect(check+5,0,height,postWidth);
        }

        //understærn
        svg.addRect(2.5,0,underFasciaHeight,length+2.5);
        //overstærn
        svg.addRect(0,0,overFasciaheight,length+5);

        //remme
        svg.addRect(5,underFasciaHeight,beamHeight, length);


        //skur
        if (shedWidth != 0) {

        }


        svgOuterDrawing.addInnerDrawing(svg);



        session.setAttribute("svgdrawingside", svgOuterDrawing.toString());
        return "carportplanside";
    }
}