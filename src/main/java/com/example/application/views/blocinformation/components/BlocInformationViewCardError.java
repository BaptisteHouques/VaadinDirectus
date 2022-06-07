package com.example.application.views.blocinformation.components;

import com.example.application.controllers.BlocInformationController;
import com.example.application.data.BlocInformation;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.charts.model.Tooltip;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.notification.Notification;

public class BlocInformationViewCardError extends ListItem{
    public BlocInformationViewCardError(int blocId, String blocImage, String blocTitre, String blocErrorType, String blocErrorDesc) {
        addClassNames("bg-contrast-5", "flex", "flex-col", "items-start", "p-m", "rounded-l");
        setId("bloc-"+blocId);

        Div div = new Div();
        div.addClassNames("bg-contrast", "flex items-center", "justify-center", "mb-m", "overflow-hidden",
                "rounded-m w-full");
        div.setHeight("160px");

        Image image = new Image();
        image.setWidth("100%");
        image.setSrc(blocImage);
        image.setAlt("");

        div.add(image);

        Span header = new Span();
        header.addClassNames("text-xl", "font-semibold");
        header.setText(blocTitre);

        Span errorType = new Span();
        errorType.addClassNames("text-xl", "font-semibold");
        errorType.setText(blocErrorType);
        errorType.getStyle().set("color", "red");
        errorType.getStyle().set("text-transform", "uppercase");
        errorType.getStyle().set("margin-top", "10px");

        Paragraph errorDescription = new Paragraph(blocErrorDesc);
        errorDescription.addClassName("my-m");
        errorDescription.getStyle().set("color", "red");
        errorDescription.getStyle().set("text-transform", "uppercase");

        Button button = new Button();
        button.setText("Rendre Accessible");
        button.setWidth("80%");
        button.getStyle().set("margin", "auto auto 0");
        button.getStyle().set("background-color", "light-gray");
        button.getStyle().set("color", "green");
        button.getStyle().set("border", "solid 1px");
        button.getStyle().set("border-color", "green");
        button.getStyle().set("cursor", "pointer");
        button.addClickListener((e) -> {
            BlocInformationController blocInformationController = new BlocInformationController();
            BlocInformation bloc = blocInformationController.updateBlocError(blocId, null);
            Paragraph description = new Paragraph("test");
            description.addClassName("my-m");
            errorDescription.getStyle().clear();
            errorDescription.setText(bloc.getDescription());

            button.setText("Rendre Inaccessible");
            button.getStyle().set("color", "grey");
            button.getStyle().set("border-color", "grey");
            button.getElement().setProperty("title", "Inactif jusqu'au rechargement de la page");

            remove(errorType);
        });

        add(div, header, errorType, errorDescription, button);

    }
}
