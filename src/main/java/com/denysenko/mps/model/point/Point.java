package com.denysenko.mps.model.point;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.awt.*;

@RequiredArgsConstructor
@Data
@EqualsAndHashCode(of = {"latitude", "longitude", "altitude", "flightSpeed", "flightSpeed"})
public class Point {

    private final double latitude;
    private final double longitude;
    private final float altitude;
    private final float flightSpeed;

    @Value("(java.lang.Integer)${frame.width}")
    private int width;

    @Value("(java.lang.Integer)${frame.height}")
    private int height;

    private int scaleFactor;
    private int x;
    private int y;

    public void draw(Graphics g) {
        int circleWidthHeight = 1;

        Graphics2D g2d = (Graphics2D) g;

        int x = getRoundCoordinate(latitude);
        int y = getRoundCoordinate(longitude);
        this.x = x;
        this.y = y;

        g2d.fillOval(x, y, circleWidthHeight, circleWidthHeight);
    }

    private int getRoundCoordinate(double number) {
        int decimal = Integer.parseInt(String.valueOf(number).split("\\.")[1]);
        calcScaleFactor(decimal);
        return relateToFrameDimensions(decimal);
    }

    private int relateToFrameDimensions(int number) {
        int relativeSize = number;
        while(relativeSize >= width) {
            relativeSize = relativeSize / 2;
            scaleFactor++;
        }
        return relativeSize;
    }

    private void calcScaleFactor(int number) {
        if (scaleFactor == 0) {
            relateToFrameDimensions(number);
        }
    }
}
