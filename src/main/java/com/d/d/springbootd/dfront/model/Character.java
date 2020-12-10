package com.d.d.springbootd.dfront.model;

public class Character {

    private int id;
    private String name;
    private String characterType;


    public Character() {
        this.id = -1;
        this.name = "";
        this.characterType = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCharacterType() {
        return characterType;
    }

    public void setCharacterType(String characterType) {
        this.characterType = characterType;
    }

    @Override
    public String toString() {
        return "Character{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", characterType=" + characterType +
                '}';
    }
}
