import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ThicknessView extends JComponent implements Observer{

    private Model model;
    ArrayList<JButton> thicknessButtons = new ArrayList<>();
    private JButton thick1, thick2, thick3;

    public ThicknessView(Model model) {

        BufferedImage lineImage1 = new BufferedImage(100, 50,
                BufferedImage.TYPE_INT_RGB);
        BufferedImage lineImage2 = new BufferedImage(100, 50,
                BufferedImage.TYPE_INT_RGB);
        BufferedImage lineImage3 = new BufferedImage(100, 50,
                BufferedImage.TYPE_INT_RGB);

        Graphics2D g1 = lineImage1.createGraphics();
        Graphics2D g2 = lineImage2.createGraphics();
        Graphics2D g3 = lineImage3.createGraphics();

        // create the view UI
        g1.setStroke(new BasicStroke((3), BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
        g1.drawLine(25, 25, 75, 25);
        thick1 = new JButton();
        thick1.setIcon(new ImageIcon(lineImage1));
        thick1.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        thick1.setMargin(new Insets(0, 0, 0, 0));
        thick1.setBackground(Color.DARK_GRAY);

        g2.setStroke(new BasicStroke((5), BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
        g2.drawLine(25, 25, 75, 25);
        thick2 = new JButton();
        thick2.setIcon(new ImageIcon(lineImage2));
        thick2.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        thick2.setMargin(new Insets(0, 0, 0, 0));
        thick2.setBackground(Color.LIGHT_GRAY);

        g3.setStroke(new BasicStroke((10), BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
        g3.drawLine(25, 25, 75, 25);
        thick3 = new JButton();
        thick3.setIcon(new ImageIcon(lineImage3));
        thick3.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        thick3.setMargin(new Insets(0, 0, 0, 0));
        thick3.setBackground(Color.LIGHT_GRAY);

        for (int i = 0; i < thicknessButtons.size(); i++) {
            thicknessButtons.get(i).setMaximumSize(new Dimension(50, 25));
            thicknessButtons.get(i).setPreferredSize(new Dimension(50, 25));
        }

        this.setLayout(new GridLayout(3, 1));
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.add(thick1);
        this.add(thick2);
        this.add(thick3);


        // Hook up this observer so that it will be notified when the model
        // changes.
        this.model = model;
        model.addObserver(this);

        // setup the event to go to the "controller"
        // (this anonymous class is essentially the controller)
        thick1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.setLineThickness(3);
                thick1.setBackground(Color.DARK_GRAY);
                thick2.setBackground(Color.LIGHT_GRAY);
                thick3.setBackground(Color.LIGHT_GRAY);
            }
        });
        thick2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.setLineThickness(5);
                thick2.setBackground(Color.DARK_GRAY);
                thick1.setBackground(Color.LIGHT_GRAY);
                thick3.setBackground(Color.LIGHT_GRAY);
            }
        });
        thick3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.setLineThickness(10);
                thick3.setBackground(Color.DARK_GRAY);
                thick1.setBackground(Color.LIGHT_GRAY);
                thick2.setBackground(Color.LIGHT_GRAY);
            }

        });

        setVisible(true);
    }


    /**
     * Update with data from the model.
     */
    public void update(Object observable) {
        // XXX Fill this in with the logic for updating the view when the model
        // changes.
    }
}
