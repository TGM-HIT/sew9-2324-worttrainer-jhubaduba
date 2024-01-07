package control;

import model.WordPair;
import model.WordTrainer;
import view.WordTrainerFrame;
import view.WordTrainerUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;

public class WordTrainerControl implements ActionListener {
    private final WordTrainerFrame frame;
    private final WordTrainerUI ui;
    private WordTrainer trainer;

    public WordTrainerControl(){
        this.ui = new WordTrainerUI(this);
        this.loadGame();
        this.frame = new WordTrainerFrame("WordTrainer", ui);
    }
    public void loadGame(){
        this.trainer = new WordTrainer();
        try {
            this.trainer.addEntry(new WordPair("Car", new URL("https://upload.wikimedia.org/wikipedia/commons/8/8c/1986_Porsche_911_SC.jpg")));
            this.trainer.addEntry(new WordPair("Motorcycle", new URL("https://upload.wikimedia.org/wikipedia/commons/8/8d/1200gsfr.jpg")));
            this.trainer.addEntry(new WordPair("Airplane", new URL("https://upload.wikimedia.org/wikipedia/commons/e/ef/G-BGMP_Reims_F172_%40Cotswold_Airport%2C_July_2005.jpg")));
            this.trainer.addEntry(new WordPair("Train", new URL("https://upload.wikimedia.org/wikipedia/commons/8/8c/Austria_1044_semmering.jpg")));
        } catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IllegalArgumentException e){
            e.printStackTrace();
        }
        this.trainer.getRandomEntry();
        this.ui.setImage(this.trainer.getCurrentEntry().getUrl());
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getActionCommand().equals("Enter")){
            String input = ui.getWord();
            this.trainer.check(input);
            this.ui.setStats(this.trainer.getCorrect(), this.trainer.getChecks());
            this.trainer.getRandomEntry();
            this.ui.setImage(this.trainer.getCurrentEntry().getUrl());
        }

        if(e.getActionCommand().equals("Reset")){
            this.ui.resetUI();
            this.loadGame();
            this.trainer.resetStats();
        }

        if(e.getActionCommand().equals("Add word")){
            try{
                this.trainer.addEntry(new WordPair(this.ui.getWord(), new URL(this.ui.showInput("Insert URL!"))));
            } catch (IllegalArgumentException | MalformedURLException e1){
                ui.showOutput("Non valid input");
            } catch (NullPointerException e1){
                System.out.println("ADD ABORTED");
            }
        }

        //TODO LOAD and SAVE
    }
    public static void main(String[] args){
        new WordTrainerControl();
    }
}
