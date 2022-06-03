package com.example.application.views.blocinformation;

import com.example.application.controllers.BlocInformationController;
import com.example.application.data.BlocInformation;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.charts.model.Tooltip;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.notification.Notification;

public class BlocInformationViewCardError extends ListItem{
    private Integer blocId;
    public BlocInformationViewCardError(int blocId, String blocImage, String blocTitre, String blocErrorType, String blocErrorDesc) {
        this.blocId = blocId;
        //String id = String.valueOf(blocId);
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
        button.getStyle().set("background-color", "light-grey");
        button.getStyle().set("color", "green");
        button.getStyle().set("border", "solid 1px");
        button.getStyle().set("border-color", "green");
        button.getStyle().set("cursor", "pointer");
        button.addClickListener((e) -> {
            BlocInformationController blocInformationController = new BlocInformationController();
            BlocInformation bloc = blocInformationController.updateBlocError(blocId);
            Paragraph description = new Paragraph("test");
            description.addClassName("my-m");
            errorDescription.getStyle().clear();
            errorDescription.setText(bloc.getDescription());

            Button button2 = new Button();
            button2.setText("Rendre Inaccessible");
            button2.setWidth("80%");
            button2.getStyle().set("margin", "auto auto 0");
            button2.getStyle().set("background-color", "light-gray");
            button2.getStyle().set("color", "grey");
            button2.getStyle().set("border", "solid 1px");
            button2.getStyle().set("border-color", "grey");
            button2.getElement().setProperty("title", "Inactif jusqu'au rechargement de la page");

            remove(errorType, button);
            add(button2);
        });

        add(div, header, errorType, errorDescription, button);

    }
}
