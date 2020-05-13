package FunctionLayer;

import java.util.Locale;

public class Svg {
    private double width;
    private double height;
    private String viewbox;
    private double x;
    private double y;
    private StringBuilder svg = new StringBuilder();

    private final String headerTemplate = "<svg version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\"x=\"%f\" y=\"%f\" height=\"%s\" width=\"%s\" viewBox=\"%s\" preserveAspectRatio=\"xMinYMin\">";
    private final String defsTemplate =  "<defs> <marker id=\"beginArrow\" markerWidth=\"12\" markerHeight=\"12\" refX=\"0\" refY=\"6\" orient=\"auto\"> <path d=\"M0,6 L12,0 L12,12 L0,6\" style=\"fill: #000000;\" /> </marker> <marker id=\"endArrow\" markerWidth=\"12\" markerHeight=\"12\" refX=\"12\" refY=\"6\" orient=\"auto\"> <path d=\"M0,0 L12,6 L0,12 L0,0 \" style=\"fill: #000000;\" /> </marker> </defs>";
    private final String rectTemplate = "<rect x=\"%f\" y=\"%f\" height=\"%f\" width=\"%f\" style=\"stroke:#000000; fill: #ffffff\" />";
    private final String perfiratedBandTemplate = "<line x1=\"%f\" y1=\"%f\" x2=\"%f\" y2=\"%f\" style=\"stroke:#000000; stroke-dasharray: 5 5\" />";
    private final String lineTemplate = "<line x1=\"%f\" y1=\"%f\" x2=\"%f\" y2=\"%f\" style=\"stroke:#000000; marker-start: url(#beginArrow); marker-end: url(#endArrow);\" />";
    private final String textRotatedTemplate = "<text style=\"text-anchor: middle\" transform=\"translate(%s) rotate(-90)\">%s</text>";
    private final String textTemplate = "<text style=\"text-anchor: middle\" x=\"%f\" y=\"%f\">%s</text>";
    private final String rammeTemplate = "<rect x=\"%f\" y=\"%f\" height=\"%f\" width=\"%f\" style=\"stroke:none; fill: #ffffff\" />";
    private final String rectThickerLineTemplate = "<rect x=\"%f\" y=\"%f\" height=\"%f\" width=\"%f\" style=\"stroke:#000000; stroke-width: 2;stroke-dasharray: 5 5; fill: #ffffff\" />";



    public Svg(double width, double height, String viewbox, double x, double y) {
        this.width = width;
        this.height = height;
        this.viewbox = viewbox;
        this.x = x;
        this.y = y;
        svg.append(String.format(Locale.ROOT,headerTemplate,x, y, height, width, viewbox));
    }


    public void addRamme(double x, double y, double height, double width) {
        svg.append(String.format(Locale.ROOT, rammeTemplate,x,y,height,width));

    }

    public void addDefs() {
        svg.append(String.format(Locale.ROOT, defsTemplate));
    }


    public void addRect(double x, double y, double height, double width){
        svg.append(String.format(Locale.ROOT,rectTemplate, x, y, height, width));
    }

    public void addRectThickerLine(double x, double y, double height, double width){
        svg.append(String.format(Locale.ROOT, rectThickerLineTemplate, x, y, height, width));
    }

    public void addPerforatedBand(double x1, double y1, double x2, double y2) {
        svg.append(String.format(Locale.ROOT,perfiratedBandTemplate, x1, y1, x2, y2));
    }

    public void addLine(double x1, double y1, double x2, double y2) {
        svg.append(String.format(Locale.ROOT,lineTemplate, x1, y1, x2, y2));
    }

    public void addTextRotated (String placement, String measurements  ) {
        svg.append(String.format(Locale.ROOT,textRotatedTemplate, placement, measurements));
    }

    public void addText (double x, double y, String measurements) {
        svg.append(String.format(Locale.ROOT,textTemplate, x, y, measurements));
    }
    public void addInnerDrawing (Svg drawing){
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
        return svg.toString() + "</svg>" ;
    }
}

