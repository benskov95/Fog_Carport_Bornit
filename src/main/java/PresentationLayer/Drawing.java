
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
        int arrowTextX = (length/2) + 75;
        int shedWidth = order.getShed_width();
        int shedLength = order.getShed_length();

        double rafterWoodWidth = 4.5;
        int eaves = 35; // udhæng
        double postWidth = 9.7;
        double postLength = 9.7;

        double widthWithRafterWidth = width + rafterWoodWidth;
        double finalWidth = widthWithRafterWidth - eaves;
        int sbr = calc.calcSpaceBetweenRafters(length);
        int yForPosts = eaves - 3;
        int yForPostsWidth = eaves + 3;
        int widthForPosts = width - yForPostsWidth;
        int sbrTo = sbr / 2;
        double spaceBetweenStartAndPosts = sbr * 1.5;
        int finalPostsSpace = length - sbrTo;
        double tempForAdditionalPosts = finalPostsSpace - spaceBetweenStartAndPosts;
        double secondTemp = tempForAdditionalPosts / 2;
        double xForAdditionalPosts = spaceBetweenStartAndPosts + secondTemp;
        double shedStart = (finalPostsSpace+postWidth)-shedLength;

        //Tegning ytre del med piler og mål
        Svg svgOuterDrawing = new Svg(1000,1000,"0,0,1000,1000",0,0);
        //pilehodedefinisjon
        svgOuterDrawing.addDefs();
        //pile
        svgOuterDrawing.addLine(30, 10, 30, width);
        svgOuterDrawing.addLine(75, width+60, length+75, width+60);
        //txt
        svgOuterDrawing.addTextRotated("15, " + placementOfThirdBeam, width + " cm");
        svgOuterDrawing.addText(arrowTextX, width+85, length + " cm");

       //Tegning indre del, selve Carport


        Svg svg = new Svg(800, 800, "0,0,800,800",75,10);

        //ramme
        svg.addRamme(0,0,width,length);
        //remme
        svg.addRect(0,eaves,rafterWoodWidth,length);
        svg.addRect(0,width-eaves,rafterWoodWidth,length);


        //spær --Loope igjennom?

        for (Material material : bom.getMaterials()) {
            if (material.getCarportPartId() == 59 || material.getCarportPartId() == 35){
                amountOfRafters = material.getQuantity();
                break;
            }

        }

        svg.addRect(0,0,width,rafterWoodWidth);

        while (amountOfRafters > 1){
            temp += sbr;
            svg.addRect(temp,0,width,rafterWoodWidth);
            amountOfRafters--;
        }


        //hulbånd

        if(shedWidth != 0) {

            svg.addPerforatedBand(sbr, eaves, shedStart, finalWidth);
            svg.addPerforatedBand(sbr, finalWidth, shedStart, eaves);

        }
        else {
            svg.addPerforatedBand(sbr, eaves, length - sbr, finalWidth);
            svg.addPerforatedBand(sbr, finalWidth, length - sbr, eaves);
        }
        //stolper
        for (Material material : bom.getMaterials()) {
            if (material.getCarportPartId() == 60 || material.getCarportPartId() == 36) {
                amountOfPosts = material.getQuantity();
                break;
            }
        }



        svg.addRect(spaceBetweenStartAndPosts,yForPosts,postLength,postWidth);
        svg.addRect(spaceBetweenStartAndPosts,widthForPosts,postLength,postWidth);
        svg.addRect(finalPostsSpace,yForPosts,postLength,postWidth);
        svg.addRect(finalPostsSpace,widthForPosts,postLength,postWidth);

        amountOfPosts -= 4;

        if (width > 600) {
            svg.addRect(0, placementOfThirdBeam, rafterWoodWidth, length); // En tredje rem tilføjes hvis bredden på carporten er større end 6 meter.
            svg.addRect(spaceBetweenStartAndPosts, placementOfThirdBeam - 3,postLength,postWidth); // Har tilføjet -3 til placementOfThirdBeam fordi stolperne ikke ligger lige på remmen ellers.
            svg.addRect(finalPostsSpace, placementOfThirdBeam - 3,postLength,postWidth);
            amountOfPosts -= 2;
        }

        // Nu kan der tages højde for, hvis der skal tilføjes endnu flere stolper
        // (dvs. hvis carporten er længere end makslængden (780). Får nok ikke brug
        // for det, men så er funktionaliteten der da hvis man nogensinde udvider længevalget.

        // Man ville så også skulle ændre bredden på viewboxen for at kunne se dem.

        while (amountOfPosts != 0) {

        svg.addRect(xForAdditionalPosts,yForPosts,postLength,postWidth);
        svg.addRect(xForAdditionalPosts,widthForPosts,postLength,postWidth);
        xForAdditionalPosts += finalPostsSpace;
        amountOfPosts -= 2;

        }

        double yForShedPostLowerCorners = eaves+shedWidth-3-postWidth;
        double yForShedMiddlePosts = (shedWidth + eaves) / 2;

//        if(shedWidth != 0) {
            // Ramme for skuret
            svg.addRectNoFill(shedStart, yForPosts, shedWidth, shedLength);

            //stolper til skur
            svg.addRect(finalPostsSpace,yForPosts,postLength,postWidth);
            svg.addRect(finalPostsSpace,yForShedPostLowerCorners, postLength,postWidth);
            svg.addRect(shedStart, yForPosts, postLength, postWidth);
            svg.addRect(shedStart, yForShedPostLowerCorners, postLength, postWidth);
            if (shedWidth > 300) {
                svg.addRect(shedStart, yForShedMiddlePosts, postLength, postWidth);
                svg.addRect(finalPostsSpace, yForShedMiddlePosts, postLength, postLength);
            }
//        }

        svgOuterDrawing.addInnerDrawing(svg);

        session.setAttribute("svgdrawing", svgOuterDrawing.toString());
        return "carportplan";
  }
}