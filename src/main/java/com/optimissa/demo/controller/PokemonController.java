package com.optimissa.demo.controller;

import com.optimissa.demo.service.PokemonService;
import com.optimissa.demo.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pokemon")
public class PokemonController {
    Logger logger = LoggerFactory.getLogger(PokemonController.class);
    @Autowired
    private PokemonService pokemonService;

    @GetMapping("/{pokemon}")
    public ResponseEntity<?> getDitto(@PathVariable("pokemon") String nombre){
        try{
            return new ResponseEntity<>(pokemonService.obtenerPokemon(nombre),HttpStatus.OK);
        } catch (BusinessException e) {
            logger.error(e.getError());
            return new ResponseEntity<String>("{\"error\":\"" + e.getError() + "\"}", HttpStatus.valueOf(e.getCode()));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<String>("{\"error\":\"Servicio no disponible.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/list/limit/{limit}/offset/{offset}")
    public ResponseEntity<?> getPokemonNames(@PathVariable int limit, @PathVariable int offset)  {
        try {
            return new ResponseEntity<>( pokemonService.getPokemonNames(limit, offset),HttpStatus.OK);
        } catch (BusinessException e) {
            logger.error(e.getError());
            return new ResponseEntity<String>("{\"error\":\"" + e.getError() + "\"}", HttpStatus.valueOf(e.getCode()));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<String>("{\"error\":\"Servicio no disponible.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
