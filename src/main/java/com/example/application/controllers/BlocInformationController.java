package com.example.application.controllers;

import com.example.application.data.BlocInformation;
import com.example.application.data.ErreurBloc;
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
        String response = this.blocService.callAPI(url, HttpMethod.GET, null, null);
        if (response != null) {
            String result = response.substring(8, response.length() - 1);
            ObjectMapper mapper = new ObjectMapper();
            List<BlocInformation> blocInformationList = null;
            try {
                blocInformationList = Arrays.asList(mapper.readValue(result, BlocInformation[].class));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return blocInformationList;
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
        final RestAPIService blocService = new RestAPIService();
        String response = blocService.callAPI(url, HttpMethod.PATCH, body, headers);
        if (response != null) {
            String result = response.substring(8, response.length() - 1);
            ObjectMapper mapper = new ObjectMapper();
            BlocInformation blocInformation = null;
            try {
                blocInformation = mapper.readValue(result, BlocInformation.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return blocInformation;
        }
        return null;
    }
}
