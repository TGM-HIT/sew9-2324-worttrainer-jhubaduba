import model.JSONSaveManager;
import model.SaveManager;
import model.WordPair;
import model.WordTrainer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Tests the Model of WordTrainer
 * @author Julian Huber
 * @version 2024-01-08
 */
public class WordTrainerTest {

    @Test
    @DisplayName("Testing creation of WordPair Object")
    public void WordPairText() throws MalformedURLException{
        WordPair pair = new WordPair("Word", new URL("https://upload.wikimedia.org/wikipedia/commons/8/8c/1986_Porsche_911_SC.jpg"));
        Assertions.assertThrows(IllegalArgumentException.class,()->{pair.setWord(null);}, "Word should not be null");
        Assertions.assertThrows(IllegalArgumentException.class,()->{pair.setWord("a");}, "Word should be longer than two characters");
        Assertions.assertThrows(IllegalArgumentException.class,()->{pair.setWord("aa");}, "Word should be longer than two characters");
        String word = "Word";
        pair.setWord(word);
        String url = "https://upload.wikimedia.org/wikipedia/commons/8/8d/1200gsfr.jpg";
        pair.setUrl(new URL(url));
        assertEquals(word, pair.getWord(), "Word should be the same");
        assertEquals(url, pair.getUrl().toString(), "URL should be the same");
    }

    @Test
    @DisplayName("Testing WordTrainer")
    public void WortTrainerTest() throws MalformedURLException{
        WordTrainer trainer = new WordTrainer();
        assertThrows(IndexOutOfBoundsException.class, ()->{trainer.getRandomEntry();},"Empty trainer can't return random entry");
        assertThrows(IndexOutOfBoundsException.class, ()->{trainer.getCurrentEntry();}, "When no entry is selected entry can't be returned");
        WordPair pair = new WordPair("Word", new URL("https://upload.wikimedia.org/wikipedia/commons/8/8d/1200gsfr.jpg"));
        assertTrue(trainer.addEntry(pair), "An entry should be able to be added");
        assertTrue(trainer.removeEntry("Word"), "An entry should be able to be removed");
        assertFalse(trainer.removeEntry("Word"),"A non existant entry can't be removed");
        trainer.addEntry(pair);
        assertEquals(pair, trainer.getRandomEntry(), "With an entry present, one should be able to be selected randomly");
        assertEquals(pair, trainer.getCurrentEntry(), "With an entry previously selected randomly, one should be able to return it");
        assertTrue(trainer.check("Word"), "With the correct word entered, it should return true");
        assertFalse(trainer.check("Woreadsf"), "With the wrong word entered, it should return false");
        assertEquals(1, trainer.getCorrect(), "The number of correct checks should be counted correctly");
        assertEquals(2, trainer.getChecks(), "The number of total checks should be counted correctly");
    }
}
