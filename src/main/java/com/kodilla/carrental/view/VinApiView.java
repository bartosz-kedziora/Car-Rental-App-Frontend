package com.kodilla.carrental.view;

import com.kodilla.carrental.client.VinApiClient;
import com.kodilla.carrental.dto.VinApiDto;
import com.kodilla.carrental.dto.VinBodyDto;
import com.kodilla.carrental.dto.VinDto;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@UIScope
@Component
public class VinApiView extends VerticalLayout {

    private final Grid<VinBodyDto> grid = new Grid<>();
    private final VinApiClient vinApiClient;

    private Dialog dialog = new Dialog();
    private VinBodyDto vinResultDto = new VinBodyDto();

    private VinDto vinDto = new VinDto();
    private Binder<VinDto> binder = new Binder<>();
    private TextField vinField = new TextField("Enter VIN below:");

    public VinApiView(VinApiClient vinApiClient) {
        this.vinApiClient = vinApiClient;

        bindFields();

        VerticalLayout dialogLayout = new VerticalLayout();
        Button confirmDecodeButton = createConfirmDecodeButton();
        dialogLayout.add(vinField, confirmDecodeButton);

        dialog.isCloseOnOutsideClick();
        dialog.add(dialogLayout);

        setColumns();

        Button decodeButton = createDecodeButton();
        add(decodeButton, grid, dialog);

    }

    private Button createConfirmDecodeButton() {
        return new Button("Decode", event -> {
            binder.writeBeanIfValid(vinDto);
            VinApiDto vinApiDto = vinApiClient.decodeVinNumber(vinDto);
            List<VinBodyDto> resultsDtoList = vinApiDto.getVinBodyDtoList();
            grid.setItems(resultsDtoList);
            dialog.close();
            clearFields();
        });
    }

    private Button createDecodeButton() {
        return new Button("Decode VIN number", event -> dialog.open());
    }

    private void setColumns() {
        grid.addColumn(VinBodyDto::getManufacturer).setHeader("Brand");
        grid.addColumn(VinBodyDto::getModel).setHeader("Model");
        grid.addColumn(VinBodyDto::getProductYear).setHeader("Year");
    }

    private void bindFields() {
        binder.forField(vinField)
                .bind(VinDto::getVinNumber, VinDto::setVinNumber);
    }

    private void clearFields() {
        vinField.clear();
    }

    public void clearGrid() {
        List<VinBodyDto> emptyList = new ArrayList<>();
        grid.setItems(emptyList);
    }
}