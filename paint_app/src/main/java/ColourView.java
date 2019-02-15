import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ColourView extends JPanel implements Observer{
    private Model model;

    ArrayList<JButton> colorButtons = new ArrayList<>();
    private JButton redColor, blackColor, blueColor, greenColor, yellowColor, pinkColor, selectColor;
    JPanel colorsPanel = new JPanel(new GridLayout(3, 2));
    JPanel pickedColorPanel = new JPanel(new GridLayout(2, 1));
    JPanel displaySelected = new JPanel();

    public ColourView(Model model) {
        this.setLayout(new BorderLayout());
        // create the view UI
        redColor = new JButton();
        redColor.setBackground(Color.RED);
        blackColor = new JButton();
        blackColor.setBackground(Color.BLACK);
        blueColor = new JButton();
        blueColor.setBackground(Color.BLUE);
        greenColor = new JButton();
        greenColor.setBackground(Color.GREEN);
        yellowColor = new JButton();
        yellowColor.setBackground(Color.YELLOW);
        pinkColor = new JButton();
        pinkColor.setBackground(Color.PINK);

        selectColor = new JButton("Select a Color");
        displaySelected.setBackground(model.getColor());

        colorButtons.add(redColor);
        colorButtons.add(blackColor);
        colorButtons.add(blueColor);
        colorButtons.add(greenColor);
        colorButtons.add(yellowColor);
        colorButtons.add(pinkColor);

        for (int i = 0; i < colorButtons.size(); i++) {
            colorButtons.get(i).setMinimumSize(new Dimension(25, 25));
            //colorButtons.get(i).setMaximumSize(new Dimension(50, 50));
            colorButtons.get(i).setPreferredSize(new Dimension(50, 50));
            colorButtons.get(i).setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        }
        //selectColor.setMaximumSize(new Dimension(100, 10));
        //selectColor.setPreferredSize(new Dimension(100, 10));

        colorsPanel.add(redColor);
        colorsPanel.add(blackColor);
        colorsPanel.add(blueColor);
        colorsPanel.add(greenColor);
        colorsPanel.add(yellowColor);
        colorsPanel.add(pinkColor);
        colorsPanel.add(selectColor);

        pickedColorPanel.add(selectColor);
        pickedColorPanel.add(displaySelected);

        this.add(colorsPanel, BorderLayout.CENTER);
        this.add(pickedColorPanel, BorderLayout.SOUTH);
        this.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        this.setBackground(Color.LIGHT_GRAY);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Hook up this observer so that it will be notified when the model
        // changes.
        this.model = model;
        model.addObserver(this);

        // setup the event to go to the "controller"
        // (this anonymous class is essentially the controller)
        redColor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.setColor(Color.RED);
            }
        });
        blackColor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.setColor(Color.BLACK);
            }
        });
        blueColor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.setColor(Color.BLUE);
            }
        });
        greenColor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.setColor(Color.GREEN);
            }
        });
        yellowColor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.setColor(Color.YELLOW);
            }
        });
        pinkColor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.setColor(Color.PINK);
            }
        });

        selectColor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Color color = JColorChooser.showDialog(ColourView.this, "Select a Color", model.getColor());
                if (color != null) { // new color selected
                    model.setColor(color);
                }
            }
        });


        setVisible(true);
    }


    /**
     * Update with data from the model.
     */
    public void update(Object observable) {
        displaySelected.setBackground(model.getColor());
        // XXX Fill this in with the logic for updating the view when the model
        // changes.
    }

}
