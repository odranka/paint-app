import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class TimelineView extends JPanel implements Observer{

    private Model model;

    JPanel controls = new JPanel(new GridLayout(1, 4));
    private JSlider timeSlider;
    private Integer max = 0;
    private Integer value = 0;
    private JButton start, end, play, rewind;

    public TimelineView(Model model) {
        this.setLayout(new BorderLayout());

        start = new JButton(new ImageIcon("images/left.png"));
        start.setBorderPainted(false);
        start.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        start.setMargin(new Insets(0, 0, 0, 0));
        start.setBackground(Color.LIGHT_GRAY);

        end = new JButton(new ImageIcon("images/right.png"));
        end.setBorderPainted(false);
        end.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        end.setMargin(new Insets(0, 0, 0, 0));
        end.setBackground(Color.LIGHT_GRAY);

        play = new JButton(new ImageIcon("images/play.png"));
        play.setBorderPainted(false);
        play.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        play.setMargin(new Insets(0, 0, 0, 0));
        play.setBackground(Color.LIGHT_GRAY);

        rewind = new JButton(new ImageIcon("images/rewind.png"));
        rewind.setBorderPainted(false);
        rewind.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        rewind.setMargin(new Insets(0, 0, 0, 0));
        rewind.setBackground(Color.LIGHT_GRAY);

        timeSlider = new JSlider(0, max, value);
        //timeSlider.setBackground(Color.LIGHT_GRAY);
        timeSlider.setMajorTickSpacing(50);
        timeSlider.setMinorTickSpacing(10);
        timeSlider.setPaintTicks(true);
        timeSlider.setPaintLabels(true);
        // Hook up this observer so that it will be notified when the model
        // changes.
        this.model = model;
        model.addObserver(this);

        this.add(timeSlider, BorderLayout.CENTER);
        controls.add(rewind);
        controls.add(start);
        controls.add(end);
        controls.add(play);
        this.add(controls, BorderLayout.EAST);
        this.setBackground(Color.LIGHT_GRAY);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));


        timeSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent evt) {
                JSlider slider = (JSlider) evt.getSource();
                int value = slider.getValue();
                model.setPaintSlider(true);
                model.setSliderPosn(value);
            }
        });

        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.setSliderPosn(0);
                model.setPaintSlider(true);
            }
        });
        end.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.setSliderPosn(model.getTotalNumPoints());
                model.setPaintSlider(true);
            }
        });

        play.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Timer t = new Timer(20,null);
                t.addActionListener(new ActionListener(){
                    int i=model.getSliderPosn();
                    public void actionPerformed(ActionEvent e){
                        model.setSliderPosn(i);
                        model.setPaintSlider(true);
                        if(i>model.getTotalNumPoints()){t.stop();}
                        i++;
                    }
                });
                t.setRepeats(true);
                t.start();
            }
        });

        rewind.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Timer t = new Timer(20,null);
                t.addActionListener(new ActionListener(){
                    int i=model.getSliderPosn();
                    public void actionPerformed(ActionEvent e){
                        model.setSliderPosn(i);
                        model.setPaintSlider(true);
                        if(i<0){t.stop();}
                        i--;
                    }
                });
                t.setRepeats(true);
                t.start();
            }
        });

        setVisible(true);
    }


    /**
     * Update with data from the model.
     */
    public void update(Object observable) {
        max = model.getTotalNumPoints();
        timeSlider.setMaximum(max);
        timeSlider.setValue(model.getSliderPosn());
    }
}
