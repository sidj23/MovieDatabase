package com.sid.moviedatabase.Model;

public class RecyclerCastModel {

    String name,character,profUrl;

    public RecyclerCastModel(String name, String character, String profUrl) {
        this.name = name;
        this.character = character;
        this.profUrl = profUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getProfUrl() {
        return profUrl;
    }

    public void setProfUrl(String profUrl) {
        this.profUrl = profUrl;
    }
}
