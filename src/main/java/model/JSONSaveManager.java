package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Saves WordTrainer in a JSON format using GSON
 * @author Julian Huber
 * @version 2024-01-08
 */
public class JSONSaveManager implements SaveManager{

    /**
     * Saves the given WordTrainer to the given path
     * @param path
     * @param trainer
     * @throws IOException
     */
    @Override
    public void save(String path, WordTrainer trainer) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        //System.out.println(gson.toJson(trainer));
        try(FileWriter writer = new FileWriter(path)) {
            gson.toJson(trainer, writer);
        }
    }

    /**
     * Loads a WordTrainer from the given path
     * @param path
     * @return WordTrainer
     * @throws IOException
     */
    @Override
    public WordTrainer load(String path) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonReader json;
        try(FileReader reader = new FileReader(path)){
            return gson.fromJson(reader, WordTrainer.class);
        }
    }
}
