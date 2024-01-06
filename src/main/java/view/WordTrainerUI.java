package view;

import control.WordTrainerControl;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

public class WordTrainerUI extends JPanel {
    private final JLabel[] labels = new JLabel[5];
    private final JTextField field = new JTextField(5);
    private final JButton[] buttons = {new JButton("Reset"), new JButton("Add word"), new JButton("Save trainer"), new JButton("Load trainer")};
    private JLabel image;
    private WordTrainerControl control;

    public WordTrainerUI(WordTrainerControl control){
        this.setLayout(new BorderLayout());
        this.control = control;
        this.labels[0] = new JLabel("What word is being displayed?");
        this.labels[1] = new JLabel("Correct words: ");
        this.labels[2] = new JLabel("Words trained: ");
        this.labels[3] = new JLabel("0");
        this.labels[4] = new JLabel("0");
        //ActionCommand fuer das JTextField
        this.field.setActionCommand("Enter");
        //PAGE_START Layout mit Label 0 und dem Textfield
        JPanel top = new JPanel(new GridLayout(2, 1));
        top.add(labels[0]);
        top.add(field);
        this.add(top, BorderLayout.PAGE_START);
        // PAGE_CENTER Initiieren eines Platzhalterbilds. Exception wird gefangen
        try {
            //ImageIcon icon = new ImageIcon(new URL("https://cohenwoodworking.com/wp-content/uploads/2016/09/image-placeholder-500x500.jpg"));
            ImageIcon icon = new ImageIcon(new URL("https://mypresta.eu/content/uploads/2013/05/loading.png"));
            Image image = icon.getImage();
            image = image.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
            this.image = new JLabel(new ImageIcon(image));
        } catch (MalformedURLException e){
            JOptionPane.showMessageDialog(null, "Keine URL!");
        }
        this.add(image, BorderLayout.CENTER);
        //PAGE_END Hinzufuegen der Buttons und Labels fuer die Statistik und Speicheroptionen
        JPanel bottom = new JPanel(new GridLayout(2, 4));
        bottom.add(labels[1]);
        bottom.add(labels[2]);
        bottom.add(buttons[0]);
        bottom.add(buttons[2]);
        bottom.add(labels[3]);
        bottom.add(labels[4]);
        bottom.add(buttons[1]);
        bottom.add(buttons[3]);
        this.add(bottom, BorderLayout.PAGE_END);
        //ActionListener
        this.field.addActionListener(control);
        this.buttons[0].addActionListener(control);
        this.buttons[1].addActionListener(control);
        this.buttons[2].addActionListener(control);
        this.buttons[3].addActionListener(control);
    }

    public void setImage(String url){
        try {
            ImageIcon icon = new ImageIcon(new URL(url));
            Image image = icon.getImage();
            //Skalierung
            int width = (int)(icon.getIconWidth() * (250.0 / icon.getIconHeight()));
            image = image.getScaledInstance(width, 250, Image.SCALE_SMOOTH);
            this.image.setIcon(new ImageIcon(image));
        } catch (MalformedURLException e){
            JOptionPane.showMessageDialog(null, "Keine URL!");
        }

    }
    public void setStats(int correct, int checked){
        this.labels[3].setText(Integer.toString(correct));
        this.labels[4].setText(Integer.toString(checked));
    }

    public String getWord(){
        return this.field.getText();
    }

    public void resetUI(){
        this.labels[3].setText("0");
        this.labels[4].setText("0");
        this.field.setText("");
    }

    public String showInput(String output){
        return JOptionPane.showInputDialog(null, output);
    }

    public void showOutput(String output){
        JOptionPane.showMessageDialog(null, output);
    }
}
