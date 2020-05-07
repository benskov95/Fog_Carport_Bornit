
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
        int amountOfPosts = 0;
        int temp = 0;
        int placementOfThirdBeam = width / 2;

        double rafterWidth = 4.5;
        int permanentYValue = 35;
        double widthWithRafterWidth = width + rafterWidth;
        double finalWidth = widthWithRafterWidth - permanentYValue;
        int sbr = calc.calcSpaceBetweenRafters(length);

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




        Svg svg = new Svg(800, 800, "0,0,800,800",75,10);

        //ramme
        svg.addRamme(0,0,width,length);
        //remme
        svg.addRect(0,35,4.5,length);
        svg.addRect(0,width-35,4.5,length);


        if (width > 600) {
            svg.addRect(0, placementOfThirdBeam, 4.5, length);
        }
        //spær --Loope igjennom?


        for (Material material : bom.getMaterials()) {
            if (material.getCarportPartId() == 59 || material.getCarportPartId() == 35){
                amountOfRafters = material.getQuantity();
                break;
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
        svg.addPerfiratedBand(sbr, 35, length-sbr, finalWidth);
        svg.addPerfiratedBand(sbr, finalWidth, length-sbr, 35);

        //stolper
        for (Material material : bom.getMaterials()) {
            if (material.getCarportPartId() == 60 || material.getCarportPartId() == 36) {
                amountOfPosts = material.getQuantity();
                break;
            }
        }

        int yForPosts = permanentYValue - 3;
        int yForPostsWidth = permanentYValue + 3;
        int widthForPosts = width - yForPostsWidth;
        int sbrTo = sbr / 2;
        double spaceBetweenStartAndPosts = sbr * 1.5;
        int finalPostsSpace = length - sbrTo;
        double test = finalPostsSpace - spaceBetweenStartAndPosts;
        double testTo = test / 2;
        double check = spaceBetweenStartAndPosts + testTo;

        svg.addRect(spaceBetweenStartAndPosts,yForPosts,9.7,9.7);
        svg.addRect(spaceBetweenStartAndPosts,widthForPosts,9.7,9.7);
        svg.addRect(finalPostsSpace,yForPosts,9.7,9.7);
        svg.addRect(finalPostsSpace,widthForPosts,9.7,9.7);

        amountOfPosts -= 4;

        if (width > 600) {
            svg.addRect(spaceBetweenStartAndPosts, placementOfThirdBeam,9.7,9.7);
            svg.addRect(finalPostsSpace, placementOfThirdBeam,9.7,9.7);
            amountOfPosts -= 2;
        }

        while (amountOfPosts != 0) {

        svg.addRect(check,yForPosts,9.7,9.7);
        svg.addRect(check,widthForPosts,9.7,9.7);
        amountOfPosts--;

        }




        String svgFinal = svg.toString().replace(",", ".");
        session.setAttribute("svgdrawing", svgFinal);
        return "carportplan";
    }
}