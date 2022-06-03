package com.example.application.services;

import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class RestAPIService {

    public String callAPI(String url, HttpMethod httpMethod, Object body, MultiValueMap<String, String> headers) {
        final RestTemplate rt = new RestTemplate();
        try {
            HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
            rt.setRequestFactory(requestFactory);
            final HttpEntity<?> request = new HttpEntity<>(body, headers);
            final ResponseEntity<String> response = rt.exchange(url, httpMethod, request, String.class);
            if (response.getStatusCode().equals(HttpStatus.OK)) {
                return response.getBody();
            }
            else return response.getStatusCode().toString();
        } catch (final Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
