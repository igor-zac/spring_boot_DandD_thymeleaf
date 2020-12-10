package com.d.d.springbootd.dfront.services;

import com.d.d.springbootd.dfront.Application;
import com.d.d.springbootd.dfront.model.Character;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public class CharactersService {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @Autowired
    RestTemplate restTemplate;

    @Value("${characters-service-url}")
    private String url;

    public ResponseEntity<String> postCharacter(Character character)
    {
        ResponseEntity<String> response = null;

        try {
            URI uri = new URI(this.url);
            response = restTemplate.postForEntity(uri, character, String.class);
        } catch (URISyntaxException e){
            log.trace(e.getMessage());
        } catch (HttpClientErrorException e){
            log.trace(e.getMessage());
        }

        return response;
    }

    public ResponseEntity<List<Character>> getAllCharacters()
    {
        ResponseEntity<List<Character>> response = restTemplate.exchange(
                this.url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Character>>() {}
        );

        return response;
    }
}
