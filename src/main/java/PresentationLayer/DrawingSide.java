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
        int amountOfPosts = 0;
        int amountofPostsSide = amountOfPosts/2;

        double rafterWidth = 4.5;
        int underFasciaHeight = 20;
        double overFasciaheight = 12.5;
        int permanentYValue = 35;
        double widthWithRafterWidth = height + rafterWidth;
        double finalWidth = widthWithRafterWidth - permanentYValue;
        int sbr = calc.calcSpaceBetweenRafters(length);

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
        svg.addRamme(0,0,height,length);
        //stolper
        int testY = permanentYValue - 3;
        int hmm = permanentYValue + 3;
        int sbrTo = sbr / 2;
        double spaceBetweenStartAndPosts = sbr * 1.5;
        int finalPostsSpace = length - sbrTo;
        double test = finalPostsSpace - spaceBetweenStartAndPosts;
        double testTo = test / 2;
        double check = spaceBetweenStartAndPosts + testTo;

//        for (Material material : bom.getMaterials()) {
//            if (material.getCarportPartId() == 60 || material.getCarportPartId() == 36) {
//                amountOfPosts = material.getQuantity();
//                amountOfPosts = amountofPostsSide;
//                break;
//            }
//        }

        svg.addRect(spaceBetweenStartAndPosts,0,height,9.7);
        svg.addRect(finalPostsSpace,0,height,9.7);

        if (length > 300) {
            svg.addRect(check,0,height,9.7);
        }

        //understærn
        svg.addRect(0,0,underFasciaHeight,length);
        //overstærn
        svg.addRect(0,0,overFasciaheight,length);





        String svgFinalSide = svg.toString().replace(",", ".");
        session.setAttribute("svgdrawingside", svgFinalSide);
        return "carportplanside";
    }
}