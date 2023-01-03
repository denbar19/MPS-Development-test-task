package com.denisenko.mps.calculator;

import com.denisenko.mps.characteristics.Characteristics;
import com.denisenko.mps.point.Point;
import com.denisenko.mps.point.TemporaryPoint;

import java.util.List;
import java.util.Set;

public interface Calculator {

    void calculateAdjustment();

    List<TemporaryPoint> calculateRoute(Characteristics characteristics, Set<Point> wayPoints);

}
