package com.optimissa.demo.controller;

import com.optimissa.demo.dto.EncryptDto;
import com.optimissa.demo.dto.EncryptRequestDto;
import com.optimissa.demo.service.AesEncryptionService;
import com.optimissa.demo.service.EncryptionService;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/cifrado")
public class EncryptionController {
    Logger logger = LoggerFactory.getLogger(EncryptionController.class);

    @Autowired
    private EncryptionService encryptionService;

    @PostMapping("/encrypt")
    public ResponseEntity<?> encrypt(@RequestBody EncryptRequestDto request) {
        try {
            EncryptDto result = encryptionService.encrypt(request.getTexto());
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error en /encrypt: {}", e.getMessage());
            return new ResponseEntity<String>("{\"error\":\"Servicio no disponible.\"}",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/decrypt")
    public ResponseEntity<?> decrypt(@RequestBody EncryptDto request) {
        try {
            String result = encryptionService.decrypt(request.getCifrado(), request.getIv());
            return new ResponseEntity<>(Map.of("texto", result), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error en /decrypt: {}", e.getMessage());
            return new ResponseEntity<String>("{\"error\":\"Servicio no disponible.\"}",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
