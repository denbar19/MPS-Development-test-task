package com.denysenko.mps.calculator;

import com.denysenko.mps.characteristics.Characteristics;
import com.denysenko.mps.model.point.Point;
import com.denysenko.mps.model.point.TemporaryPoint;
import com.denysenko.mps.model.vehicle.AirVehicle;

import java.util.List;
import java.util.Set;

public interface Calculator {

    void calculateAdjustment();

    List<TemporaryPoint> calculateRoute(Characteristics characteristics, Set<Point> wayPoints);

    void setPlane(AirVehicle vehicle);
}
