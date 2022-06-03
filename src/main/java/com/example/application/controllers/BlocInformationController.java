package com.example.application.controllers;

import com.example.application.data.BlocInformation;
import com.example.application.data.ErreurService;
import com.example.application.services.RestAPIService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class BlocInformationController {
    final RestAPIService blocService = new RestAPIService();

        public List<BlocInformation> getBlocInformation() {
        String url = "http://localhost:8055/items/bloc_information/?fields=*,Erreur_Bloc.*";
            Object response = this.blocService.callAPI(url, HttpMethod.GET, null, null, BlocInformation[].class);
            if (response instanceof BlocInformation[]) {
                return Arrays.asList((BlocInformation[]) response);
            }
            return null;
    }

    public List<ErreurService> getErreurService() {
        String url = "http://localhost:8055/items/erreur_service";
        Object response = this.blocService.callAPI(url, HttpMethod.GET, null, null, ErreurService[].class);
        if (response instanceof ErreurService[]) {
            return Arrays.asList((ErreurService[]) response);
        }
        return null;
    }

    public BlocInformation updateBlocError(int idBloc) {
        String url = "http://localhost:8055/items/bloc_information/" + idBloc;
        String token = "t";
        Integer errorValue = null;
        String body = "{\"Erreur_Bloc\":"+errorValue+"}";
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.set("Authorization", "Bearer "+token);
        headers.set("Content-Type", "application/json");
        Object response = blocService.callAPI(url, HttpMethod.PATCH, body, headers, BlocInformation.class);
        if (response instanceof BlocInformation) {
            return (BlocInformation) response;
        }
        return null;
    }
}
