package view;

import javax.swing.*;

/**
 * Frame Klasse des Programms
 * @author Julian Huber
 * @version 2021-11-15
 */
public class WorttrainerFrame extends JFrame {
    /**
     * Frame Konstruktor
     * @param titel Titel des Programms
     * @param layoutPanel JPanel welches hinzugefuegt werden soll
     */
    public WorttrainerFrame(String titel, JPanel layoutPanel){
        super(titel);
        this.add(layoutPanel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setBounds(100, 100, 500, 500);
        this.setVisible(true);
    }
}
