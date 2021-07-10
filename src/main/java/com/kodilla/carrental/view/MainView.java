package com.kodilla.carrental.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route
public class MainView extends VerticalLayout {

    public MainView() {
        Button button1 = new Button("Click1");
        Button button2 = new Button("Click2");
        Button button3 = new Button("Click3");

        VerticalLayout layout = new VerticalLayout();
        layout.add(button1, button2, button3);

        add(layout);
        setSizeFull();
    }
}
