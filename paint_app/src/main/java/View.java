import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class View extends JFrame implements Observer {

    private Model model;

    /**
     * Create a new View.
     */
    public View(Model model) {
        // Set up the window.
        this.setTitle("Sketch!");
        this.setMinimumSize(new Dimension(400, 550));
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Hook up this observer so that it will be notified when the model
        // changes.
        this.model = model;
        model.addObserver(this);

        CanvasView canvasView = new CanvasView(model);
        model.addObserver(canvasView);

        NavigationView navigationView = new NavigationView(model, canvasView);
        model.addObserver(navigationView);

        JPanel panel = new JPanel(new GridLayout(2, 1));

        ColourView colourView = new ColourView(model);
        model.addObserver(colourView);
        ThicknessView thicknessView = new ThicknessView(model);
        model.addObserver(thicknessView);

        panel.add(colourView);
        panel.add(thicknessView);

        TimelineView timelineView = new TimelineView(model);
        model.addObserver(timelineView);

        // create a layout
        Container container = this.getContentPane();
        container.setLayout(new BorderLayout());
        container.add(canvasView, BorderLayout.CENTER);
        container.add(panel, BorderLayout.WEST);
        container.add(timelineView, BorderLayout.SOUTH);
        container.add(navigationView, BorderLayout.NORTH);

        setVisible(true);

        this.addComponentListener(new ComponentAdapter(){
            public void componentResized(ComponentEvent e){
                Dimension d=View.this.getSize();
                Dimension minD=View.this.getMinimumSize();
                if(d.width<minD.width)
                    d.width=minD.width;
                if(d.height<minD.height)
                    d.height=minD.height;
                View.this.setSize(d);
            }
        });

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                if(canvasView.getLines().size() > 0){
                    int returnVal=JOptionPane.showConfirmDialog(View.this,
                            "Would you like to save this file?", "Save File", JOptionPane.YES_NO_CANCEL_OPTION);
                    if(returnVal==JOptionPane.CANCEL_OPTION) {
                        return;
                    }
                    else if(returnVal==JOptionPane.YES_OPTION) {
                        navigationView.saveFile();
                        System.exit(0);
                    }
                    else if(returnVal==JOptionPane.NO_OPTION) {
                        System.exit(0);
                    }
                }
                else {
                    System.exit(0);
                }
            }
        });
    }


    /**
     * Update with data from the model.
     */
    public void update(Object observable) {
        // XXX Fill this in with the logic for updating the view when the model
        // changes.
    }
}
