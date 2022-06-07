package com.example.application.services;

import com.example.application.data.ErreurService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class RestAPIService {

    public Object callAPI(String url, HttpMethod httpMethod, Object body, MultiValueMap<String, String> headers, Class<?> clazz) {
        final RestTemplate rt = new RestTemplate();
        try {
            HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
            rt.setRequestFactory(requestFactory);
            final HttpEntity<?> request = new HttpEntity<>(body, headers);
            final ResponseEntity<String> response = rt.exchange(url, httpMethod, request, String.class);
            if (!response.getStatusCode().equals(HttpStatus.OK))
                System.out.println("Erreur à l'appelle de l'url : "+url+", erreur : "+response.getStatusCode());
            if (response.getBody() == null)
                return null;
            if (clazz == null)
                return null;
            String result = response.getBody().substring(8, response.getBody().length() - 1);
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(result, clazz);
        } catch (final Exception e) {
            System.out.println("erreur : "+e.getMessage());
            return null;
        }
    }
}
