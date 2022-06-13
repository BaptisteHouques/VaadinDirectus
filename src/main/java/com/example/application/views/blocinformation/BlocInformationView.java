package com.example.application.views.blocinformation;

import com.example.application.controllers.BlocInformationController;
import com.example.application.data.BlocInformation;
import com.example.application.views.MainLayout;
import com.example.application.views.blocinformation.components.BlocInformationViewCard;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.component.html.OrderedList;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;

@PageTitle("BlocInformation")
@Route(value = "blocInformation", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class BlocInformationView extends Main implements HasComponents, HasStyle {
    private OrderedList imageContainer;
    @Autowired
    BlocInformationController blocInformationController;
    @Autowired
    private ObjectFactory<BlocInformationViewCard> blocInformationViewCardFactory;
    public BlocInformationView() {
    }

    // Méthode appelée avant le constructeur faisant appel à la méthode constructUI et à la méthode du constructeur des blocs d'information pour les récupérer.
    // Remplit la liste imageContainer de composant card ayant les données des bloc d'information récupérés.
    @PostConstruct
    private void init() {
        constructUI();
        List<BlocInformation> result = blocInformationController.getBlocInformation();

        if (result == null) {
            Notification notification = Notification.show("Une erreur est survenue, merci de réessayer plus tard", 5000, Notification.Position.TOP_CENTER);
            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            return;
        }

        if (result.size() == 0) {
            add(new H3("Aucun bloc d'information disponible"));
            return;
        }

        for (BlocInformation bloc : result) {
            String image = ((bloc.getImage() == null) ? "https://www.projetcartylion.fr/app/uploads/2020/08/Placeholder.png" : "https://cms-test.univ-lorraine.fr/assets/" + bloc.getImage());
            BlocInformationViewCard blocInformationViewCard = blocInformationViewCardFactory.getObject();
            if (bloc.getErreur() != null){
                blocInformationViewCard.init(bloc.getId(), image, bloc.getTitre(), null, null, bloc.getErreur());
                imageContainer.add(blocInformationViewCard);
            }
            else {
                blocInformationViewCard.init(bloc.getId(), image, bloc.getTitre(), bloc.getDescription(), bloc.getLien(), null);
                imageContainer.add(blocInformationViewCard);
            }
        }
    }

    // Construction de la page avec des composants Vaadin.
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