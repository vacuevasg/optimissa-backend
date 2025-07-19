package com.optimissa.demo.controller;



import com.optimissa.demo.dto.AuthResponseDto;
import com.optimissa.demo.dto.SecurityRequestDto;
import com.optimissa.demo.exception.BusinessException;
import com.optimissa.demo.model.AuthUser;
import com.optimissa.demo.security.JwtUtils;
import com.optimissa.demo.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private PasswordEncoder bcryptEncoder;
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody SecurityRequestDto request) {
        try {
            AuthUser user = authService.login(request);
            if(user == null){throw new BusinessException("Credenciales invalidas.",401);}

            if (!bcryptEncoder.matches(request.getPassword(), user.getPassword())) {
                throw new BusinessException("Credenciales invalidas.",401);
            }

            String token =  jwtUtils.generateToken(user.getEmail(), user.getId());
            AuthResponseDto auth = new AuthResponseDto();

            auth.setId(user.getId());
            auth.setToken(token);
            return ResponseEntity.ok(auth);
        } catch (BusinessException e) {
            logger.error(e.getError());
            return new ResponseEntity<String>("{\"error\":\"" + e.getError() + "\"}", HttpStatus.valueOf(e.getCode()));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<String>("{\"error\":\"Servicio no disponible.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }



}