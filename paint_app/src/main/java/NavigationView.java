import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class NavigationView extends JMenuBar implements Observer{

    private JMenu menu;
    private JMenuItem open, save, newPage, exit;
    CanvasView canvasView;

    public NavigationView(Model model, CanvasView cv) {
        canvasView = cv;

        menu = new JMenu("File");

        save = new JMenuItem("save");
        open = new JMenuItem("open");
        newPage = new JMenuItem("new");
        exit = new JMenuItem("exit");
        menu.add(save);
        menu.add(open);
        menu.add(newPage);
        menu.add(exit);
        this.add(menu);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if(canvasView.getLines().size() > 0){
                    int returnVal=JOptionPane.showConfirmDialog(NavigationView.this,
                            "Would you like to save this file?", "Save File", JOptionPane.YES_NO_CANCEL_OPTION);
                    if(returnVal==JOptionPane.CANCEL_OPTION) {
                        return;
                    }
                    else if(returnVal==JOptionPane.YES_OPTION) {
                        saveFile();
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

        open.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                createNewPage();
                DrawnLine drawnLine = null;
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Paint Files", "paint");
                JFileChooser chooser = new JFileChooser();
                chooser.setFileFilter(filter);
                int returnVal = chooser.showOpenDialog(NavigationView.this);
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    try {
                        FileInputStream fileIn = new FileInputStream(chooser.getSelectedFile().getPath());
                        ObjectInputStream in = new ObjectInputStream(fileIn);
                        while(true){
                            try {
                                drawnLine = (DrawnLine) in.readObject();
                                canvasView.addLines(drawnLine);
                            } catch (IOException ex) {
                                break;
                            } catch (ClassNotFoundException ex) {
                                System.out.println("Class not found");
                                ex.printStackTrace();
                                return;
                            }
                        }
                        in.close();
                        fileIn.close();
                    } catch (IOException i) {
                        i.printStackTrace();
                        return;
                    }
                }
            }
        });
        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                saveFile();
            }
        });
        newPage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                createNewPage();
            }
        });

    }

    public void createNewPage(){
        if(canvasView.getLines().size() > 0){
            int returnVal=JOptionPane.showConfirmDialog(NavigationView.this,
                    "Would you like to save this file?", "Save File", JOptionPane.YES_NO_CANCEL_OPTION);
            if(returnVal==JOptionPane.CANCEL_OPTION) {
                return;
            }
            else if(returnVal==JOptionPane.YES_OPTION) {
                saveFile();
                canvasView.clearPage();
            }
            else if(returnVal==JOptionPane.NO_OPTION) {
                canvasView.clearPage();
            }
        }
        else {
            canvasView.clearPage();
        }
    }

    public void saveFile(){
        JFileChooser chooser = new JFileChooser();
        int returnVal = chooser.showSaveDialog(NavigationView.this);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                File file = chooser.getSelectedFile();
                FileOutputStream fileOut = new FileOutputStream(file + ".paint");
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                for(int i =0; i < canvasView.getLines().size(); i++){
                    out.writeObject(canvasView.getLines().get(i));
                }
                out.close();
                fileOut.close();
            } catch (IOException i) {
                i.printStackTrace();
            }
        }
    }

    /**
     * Update with data from the model.
     */
    public void update(Object observable) {
        // XXX Fill this in with the logic for updating the view when the model
        // changes.
    }
}
