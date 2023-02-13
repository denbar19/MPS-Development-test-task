package com.denysenko.mps.generator;

import com.denysenko.mps.model.point.Point;

public interface TelemetryGenerator {

    Point generateCurrentPosition();

    void setBasePoint(Point basePoint);

}
