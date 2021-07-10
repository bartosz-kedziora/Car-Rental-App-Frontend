package com.kodilla.carrental.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route
public class MainView extends VerticalLayout {

    private final CarView carView;

    @Autowired
    public MainView(CarView carView) {
        this.carView = carView;
        Tab carsTab = new Tab("Cars");
        Button button1 = new Button("Click1");
        PagedTabs tabs = new PagedTabs();

        tabs.add(carView, carsTab);
        tabs.add(button1, "Tab 1");
        carsTab.setEnabled(true);
        add(tabs);
        setSizeFull();
    }
}
