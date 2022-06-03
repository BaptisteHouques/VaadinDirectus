package com.example.application.views;

import com.example.application.controllers.BlocInformationController;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@PageTitle("test")
@Route(value = "test", layout = MainLayout.class)
public class TestView extends HorizontalLayout {
//    @Autowired
//    private BlocInformationService bis;

    public TestView() {
        BlocInformationController bis = new BlocInformationController();
        //bis.getBlocInformation();
    }
}
