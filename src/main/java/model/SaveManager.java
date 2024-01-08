package model;

import java.io.IOException;

/**
 * Interface for persistence methods which can be interchanged in correspondents to the strategy pattern
 * @author Julian Huber
 * @version 2024-01-08
 */
public interface SaveManager {
    void save(String path, WordTrainer trainer) throws IOException;

    WordTrainer load(String path) throws IOException;
}
