package control;

import model.WortTrainer;
import model.WortTrainerSave;
import view.WorttrainerFrame;
import view.WorttrainerUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Control Klasse.
 * Verbindet UI mit Model.
 * Programm zum Trainieren von Woertern mithilfe von Bildern. Enthaelt Lade- und Speicherfunktion,
 * sowie das Hinzufuegen von neuen Woertern.
 * @author Julian Huber
 * @version 15.11.2021
 */

public class WorttrainerControl implements ActionListener {
    private final WorttrainerUI ui;
    private WortTrainer trainer;
    private final WorttrainerFrame frame;

    /**
     * Konstruktor zum Erstellen des Worttrainers
     * Such nach einer Speicherdatei, falls nicht vorhanden werden Standardwerte verwendet.
     */
    public WorttrainerControl(){
        this.ui = new WorttrainerUI(this);
        this.loadGame();
        this.frame = new WorttrainerFrame("Worttrainer", ui);

    }

    /**
     * Methode die das Spiel initiiert. Sucht nach einer Speicherdatei, falls nicht vorhanden werden Standardwerte verwendet.
     */
    public void loadGame(){
        //Aus Standard-Speicherdatei wird versucht zu laden
        try {
            this.trainer = WortTrainerSave.readTrainer();
        //Standardwerte, falls Speicherdatei nicht gefunden ist
        } catch (IOException e){
            this.trainer = new WortTrainer();
            this.trainer.addWortEintrag("Auto", "https://upload.wikimedia.org/wikipedia/commons/8/8c/1986_Porsche_911_SC.jpg");
            this.trainer.addWortEintrag("Motorrad", "https://upload.wikimedia.org/wikipedia/commons/8/8d/1200gsfr.jpg");
            this.trainer.addWortEintrag("Flugzeug", "https://upload.wikimedia.org/wikipedia/commons/e/ef/G-BGMP_Reims_F172_%40Cotswold_Airport%2C_July_2005.jpg");
            this.trainer.addWortEintrag("Zug", "https://upload.wikimedia.org/wikipedia/commons/8/8c/Austria_1044_semmering.jpg");

        }
        //Erster Eintrag wird ausgewaehlt und Bild angezeigt
        this.trainer.getRandomWortEintrag();
        this.ui.setlImage(this.trainer.getUrl());
    }

    /**
     * ActionListener fuer das Programm
     * @param e Ausloesendes Objekt
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        /*
        Wird ausgeloest, falls Enter gedrueckt wird.
        Uebernimmt das eingegebene Wort und checked ob es mit dem angezeigten uebereinstimmt.
        Nach dem Check wird ein neues Wort ausgewaehlt und das Bild angezeigt.
         */
        if(e.getActionCommand().equals("Enter")){
            String input = ui.getWort();
            this.trainer.check(input);
            this.ui.setStats(this.trainer.getCorrect(), this.trainer.getChecks());
            this.trainer.getRandomWortEintrag();
            this.ui.setlImage(this.trainer.getUrl());
            System.out.println("ENTER" + "\n" + this.trainer.getWort());
        }
        /*
        Setzt das Spiel zurueck. Falls Default.txt vorhanden wird Default.txt geladen, sonst wird eine Standardliste erstellt.
        Zusaetzlich wird die Statistik zurueck gesetzt.
         */
        if(e.getActionCommand().equals("Zurücksetzen")){
            this.ui.resetUI();
            this.loadGame();
            this.trainer.resetStats();
            //Debug Konsolen Output
            System.out.println("RESET" + "\n" + this.trainer.getWort());
        }
        /*
        Speichert ein neues Wort temporaer in den Trainer, wird aber nicht abgespeichert.
        Bei falscher Eingabe wird eine PopUp angezeigt.
         */
        if(e.getActionCommand().equals("Wort hinzufügen")){
            //Debug Konsolen Output
            System.out.println("ADD");
            try {
                this.trainer.addWortEintrag(this.ui.getWort(), this.ui.showInput("URL eingeben!"));
            } catch (IllegalArgumentException exception){
                ui.showOutput("Keine gültige Eingabe (URL oder Wort)");
            } catch (NullPointerException exception){
                System.out.println("ADD ABORTED");
            }
            /*try {
                this.trainer.addWortEintrag(this.ui.getWort(), this.ui.showInput("URL eingeben!"));
                try {
                    WortTrainerSave.writeTrainer(this.trainer);
                } catch (IOException ex){
                    ui.showOutput("Schreibvorgang fehlgeschlagen!");
                }
            } catch (IllegalArgumentException ex){
                ui.showOutput("Keine gültige URL");
            }*/
        }
        //Speichert einen Trainer in eine Datei
        if(e.getActionCommand().equals("Trainer speichern")){
            //Debug Konsolen Output
            System.out.println("SAVE");
            try{
                String input = ui.showInput("Speicherort (Leer = Default.txt)");
                if(input.equals("")){
                    WortTrainerSave.writeTrainer(this.trainer);
                } else{
                    WortTrainerSave.writeTrainer(this.trainer, input);
                }
            } catch(IOException exception){
                ui.showOutput("Datei konnte nicht gespeichert werden!");
            } catch (NullPointerException exception){
                System.out.println("SAVE ABORTED");
            }
        }
        //Laedt einen Trainer aus einer Datei
        if(e.getActionCommand().equals("Trainer laden")){
            //Debug Konsolen Output
            System.out.println("LOAD");
            try{
                String input = ui.showInput("Pfad der Datei (Leer = Default.txt");
                if(input.equals("")){
                    this.trainer = WortTrainerSave.readTrainer();
                } else {
                    this.trainer = WortTrainerSave.readTrainer(input);
                }
            }catch(IOException exception){
                ui.showOutput("Datei nicht gefunden!");
            } catch (NullPointerException exception){
                System.out.println("LOAD ABORTED");
            }
            this.trainer.getRandomWortEintrag();
            this.ui.setlImage(this.trainer.getUrl());
            this.ui.resetUI();
        }
    }

    public static void main(String[] args){
        new WorttrainerControl();
    }
}

