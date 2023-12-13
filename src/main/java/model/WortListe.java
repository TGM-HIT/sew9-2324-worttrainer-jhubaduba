package model;

import java.util.ArrayList;

/**
 * Speichert mehrere Worteintraege in einem Array. Das Array ist dynamisch und eine Textausgabe des Arrays ist moeglich
 * @author Julian Huber
 * @version 7.10.2021
 */
public class WortListe {
    private ArrayList<WortEintrag> liste;

    /**
     * Konstruktor der eine leere Liste erzeugt
     */
    public WortListe(){
        this.liste = new ArrayList<>();
    }

    /**
     * Gettermethode für die Liste
     * @return Die Liste
     */
    public ArrayList<WortEintrag> getListe(){
        ArrayList<WortEintrag> tmp = new ArrayList<>();
        tmp.addAll(this.liste);
        return tmp;
    }
    /**
     * Methode zum hinzufuegen von einem Wort. Erstellt eine temporaeres Array, welches 1 groeßer ist als liste,
     * um die Eintraege zu uebertragen und das neue Wort hinzuzufuegen
     * @param wort Das Wort des Eintrags
     * @param url Die URL des Eintrags
     */
    public void addWortEintrag(String wort, String url) {
        this.liste.add(new WortEintrag(wort, url));
    }

    /**
     * Gibt den Worteintrag der Liste zurueck mithilfe eines Index
     * @param index Index in der Liste
     * @return Worteintrag an der Stelle des Index
     */
    public WortEintrag getWortEintrag(int index) throws IndexOutOfBoundsException{
        if(index < this.liste.size() && index >= 0) {
            return this.liste.get(index);
        } else{
            throw new IndexOutOfBoundsException("Index existiert nicht!");
        }
    }

    /**
     * Entfernt einen Eintrag mittels Übergabe des Worts des Eintrags. Sucht zuerst ob das Wort ueberhaupt enthalten ist und loescht in ggf. dann.
     * @param wort Das Wort des zu loeschenden Eintrags
     * @return Bestaetigung
     */
    public boolean removeWortEintrag(String wort) {
        return this.liste.removeIf(wortEintrag -> wortEintrag.getWort().equals(wort));
    }

    /**
     * Methode, welche alle Eintraege, mithilfe der WortEintrag toString() Methode, eine Ausgabe erzeugt.
     * @return Liste aller Eintraege von WortListe
     */
    @Override
    public String toString(){
        StringBuilder out = new StringBuilder();
        for (WortEintrag wortEintrag : this.liste) {
            out.append(wortEintrag.toString() + "\n");
        }
        return out.toString();
    }

    /**
     * Gibt die durchschnittliche Wortlaenge zurueck
     * @return die Wortlaenge
     */
    public double averageWordLength(){
        int allChars = 0;
        for(WortEintrag wortEintrag : this.liste){
            allChars += wortEintrag.getWort().length();
        }
        return allChars / (double) this.liste.size();
    }

    /**
     * Loescht alle Eintrage deren Wortlaenge kleiner ist als die angegebene
     * @param length die Laenge
     * @return Bestaetigung
     */
    public boolean filter(int length){
        return this.liste.removeIf(wortEintrag -> wortEintrag.getWort().length() < length);
    }
}
