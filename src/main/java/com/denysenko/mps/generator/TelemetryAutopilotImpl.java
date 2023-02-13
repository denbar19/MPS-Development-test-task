package com.denysenko.mps.generator;

import com.denysenko.mps.model.point.Point;
import com.denysenko.mps.model.point.TemporaryPoint;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * Generates coordinates deviation - kinda real flight
 */
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Component
@Getter
@Setter
public class TelemetryAutopilotImpl implements TelemetryGenerator {

    private static final String SPLITTER = "\\.";

    @Value("${deviation.percent}")
    private int deviationPercent;

    private Point basePoint;
    private Point point;
    
    public void setBasePoint(Point basePoint) {
        this.basePoint = basePoint;
    }

    public TemporaryPoint generateCurrentPosition() {
        String latitudeWhole = String.valueOf(basePoint.getLatitude()).split(SPLITTER)[0];
        String longitudeWhole = String.valueOf(basePoint.getLongitude()).split(SPLITTER)[0];

        double fractionalLatitude = Double.parseDouble(String.valueOf(basePoint.getLatitude())
                                                             .split(SPLITTER)[1]);
        double fractionalLongitude = Double.parseDouble(String.valueOf(basePoint.getLongitude())
                                                              .split(SPLITTER)[1]);

        String rFractionalLatitude = String.valueOf(getRandomDeviation(fractionalLatitude)).split(SPLITTER)[0];
        String rFractionalLongitude = String.valueOf(getRandomDeviation(fractionalLongitude)).split(SPLITTER)[0];

        return TemporaryPoint.builder()
                             .latitude(Double.parseDouble(latitudeWhole + "." + rFractionalLatitude))
                             .longitude(Double.parseDouble(longitudeWhole + "." + rFractionalLongitude))
                             .build();
    }

    private double getRandomDeviation(double base) {
        Random r = new Random();
        double origin = 0;
        double bound = 0;
        while (origin >= bound) {
            origin = getOrigin(base);
            bound = getBound(base);
        }
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
