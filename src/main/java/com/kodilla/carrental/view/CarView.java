package com.kodilla.carrental.view;

import com.kodilla.carrental.client.CarClient;
import com.kodilla.carrental.domain.CarDto;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CarView extends VerticalLayout {

    private final Grid<CarDto> carGrid = new Grid<>(CarDto.class);
    private final CarClient carClient;

    @Autowired
    public CarView(CarClient carClient) {
        this.carClient = carClient;

        Button addCarButton = new Button("Add new car");

        carGrid.setColumns(
                "id",
                "vin",
                "brand",
                "model",
                "productionYear",
                "fuelType",
                "engineCapacity",
                "bodyClass",
                "mileage",
                "costPerDay",
                "status");

        List<CarDto> carDtoList = carClient.getCars();
        carGrid.setItems(carDtoList);

        add(addCarButton, carGrid);
    }
}
