package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

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
    public void addEntry(WordPair pair){
        this.wordList.add(pair);
    }

    public void removeEntry(String word){
        this.wordList.removeIf(wordPair -> wordPair.getWord().equals(word));
    }
     public WordPair getRandomEntry(){
        Random r = new Random();
        this.index = r.nextInt(this.wordList.size());
        return getCurrentEntry();
     }

     public WordPair getCurrentEntry() throws IndexOutOfBoundsException{
        if(this.index != -1){
            return this.wordList.get(index);
        }
        throw new IndexOutOfBoundsException("No entry selected!");
     }

     public boolean check(String word){
        this.checks++;
        if(word.equals(this.getCurrentEntry().getWord())){
            this.correct++;
            return true;
        }
        return false;
     }

    public int getChecks() {
        return checks;
    }

    public int getCorrect() {
        return correct;
    }

    public void resetStats(){
        this.checks = 0;
        this.correct = 0;
    }
}
