package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Klasse die Methoden zum Speichern und Auslesen des TrainerObjekts enthält
 * @author Julian Huber
 * @version 18.10.2021
 */
public class WortTrainerSave{

    /**
     * Speichert das Objekt als Default.txt ab. Zur Ausgabe wird eine toString Methode verwendet die alle Daten des Objekts ausgibt
     * @param trainer Trainer Objekt, welches gespeichert werden soll
     * @throws IOException falls das Schreiben fehlschlägt
     */
    public static void writeTrainer(WortTrainer trainer) throws IOException {
        try (PrintWriter outputStream = new PrintWriter("Default.txt")) {
            outputStream.print(trainer.printSaveString());
        }
    }

    /**
     * Speichert das Objekt in einer Datei mit als Parameter übergebenen Pfad. Zur Ausgabe wird eine toString Methode verwendet die alle Daten des Objekts ausgibt
     * @param trainer Trainer Objekt welches gespeichert werden soll.
     * @param filepath Der Speicherpfad der Datei
     * @throws IOException falls das Schreiben fehlschlägt
     */
    public static void writeTrainer(WortTrainer trainer, String filepath) throws IOException{
        try (PrintWriter outputStream = new PrintWriter(filepath)) {
            outputStream.print(trainer.printSaveString());
        }
    }

    /**
     * Liest ein Trainer Objekt aus einer Datei aus. In einer Zeile ist immer ein Name und eine URL enthalten und werden dem
     * neu erstellen Liste-Objekt hinzugefügt.
     * Falls das nächste Element ein Integer ist, so werden diese in check und correct gespeichert.
     * Aus der Liste, sowie check und correct wird ein neues Trainer-Objekt erstellt und zurückgegeben.
     * Augelesen wird die Defaut.txt Datei
     * @return Trainer-Objekt
     * @throws IOException falls das Lesen fehlschlägt
     */
    public static WortTrainer readTrainer() throws IOException{
        WortListe liste = new WortListe();
        int check = 0, correct = 0;
        try (Scanner s = new Scanner(new BufferedReader(new FileReader("Default.txt")))){
            while (s.hasNextLine()){
                if(s.hasNextInt()){
                    check = s.nextInt();
                    correct = s.nextInt();
                    break;
                }
                else{
                    liste.addWortEintrag(s.next(), s.next());
                }
            }
        }
        return new WortTrainer(liste, check, correct);
    }
    /**
     * Liest ein Trainer Objekt aus einer Datei aus. In einer Zeile ist immer ein Name und eine URL enthalten und werden dem
     * neu erstellen Liste-Objekt hinzugefügt.
     * Falls das nächste Element ein Integer ist, so werden diese in check und correct gespeichert.
     * Aus der Liste, sowie check und correct wird ein neues Trainer-Objekt erstellt und zurückgegeben.
     * Augelesen eine vom Nutzer ausgewählte Datei.
     * @param filepath Dateipfad des auszulesenden Objekts
     * @return Trainer-Objekt
     * @throws IOException falls das Lesen fehlschlägt
     */
    public static WortTrainer readTrainer(String filepath) throws IOException{
        WortListe liste = new WortListe();
        int check = 0, correct = 0;
        try (Scanner s = new Scanner(new BufferedReader(new FileReader(filepath)))){
            while (s.hasNextLine()){
                if(s.hasNextInt()){
                    check = s.nextInt();
                    correct = s.nextInt();
                    break;
                }else{
                    liste.addWortEintrag(s.next(), s.next());
                }
            }
        }
        return new WortTrainer(liste, check, correct);
    }
}

