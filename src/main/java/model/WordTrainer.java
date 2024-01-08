package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * WordTrainer - Displays words from WordPair. User has to guess the word to the corresponding image
 * @author Julian Huber
 * @verion 2024-01-08
 */
public class WordTrainer implements Serializable {
    private ArrayList<WordPair> wordList;
    private int checks;
    private int correct;
    private int index = -1;

    public WordTrainer(){
        this.wordList = new ArrayList<>();
    }
    public WordTrainer(ArrayList<WordPair> wordList){
        this.wordList = wordList;
    }

    /**
     * Adds a WordPair to the list of the object
     * @param pair
     */
    public void addEntry(WordPair pair){
        this.wordList.add(pair);
    }

    /**
     * Removes a WordPair from the list by searching for the word
     * @param word
     */
    public void removeEntry(String word){
        this.wordList.removeIf(wordPair -> wordPair.getWord().equals(word));
    }

    /**
     * Selects a random WordPair and returns it
     * @return Random WordPair
     */
    public WordPair getRandomEntry(){
        Random r = new Random();
        this.index = r.nextInt(this.wordList.size());
        return getCurrentEntry();
    }

    /**
     * Returns the currently selected WordPair
     * @return WordPair
     * @throws IndexOutOfBoundsException If no WordPair is currently selected
     */
     public WordPair getCurrentEntry() throws IndexOutOfBoundsException{
        if(this.index != -1){
            return this.wordList.get(index);
        }
        throw new IndexOutOfBoundsException("No entry selected!");
     }

    /**
     * Checks if a given word corresponds to the currently selected WordPair
     * @param word
     * @return boolean
     */
     public boolean check(String word){
        this.checks++;
        if(word.equals(this.getCurrentEntry().getWord())){
            this.correct++;
            return true;
        }
        return false;
     }

    /**
     * Returns the total number of guesses by the user
     * @return Number of guesses
     */
    public int getChecks() {
        return checks;
    }

    /**
     * Returns the total number of correct guesses by the user
     * @return Number of correct guesses
     */
    public int getCorrect() {
        return correct;
    }

    /**
     * Resets the statistics
     */
    public void resetStats(){
        this.checks = 0;
        this.correct = 0;
    }
}
