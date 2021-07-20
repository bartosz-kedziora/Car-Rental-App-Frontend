package com.kodilla.carrental.view;

import com.kodilla.carrental.dto.UserDto;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@UIScope
@Component
@Route(value = "mainView")
public class MainView extends VerticalLayout {

    private final CarView carsView;
    private final UsersView usersView;
    private final RentalsView rentalsView;
    private final HereApiView hereApiView;
    private final VinApiView vinApiView;

    private PagedTabs tabs = new PagedTabs();
    private Tab carsTab = new Tab("Cars");
    private Tab usersTab = new Tab("Users");
    private Tab rentalsTab = new Tab("Rentals");
    private Tab userAccountTab = new Tab("User account");
    private Tab hereApiTab = new Tab("Agency searcher");
    private Tab vinDecoderTab = new Tab("Vin decoder");
    private Tab logoutTab = new Tab();

    @Autowired
    public MainView(CarView carsView, UsersView usersView, RentalsView rentalsView,
                    HereApiView hereApiView, VinApiView vinApiView) {
        this.carsView = carsView;
        this.usersView = usersView;
        this.rentalsView = rentalsView;
        this.hereApiView = hereApiView;
        this.vinApiView = vinApiView;

        tabs.add(carsView, carsTab);
        tabs.add(usersView, usersTab);
        tabs.add(rentalsView, rentalsTab);
        tabs.add(hereApiView, hereApiTab);
        tabs.add(vinApiView, vinDecoderTab);

        add(tabs);
    }

    public void adminViewSetup() {
        userAccountTab.setVisible(false);
        usersTab.setVisible(true);
        carsView.refreshCarsForAdmin();
        rentalsView.refreshRentalsForAdmin();
        usersView.refreshUsers();
        hereApiView.clearGrid();
        vinApiView.clearGrid();
    }

    public void userViewSetup(UserDto userDto) {
        userAccountTab.setVisible(true);
        usersTab.setVisible(false);
        carsView.refreshCarsForUser(userDto);
        rentalsView.refreshRentalsForUser(userDto);
        hereApiView.clearGrid();
        vinApiView.clearGrid();
    }

    public void setBackStartingTab() {
        tabs.select(carsTab);
    }
}
