package model;


/**
 * Objekt welches ein Wort und zugehörige Bild URL speichert. Die URL wird auf Gueltigkeit ueberprueft
 * @author Julian Huber
 * @version 26.09.2021
 */
public class WortEintrag {
    private String wort;
    private String url;


    /**
     * WortEintrag Konstruktor der die Attribute mittels Parametern setzt
     */
    public WortEintrag(String wort, String url){
        this.setWort(wort);
        this.setUrl(url);
    }

    /**
     * Settermethode für wort. Ueberprueft ob das String min. aus 2 Buchstaben besteht.
     * @param wort Das Wort welches gesetzt werden soll
     * @throws IllegalArgumentException wenn das String kleiner als 2 ist oder nicht aus Buchstaben besteht
     */
    public void setWort(String wort) throws IllegalArgumentException {
        if(wort.length() >= 2) {
            for(int i = 0; i < wort.length(); i++){
                if(!Character.isLetter(wort.charAt(i))){
                    throw new IllegalArgumentException("String muss mindestens aus 2 Buchstaben bestehen");
                }
            }
            this.wort = wort;
        }
        else{

             throw new IllegalArgumentException("String muss mindestens aus 2 Buchstaben bestehen");
        }
    }

    /**
     * Gettermethode für wort
     * @return wort
     */
    public String getWort(){
        return this.wort;
    }

    /**
     * Settermethode fuer die URL. Ueberprueft mithilfe der checkURL() Methode die Gueltigkeit. Wirft bei false eine Exception
     * @param url Die URL die uebernommen wird
     */
    public void setUrl(String url){
        if(WortEintrag.checkURL(url)){
            this.url = url;
        }
        else{
            throw new IllegalArgumentException("Ungültige URL");
        }
    }

    /**
     * Gettermethode der URL
     * @return Die URL
     */
    public String getUrl(){
        return this.url;
    }

    /**
     * Ueberprueft mithilfe einer regex. Sie Ueberprueft ob alle Elemente einer URL gegeben sind.
     * @param url Die zu pruefende URL
     * @return boolean Wert
     */
    public static boolean checkURL(String url){
        return (url.matches("(?:(?:https?|ftp):\\/\\/|\\b(?:[a-z\\d]+\\.))(?:(?:[^\\s()<>]+|\\((?:[^\\s()<>]+|(?:\\([^\\s()<>]+\\)))?\\))+(?:\\((?:[^\\s()<>]+|(?:\\(?:[^\\s()<>]+\\)))?\\)|[^\\s`!()\\[\\]{};:'\".,<>?«»“”‘’]))?"));
    }

    /**
     * Gibt die Attribute zusammen als String aus
     * @return String
     */
    @Override
    public String toString(){
        return this.getWort() + " " + this.getUrl();
    }


}

