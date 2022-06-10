package com.example.application.views;

import com.example.application.controllers.BlocInformationController;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;


@PageTitle("test")
@Route(value = "test", layout = MainLayout.class)
public class TestView extends HorizontalLayout {
    @Autowired
    private BlocInformationController controller;
    @Value("${api.directus}")
    private String url;

    public TestView() {

    }
    @PostConstruct
    private void init(){
        controller.getBlocInformation();
        System.out.println(url);
    }
}
