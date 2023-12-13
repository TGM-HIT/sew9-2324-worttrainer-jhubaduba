package view;

import jhuber.control.WorttrainerControl;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * UI Klasse.
 * Steuert das GUI.
 * Texteingabe durch eine Textfield.
 * Ermoeglicht Ausgabe eines Bildes durch eine URL.
 * @author Julian Huber
 * @version 15.11.2021
 */

public class WorttrainerUI extends JPanel{
    private final JLabel[] labels = new JLabel[5];
    private final JTextField field = new JTextField(5);
    private final JButton[] buttons = {new JButton("Zurücksetzen"), new JButton("Wort hinzufügen"), new JButton("Trainer speichern"), new JButton("Trainer laden")};
    private JLabel lImage;
    private WorttrainerControl control;

    /**
     * Konstruktor fuer das UI
     * @param control Uebergabe der ActionListener
     */
    public WorttrainerUI(WorttrainerControl control){
        this.setLayout(new BorderLayout());
        this.control = control;
        this.labels[0] = new JLabel("Welches Wort wird unten dargestellt?");
        this.labels[1] = new JLabel("Richtige Wörter: ");
        this.labels[2] = new JLabel("Anzahl Wörter: ");
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
            this.lImage = new JLabel(new ImageIcon(image));
        } catch (MalformedURLException e){
            JOptionPane.showMessageDialog(null, "Keine URL!");
        }
        this.add(lImage, BorderLayout.CENTER);
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

    /**
     * Aendert das Bild welches als URL uebergeben wird
     * @param url Die URL des Bildes
     */
    public void setlImage(String url){
        try {
            ImageIcon icon = new ImageIcon(new URL(url));
            Image image = icon.getImage();
            //Skalierung
            int width = (int)(icon.getIconWidth() * (250.0 / icon.getIconHeight()));
            image = image.getScaledInstance(width, 250, Image.SCALE_SMOOTH);
            this.lImage.setIcon(new ImageIcon(image));
        } catch (MalformedURLException e){
            JOptionPane.showMessageDialog(null, "Keine URL!");
        }

    }

    /**
     * Methode zum Aendern der Statistik
     * @param correct Anzahl korrekte Checks
     * @param checked Anzahl der Checks
     */
    public void setStats(int correct, int checked){
        this.labels[3].setText("" + correct);
        this.labels[4].setText("" + checked);
    }

    /**
     * Gettermethode fuer die Eingabe im Textfield
     * @return String Wort
     */
    public String getWort(){
        return this.field.getText();
    }

    /**
     * Resetmethode fuer das UI
     */
    public void resetUI(){
        this.labels[3].setText("0");
        this.labels[4].setText("0");
        this.field.setText("");
    }

    /**
     * Erzeugt ein JOtionPane Input PopUp
     * @param output Nachricht
     * @return String Eingabe
     */
    public String showInput(String output){
        return JOptionPane.showInputDialog(null, output);
    }

    /**
     * Erzeugt ein JOptionPane Message PopUp
     * @param output Nachricht
     */
    public void showOutput(String output){
        JOptionPane.showMessageDialog(null, output);
    }

}
