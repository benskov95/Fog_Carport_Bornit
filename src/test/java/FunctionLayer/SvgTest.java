package FunctionLayer;

import org.junit.Before;
import org.junit.Test;

import java.util.Locale;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class SvgTest {

    Svg svg;

    @Before
    public void setUp() throws Exception {
        svg = new Svg(800, 600, "0,0,800,600", 0,0);
    }

    @Test
    public void addRect01() {
        svg.addRect(0,0,100,100);
        String expected = "<svg version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\"x=\"0.000000\" y=\"0.000000\" height=\"600.0\" width=\"800.0\" viewBox=\"0,0,800,600\" preserveAspectRatio=\"xMinYMin\"><rect x=\"0.000000\" y=\"0.000000\" height=\"100.000000\" width=\"100.000000\" style=\"stroke:#000000; fill: #ffffff\" /></svg>";
        assertEquals(svg.toString(),expected);
    }

    @Test
    public void addRect02() {
        svg.addRect(0,0,99,99);
        String expected = "<rect x=\"0.000000\" y=\"0.000000\" height=\"99.000000\" width=\"99.000000\" style=\"stroke:#000000; fill: #ffffff\" />";
        assertThat(svg.toString(), containsString(expected));
    }

    @Test
    public void addDefs() {
        svg.addDefs();
        String expected = "<defs> <marker id=\"beginArrow\" markerWidth=\"12\" markerHeight=\"12\" refX=\"0\" refY=\"6\" orient=\"auto\"> <path d=\"M0,6 L12,0 L12,12 L0,6\" style=\"fill: #000000;\" /> </marker> <marker id=\"endArrow\" markerWidth=\"12\" markerHeight=\"12\" refX=\"12\" refY=\"6\" orient=\"auto\"> <path d=\"M0,0 L12,6 L0,12 L0,0 \" style=\"fill: #000000;\" /> </marker> </defs>";
        assertThat(svg.toString(), containsString(expected));
    }

    @Test
    public void addPerforatedBand() {
        svg.addPerforatedBand(0,0,10,10);
        String expected = "<line x1=\"0.000000\" y1=\"0.000000\" x2=\"10.000000\" y2=\"10.000000\" style=\"stroke:#000000; stroke-dasharray: 5 5\" />";
        assertThat(svg.toString(), containsString(expected));

    }


    @Test
    public void addTextRotated() {
        String placement = "10";
        String measurement = "10 cm";
        svg.addTextRotated(placement, measurement);
        String expected = "<text style=\"text-anchor: middle\" transform=\"translate(10) rotate(-90)\">10 cm</text>";
        assertThat(svg.toString(), containsString(expected));

    }

    @Test
    public void addText() {
        String measurement = "10 cm";
        svg.addText(10, 10, measurement);
        String expected = "<text style=\"text-anchor: middle\" x=\"10.000000\" y=\"10.000000\">10 cm</text>";
        assertThat(svg.toString(), containsString(expected));

    }

    @Test
    public void addRectDecline() {
        svg.addRectDecline(10, 10,5 ,60);
        String expected = "<rect x=\"10.000000\" y=\"10.000000\" height=\"5.000000\" width=\"60.000000\" style=\"stroke:#000000; fill: #ffffff\" transform=\"skewY(1)\" />";
        assertThat(svg.toString(), containsString(expected));

    }

    @Test
    public void addInnerDrawing() {
        Svg drawing = new Svg(800,300,"0,0,800,300",10,30);
        svg.addInnerDrawing(drawing);
        String expected = "<svg version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\"x=\"10.000000\" y=\"30.000000\" height=\"300.0\" width=\"800.0\" viewBox=\"0,0,800,300\" preserveAspectRatio=\"xMinYMin\">";
        assertThat(svg.toString(), containsString(expected));

    }
}
