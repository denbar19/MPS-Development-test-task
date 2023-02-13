package com.denysenko.mps.math;

import com.denysenko.mps.generator.TelemetryGenerator;
import com.denysenko.mps.model.point.Point;
import lombok.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static java.lang.Math.*;

/**
 *
 */
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Component
@Scope("prototype")
@ComponentScan("com.denysenko.mps.generator")
@Getter
@Setter
public class Computer {

    private static final Logger LOG = LoggerFactory.getLogger(Computer.class);

    public static final double MEAN_EARTH_RADIUS = 6371.009; // km

    private final TelemetryGenerator generator;

    private Point flightStartPoint;

    private double firstLatitude;
    private double firstLongitude;

    private double secondLatitude;
    private double secondLongitude;

    private Point currentPosition;
    private double currentLatitude;
    private double currentLongitude;

    private Point desiredPosition;
    private double desiredLatitude;
    private double desiredLongitude;

    private Point previousBasePoint;
    private Point baseCalculationPoint;

    private double realSegmentDistance;
    private double desiredSegmentDistance;
    private double oppositeSegmentDistance;

    private double currentAngle;
    private double desiredAngle;
    private double oppositeAngle;

    private double linearAngle;

    public void setBaseCalculationPoint(Point baseCalculationPoint) {
        this.baseCalculationPoint = baseCalculationPoint;
        generator.setBasePoint(baseCalculationPoint);
    }

    public void calculate() {
        currentPosition = generator.generateCurrentPosition();
        currentLatitude = currentPosition.getLatitude();
        currentLongitude = currentPosition.getLongitude();

        calcCurrentSegmentDistance();
        calcDesiredSegmentDistance();
        calcOppositeSegmentDistance();
        computeLinearAngle();


    }

    public double computeCurrentAngle() {
        this.currentAngle =
                computeSphericalAngle(baseCalculationPoint.getLatitude(), baseCalculationPoint.getLongitude(),
                                      currentLatitude, currentLongitude);
        return currentAngle;
    }

    public double computeDesiredAngle() {
        this.currentAngle =
                computeSphericalAngle(baseCalculationPoint.getLatitude(), baseCalculationPoint.getLongitude(),
                                      desiredLatitude, desiredLongitude);
        return desiredAngle;
    }

    public double computeOppositeAngle() {
        this.currentAngle =
                computeSphericalAngle(currentLatitude, currentLongitude,
                                      desiredLatitude, desiredLongitude);
        return oppositeAngle;
    }

    public double calcCurrentSegmentDistance() {
        this.realSegmentDistance = MEAN_EARTH_RADIUS * computeCurrentAngle();
        return realSegmentDistance;
    }

    public double calcDesiredSegmentDistance() {
        this.desiredSegmentDistance = MEAN_EARTH_RADIUS * computeDesiredAngle();
        return desiredSegmentDistance;
    }

    public double calcOppositeSegmentDistance() {
        this.oppositeSegmentDistance = MEAN_EARTH_RADIUS * computeOppositeAngle();
        return oppositeSegmentDistance;
    }

    /**
     * Spherical angle by the law of spherical cosines
     * @param firstLatitude
     * @param secondLatitude
     * @param firstLongitude
     * @param secondLongitude
     * @return
     */
    public double computeSphericalAngle(double firstLatitude, double secondLatitude,
                                        double firstLongitude, double secondLongitude) {
        double angle = 2 * asin(pow(sin(abs(firstLatitude - secondLatitude) / 2
                                                + cos(firstLatitude) * cos(secondLatitude) * pow(sin(abs(firstLongitude - secondLongitude) / 2), 2)), 2));
        return angle;
    }

    public double computeLinearAngle() {
        this.linearAngle = (pow(realSegmentDistance, 2) + pow(desiredSegmentDistance, 2)
                - pow(oppositeSegmentDistance, 2)) / (2 * realSegmentDistance * desiredSegmentDistance);
        return linearAngle;
    }
}
