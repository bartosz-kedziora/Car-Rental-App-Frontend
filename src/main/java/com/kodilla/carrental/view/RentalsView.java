package com.kodilla.carrental.view;

import com.kodilla.carrental.client.RentalClient;
import com.kodilla.carrental.dto.RentalDto;
import com.kodilla.carrental.dto.UserDto;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@UIScope
@Component
public class RentalsView extends VerticalLayout {

    private final Grid<RentalDto> rentalGrid = new Grid<>();
    private final RentalClient rentalClient;

    private Dialog extendRentalDialog = new Dialog();
    //private Binder<RentalExtensionDto> binderForExtendRental = new Binder<>();

    private Dialog modifyRentalDialog = new Dialog();
    private Binder<RentalDto> binderForModifyRental = new Binder<>();
    private DatePicker modifyStartDate = new DatePicker("Rented from");
    private DatePicker modifyEndDate = new DatePicker("Rented to");

    private UserDto loggedUserDto;
    private Long rentalId;
    private Long carId;

    @Autowired
    public RentalsView(RentalClient rentalClient) {
        this.rentalClient = rentalClient;


        setColumns();

        rentalGrid.addComponentColumn(this::createCloseRentalButton);

        add(rentalGrid);
    }

    public void refreshRentalsForAdmin() {
        loggedUserDto = null;
        List<RentalDto> rentals = rentalClient.getAllRentals();
        rentalGrid.setItems(rentals);
    }

    public void refreshRentalsForUser(UserDto userDto) {
        loggedUserDto = userDto;
        List<RentalDto> rentals = rentalClient.getRentalsByUserId(userDto.getId());
        rentalGrid.setItems(rentals);
    }

    private Button createCloseRentalButton(RentalDto rentalDto) {
        Dialog confirmCloseRentalDialog = createCloseRentalDialog(rentalDto);

        Button closeRentalButton = new Button("Close", event -> confirmCloseRentalDialog.open());

        if (loggedUserDto == null) {
            closeRentalButton.setEnabled(false);
        } else {
            closeRentalButton.setEnabled(true);
        }
        return closeRentalButton;
    }

    private Dialog createCloseRentalDialog(RentalDto rentalComplexDto){
        Dialog confirmCloseRentalDialog = new Dialog();
        VerticalLayout confirmationLayout = new VerticalLayout();
        Button confirmCloseRentalButton = createConfirmCloseRentalButton(confirmCloseRentalDialog, rentalComplexDto);
        Button cancelCloseRentalButton = createCancelConfirmationButton(confirmCloseRentalDialog);
        Label confirmationLabel = new Label("Are You sure about closing rental?");
        confirmationLayout.add(confirmationLabel, confirmCloseRentalButton, cancelCloseRentalButton);
        confirmCloseRentalDialog.add(confirmationLayout);
        return confirmCloseRentalDialog;
    }

    private Button createConfirmCloseRentalButton(Dialog dialog, RentalDto rentalDto) {
        return new Button("Confirm", event -> {
            rentalId = rentalDto.getId();
            closeRental(rentalId);
            dialog.close();
        });
    }

    private Button createCancelConfirmationButton(Dialog dialog) {
        return new Button("Cancel", event -> dialog.close());
    }


    private void closeRental(Long rentalId) {
        rentalClient.deleteRental(rentalId);
        refreshRentalsForUser(loggedUserDto);
    }

    private void setColumns() {
        rentalGrid.addColumn(RentalDto::getId).setHeader("Id");
        rentalGrid.addColumn(RentalDto::getRentedFrom).setHeader("Start");
        rentalGrid.addColumn(RentalDto::getRentedTo).setHeader("End");
        rentalGrid.addColumn(RentalDto::getRentalCost).setHeader("Cost");
        rentalGrid.addColumn(RentalDto::getCarBrand).setHeader("Brand");
        rentalGrid.addColumn(RentalDto::getCarModel).setHeader("Model");
        rentalGrid.addColumn(RentalDto::getUserName).setHeader("Name");
        rentalGrid.addColumn(RentalDto::getUserLastName).setHeader("Surname");
    }
}