package FunctionLayer;

import java.util.Locale;

/**
 * The purpose of the Svg class is to provide templates and methods that makes it possible to draw
 * a model of the carport the customer is ordering.
 */

public class Svg {
    private double width;
    private double height;
    private String viewbox;
    private double x;
    private double y;
    private StringBuilder svg = new StringBuilder();

    private final String headerTemplate = "<svg version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\"x=\"%f\" y=\"%f\" height=\"%s\" width=\"%s\" viewBox=\"%s\" preserveAspectRatio=\"xMinYMin\">";
    private final String defsTemplate = "<defs> <marker id=\"beginArrow\" markerWidth=\"12\" markerHeight=\"12\" refX=\"0\" refY=\"6\" orient=\"auto\"> <path d=\"M0,6 L12,0 L12,12 L0,6\" style=\"fill: #000000;\" /> </marker> <marker id=\"endArrow\" markerWidth=\"12\" markerHeight=\"12\" refX=\"12\" refY=\"6\" orient=\"auto\"> <path d=\"M0,0 L12,6 L0,12 L0,0 \" style=\"fill: #000000;\" /> </marker> </defs>";
    private final String rectTemplate = "<rect x=\"%f\" y=\"%f\" height=\"%f\" width=\"%f\" style=\"stroke:#000000; fill: #ffffff\" />";
    private final String perfiratedBandTemplate = "<line x1=\"%f\" y1=\"%f\" x2=\"%f\" y2=\"%f\" style=\"stroke:#000000; stroke-dasharray: 5 5\" />";
    private final String lineTemplate = "<line x1=\"%f\" y1=\"%f\" x2=\"%f\" y2=\"%f\" style=\"stroke:#000000; marker-start: url(#beginArrow); marker-end: url(#endArrow);\" />";
    private final String textRotatedTemplate = "<text style=\"text-anchor: middle\" transform=\"translate(%s) rotate(-90)\">%s</text>";
    private final String textTemplate = "<text style=\"text-anchor: middle\" x=\"%f\" y=\"%f\">%s</text>";
    private final String rammeTemplate = "<rect x=\"%f\" y=\"%f\" height=\"%f\" width=\"%f\" style=\"stroke:none; fill: #ffffff\" />";
    private final String rectThickerLineTemplate = "<rect x=\"%f\" y=\"%f\" height=\"%f\" width=\"%f\" style=\"stroke:#000000; stroke-width: 2;stroke-dasharray: 5 5; fill: none\" />";
    private final String rectDeclineTemplate = "<rect x=\"%f\" y=\"%f\" height=\"%f\" width=\"%f\" style=\"stroke:#000000; fill: #ffffff\" transform=\"skewY(1)\" />";


    /**
     * This method is the constructor of the class, it builds the string that makes the "canvas" of a svg drawing.
     * @param width
     * How many pixels horizontally, x axis.
     * @param height
     * How many pixels vertically, y axis
     * @param viewbox
     * The viewbox makes it possible to scale photos without distortion.
     * @param x
     * @param y
     */
    public Svg(double width, double height, String viewbox, double x, double y) {
        this.width = width;
        this.height = height;
        this.viewbox = viewbox;
        this.x = x;
        this.y = y;
        svg.append(String.format(Locale.ROOT, headerTemplate, x, y, height, width, viewbox));
    }

    /**
     *This method builds a string for adding a rectangle with no outline to the svg.
     * @param x
     * Where on the x axis the rectangle should start
     * @param y
     * Where on the y axis the rectangle should start
     * @param height
     * The hight of the rectangle
     * @param width
     * the width of the rectangle
     */
    public void addRamme(double x, double y, double height, double width) {
        svg.append(String.format(Locale.ROOT, rammeTemplate, x, y, height, width));

    }

    /**
     * This method adds the definition of the arrow heads. To draw an arrow head and furthermore place it where
     * you want, you first need sort of a recipe for arrowheads. This method builds the string for that recipe.
     */
    public void addDefs() {
        svg.append(String.format(Locale.ROOT, defsTemplate));
    }

    /**
     *This method builds a string for adding a rectangle with an outline to the svg.
     * @param x
     * Where on the x axis the rectangle should start
     * @param y
     * Where on the y axis the rectangle should start
     * @param height
     * The hight of the rectangle
     * @param width
     * the width of the rectangle
     */
    public void addRect(double x, double y, double height, double width) {
        svg.append(String.format(Locale.ROOT, rectTemplate, x, y, height, width));
    }


    /**
     * It says thicker line, but in reality this method builds a string that adds a rectangle with
     * a stroke-dash outline to the svg.
     * @param x
     * Where on the x axis the rectangle should start
     * @param y
     * Where on the y axis the rectangle should start
     * @param height
     * The hight of the rectangle
     * @param width
     * the width of the rectangle
     */
    public void addRectThickerLine(double x, double y, double height, double width) {
        svg.append(String.format(Locale.ROOT, rectThickerLineTemplate, x, y, height, width));
    }

    /**
     * This method builds a string for adding a stroke-dash line to the svg, by having two x's and two y's
     * (one for start and one for end) you kan make the line go in either direction (horizontally, vertically, diagonally).
     * @param x1
     * Where on the x axis the line starts
     * @param y1
     * where on the y axis the line starts
     * @param x2
     * where on the x axis the line ends
     * @param y2
     * where on the y axis the line ends
     */
    public void addPerforatedBand(double x1, double y1, double x2, double y2) {
        svg.append(String.format(Locale.ROOT, perfiratedBandTemplate, x1, y1, x2, y2));
    }

    /**
     * This method builds a string for adding a line to the svg, by having two x's and two y's
     * (one for start and one for end) you kan make the line go in either direction (horizontally, vertically, diagonally).
     * @param x1
     * Where on the x axis the line starts
     * @param y1
     * where on the y axis the line starts
     * @param x2
     * where on the x axis the line ends
     * @param y2
     * where on the y axis the line ends
     */
    public void addLine(double x1, double y1, double x2, double y2) {
        svg.append(String.format(Locale.ROOT, lineTemplate, x1, y1, x2, y2));
    }

    /**
     * This method builds a string for adding a text to the vertical arrows we add for measurements.
     * @param placement
     * Where in the svg it is placed
     * @param measurements
     * The text f.eks 600 cm
     */
    public void addTextRotated(String placement, String measurements) {
        svg.append(String.format(Locale.ROOT, textRotatedTemplate, placement, measurements));
    }

    /**
     * This method builds a string for adding a text to the horizontal arrows we add for measurements.
     * @param x
     * Where on the x axis the text is placed
     * @param y
     * Where on the y axis the text is placed
     * @param measurements
     * The text f.eks 780 cm
     */
    public void addText(double x, double y, String measurements) {
        svg.append(String.format(Locale.ROOT, textTemplate, x, y, measurements));
    }

    /**
     * This method builds a string for adding a rectangle that has a little bit of decline as the roof of the carport has.
     * @param x
     * Where on the x axis the rectangle should start
     * @param y
     * Where on the y axis the rectangle should start
     * @param height
     * The hight of the rectangle
     * @param width
     * the width of the rectangle
     */
    public void addRectDecline(double x, double y, double height, double width) {
        svg.append(String.format(Locale.ROOT, rectDeclineTemplate, x, y, height, width));

    }

    /**
     * This method builds a string that makes it possible to add a svg drawing to another svg drawing.
     * @param drawing
     */
    public void addInnerDrawing(Svg drawing) {
        svg.append(drawing);
    }


    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getViewbox() {
        return viewbox;
    }

    public void setViewbox(String viewbox) {
        this.viewbox = viewbox;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }


    @Override
    public String toString() {
        return svg.toString() + "</svg>";
    }
}

