package com.denysenko.mps.model.flight;

import com.denysenko.mps.model.point.Point;
import com.denysenko.mps.model.point.WayPoint;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Route
        //extends JPanel
{

    private final long number;
    private final List<WayPoint> wayPoints;
    // temporary points before each next way point
    private final Map<WayPoint, List<Point>> passedPoints;
    private WayPoint currentWayPoint;

    //List<RectangularColumns> shapes = new ArrayList<>();

    public WayPoint updateToNextWayPoint() {
        currentWayPoint = wayPoints.get(currentWayPoint.getId() + 1);
        return currentWayPoint;
    }

//    protected void draw() {
//
//    }
}
