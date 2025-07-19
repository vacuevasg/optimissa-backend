package com.optimissa.demo.service;

import com.optimissa.demo.exception.BusinessException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PokemonService {

    private final RestTemplateService restTemplateService;

    @Autowired
    public PokemonService(RestTemplateService restTemplateService) {
        this.restTemplateService = restTemplateService;
    }

    public String getDitto() throws BusinessException {
        String url = "https://pokeapi.co/api/v2/pokemon/ditto";
        return restTemplateService.sendRequest(url, HttpMethod.GET, HttpEntity.EMPTY);
    }

    public String obtenerPokemon(String nombre) throws BusinessException {
        String url = "https://pokeapi.co/api/v2/pokemon/"+nombre;
        return restTemplateService.sendRequest(url, HttpMethod.GET, HttpEntity.EMPTY);
    }

    public List<String> getPokemonNames(int limit, int offset) throws BusinessException {
        String url = String.format("https://pokeapi.co/api/v2/pokemon?limit=%d&offset=%d", limit, offset);
        String response = restTemplateService.sendRequest(url, HttpMethod.GET, HttpEntity.EMPTY);

        JSONObject json = new JSONObject(response);
        JSONArray results = json.getJSONArray("results");

        List<String> names = new ArrayList<>();
        for (int i = 0; i < results.length(); i++) {
            names.add(results.getJSONObject(i).getString("name"));
        }
        return names;
    }
}
