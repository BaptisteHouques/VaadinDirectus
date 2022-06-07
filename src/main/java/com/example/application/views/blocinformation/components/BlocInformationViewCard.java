package com.example.application.views.blocinformation.components;

import com.example.application.controllers.BlocInformationController;
import com.example.application.data.BlocInformation;
import com.example.application.data.ErreurService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.radiobutton.RadioGroupVariant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class BlocInformationViewCard extends ListItem{

    public BlocInformationViewCard(int blocId, String blocImage, String blocTitre, String blocDescription, String blocLien) {
        addClassNames("bg-contrast-5", "flex", "flex-col", "items-start", "p-m", "rounded-l");

        Div div = new Div();
        div.addClassNames("bg-contrast", "flex items-center", "justify-center", "mb-m", "overflow-hidden",
                "rounded-m w-full");
        div.setHeight("160px");

        Anchor anchor = new Anchor();
        anchor.setHref(blocLien);
        anchor.setTarget("_blank");

        Image image = new Image();
        image.setWidth("100%");
        image.setSrc(blocImage);
        image.setAlt("");

        anchor.add(image);

        div.add(anchor);

        Span header = new Span();
        header.addClassNames("text-xl", "font-semibold");
        header.setText(blocTitre);

        Paragraph description = new Paragraph(blocDescription);
        description.addClassName("my-m");

        Button button = new Button();
        button.setText("Rendre Inaccessible");
        button.setWidth("80%");

        //Button style
        button.getStyle().set("margin", "auto auto 0");
        button.getStyle().set("background-color", "light-gray");
        button.getStyle().set("color", "red");
        button.getStyle().set("border", "solid 1px");
        button.getStyle().set("border-color", "red");
        button.getStyle().set("cursor", "pointer");

        button.addClickListener((event1) -> {
            BlocInformationController blocController = new BlocInformationController();
            List<ErreurService> erreurServiceList = blocController.getErreurService();

            List<String> erreurListString = new ArrayList<>();
            for (ErreurService erreur : erreurServiceList) {
                erreurListString.add(erreur.getTitreErreur());
            }

            RadioButtonGroup<String> erreurRadioList = new RadioButtonGroup<>();
            erreurRadioList.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
            erreurRadioList.setLabel("Sélectionner une erreur");
            erreurRadioList.setItems(erreurListString);

            add(erreurRadioList);

            Button save = new Button("Valider");
            save.setWidth("80%");
            save.getStyle().set("margin", "auto auto 0");
            save.getStyle().set("background-color", "light-gray");
            save.getStyle().set("border", "solid 1px");
            save.getStyle().set("cursor", "pointer");
            save.addClickListener(event2 -> {
                if (erreurRadioList.getValue() == null){
                    Notification.show("Veuillez sélectionner une option", 5000, Notification.Position.TOP_CENTER);
                }
                else {
                    ErreurService erreur = erreurServiceList.stream().filter(e -> Objects.equals(e.getTitreErreur(), erreurRadioList.getValue())).findFirst().get();
                    blocController.updateBlocError(blocId, erreur.getId());
                    remove(erreurRadioList, save);

                    Span errorType = new Span();
                    errorType.addClassNames("text-xl", "font-semibold");
                    errorType.setText(erreur.getCodeJSONErreur().getType());
                    errorType.getStyle().set("color", "red");
                    errorType.getStyle().set("text-transform", "uppercase");
                    errorType.getStyle().set("margin-top", "10px");

                    Paragraph errorDescription = new Paragraph(erreur.getCodeJSONErreur().getDescription());
                    errorDescription.addClassName("my-m");
                    errorDescription.getStyle().set("color", "red");
                    errorDescription.getStyle().set("text-transform", "uppercase");

                    save.setText("Rendre Accessible");
                    save.getStyle().set("color", "grey");
                    save.getStyle().set("border-color", "grey");
                    save.getElement().setProperty("title", "Inactif jusqu'au rechargement de la page");

                    add(errorType, errorDescription, save);
                }
            });

            remove(description, button);
            add(save);
        });

        add(div, header, description, button);

    }
}
