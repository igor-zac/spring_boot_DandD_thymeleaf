package com.d.d.springbootd.dfront.web.controller;

import com.d.d.springbootd.dfront.form.CharacterForm;
import com.d.d.springbootd.dfront.model.Character;
import com.d.d.springbootd.dfront.services.CharactersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CharacterController {

    private static List<Character> characters = new ArrayList<>();

    @Autowired
    private CharactersService charactersService;


    @GetMapping(value={"/", "/characters"})
    public String index(Model model)
    {
        characters = charactersService.getAllCharacters().getBody();

        model.addAttribute("characters", characters);

        return "characterList";
    }

    @GetMapping(value="/characters/{id}")
    public String show(Model model, @PathVariable int id)
    {
        Character character = findById(id);

        if (character == null) {
            character = charactersService.getCharacter(id).getBody();
        }

        if (character == null) {

            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Page not found"
            );
        }

        characters.add(character);

        model.addAttribute("character", character);

        return "characterDetail";
    }

    @GetMapping(value={"/characters/new"})
    public String create(Model model)
    {
        CharacterForm characterForm = new CharacterForm();
        model.addAttribute("characterForm", characterForm);

        return "addCharacter";
    }

    @PostMapping(value={"/characters"})
    public String store(Model model, @ModelAttribute("characterForm") CharacterForm characterForm)
    {
        Character newCharacter = new Character();
        newCharacter.setName(characterForm.getName());
        newCharacter.setCharacterType(characterForm.getCharacterType());

        ResponseEntity<String> response = charactersService.postCharacter(newCharacter);

        if (response == null) {
            String errorMessage = "Invalid values entered";
            model.addAttribute("errorMessage", errorMessage);
            return "addCharacter";
        }

        return "redirect:/characters";
    }

    private Character findById(int id)
    {
        Character character;

        for (Character possibleCharacter : characters){
            if(possibleCharacter.getId() == id){
                character = possibleCharacter;
                return character;
            }
        }

        return null;
    }

}
