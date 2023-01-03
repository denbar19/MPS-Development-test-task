package com.denisenko.mps.flight;

import com.denisenko.mps.point.Point;
import com.denisenko.mps.point.WayPoint;
import lombok.*;

import java.util.List;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
public class Flight extends Route {

    @Builder
    public Flight(long number, List<WayPoint> wayPoints, Map<Point, List<Point>> passedPoints) {
        super(number, wayPoints, passedPoints);
    }
}

