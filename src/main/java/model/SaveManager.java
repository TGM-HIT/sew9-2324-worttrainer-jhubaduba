package model;


import java.io.FileNotFoundException;
import java.io.IOException;

public interface SaveManager {
    void save(String path, WordTrainer trainer) throws IOException;

    WordTrainer load(String path) throws IOException;
}
