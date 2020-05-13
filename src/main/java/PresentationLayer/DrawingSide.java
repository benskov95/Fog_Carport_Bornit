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

        int carportHeight = 210;
        int length = order.getCarport_length();
        int arrowTextY = carportHeight/2;
        int arrowTextX = (length/2) + 75;
        double offset = 4.5;

        double rafterWidth = 4.5;
        int underFasciaHeight = 20;
        double overFasciaheight = 12.5;
        double postWidth = 9.7;
        int permanentYValue = 35;
        double widthWithRafterWidth = carportHeight + rafterWidth;
        int sbr = calc.calcSpaceBetweenRafters(length);
        int shedWidth = order.getShed_width();
        int shedLength = order.getShed_length();
        double beamHeight = 19.5;
        double fasciaWidth = 2.5;
        double fasciaWidthTo = fasciaWidth*2;


        //Tegning ytre del med piler og mål
        Svg svgOuterDrawing = new Svg(1000,1000,"0,0,1000,1000",0,0);
        //pilehodedefinisjon
        svgOuterDrawing.addDefs();
        //pile
        svgOuterDrawing.addLine(30, 10, 30, carportHeight+10);
        svgOuterDrawing.addLine(75, carportHeight+60, length+75, carportHeight+60);
        //txt
        svgOuterDrawing.addTextRotated("15, " + arrowTextY, carportHeight + " cm");
        svgOuterDrawing.addText(arrowTextX, carportHeight+85, length + " cm");

        //Tegning indre del, selve Carport
        Svg svg = new Svg(800, carportHeight, "0,0,780,210",75,10);

        //ramme
        svg.addRamme(0,0,carportHeight,length);
        //stolper

        int sbrTo = sbr / 2;
        double spaceBetweenStartAndPosts = sbr * 1.5;
        int finalPostsSpace = length - sbrTo;
        double test = finalPostsSpace - spaceBetweenStartAndPosts;
        double testTo = test / 2;
        double check = spaceBetweenStartAndPosts + testTo;


        svg.addRect(spaceBetweenStartAndPosts+fasciaWidthTo,0,carportHeight,postWidth);
        svg.addRect(finalPostsSpace+fasciaWidthTo,0,carportHeight,postWidth);

        if (length > 300) {
            svg.addRect(check+fasciaWidthTo,0,carportHeight,postWidth);
        }

        //understærn
        svg.addRect(fasciaWidth,0,underFasciaHeight,length+fasciaWidth);
        //overstærn
        svg.addRect(0,0,overFasciaheight,length+fasciaWidthTo);

        //remme
        svg.addRect(fasciaWidthTo,underFasciaHeight,beamHeight, length);


        //skur

        if (shedWidth != 0) {

           int numberOfCladdingPlanks = calcNumberOfCladdingPlanks(shedLength);
           int underPlank = (int) Math.ceil( numberOfCladdingPlanks/2);
           int overPlank = (int) Math.floor(numberOfCladdingPlanks/2);
           double firstUnderPlankX = finalPostsSpace+offset;
           double firstOverPlankX = finalPostsSpace- 2.5;
           double firstPlankY = underFasciaHeight;
           double plankWidth = 10;
           double plankHeight = carportHeight-underFasciaHeight;

            for (int i = 0; i < underPlank ; i++) {

           svg.addRect(firstUnderPlankX,firstPlankY,plankHeight,plankWidth );
           firstUnderPlankX -=15;
            }

            for (int i = 0; i < overPlank ; i++) {

                svg.addRect(firstOverPlankX,firstPlankY,plankHeight,plankWidth );
                firstOverPlankX -=15;
            }





            }





        svgOuterDrawing.addInnerDrawing(svg);



        session.setAttribute("svgdrawingside", svgOuterDrawing.toString());
        return "carportplanside";
    }
    public static int calcNumberOfCladdingPlanks(int shedLength){
        double boardWidthWithOverlap = 7.5;
        int circumference =  shedLength;
        return (int) Math.ceil(circumference / boardWidthWithOverlap);
    }
}