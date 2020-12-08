package com.d.d.springbootd.dfront.form;

public class CharacterForm {

    private String name;
    private String characterType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCharacterType() {
        if(characterType != null)
            return characterType.toUpperCase();

        return characterType;
    }

    public void setCharacterType(String characterType) {
        this.characterType = characterType;
    }
}
