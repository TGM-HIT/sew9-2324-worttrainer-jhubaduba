package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JSONSaveManager implements SaveManager{

    @Override
    public void save(String path, WordTrainer trainer) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        //System.out.println(gson.toJson(trainer));
        try(FileWriter writer = new FileWriter(path)) {
            gson.toJson(trainer, writer);
        }
    }

    @Override
    public WordTrainer load(String path) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonReader json;
        try(FileReader reader = new FileReader(path)){
            return gson.fromJson(reader, WordTrainer.class);
        }
    }
}
