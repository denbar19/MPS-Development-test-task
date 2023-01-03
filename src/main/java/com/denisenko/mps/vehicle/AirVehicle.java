package com.denisenko.mps.vehicle;

import com.denisenko.mps.characteristics.Characteristics;
import com.denisenko.mps.flight.Flight;
import com.denisenko.mps.point.TemporaryPoint;
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
    private TemporaryPoint startPoint;
    private TemporaryPoint targetPosition;
    protected TemporaryPoint position;

}


