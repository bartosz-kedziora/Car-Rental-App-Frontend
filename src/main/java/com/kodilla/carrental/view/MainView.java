package com.kodilla.carrental.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route
public class MainView extends VerticalLayout {

    private final CarView carView;
    private final LoginView loginView;
    private final LogOutView logoutView;

    @Autowired
    public MainView(CarView carView, LoginView loginView, LogOutView logoutView) {
        this.carView = carView;
        this.loginView = loginView;
        this.logoutView = logoutView;

        Tab carsTab = new Tab("Cars");
        Button button1 = new Button("Click1");
        PagedTabs tabs = new PagedTabs();
        Tab loginTab = new Tab("Login");
        Tab logoutTab = new Tab("Logout");

        tabs.add(carView, carsTab);
        tabs.add(button1, "Tab 1");
        tabs.add(loginView, loginTab);
        tabs.add(logoutView, logoutTab);

        carsTab.setEnabled(true);
        logoutTab.setEnabled(false);
        add(tabs);
        setSizeFull();
    }
}
