package com.example.application.views.blocinformation;

import com.example.application.controllers.BlocInformationController;
import com.example.application.data.BlocInformation;
import com.example.application.views.MainLayout;
import com.example.application.views.blocinformation.components.BlocInformationViewCard;
import com.example.application.views.blocinformation.components.BlocInformationViewCardError;
import com.example.application.views.blocinformation.components.BlocInformationViewCardGeneral;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.component.html.OrderedList;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

import java.util.List;

@PageTitle("BlocInformation")
@Route(value = "blocInformation", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class BlocInformationView extends Main implements HasComponents, HasStyle {

    private OrderedList imageContainer;

    public BlocInformationView() {
        constructUI();
        BlocInformationController blocInformationController = new BlocInformationController();
        List<BlocInformation> result = blocInformationController.getBlocInformation();

        if (result == null) {
            Notification.show("Une erreur est survenue", 10000, Notification.Position.TOP_CENTER);
            return;
        }

        for (BlocInformation bloc : result) {
            String image = ((bloc.getImageArrierePlan() == null) ? "https://www.projetcartylion.fr/app/uploads/2020/08/Placeholder.png" : "http://localhost:8055/assets/" + bloc.getImageArrierePlan());
            if (bloc.getErreurBloc() != null){
                //imageContainer.add(new BlocInformationViewCardError(bloc.getId(), image, bloc.getTitre(), bloc.getErreurBloc().getCodeJSONErreur().getType(), bloc.getErreurBloc().getCodeJSONErreur().getDescription()));
                imageContainer.add(new BlocInformationViewCardGeneral(bloc.getId(), image, bloc.getTitre(), null, null, bloc.getErreurBloc().getCodeJSONErreur().getType(), bloc.getErreurBloc().getCodeJSONErreur().getDescription()));
            }
            //else imageContainer.add(new BlocInformationViewCard(bloc.getId(), image, bloc.getTitre(), bloc.getDescription(), bloc.getLien()));
            else imageContainer.add(new BlocInformationViewCardGeneral(bloc.getId(), image, bloc.getTitre(), bloc.getDescription(), bloc.getLien(), null, null));
        }

    }

    private void constructUI() {
        addClassNames("bloc-information-view", "max-w-screen-lg", "mx-auto", "pb-l", "px-l");

        HorizontalLayout container = new HorizontalLayout();
        container.addClassNames("items-center", "justify-between");

        VerticalLayout headerContainer = new VerticalLayout();
        H2 header = new H2("Bloc Informations");
        header.addClassNames("mb-1", "mt-xl", "text-3xl");
        headerContainer.add(header);

        imageContainer = new OrderedList();
        imageContainer.addClassNames("gap-m", "grid", "list-none", "m-0", "p-0");

        container.add(header);
        add(container, imageContainer);

    }
}