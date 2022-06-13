package com.example.application.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

// Service générique pour des appels API REST au CMS Directus, utilisant le service RestTemplate de Spring.
// Le paramètre clazz peut être une classe de n'importe quel type.
// Ce paramètre va servir à mapper la réponse obtenue, suite à la requête, avec la classe souhaitée.
@Service
public class RestAPIService {
    public Object callAPI(String url, HttpMethod httpMethod, Object body, MultiValueMap<String, String> headers, Class<?> clazz) {
        final RestTemplate rt = new RestTemplate();
        try {
            HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
            rt.setRequestFactory(requestFactory);
            final HttpEntity<?> request = new HttpEntity<>(body, headers);
            // On renseigne la class String comme RequestEntity afin de modifier la réponse avant de la mapper.
            final ResponseEntity<String> response = rt.exchange(url, httpMethod, request, String.class);
            if (!response.getStatusCode().equals(HttpStatus.OK))
                System.out.println("Erreur à l'appelle de l'url : "+url+", erreur : "+response.getStatusCode());
            if (response.getBody() == null || clazz == null)
                return "";
            // Directus envoi la réponse d'une requête en stockant les données dans un attribut "data".
            // On modifie la chaine de charactère pour enlever cet attribut.
            String result = response.getBody().substring(8, response.getBody().length() - 1);
            ObjectMapper mapper = new ObjectMapper();
            // Mapping de la nouvelle chaine de charactère en fonction du paramètre clazz.
            return mapper.readValue(result, clazz);
        } catch (final Exception e) {
            System.out.println("erreur : "+e.getMessage());
            return null;
        }
    }
}
