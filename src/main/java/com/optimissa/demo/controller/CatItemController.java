package com.optimissa.demo.controller;

import com.optimissa.demo.dto.CatalogueRequestDto;
import com.optimissa.demo.exception.BusinessException;
import com.optimissa.demo.model.CatItem;
import com.optimissa.demo.service.CatItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/catalogo")
public class CatItemController {

    Logger logger = LoggerFactory.getLogger(AuthController.class);
    @Autowired
    private CatItemService itemService;
    @PostMapping
    public ResponseEntity<?> listar(@RequestBody CatalogueRequestDto request) {

    try{

        return new ResponseEntity<>(itemService.getItems(request.getNombre()),HttpStatus.OK);

    }  catch (Exception e) {
        logger.error(e.getMessage());
        return new ResponseEntity<String>("{\"error\":\"Servicio no disponible.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    }
}