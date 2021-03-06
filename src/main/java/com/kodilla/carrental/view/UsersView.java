package com.kodilla.carrental.view;

import com.kodilla.carrental.client.UserClient;
import com.kodilla.carrental.dto.UserDto;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@UIScope
@Component
public class UsersView extends VerticalLayout {

    private final Grid<UserDto> userGrid = new Grid<>();
    private final UserClient userClient;
    private final Dialog dialog = new Dialog();
    private final Binder<UserDto> binder = new Binder<>();
    private final TextField name = new TextField("Name");
    private final TextField lastName = new TextField("Last name");
    private final TextField email = new TextField("Email address");
    private final TextField password = new TextField("Password");
    private final IntegerField phoneNumber = new IntegerField("Phone number");
    private final UserDto userDto = new UserDto();

    @Autowired
    public UsersView(UserClient userClient) {
        this.userClient = userClient;

        bindFields();

        VerticalLayout dialogLayout = new VerticalLayout();
        Button saveUserButton = createSaveUserButton();
        dialogLayout.add(name, lastName, email, password, phoneNumber, saveUserButton);

        dialog.isCloseOnOutsideClick();
        dialog.add(dialogLayout);

        setColumns();

        Button addUserButton = createAddUserButton();
        add(addUserButton, userGrid, dialog);
    }

    public void refreshUsers() {
        List<UserDto> users = userClient.getAllUsers();
        userGrid.setItems(users);
    }

    private void saveUser(UserDto userDto) {
            userClient.registerUser(userDto);
            refreshUsers();
            dialog.close();
            clearFields();
    }

    private Button createAddUserButton() {
        return new Button("Add new user", event -> dialog.open());
    }

    private Button createSaveUserButton() {
        return new Button("Save user", event -> {
            if (areFieldsFilled()) {
                binder.writeBeanIfValid(userDto);
                saveUser(userDto);
            } else {
                Dialog alertDialog = createEmptyFieldsDialog();
                alertDialog.open();
            }
        });
    }

    private boolean areFieldsFilled() {
        return (!name.getValue().equals("") &&
                !lastName.getValue().equals("") &&
                !email.getValue().equals("") &&
                !password.getValue().equals("") &&
                phoneNumber.getValue() != null);
    }

    private Dialog createEmptyFieldsDialog() {
        Dialog alertDialog = new Dialog();
        VerticalLayout alertLayout = new VerticalLayout();
        Button cancelAlertButton = new Button("Cancel", event -> alertDialog.close());
        Label alertLabel = new Label("All fields must be filled!");
        alertLayout.add(alertLabel, cancelAlertButton);
        alertDialog.add(alertLayout);
        return alertDialog;
    }

    private void setColumns() {
        userGrid.addColumn(UserDto::getId).setHeader("Id");
        userGrid.addColumn(UserDto::getName).setHeader("Name");
        userGrid.addColumn(UserDto::getLastName).setHeader("Surname");
        userGrid.addColumn(UserDto::getEmail).setHeader("Email");
        userGrid.addColumn(UserDto::getPassword).setHeader("Password");
        userGrid.addColumn(UserDto::getPhoneNumber).setHeader("Phone");
        userGrid.addColumn(UserDto::getAccountCreated).setHeader("Created");
    }

    private void clearFields() {
        name.clear();
        lastName.clear();
        email.clear();
        password.clear();
        phoneNumber.clear();
    }

    private void bindFields() {
        binder.forField(name)
                .bind(UserDto::getName, UserDto::setName);
        binder.forField(lastName)
                .bind(UserDto::getLastName, UserDto::setLastName);
        binder.forField(email)
                .bind(UserDto::getEmail, UserDto::setEmail);
        binder.forField(password)
                .bind(UserDto::getPassword, UserDto::setPassword);
        binder.forField(phoneNumber)
                .bind(UserDto::getPhoneNumber, UserDto::setPhoneNumber);
    }
}