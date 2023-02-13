package com.denysenko.mps.model.vehicle;

import com.denysenko.mps.characteristics.Characteristics;
import com.denysenko.mps.model.flight.Flight;
import com.denysenko.mps.model.point.Point;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.swing.*;
import java.util.List;

@RequiredArgsConstructor
@Data
public class AirVehicle extends JPanel {

    private final long id;
    private final Characteristics characteristics;
    private final List<Flight> flights;
    private int flightNumber;
    private Point startPoint;
    private Point targetPosition;
    protected Point position;

    protected Flight getCurrentFlight() {
        return flights.get(flightNumber);
    }

}


