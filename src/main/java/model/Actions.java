package model;

public enum Actions {
    ENTER("Enter"),
    RESET("Reset"),
    LOAD("Load trainer"),
    SAVE("Save trainer"),
    ADD("Add word");


    private String value;
    private Actions(String value){
        this.value = value;
    }
    public String getValue(){
        return this.value;
    }
}
