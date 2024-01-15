package model;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * WordPair consists of a Word and URL which represents an image.
 * @author Julian Huber
 * @version 2024-01-08
 */
public class WordPair implements Serializable {
    private String word;
    private URL url;

    /**
     * Constructor - creates WordPair object
     * @param word
     * @param url
     * @throws MalformedURLException
     */
    public WordPair(String word, URL url) throws MalformedURLException{
        this.setWord(word);
        this.setUrl(url);
    }

    public void setWord(String word) {
        if(word == null || word.length() <= 2){
            throw new IllegalArgumentException("Invalid word");
        }
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

    /**
     * Checks a given URL for validity using regex
     * @param url
     * @return boolean
     */
    public boolean checkURL(URL url){
        return url.toString().matches("^(https?://)?[^\s/$.?#].[^\s]*\.(jpg|jpeg|png)$");
    }
}
