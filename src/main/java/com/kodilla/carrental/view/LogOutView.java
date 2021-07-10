package com.kodilla.carrental.view;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.springframework.stereotype.Component;

@Component
public class LogOutView extends VerticalLayout {

    public LogOutView() {
        Button logoutButton = new Button("Log out");
        add(logoutButton);
    }
}
