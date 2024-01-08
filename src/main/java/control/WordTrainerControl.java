package control;

import model.*;
import view.WordTrainerFrame;
import view.WordTrainerUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Control class of WordTrainer
 * @author Julian Huber
 * @version 2024-01-08
 */
public class WordTrainerControl implements ActionListener {
    private final WordTrainerFrame frame;
    private final WordTrainerUI ui;
    private WordTrainer trainer;
    private SaveManager saveManager;

    /**
     * Constructor - The used persistence method has to be specified
     * @param saveManager Specified persistence method
     */
    public WordTrainerControl(SaveManager saveManager){
        this.saveManager = saveManager;
        this.ui = new WordTrainerUI(this);
        this.initGame();
        this.frame = new WordTrainerFrame("WordTrainer", ui);
    }

    /**
     * Initialised the game by using a standard list of WordPairs. Selects a random pari and displays the image
     */
    public void initGame(){
        this.trainer = new WordTrainer();
        try {
            this.trainer.addEntry(new WordPair("Car", new URL("https://upload.wikimedia.org/wikipedia/commons/8/8c/1986_Porsche_911_SC.jpg")));
            this.trainer.addEntry(new WordPair("Motorcycle", new URL("https://upload.wikimedia.org/wikipedia/commons/8/8d/1200gsfr.jpg")));
            this.trainer.addEntry(new WordPair("Airplane", new URL("https://upload.wikimedia.org/wikipedia/commons/e/ef/G-BGMP_Reims_F172_%40Cotswold_Airport%2C_July_2005.jpg")));
            //this.trainer.addEntry(new WordPair("Train", new URL("https://upload.wikimedia.org/wikipedia/commons/8/8c/Austria_1044_semmering.jpg")));
        } catch (MalformedURLException e){
            e.printStackTrace();
        }
        this.trainer.getRandomEntry();
        this.ui.setImage(this.trainer.getCurrentEntry().getUrl());
    }

    /**
     * ActionListener for the events
     * @param e Calling event
     */
    @Override
    public void actionPerformed(ActionEvent e){
        //When enter is pressed check the guess and select new pair
        if(e.getActionCommand().equals(Actions.ENTER.getValue())){
            String input = ui.getWord();
            this.trainer.check(input);
            this.ui.setStats(this.trainer.getCorrect(), this.trainer.getChecks());
            this.trainer.getRandomEntry();
            this.ui.setImage(this.trainer.getCurrentEntry().getUrl());
        }
        //Reset game by resetting UI and statistics
        if(e.getActionCommand().equals(Actions.RESET.getValue())){
            this.ui.resetUI();
            this.initGame();
            this.trainer.resetStats();
        }
        //Adds a word to the currently active list of Words
        if(e.getActionCommand().equals(Actions.ADD.getValue())){
            try{
                String input = this.ui.showInput("Insert URL!");
                if(input != null) {
                    this.trainer.addEntry(new WordPair(this.ui.getWord(), new URL(input)));
                }
            } catch (MalformedURLException | IllegalArgumentException e1){
                this.ui.showOutput(e1.getMessage());
            }
        }

        //Saves the current state of the game and all words with the given persistence method
        if(e.getActionCommand().equals(Actions.SAVE.getValue())){
            String path = this.ui.showInput("Please enter filepath to save session");
            try {
                this.saveManager.save(path, this.trainer);
            }catch (IOException exception){
                this.ui.showOutput("Something went wrong while saving!");
            }
        }
        //Loads a saved state of the game and its list of words with the given persistence method
        if(e.getActionCommand().equals(Actions.LOAD.getValue())){
            String path = this.ui.showInput("Please enter filepath to load session");
            try{
                this.trainer = this.saveManager.load(path);
            } catch (IOException exception){
                this.ui.showOutput("File not found");
            }
            this.ui.setStats(this.trainer.getCorrect(), this.trainer.getChecks());
            this.ui.setImage(this.trainer.getCurrentEntry().getUrl());
        }
    }
    public static void main(String[] args){
        new WordTrainerControl(new JSONSaveManager());
    }
}
