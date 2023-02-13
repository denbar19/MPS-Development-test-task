package com.denysenko.mps.calculator;

import com.denysenko.mps.characteristics.Characteristics;
import com.denysenko.mps.model.flight.Flight;
import com.denysenko.mps.model.point.Point;
import com.denysenko.mps.model.point.TemporaryPoint;
import com.denysenko.mps.math.Computer;
import com.denysenko.mps.model.point.WayPoint;
import com.denysenko.mps.model.vehicle.AirVehicle;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Component
@Scope("prototype")
@ComponentScan("com.denysenko.mps.math")
@Getter
@Setter
public class PlaneCalculator implements Calculator {

    public static final Logger LOG = LoggerFactory.getLogger(PlaneCalculator.class);

    private final Computer computer;

    private AirVehicle plane;

    @Value("${threshold}")
    private float threshold;

    @Value("${way.point.pass.precision}")
    private float precision;

    private float currentSpeed;
    private float currentAltitude;

    private Point currentPosition;

    public void setPlane(AirVehicle vehicle) {
        this.plane = vehicle;
    }

    /**
     * Updates all related plane and flight parameters
     */
    @Override
    public void calculateAdjustment() {

        computer.setBaseCalculationPoint(plane.getStartPoint());

        computer.calculate();
        currentPosition = computer.getCurrentPosition();
        plane.setPosition(currentPosition);

        Flight currentFlight = plane.getFlights().get(plane.getFlightNumber());
        WayPoint currentWayPoint = currentFlight.getCurrentWayPoint();

//        LOG.info("currentWayPoint: {}", currentWayPoint.hashCode());
        List<Point> passedPointsForWayPoint = currentFlight.getPassedPoints().get(currentWayPoint);

        /*currentFlight.getPassedPoints().forEach((k,v) -> LOG.info("key hashCode: {},\nvalue hashCode: {}",
                                                                  k.hashCode(), v));*/
        passedPointsForWayPoint.add(currentPosition);

        double distanceToWayPoint = computer.getDesiredSegmentDistance();
        if (distanceToWayPoint <= precision) {
            //currentFlight.updateToNextWayPoint();
        }



    }

    public List<TemporaryPoint> calculateRoute(Characteristics characteristics, Set<Point> wayPoints) {

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
