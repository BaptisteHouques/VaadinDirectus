package com.example.application.views.blocinformation.components;

import com.example.application.controllers.BlocInformationController;
import com.example.application.data.BlocInformation;
import com.example.application.data.ErreurService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.radiobutton.RadioGroupVariant;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class BlocInformationViewCard extends ListItem {
    private Button button = newButton();
    private List<ErreurService> listErreurs;
    private final BlocInformationController blocController = new BlocInformationController();

    public BlocInformationViewCard(int blocId, String blocImage, String blocTitre, String blocDescription, String blocLien, ErreurService blocError) {
        addClassNames("bg-contrast-5", "flex", "flex-col", "items-start", "p-m", "rounded-l");

        Div div = new Div();
        div.addClassNames("bg-contrast", "flex items-center", "justify-center", "mb-m", "overflow-hidden",
                "rounded-m w-full");
        div.setHeight("160px");

        Span header = new Span();
        header.addClassNames("text-xl", "font-semibold");
        header.setText(blocTitre);

        add(div, header);

        Image image = new Image();
        image.setWidth("100%");
        image.setSrc(blocImage);
        image.setAlt("");

        Span errorType = new Span();
        Paragraph description = new Paragraph(blocDescription);

        if (blocError != null ) {
            errorTypeStyle(errorType, blocError);
            descriptionStyle(description, blocError);

            div.add(image);
            add(errorType, description);
            removeErrorButton(button, blocId, errorType, description);
        }
        else {
            description.setText(blocDescription);

            Anchor anchor = new Anchor();
            anchor.setHref(blocLien);
            anchor.setTarget("_blank");

            anchor.add(image);
            div.add(anchor);
            add(description);
            setErrorButton(button, blocId, errorType, description);
        }

        add(button);
    }

    private List<ErreurService> getListErreurs() {
        if (listErreurs == null)
            listErreurs = blocController.getErreurService();
        return listErreurs;
    }

    private void removeErrorButton(Button button, Integer blocId, Span errorType, Paragraph description) {
        button.setText("Rendre Accessible");
        button.getStyle().set("color", "green");
        button.getStyle().set("border-color", "green");
        button.addClickListener((e) -> {
            BlocInformation bloc = blocController.updateBlocError(blocId, null);
            description.getStyle().clear();
            description.setText(bloc.getDescription());
            remove(errorType, button);

            Button button2 = newButton();
            add(button2);
            setErrorButton(button2, blocId, errorType, description);
        });
    }

    private void setErrorButton(Button button, Integer blocId, Span errorType, Paragraph description) {
        button.setText("Rendre Inaccessible");
        button.getStyle().set("color", "red");
        button.getStyle().set("border-color", "red");

        button.addClickListener((event1) -> {
            List<String> erreurListString = new ArrayList<>();
            for (ErreurService erreur : getListErreurs()) {
                erreurListString.add(erreur.getTitreErreur());
            }

            RadioButtonGroup<String> erreurRadioList = new RadioButtonGroup<>();
            erreurRadioList.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
            erreurRadioList.setLabel("Sélectionner une erreur");
            erreurRadioList.setItems(erreurListString);
            //erreurRadioList.setItems(getListErreurs().stream().reduce(e -> e.getTitreErreur()).get());
            erreurRadioList.addClassName("mb-m");

            remove(description, button);

            Button save = newButton();
            save.setText("Valider");
            save.getStyle().remove("color");
            save.getStyle().remove("border-color");

            add(erreurRadioList, save);

            save.addClickListener(event2 -> {
                if (erreurRadioList.getValue() == null) {
                    Notification.show("Veuillez sélectionner une option", 5000, Notification.Position.TOP_CENTER);
                } else {
                    Optional<ErreurService> optionalErreur = getListErreurs().stream().filter(e -> Objects.equals(e.getTitreErreur(), erreurRadioList.getValue())).findFirst();
                    if (optionalErreur.isEmpty()) {
                        Notification.show("Erreur : ça ne devrait pas arriver", 5000, Notification.Position.TOP_CENTER);
                        return;
                    }

                    ErreurService erreur = optionalErreur.get();
                    blocController.updateBlocError(blocId, erreur.getId());
                    remove(erreurRadioList, save);

                    errorTypeStyle(errorType, erreur);
                    descriptionStyle(description, erreur);

                    Button button2 = newButton();

                    add(errorType, description, button2);
                    removeErrorButton(button2, blocId, errorType, description);
                }
            });
        });
    }

    private Button newButton(){
        button = new Button();
        button.setWidth("80%");
        button.getStyle().set("margin", "auto auto 0");
        button.getStyle().set("background-color", "light-gray");
        button.getStyle().set("border", "solid 1px");
        button.getStyle().set("cursor", "pointer");

        return button;
    }
    private void errorTypeStyle(Span errorType, ErreurService blocError) {
        errorType.addClassNames("text-xl", "font-semibold");
        errorType.setText(blocError.getCodeJSONErreur().getType());
        errorType.getStyle().set("color", "red");
        errorType.getStyle().set("text-transform", "uppercase");
        errorType.getStyle().set("margin-top", "10px");
    }

    private void descriptionStyle(Paragraph description, ErreurService erreur) {
        description.setText(erreur.getCodeJSONErreur().getDescription());
        description.addClassName("my-m");
        description.getStyle().set("color", "red");
        description.getStyle().set("text-transform", "uppercase");
    }
}
