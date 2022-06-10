package com.example.application.controllers;

import com.example.application.data.BlocInformation;
import com.example.application.data.ErreurService;
import com.example.application.services.RestAPIService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class BlocInformationController {
    @Autowired
    RestAPIService blocService;
    @Value("${directus.token}")
    private String directusToken;
    @Value("${api.directus}")
    private String apiDirectus;
    @Value("${api.getBlocInformation}")
    private String apiGetBlocInformation;
    @Value("${api.withErreur}")
    private String apiWithErreur;
    @Value("${api.getErreurService}")
    private String apiGetErreurService;

    public List<BlocInformation> getBlocInformation() {
        String url = apiDirectus+apiGetBlocInformation+apiWithErreur;
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.set("Authorization", "Bearer "+directusToken);
        Object response = this.blocService.callAPI(url, HttpMethod.GET, null, headers, BlocInformation[].class);
        if (response instanceof BlocInformation[])
            return Arrays.asList((BlocInformation[]) response);
        if (response == "")
            return new ArrayList<>();
        return null;
    }
    public List<ErreurService> getErreurService() {
        String url = apiDirectus+apiGetErreurService;
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.set("Authorization", "Bearer "+directusToken);
        Object response = this.blocService.callAPI(url, HttpMethod.GET, null, headers, ErreurService[].class);
        if (response instanceof ErreurService[])
            return Arrays.asList((ErreurService[]) response);
        if (response == "")
            return new ArrayList<>();
        return null;
    }

    public BlocInformation updateBlocError(int idBloc, Integer errorValue) {
        String url = apiDirectus+apiGetBlocInformation + "/" + idBloc + apiWithErreur;
        String body = "{\"Erreur\":"+errorValue+"}";
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.set("Authorization", "Bearer "+directusToken);
        headers.set("Content-Type", "application/json");
        Object response = blocService.callAPI(url, HttpMethod.PATCH, body, headers, BlocInformation.class);

        if (response instanceof BlocInformation)
            return (BlocInformation) response;

        System.out.println(response);
        return null;
    }
}
