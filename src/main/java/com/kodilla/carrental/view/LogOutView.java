package com.kodilla.carrental.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.springframework.stereotype.Component;

@Component
public class LogOutView extends VerticalLayout {

    public LogOutView() {
        Button button = new Button("Log out", new Icon(VaadinIcon.EXIT));
        button.setIconAfterText(true);

        add(button);
        setHorizontalComponentAlignment(Alignment.CENTER, button);
    }
}
