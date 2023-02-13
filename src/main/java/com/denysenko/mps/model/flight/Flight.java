package com.denysenko.mps.model.flight;

import com.denysenko.mps.model.point.Point;
import com.denysenko.mps.model.point.WayPoint;
import lombok.*;

import java.util.List;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
public class Flight extends Route {

    @Builder
    public Flight(long number, List<WayPoint> wayPoints, Map<WayPoint, List<Point>> passedPoints) {
        super(number, wayPoints, passedPoints);
    }
}

