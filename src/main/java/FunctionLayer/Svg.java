package FunctionLayer;

public class Svg {
    private double width;
    private double height;
    private String viewbox;
    private double x;
    private double y;
    private StringBuilder svg = new StringBuilder();

    private final String headerTemplate = "<svg version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" height=\"%s\" width=\"%s\" viewBox=\"%s\" preserveAspectRatio=\"xMinYMin\">";
    private final String defsTemplate =  "<defs> <marker id=\"beginArrow\" markerWidth=\"12\" markerHeight=\"12\" refX=\"0\" refY=\"6\" orient=\"auto\"> <path d=\"M0,6 L12,0 L12,12 L0,6\" style=\"fill: #000000;\" /> </marker> <marker id=\"endArrow\" markerWidth=\"12\" markerHeight=\"12\" refX=\"12\" refY=\"6\" orient=\"auto\"> <path d=\"M0,0 L12,6 L0,12 L0,0 \" style=\"fill: #000000;\" /> </marker> </defs>";
    private final String rectTemplate = "<rect x=\"%d\" y=\"%d\" height=\"%d\" width=\"%d\" style=\"stroke:#000000; fill: #ffffff\" />";
    private final String perfiratedBandTemplate = "<line x1=\"%d\" y1=\"%d\" x2=\"%d\" y2=\"%d\" style=\"stroke:#000000; stroke-dasharray: 5 5\" />";
    private final String lineTemplate = "<line x1=\"%d\" y1=\"%d\" x2=\"%d\" y2=\"%d\" style=\"stroke:#000000; marker-start: url(#beginArrow); marker-end: url(#endArrow);\" />";
    private final String textRotatedTemplate = "<text style=\"text-anchor: middle\" transform=\"translate(%s) rotate(-90)\">%s</text>";
    private final String textTemplate = "<text style=\"text-anchor: middle\" x=\"%d\" y=\"%d\">%s</text>";
    private final String rammeTemplate = "<rect x=\"%d\" y=\"%d\" height=\"%d\" width=\"%d\" style=\"stroke:none; fill: #ffffff\" />";



    public Svg(double width, double height, String viewbox, double x, double y) {
        this.width = width;
        this.height = height;
        this.viewbox = viewbox;
        this.x = x;
        this.y = y;
        svg.append(String.format(headerTemplate, height, width, viewbox));
    }

    public void addRamme(double x, double y, double height, double width) {
        svg.append(String.format(rammeTemplate,x,y,height,width));

    }

    public void addDefs() {
        svg.append(String.format(defsTemplate));
    }


    public void addRect(double x, double y, double height, double width){
        svg.append(String.format(rectTemplate, x, y, height, width));
    }

    public void addPerfiratedBand(double x1, double y1, double x2, double y2) {
        svg.append(String.format(perfiratedBandTemplate, x1, y1, x2, y2));
    }

    public void addLine(double x1, double y1, double x2, double y2) {
        svg.append(String.format(lineTemplate, x1, y1, x2, y2));
    }

    public void addTextRotated (String placement, String measurements  ) {
        svg.append(String.format(textRotatedTemplate, placement, measurements));
    }

    public void addText (double x, double y, String measurements) {
        svg.append(String.format(textTemplate, x, y, measurements));
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

