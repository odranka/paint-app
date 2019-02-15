
import java.awt.*;
import java.util.*;
import java.util.List;

public class Model {
    /** The observers that are watching this model for changes. */
    private List<Observer> observers;
    private Color c = Color.BLACK;
    private Integer lineThickness = 3;
    private Integer totalNumPoints = 0;
    private Integer sliderPosn = 0;
    Boolean paintSlider = false;

    /**
     * Create a new model.
     */
    public Model() {
        this.observers = new ArrayList();
    }

    /**
     * Add an observer to be notified when this model changes.
     */
    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }

    /**
     * Remove an observer from this model.
     */
    public void removeObserver(Observer observer) {
        this.observers.remove(observer);
    }

    public void setColor(Color color){
        this.c = color;
        notifyObservers();
    }
    public Color getColor(){
        return this.c;
    }
    public void setLineThickness(Integer thickness){
        lineThickness = thickness;
    }

    public Integer getLineThickness() {
        return lineThickness;
    }

    public void setTotalNumPoints(Integer num){
        totalNumPoints = num;
        notifyObservers();
    }
    public Integer getTotalNumPoints(){
        return totalNumPoints;
    }
    public void setSliderPosn(Integer num){
        sliderPosn = num;
        notifyObservers();
    }
    public Integer getSliderPosn(){
        return sliderPosn;
    }
    public void setPaintSlider(Boolean b){
        paintSlider = b;
        notifyObservers();
    }
    public Boolean getPaintSlider(){
        return paintSlider;
    }



    /**
     * Notify all observers that the model has changed.
     */
    public void notifyObservers() {
        for (Observer observer: this.observers) {
            observer.update(this);
        }
    }
}
