import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class CanvasView extends JPanel implements Observer{

    private ArrayList<DrawnLine> lines = new ArrayList<>();
    private DrawnLine current;
    Graphics2D g2;

    private Model model;
    /**
     * Create a new View.
     */
    public CanvasView(Model model) {
        super();
        this.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){
                removePoints();
                model.setPaintSlider(false);
                current = new DrawnLine();
                lines.add(current);
                current.addPoint(e.getX(), e.getY());
                current.setLineColor(model.getColor());
                current.setLineThickness(model.getLineThickness());
            }
            public void mouseReleased(MouseEvent e){
                model.setTotalNumPoints(getTotalNumPoints());
                model.setSliderPosn(getTotalNumPoints());
                repaint();
            }
        });

        this.addMouseMotionListener(new MouseAdapter(){
            public void mouseDragged(MouseEvent e){
                current.addPoint(e.getX(), e.getY());
                repaint();
            }
        });

        // Hook up this observer so that it will be notified when the model
        // changes.

        this.model = model;
        model.addObserver(this);

        setVisible(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // cast to get 2D drawing methods
        g2 = (Graphics2D) g;

        if(!model.getPaintSlider()){
            for(DrawnLine line: lines){
                line.draw(g2);
            }
        }
        else{
            Integer pointsToDraw = model.getSliderPosn();
            Integer countPointsDrawn = 0;
            for(DrawnLine line: lines){
                if(countPointsDrawn < pointsToDraw) {
                    if(countPointsDrawn + line.getNumLinePoints() < pointsToDraw){
                        line.draw(g2);
                        countPointsDrawn += line.getNumLinePoints();
                    }
                    else{
                        line.draw(g2, pointsToDraw-countPointsDrawn);
                        countPointsDrawn += pointsToDraw-countPointsDrawn;
                    }
                }
            }
        }
    }

    public  void addLines(DrawnLine drawnLine){
        model.setPaintSlider(false);
        lines.add(drawnLine);
        repaint();
        model.setTotalNumPoints(getTotalNumPoints());
        model.setSliderPosn(getTotalNumPoints());
    }

    public ArrayList<DrawnLine> getLines() {
        return lines;
    }

    public Integer getTotalNumPoints(){
        Integer num=0;
        for(DrawnLine line: lines){
           num += line.getNumLinePoints();
        }
        return num;
    }

    public void clearPage(){
        lines = new ArrayList<>();
        repaint();
    }

    public void removePoints(){
        Integer pointsToKeep = model.getSliderPosn();
        Integer countPoints = 0;
        for(int i = 0; i < lines.size(); i++){
            if(countPoints < pointsToKeep) {
                if(countPoints + lines.get(i).getNumLinePoints() <= pointsToKeep){
                    countPoints += lines.get(i).getNumLinePoints();
                }
                else{
                    //remove the points in the line we no longer need
                    for(int j = 0; j < lines.get(i).getNumLinePoints(); j++){
                        if(j >= pointsToKeep-countPoints){
                            lines.get(i).removePoints(j);
                            break;
                        }
                    }
                    countPoints += pointsToKeep-countPoints;
                }
            }
            else{
                //remove the rest of the lines
                lines.subList(i, lines.size()).clear();
                break;
            }

        }
    }

    /**
     * Update with data from the model.
     */
    public void update(Object observable) {
        repaint();
    }

}


