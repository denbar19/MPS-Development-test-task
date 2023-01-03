package com.denisenko.mps.calculator;

import com.denisenko.mps.characteristics.Characteristics;
import com.denisenko.mps.flight.Flight;
import com.denisenko.mps.point.Point;
import com.denisenko.mps.point.TemporaryPoint;
import com.denisenko.mps.math.Computer;
import com.denisenko.mps.point.WayPoint;
import com.denisenko.mps.vehicle.AirVehicle;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@Getter
@Setter
public class PlaneCalculator implements Calculator {

    private AirVehicle plane;

    @Value("${threshold}")
    private float threshold;

    @Value("${way.point.pass.precision}")
    private float precision;

    private float currentSpeed;
    private float currentAltitude;

    private TemporaryPoint currentPosition;

    /**
     * Updates all related plane and flight parameters
     */
    @Override
    public void calculateAdjustment() {
        Computer computer = new Computer();
        computer.setBaseCalculationPoint(plane.getStartPoint());

        computer.calculate();
        currentPosition = computer.getCurrentPosition();
        plane.setPosition(currentPosition);

        Flight currentFlight = plane.getFlights().get(plane.getFlightNumber());
        WayPoint currentWayPoint = currentFlight.getCurrentWayPoint();

        List<Point> passedPointForWayPoint = currentFlight.getPassedPoints().get(currentWayPoint);
        passedPointForWayPoint.add(currentPosition);

        double distanceToWayPoint = computer.getDesiredSegmentDistance();
        if (distanceToWayPoint <= precision) {
            currentFlight.updateToNextWayPoint();
        }



    }

    //private final Computer computer = new Computer();
    public List<TemporaryPoint> calculateRoute(Characteristics characteristics, Set<Point> wayPoints) {
        Computer computer = new Computer();
        return null;
    }

    public TemporaryPoint getAdjustedCoordinate() {
        return null;
    }

    private void calcCurrentSpeed() {
        // расчитать длину и разделить на threshold
        float speedInTimeThreshold = plane.getCharacteristics().getAcceleration() * threshold;
        this.currentSpeed = speedInTimeThreshold;
        float distanceInTimeThreshold = speedInTimeThreshold * threshold;
    }

}
