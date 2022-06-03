package com.example.application.views.blocinformation;

import com.example.application.controllers.BlocInformationController;
import com.example.application.data.BlocInformation;
import com.example.application.data.ErreurService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.radiobutton.RadioGroupVariant;

import java.util.Arrays;
import java.util.List;

public class BlocInformationViewCard extends ListItem{
    private Integer blocId;

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

        button.addClickListener((e) -> {
            BlocInformationController blocController = new BlocInformationController();
            List<ErreurService> erreurServiceList = blocController.getErreurService();

            List<String> erreurList = null;

//            for (ErreurService erreur : erreurServiceList) {
//                List<String> erreurList = List.of(erreur.getTitreErreur());
//            }

            RadioButtonGroup<String> radioGroup = new RadioButtonGroup<>();
            radioGroup.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
            radioGroup.setLabel("Travel class");
            //radioGroup.setItems(list);

            add(radioGroup);
        });

        add(div, header, description, button);

    }
}
