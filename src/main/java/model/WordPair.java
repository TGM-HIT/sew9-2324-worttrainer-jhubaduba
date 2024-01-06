package model;

import java.net.URL;

public class WordPair {
    private String word;
    private URL url;

    public WordPair(String word, URL url){
        this.setWord(word);
        this.setUrl(url);
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setUrl(URL url) throws IllegalArgumentException{
        if(this.checkURL(url)){
            this.url = url;
        } else {
            throw new IllegalArgumentException("Invalid URL");
        }
    }

    public String getWord() {
        return word;
    }

    public URL getUrl() {
        return url;
    }

    public boolean checkURL(URL url){
        return url.toString().matches("(?:(?:https?|ftp):\\/\\/|\\b(?:[a-z\\d]+\\.))(?:(?:[^\\s()<>]+|\\((?:[^\\s()<>]+|(?:\\([^\\s()<>]+\\)))?\\))+(?:\\((?:[^\\s()<>]+|(?:\\(?:[^\\s()<>]+\\)))?\\)|[^\\s`!()\\[\\]{};:'\".,<>?«»“”‘’]))?");
    }
}
