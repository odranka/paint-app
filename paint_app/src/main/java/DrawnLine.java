import java.awt.*;
import java.util.ArrayList;

/*
 * The PolyLine class model a line made up of many points
 */
class DrawnLine implements java.io.Serializable{
    private ArrayList<Integer> xList;
    private ArrayList<Integer> yList;
    private Color color;
    private Integer thickness;

    // Constructor
    public DrawnLine() {
        xList = new ArrayList<>();
        yList = new ArrayList<>();
    }

    // Add a point
    public void addPoint(int x, int y) {
        xList.add(x);
        yList.add(y);
    }

    public void removePoints(int fromPosn) {
        xList.subList(fromPosn, xList.size()).clear();
        yList.subList(fromPosn, xList.size()).clear();
    }
    // Add a color to this line
    public void setLineColor(Color c) {
        color = c;
    }
    public Color getLineColor() {
        return color;
    }
    public void setLineThickness(Integer t){
        thickness = t;
    }
    public Integer getLineThickness(){
        return thickness;
    }
    public Integer getNumLinePoints(){
        return xList.size();
    }

    // Paints itself given the Graphics context
    public void draw(Graphics2D g2) { // draw itself
        g2.setColor(getLineColor());
        g2.setStroke(new BasicStroke(getLineThickness(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
        for (int i = 0; i < xList.size()-1; i++) {
            g2.drawLine(xList.get(i), yList.get(i), xList.get(i + 1),
                    yList.get(i + 1));
        }
    }
    // Paints itself given the Graphics context
    public void draw(Graphics2D g2, Integer numPoints) { // draw itself to a specific point
        g2.setColor(getLineColor());
        g2.setStroke(new BasicStroke(getLineThickness(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
        for (int i = 0; i < numPoints-1; i++) {
            g2.drawLine(xList.get(i), yList.get(i), xList.get(i + 1),
                    yList.get(i + 1));
        }
    }
}