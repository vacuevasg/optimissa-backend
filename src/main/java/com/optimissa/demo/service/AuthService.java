package com.optimissa.demo.service;


import com.optimissa.demo.dto.SecurityRequestDto;
import com.optimissa.demo.exception.BusinessException;
import com.optimissa.demo.model.AuthUser;
import com.optimissa.demo.repository.AuthUserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AuthService {

    @Autowired
    private AuthUserRepository authUserRepository;

    Logger logger = LoggerFactory.getLogger(AuthService.class);

    public AuthUser login(SecurityRequestDto securityRequest) throws BusinessException {
        AuthUser user =  authUserRepository.findByEmail(securityRequest.getEmail()).orElseThrow(()-> new BusinessException("Invalid user information.",401));

        return user;
    }

    public UserDetails getData(String email){
        try{

            AuthUser u =  authUserRepository.findByEmail(email).orElseThrow(()-> new RuntimeException("Invalid User information."));

            UserDetails userDetails = new User(u.getEmail(), u.getPassword(), new ArrayList<>());

            return userDetails;

        }catch (Exception e){
            logger.error(e.getMessage());
        }

    return null;
    }

}
