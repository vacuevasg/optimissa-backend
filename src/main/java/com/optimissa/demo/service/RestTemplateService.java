package com.optimissa.demo.service;

import com.optimissa.demo.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class RestTemplateService {

    @Autowired
    private RestTemplate restTemplate;

    public String sendRequest(String url, HttpMethod method, HttpEntity<?> entity) throws BusinessException {
        try {
            ResponseEntity<String> response = restTemplate.exchange(url, method, entity, String.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            } else {
                throw new BusinessException("Error: " + response.getBody(), response.getStatusCode().value());
            }
        } catch (HttpClientErrorException e) {
            throw new BusinessException("API Error: " + e.getResponseBodyAsString(), e.getStatusCode().value());
        }
    }
}
