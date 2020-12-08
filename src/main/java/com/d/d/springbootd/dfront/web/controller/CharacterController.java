package com.d.d.springbootd.dfront.web.controller;

import com.d.d.springbootd.dfront.form.CharacterForm;
import com.d.d.springbootd.dfront.model.Character;
import com.d.d.springbootd.dfront.model.CharacterType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Controller
public class CharacterController {
    private static List<Character> characters = new ArrayList<>();
    private static int id;
    static {
        characters.add(new Character(1, "Dumbledore", CharacterType.WIZARD));
        characters.add(new Character(2, "Gandalf", CharacterType.WIZARD));
        characters.add(new Character(3, "Charlemagne", CharacterType.WARRIOR));
        id = 4;
    }

    private static HashSet<String> characterTypes = new HashSet<String>();
    static {
        for (CharacterType characterType : CharacterType.values()){
            characterTypes.add(characterType.name());
        }
    }

    @GetMapping(value={"/", "/characters"})
    public String index(Model model){

        model.addAttribute("characters", characters);

        return "characterList";
    }

    @GetMapping(value={"/characters/new"})
    public String create(Model model){
        CharacterForm characterForm = new CharacterForm();
        model.addAttribute("characterForm", characterForm);

        return "addCharacter";
    }

    @PostMapping(value={"/characters"})
    public String store(Model model, @ModelAttribute("characterForm") CharacterForm characterForm){

        String name = characterForm.getName();
        String characterType = characterForm.getCharacterType();

        if(name == null || characterType == null || name.length() < 2 || !characterTypes.contains(characterType)){
            String errorMessage = "Invalid values entered";
            model.addAttribute("errorMessage", errorMessage);
            return "addCharacter";
        }

        Character newCharacter = new Character();
        newCharacter.setName(name);
        newCharacter.setCharacterType(CharacterType.valueOf(characterType));

        characters.add(newCharacter);
        return "redirect:/characters";
    }

}
