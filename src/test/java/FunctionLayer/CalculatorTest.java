package FunctionLayer;

import org.junit.Test;

import java.sql.SQLException;

public class CalculatorTest {

    private int carportLength = 780;
    private int carportWidth = 600;
    private int shedLength = 210;
    private int shedWidth = 540;
    Calculator calculator = new Calculator();

    @Test
    public void testType1Calc() throws SQLException, ClassNotFoundException {
        BillOfMaterials bom = calculator.type1Calc(carportLength, carportWidth);
        assert (bom.getMaterials().size() == 19);
    }

    @Test
    public void testType2Calc() throws SQLException, ClassNotFoundException {
        BillOfMaterials bom = calculator.type2Calc(carportLength, carportWidth, shedLength, shedWidth);
        assert (bom.getMaterials().size() == 29);
    }

    @Test
    public void testCalcOptimalLengthOfMaterial() throws SQLException, ClassNotFoundException {
        int res = calculator.calcOptimalLengthOfMaterial(shedWidth, 54);
        assert (res == 270);
    }

    @Test
    public void testCalcNumberOfCladdingPlanks() {
        int res = calculator.calcNumberOfCladdingPlanks(shedWidth, shedLength);
        assert (res == 200);
    }

    @Test
    public void testCalcLengthOfTrapezPlates() throws SQLException, ClassNotFoundException {
        int res = calculator.calcLengthOfTrapezPlates(carportLength, carportWidth, MaterialFacade.getMaterialLengths(69));
        assert (res == 8 && calculator.getPrimaryRoofPlateLength() == 600 && calculator.getSecondaryRoofPlateLength() == 240);
    }

    @Test
    public void testCalcSpaceBetweenRafters() {
        int res = calculator.calcSpaceBetweenRafters(carportLength);
        assert (res == 52);
    }

    @Test
    public void testCalcNumberOfPosts() {
        int res = calculator.calcNumberOfPosts(carportLength, carportWidth, shedLength, shedWidth);
        assert (res == 10);
    }
}
