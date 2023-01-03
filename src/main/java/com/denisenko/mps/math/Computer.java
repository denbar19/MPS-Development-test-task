package com.denisenko.mps.math;


import com.denisenko.mps.generator.TelemetryAutopilotImpl;
import com.denisenko.mps.point.TemporaryPoint;
import lombok.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.lang.Math.*;

/**
 *
 */
@NoArgsConstructor
@Getter
@Setter
public class Computer {

    private static final Logger LOG = LoggerFactory.getLogger(Computer.class);

    public static final double MEAN_EARTH_RADIUS = 6371.009; // km

    private TelemetryAutopilotImpl generator;

    private TemporaryPoint flightStartPoint;

    private double firstLatitude;
    private double firstLongitude;

    private double secondLatitude;
    private double secondLongitude;

    private TemporaryPoint currentPosition;
    private double currentLatitude;
    private double currentLongitude;

    private TemporaryPoint desiredPosition;
    private double desiredLatitude;
    private double desiredLongitude;

    private TemporaryPoint previousBasePoint;
    private TemporaryPoint baseCalculationPoint;

    private double realSegmentDistance;
    private double desiredSegmentDistance;
    private double oppositeSegmentDistance;

    private double currentAngle;
    private double desiredAngle;
    private double oppositeAngle;

    private double linearAngle;

    public void calculate() {
        generator = new TelemetryAutopilotImpl(baseCalculationPoint);
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
