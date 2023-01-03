package com.denisenko.mps.generator;

import com.denisenko.mps.point.TemporaryPoint;
import org.springframework.beans.factory.annotation.Value;

import java.util.Random;

/**
 * Generates coordinates deviation - kinda real flight
 */
public class TelemetryAutopilotImpl implements TelemetryGenerator {

    @Value("(java.lang.Integer)${deviationPercent}")
    private int deviationPercent;

    private static final String SPLITTER = "\\.";

    private final TemporaryPoint fiducialPoint;
    private TemporaryPoint point;

    public TelemetryAutopilotImpl(TemporaryPoint fiducialPoint) {
        this.fiducialPoint = fiducialPoint;
    }

    public TemporaryPoint generateCurrentPosition() {
        String latitudeWhole = String.valueOf(fiducialPoint.getLatitude()).split(SPLITTER)[0];
        String longitudeWhole = String.valueOf(fiducialPoint.getLongitude()).split(SPLITTER)[0];

        double fractionalLatitude = Double.parseDouble(String.valueOf(fiducialPoint.getLatitude())
                                                             .split(SPLITTER)[1]);
        double fractionalLongitude = Double.parseDouble(String.valueOf(fiducialPoint.getLongitude())
                                                              .split(SPLITTER)[1]);

        String rFractionalLatitude = String.valueOf(getRandomDeviation(fractionalLatitude)).split(SPLITTER)[0];
        String rFractionalLongitude = String.valueOf(getRandomDeviation(fractionalLongitude)).split(SPLITTER)[0];

        this.point = TemporaryPoint.builder()
                                   .latitude(Double.parseDouble(latitudeWhole + "." + rFractionalLatitude))
                                   .longitude(Double.parseDouble(longitudeWhole + "." + rFractionalLongitude))
                                   .build();
        return point;
    }

    private double getRandomDeviation(double base) {
        Random r = new Random();
        return r.doubles(getOrigin(base),
                         getBound(base))
                .findAny().getAsDouble();
    }

    private double getOrigin(double fractional) {
        return fractional * (100 - deviationPercent);
    }

    private double getBound(double fractional) {
        return fractional * (100 + deviationPercent);
    }
}
