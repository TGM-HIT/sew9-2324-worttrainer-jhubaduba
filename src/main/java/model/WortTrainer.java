package model;

import java.util.Random;

/**
 * WortTrainer Klasse
 * Ueberprueft Eingaben, liest zufaellige Eintraege aus und speichert auch den zuletzt ausgewaehlten Eintrag
 * @author Julian Huber
 * @version 15.11.2021
 */
public class WortTrainer{
    private WortListe liste;
    private int randomIndex = -1;
    private int checks = 0;
    private int correct = 0;

    /**
     * Konstruktor ohne Parameter.
     * Erstellt leere Liste
     */
    public WortTrainer(){
        this.liste = new WortListe();
    }
    /**
     * Erstellt Worttrainer mit der übergebenen Liste
     * @param liste Die übergebene Liste
     */
    public WortTrainer(WortListe liste){
        this.liste = liste;
    }
    /**
     * Erstellt Worttrainer mit der übergebenen Liste
     * @param liste Die übergebene Liste
     */
    public WortTrainer(WortListe liste, int checks, int correct){
        this.liste = liste;
        this.checks = checks;
        this.correct = correct;
    }

    /**
     * Methode zum Hinzufuegen von einem Eintrag. Verwendet die gleichnamige WortListe Methode
     * @param wort Wort String
     * @param url URL String
     */
    public void addWortEintrag(String wort, String url){
        this.liste.addWortEintrag(wort, url);
    }

    /**
     * Methode zum Entfernen eines Eintrags. Verwendet die gleichnamige WortListe Methode
     * @param wort Wort String
     */
    public void removeWortEintrag(String wort){
        this.liste.removeWortEintrag(wort);
    }
    /**
     * Wählt einen zufälligen Index aus und gibt den WortEintrag zurueck. Speichert den gesuchten Index als Attribut
     * @return Einen WortEintrag
     */
    public WortEintrag getRandomWortEintrag(){
        Random r = new Random();
        this.randomIndex = r.nextInt(this.liste.getListe().size());
        return liste.getWortEintrag(this.randomIndex);
    }

    /**
     * Gibt den WortEintrag vom im Attribut gespeicherten Index zurueck
     * @return WortEintrag
     * @throws IllegalArgumentException wenn randomIndex noch keinen Wert hat -> getRandomEintrag() noch nicht zumindest einmal ausgeführt
     */
    public WortEintrag getCurrentWortEintrag() throws IllegalArgumentException{
        if(randomIndex != -1){
            return liste.getWortEintrag(this.randomIndex);
        }
        else{
            throw new IllegalArgumentException("Keinen Eintrag ausgewählt");
        }
    }

    /**
     * Methode die das Wort des ausgewaehlten Eintrags ausgibt
     * @return Wort als String
     */
    public String getWort(){
        return this.getCurrentWortEintrag().getWort();
    }

    /**
     * Methode die die URL des ausgewaehlten Eintrags ausgibt
     * @return URL als String
     */
    public String getUrl(){
        return this.getCurrentWortEintrag().getUrl();
    }

    /**
     * Überprüft Eingabe mit dem zuletzt ausgewaehlten Eintrag
     * @param wort Eingabe
     * @return boolean
     */
    public boolean check(String wort){
        this.checks++;
        if(wort.equals(this.getCurrentWortEintrag().getWort())){
            this.correct++;
            return true;
        } else{
            return false;
        }
    }

    /**
     * Überprüft Eingabe mit dem zuletzt ausgewaehlten Eintrag, case insensitive
     * @param wort Eingabe
     * @return boolean
     */
    public boolean checkIgnoreCase(String wort){
        this.checks++;
        wort = wort.toLowerCase();
        String wortEintrag = this.getCurrentWortEintrag().getWort().toLowerCase();
        if(wort.equals(wortEintrag)){
            this.correct++;
            return true;
        } else {
            return true;
        }
    }

    /**
     * Gettermethode fuer die ausgefuehrten checks
     * @return checks
     */
    public int getChecks(){
        return this.checks;
    }

    /**
     * Gettermethode fuer die erfolgreichen checks
     * @return correct
     */
    public int getCorrect(){
        return this.correct;
    }

    /**
     * Methode zum Ausgeben der Statistik in einem String
     * @return String
     */
    public String printStats(){
        return "Einträge überprüft: " + this.getChecks() + "\ndavon korrekt: " + this.getCorrect();
    }

    /**
     * Methode zum Ausgeben eines Strings, welches die Speichermethoden verwenden
     * @return String
     */
    public String printSaveString(){
        return this.liste.toString() + "\n" + this.getChecks() + " " + this.getCorrect();
    }

    /**
     * Methode zum Ausgeben eines String, welches für die Nutzer vogesehen ist.
     * @return String
     */
    @Override
    public String toString(){
        return this.liste.toString() + "\n" +  this.printStats();
    }

    /**
     * Methode zum Zuruecksetzen der Statistik.
     */
    public void resetStats(){
        this.correct = 0;
        this.checks = 0;
    }
}
