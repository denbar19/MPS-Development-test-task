package com.denisenko.mps.flight;

import com.denisenko.mps.point.Point;
import com.denisenko.mps.point.WayPoint;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Route extends JPanel {

    private final long number;
    private final List<WayPoint> wayPoints;
    // temporary points before each next way point
    private final Map<Point, List<Point>> passedPoints;
    private WayPoint currentWayPoint;

    //List<RectangularColumns> shapes = new ArrayList<>();

    public WayPoint updateToNextWayPoint() {
        currentWayPoint = wayPoints.get(currentWayPoint.getId() + 1);
        return currentWayPoint;
    }


}
